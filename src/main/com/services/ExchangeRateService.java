package main.com.services;

import java.io.IOException;

public interface ExchangeRateService {
    double getConversionRate(String baseCurrency, String targetCurrency) throws IOException;
}
