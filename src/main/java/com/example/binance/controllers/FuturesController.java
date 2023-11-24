package com.example.binance.controllers;

import com.example.binance.services.FuturesService;
import com.example.binance.services.SpotService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FuturesController {
    private final FuturesService futuresService;
    private final SpotService spotService;

    @PostConstruct
    public void test(){
//        System.out.println(futuresService.getBalance("USDT"));
//        System.out.println(spotService.getBalance("USDT"));
        System.out.println(spotService.createNewSubAccount("test1"));
    }
}
