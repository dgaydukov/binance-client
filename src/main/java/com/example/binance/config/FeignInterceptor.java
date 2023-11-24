package com.example.binance.config;

import com.example.binance.services.SignatureService;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignInterceptor implements RequestInterceptor {
    private final String apiKey;
    private final String secretKey;
    private final SignatureService signatureService;

    public FeignInterceptor(String apiKey, String secretKey, SignatureService signatureService){
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.signatureService = signatureService;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(ApiConstants.HEADER_API_KEY, apiKey);
        final String data = requestTemplate.queryLine().substring(1);
        final String sig = signatureService.sign(data, secretKey);
        requestTemplate.query(ApiConstants.PARAM_SIGNATURE, sig);
    }
}