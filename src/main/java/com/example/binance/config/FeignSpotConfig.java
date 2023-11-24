package com.example.binance.config;

import com.example.binance.services.SignatureService;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignSpotConfig {
    @Bean
    public RequestInterceptor requestInterceptorSpot(SignatureService signatureService) {
        return new FeignInterceptor(ApiConstants.SPOT_API_KEY, ApiConstants.SPOT_SECRET_KEY, signatureService);
    }
}