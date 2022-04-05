package logic.badweather;

import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CurrencyManager {



    public static void CurrManager() throws Exception {
        System.out.println("Country : Currency Code : Display Name");
        for (Locale locale : Locale.getAvailableLocales()) {
            Locale.setDefault(Locale.ENGLISH);
            String country = locale.getDisplayCountry();

            Currency currency = null;
            try {
                currency = Currency.getInstance(locale);
            } catch (IllegalArgumentException e) {
                continue;
            }

            String currencyCode = currency.getCurrencyCode();
            String displayName = currency.getDisplayName();

            System.out.println(country + " : " + currencyCode + " : " + displayName);
        }
    }
}
