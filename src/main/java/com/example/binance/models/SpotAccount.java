package com.example.binance.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpotAccount {
    private String accountType;
    private boolean brokered;
    private BigDecimal buyerCommission;
    private boolean canDeposit;
    private boolean canTrade;
    private boolean canWithdraw;
    private SpotCommissionRate commissionRates;
    private BigDecimal makerCommission;
    private boolean preventSor;
    private boolean requireSelfTradePrevention;
    private BigDecimal sellerCommission;
    private BigDecimal takerCommission;
    private long uid;
    private long updateTime;
    private List<String> permissions;
    private List<SpotBalance> balances;
}


