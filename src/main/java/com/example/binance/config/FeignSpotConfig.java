package com.example.binance.config;

import com.example.binance.services.SignatureService;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignSpotConfig {
    @Bean
    public RequestInterceptor requestInterceptorSpot(SignatureService signatureService, AppProperties props) {
        return new FeignInterceptor(props.getSpotApiKey(), props.getSpotSecretKey(), signatureService);
    }
}