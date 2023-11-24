package com.example.binance.services;

import com.example.binance.feign.BinanceFuturesClient;
import com.example.binance.feign.BinanceSpotClient;
import com.example.binance.models.SpotAccount;
import com.example.binance.models.enums.OrderSide;
import com.example.binance.models.enums.OrderType;
import com.example.binance.models.request.BinanceRequest;
import com.example.binance.models.SpotBalance;
import com.example.binance.models.request.NewSubAccountRequest;
import com.example.binance.models.request.SpotNewOrderRequest;
import com.example.binance.models.response.SpotNewOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotService {
    private final BinanceSpotClient binanceSpotClient;
    private final BinanceFuturesClient binanceFuturesClient;


    public SpotBalance getBalance(String asset) {
        if (asset == null) {
            return null;
        }
        return getBalances()
                .stream()
                .filter(b -> b.getAsset().equals(asset))
                .findAny()
                .orElse(null);
    }

    public List<SpotBalance> getBalances() {
        return getAccount()
                .getBalances();
    }


    public SpotAccount getAccount() {
        return binanceSpotClient.getAccount(new BinanceRequest());
    }

    /**
     * limit buy 1BTC => {"code":-2010,"msg":"Account has insufficient balance for requested action."}
     * market buy with timeInForce => {"code":-1106,"msg":"Parameter 'timeInForce' sent when not required."}
     * market buy with price => {"code":-1106,"msg":"Parameter 'price' sent when not required."}
     * market buy quantity=1 => {"code":-2010,"msg":"Account has insufficient balance for requested action."} => apparently on api-server they check quantity against marketPrice and decide to reject
     * market buy quantity=0.2 => SpotNewOrderResponse(symbol=BTCUSDT, orderId=3864975, orderListId=-1, clientOrderId=Q78YhrE1KsVfYIvkk2EMBp, transactTime=1699976490936, price=0E-8, origQty=0.20000000, executedQty=0.20000000, cummulativeQuoteQty=7452.76297140, status=FILLED, timeInForce=GTC, type=MARKET, side=BUY, workingTime=1699976490936, fills=[{price=36122.66000000, qty=0.00147000, commission=0.00000000, commissionAsset=BTC, tradeId=844709}, {price=36129.78000000, qty=0.01315000, commission=0.00000000, commissionAsset=BTC, tradeId=844710}, {price=36129.79000000, qty=0.01163000, commission=0.00000000, commissionAsset=BTC, tradeId=844711}, {price=36137.79000000, qty=0.00021000, commission=0.00000000, commissionAsset=BTC, tradeId=844712}, {price=36137.79000000, qty=0.00018000, commission=0.00000000, commissionAsset=BTC, tradeId=844713}, {price=36146.18000000, qty=0.00034000, commission=0.00000000, commissionAsset=BTC, tradeId=844714}, {price=36147.77000000, qty=0.00183000, commission=0.00000000, commissionAsset=BTC, tradeId=844715}, {price=36148.43000000, qty=0.00100000, commission=0.00000000, commissionAsset=BTC, tradeId=844716}, {price=36193.10000000, qty=0.00183000, commission=0.00000000, commissionAsset=BTC, tradeId=844717}, {price=36214.37000000, qty=0.00972000, commission=0.00000000, commissionAsset=BTC, tradeId=844718}, {price=36494.80000000, qty=0.00075000, commission=0.00000000, commissionAsset=BTC, tradeId=844719}, {price=36494.80000000, qty=0.00090000, commission=0.00000000, commissionAsset=BTC, tradeId=844720}, {price=36497.13000000, qty=0.00075000, commission=0.00000000, commissionAsset=BTC, tradeId=844721}, {price=36497.13000000, qty=0.00062000, commission=0.00000000, commissionAsset=BTC, tradeId=844722}, {price=36497.13000000, qty=0.00075000, commission=0.00000000, commissionAsset=BTC, tradeId=844723}, {price=36497.14000000, qty=0.00083000, commission=0.00000000, commissionAsset=BTC, tradeId=844724}, {price=36504.88000000, qty=0.00090000, commission=0.00000000, commissionAsset=BTC, tradeId=844725}, {price=36504.88000000, qty=0.00090000, commission=0.00000000, commissionAsset=BTC, tradeId=844726}, {price=36504.88000000, qty=0.00182000, commission=0.00000000, commissionAsset=BTC, tradeId=844727}, {price=36504.88000000, qty=0.00098000, commission=0.00000000, commissionAsset=BTC, tradeId=844728}, {price=36525.48000000, qty=0.00083000, commission=0.00000000, commissionAsset=BTC, tradeId=844729}, {price=36543.34000000, qty=0.00092000, commission=0.00000000, commissionAsset=BTC, tradeId=844730}, {price=36561.19000000, qty=0.00055000, commission=0.00000000, commissionAsset=BTC, tradeId=844731}, {price=36561.19000000, qty=0.00075000, commission=0.00000000, commissionAsset=BTC, tradeId=844732}, {price=36561.51000000, qty=0.00365000, commission=0.00000000, commissionAsset=BTC, tradeId=844733}, {price=37349.55000000, qty=0.00133000, commission=0.00000000, commissionAsset=BTC, tradeId=844734}, {price=37670.97000000, qty=0.14141000, commission=0.00000000, commissionAsset=BTC, tradeId=844735}], selfTradePreventionMode=NONE)
     */
    public SpotNewOrderResponse createNewOrder(){
        SpotNewOrderRequest req = new SpotNewOrderRequest();
        req.setSymbol("BTCUSDT");
        req.setSide(OrderSide.SELL);
        req.setType(OrderType.MARKET);
        //req.setTimeInForce(OrderTimeInForce.GTC);
        //req.setPrice(new BigDecimal("30000"));
        req.setQuantity(new BigDecimal("0.01"));
        //req.setQuoteOrderQty(new BigDecimal("1000"));
        return binanceSpotClient.createNewOrder(req);
    }

    /**
     * All sub accounts API not working in testnet
     * https://dev.binance.vision/t/how-to-create-sub-account-on-testnet/3377
     */
    public String createNewSubAccount(String subAccountName){
        NewSubAccountRequest req = new NewSubAccountRequest();
        req.setSubAccountString(subAccountName);
        return binanceSpotClient.createNewSubAccount(req);
    }
}