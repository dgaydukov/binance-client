package com.example.binance.models.request;

import lombok.Data;

@Data
public class BinanceRequest {
    private long timestamp = System.currentTimeMillis();
    private long recvWindow = 5000;
}
