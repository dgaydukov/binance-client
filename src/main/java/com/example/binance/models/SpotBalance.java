package com.example.binance.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotBalance {
    private String asset;
    private BigDecimal free;
    private BigDecimal locked;
}
