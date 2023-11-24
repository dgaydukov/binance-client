package com.example.binance.models.request;

import lombok.Data;

@Data
public class NewSubAccountRequest extends BinanceRequest {
    private String subAccountString;
}
