package main.com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.com.utils.Deserializer;

import java.lang.reflect.Type;
import java.util.Map;

public class ConversionResult {
    public String baseCurrency;
    public String targetCurrency;
    public double conversionRate;

    private ConversionResult(String baseCurrency, String targetCurrency, double conversionRate){
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionRate = conversionRate;
    }

    public double convert (double amount) {
        return amount * conversionRate;
    }

    public static ConversionResult mapResponseToConverionResult(String response,String baseCurrency, String targetCurrency){
        Type type =     new TypeToken<Map<String,Object>>(){}.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(type, new Deserializer())
                .create();
        Map<String,String> responseMap = gson.fromJson(response,type);
        double rate = Double.parseDouble(responseMap.get(baseCurrency + "_" + targetCurrency));
        return new ConversionResult(baseCurrency,targetCurrency,rate);
    }
}
