package com.example.binance.config;

import com.example.binance.services.SignatureService;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignFuturesConfig {
    @Bean
    public RequestInterceptor requestInterceptorFutures(SignatureService signatureService, AppProperties props) {
        return new FeignInterceptor(props.getFuturesApiKey(), props.getFuturesSecretKey(), signatureService);
    }
}