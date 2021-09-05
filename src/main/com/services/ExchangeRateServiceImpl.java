package main.com.services;

import main.com.ConversionResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeRateServiceImpl implements ExchangeRateService{
    URL serviceUrl;

    public ExchangeRateServiceImpl(String url, String apiKey) throws MalformedURLException {
        this.serviceUrl = new URL(url + "?compact=ultra&apiKey=" + apiKey);
    }

    @Override
    public double getConversionRate(String baseCurrency, String targetCurrency) throws IOException {
        URL queryURL = new URL(this.serviceUrl.getProtocol() + "://" +
                this.serviceUrl.getHost() + this.serviceUrl.getPath() + "?" +
                this.serviceUrl.getQuery() + "&q=" + baseCurrency + "_" + targetCurrency);
        HttpURLConnection conn = (HttpURLConnection) queryURL.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        ConversionResult result = ConversionResult.mapResponseToConverionResult(content.toString(),baseCurrency,targetCurrency);
        return result.conversionRate;
    }
}