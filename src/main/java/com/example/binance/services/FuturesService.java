package com.example.binance.services;

import com.example.binance.feign.BinanceFuturesClient;
import com.example.binance.models.request.BinanceRequest;
import com.example.binance.models.response.FuturesBalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuturesService {
    private final BinanceFuturesClient binanceFuturesClient;

    public FuturesBalanceResponse getBalance(String asset) {
        if (asset == null) {
            return null;
        }
        return getBalances()
                .stream()
                .filter(b -> b.getAsset().equals(asset))
                .findAny()
                .orElse(null);
    }

    public List<FuturesBalanceResponse> getBalances() {
        return binanceFuturesClient.getBalances(new BinanceRequest());
    }
}