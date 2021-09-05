package main.com.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class ConversionResult {
    public String baseCurrency;
    public String targetCurrency;
    public double conversionRate;

    private ConversionResult(String baseCurrency, String targetCurrency, double conversionRate){

    }

    public static ConversionResult mapResponseToConverionResult(String response){

        return new ConversionResult("","",0);
    }
}
