package com.example.binance.feign;

import com.example.binance.config.ApiConstants;
import com.example.binance.config.FeignSpotConfig;
import com.example.binance.models.SpotAccount;
import com.example.binance.models.request.BinanceRequest;
import com.example.binance.models.request.NewSubAccountRequest;
import com.example.binance.models.request.SpotNewOrderRequest;
import com.example.binance.models.response.SpotNewOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spot", contextId = "spot", url = ApiConstants.SPOT_BASE_URL, configuration = FeignSpotConfig.class)
public interface BinanceSpotClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v3/account", consumes = "application/json")
    SpotAccount getAccount(@SpringQueryMap BinanceRequest req);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v3/order", consumes = "application/json")
    SpotNewOrderResponse createNewOrder(@SpringQueryMap SpotNewOrderRequest req);

    @RequestMapping(method = RequestMethod.POST, value = "/sapi/v1/sub-account/virtualSubAccount", consumes = "application/json")
    String createNewSubAccount(@SpringQueryMap NewSubAccountRequest req);


}