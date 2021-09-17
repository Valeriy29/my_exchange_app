package com.example.exchangeapp.service;

import java.util.function.Consumer;

public interface ExchangeService {

   void getExchangeCandlestick(String exchangeUrl, Consumer<String> saveInfo);
}
