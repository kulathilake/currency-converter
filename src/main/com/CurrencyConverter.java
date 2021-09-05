package main.com;
import main.com.services.ExchangeRateService;
import main.com.services.ExchangeRateServiceImpl;
import org.apache.commons.cli.*;

import java.text.DecimalFormat;

public class CurrencyConverter {
    public static void main(String[] args) throws Exception{
        Options options = new Options();
        DecimalFormat df = new DecimalFormat("#.##");

        Option baseCurrencyOption = new Option("b","base-currency",true,"Currency to convert from");
        baseCurrencyOption.setRequired(true);
        options.addOption(baseCurrencyOption);

        Option targetCurrencyOption = new Option("t", "target-currency",true, "Currency to convert to");
        targetCurrencyOption.setRequired(true);
        options.addOption(targetCurrencyOption);

        Option amountOption = new Option("a", "amount", true, "Amount to convert");
        options.addOption(amountOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options,args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("currency-converter",options);
            System.exit(1);
        }

        assert cmd != null;
        String baseCurrency = cmd.getOptionValue("base-currency");
        String targetCurrency = cmd.getOptionValue("target-currency");
        double amount = Double.parseDouble(cmd.getOptionValue("amount","1.00"));
        System.out.print("Converting " + amount + " " + baseCurrency + " to " + targetCurrency);
        ExchangeRateService service = new ExchangeRateServiceImpl("https://free.currconv.com/api/v7/convert","");
        double conversionRate = service.getConversionRate(baseCurrency,targetCurrency);
        System.out.print("\n" + df.format(amount*conversionRate) + " " + targetCurrency);
        System.out.println(" (Rate " + conversionRate + ")");
        System.out.println("Currency Converter v1.0");
    }
}
