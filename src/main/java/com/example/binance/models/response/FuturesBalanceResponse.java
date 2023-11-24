package com.example.binance.models.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FuturesBalanceResponse{
    private String accountAlias;
    private String asset;
    private BigDecimal balance;
    private BigDecimal crossWalletBalance;
    private BigDecimal crossUnPnl;
    private BigDecimal availableBalance;
    private BigDecimal maxWithdrawAmount;
    private boolean marginAvailable;
    private long updateTime;
}
