package com.utils;

import main.com.utils.ConversionResult;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConversionResultTest {
    @Test
    public void testResponseJsonMapsToConversionResult(){
        String response = "{\"USD_LKR\":200.446818}";
        double conversionRate = 200.446818;
        ConversionResult result = ConversionResult.mapResponseToConverionResult(response);
        assertEquals(conversionRate,result.conversionRate,0.00001);
    }
}
