package com.example.binance.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotCommissionRate {
    private BigDecimal buyer;
    private BigDecimal maker;
    private BigDecimal seller;
    private BigDecimal taker;
}
