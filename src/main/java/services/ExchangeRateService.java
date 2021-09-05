package main.java.services;

public interface ExchangeRateService {
    float getRate(String baseCurrency, String targetCurrency);
}
