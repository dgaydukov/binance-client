package com.example.binance.models.response;

import com.example.binance.models.enums.OrderSide;
import com.example.binance.models.enums.OrderTimeInForce;
import com.example.binance.models.enums.OrderType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotNewOrderResponse {
    private String symbol;
    private long orderId;
    private String orderListId;
    private String clientOrderId;
    private long transactTime;
    private BigDecimal price;
    private BigDecimal origQty;
    private BigDecimal executedQty;
    private BigDecimal cummulativeQuoteQty;
    private String status;
    private OrderTimeInForce timeInForce;
    private OrderType type;
    private OrderSide side;
    private long workingTime;
    private Object fills;
    private String selfTradePreventionMode;
}