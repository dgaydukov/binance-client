package com.example.binance.models.request;

import com.example.binance.models.enums.OrderSide;
import com.example.binance.models.enums.OrderTimeInForce;
import com.example.binance.models.enums.OrderType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotNewOrderRequest extends BinanceRequest{
    private String symbol;
    private OrderSide side;
    private OrderType type;
    private OrderTimeInForce timeInForce;
    private BigDecimal quantity;
    private BigDecimal quoteOrderQty;
    private BigDecimal price;
    private String newClientOrderId;
    private BigDecimal stopPrice;
}