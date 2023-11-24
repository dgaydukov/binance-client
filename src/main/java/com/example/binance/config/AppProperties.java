package com.example.binance.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {
    private String spotBaseUrl;
    private String spotApiKey;
    private String spotSecretKey;
    private String futuresBaseUrl;
    private String futuresApiKey;
    private String futuresSecretKey;
}