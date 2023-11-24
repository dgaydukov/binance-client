package com.example.binance.feign;

import com.example.binance.config.ApiConstants;
import com.example.binance.config.FeignFuturesConfig;
import com.example.binance.models.request.BinanceRequest;
import com.example.binance.models.response.FuturesBalanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "futures", contextId = "futures", url = ApiConstants.FUTURES_BASE_URL, configuration = FeignFuturesConfig.class)
public interface BinanceFuturesClient {
    @RequestMapping(method = RequestMethod.GET, value = "/fapi/v2/balance", consumes = "application/json")
    List<FuturesBalanceResponse> getBalances(@SpringQueryMap BinanceRequest req);
}