package com.example.binance.config;

import com.example.binance.services.SignatureService;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignFuturesConfig {
    @Bean
    public RequestInterceptor requestInterceptorFutures(SignatureService signatureService) {
        return new FeignInterceptor(ApiConstants.FUTURES_API_KEY, ApiConstants.FUTURES_SECRET_KEY, signatureService);
    }
}