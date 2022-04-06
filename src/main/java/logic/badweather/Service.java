package logic.badweather;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import java.util.stream.Collectors;

public class Service {
    private final String keyOpenWeather = "&appid=5928457613c5d7cbfc8b2c0564c44268";
    private final String urlNbp = "https://api.nbp.pl/api/exchangerates/rates/a/";
    private final String urlOpenWeather = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final JSONParser jsonParser = new JSONParser();
    public String countryName;


    public Service(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;

    }


    public String getWeather(String cityName) throws IOException {

        URL url = new URL(urlOpenWeather + cityName + keyOpenWeather);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

        return bufferedReader.readLine();
    }

    public String getTemp(String cityName) throws IOException, ParseException {

        String weatherString = getWeather(cityName);

        JSONObject jsonWeather = getJsonFromString(weatherString);
        JSONObject jsonMain = (JSONObject) jsonWeather.get("main");

        double tempKelvin = Double.parseDouble(jsonMain.get("temp").toString());

        double tempCelsius = tempKelvin - 273;


        return new DecimalFormat("0.00").format(tempCelsius);
    }

    public double getRateFor(String currencyCode) throws IOException, ParseException {  //zwraca kurs waluty danego kraju wobec waluty podanej jako argument,

        return getExchangeRate(currencyCode);
    }


    public double getNBPRate() throws Exception {  //zwraca kurs złotego wobec waluty danego kraju   (woec kraju który przegładam)
        //https://stackoverflow.com/questions/14155049/iso2-country-code-from-country-name

        if (!countryName.equals("Poland")) {
            countryName = correctName(countryName);

            Locale.setDefault(Locale.ENGLISH);
            String currencyCode = Currency.getInstance(new Locale("", Arrays
                            .stream(Locale.getISOCountries())
                            .filter(e -> (new Locale("", e).getDisplayCountry().equals(countryName)))
                            .collect(Collectors.toList()).get(0)))
                    .getCurrencyCode();
            return getExchangeRate(currencyCode);
        } else
            return 1.0;

    } //sRi lSaNkA

    //Sri Lanka
    public String correctName(String nameToCorrect) {
        nameToCorrect = nameToCorrect.toLowerCase();
        char[] chars = nameToCorrect.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ' && i < chars.length - 2)
                chars[i + 1] -= 'a' - 'A';
        }
        nameToCorrect = String.valueOf(chars);
        return nameToCorrect.substring(0, 1).toUpperCase() + nameToCorrect.substring(1);
    }


    private double getExchangeRate(String currencyCode) throws IOException, ParseException {
        URL url = new URL(urlNbp + currencyCode + "/?format=json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

        JSONObject jsonObject = getJsonFromString(bufferedReader.readLine());
        JSONArray jsonArray = (JSONArray) jsonObject.get("rates");
        JSONObject jsonFinal = (JSONObject) jsonArray.get(0);

        return (double) jsonFinal.get("mid");
    }

    public JSONObject getJsonFromString(String jsonString) throws ParseException {

        return (JSONObject) jsonParser.parse(jsonString);
    }

}
