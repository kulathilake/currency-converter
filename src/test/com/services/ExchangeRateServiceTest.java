package com.services;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import main.com.services.ExchangeRateServiceImpl;
import main.com.services.ExchangeRateService;

public class ExchangeRateServiceTest {
    @Test
    public void testGetRatesReturnsConvertedValue() throws IOException {
        double conversionRate = 200.45;
        ExchangeRateService service = new ExchangeRateServiceImpl("https://free.currconv.com/api/v7/convert","f3dcee2b943dac448e17");
        double fetchedConversionRate = service.getConversionRate("USD","LKR");
        assertEquals(conversionRate,fetchedConversionRate,0.01);
    }

}
