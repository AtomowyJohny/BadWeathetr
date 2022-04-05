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
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Service {
    private final String keyOpenWeather = "5928457613c5d7cbfc8b2c0564c44268";
    private final String urlNbp = "https://api.nbp.pl/api/exchangerates/rates/a/";
    private final String urlOpenWeather = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final JSONParser jsonParser = new JSONParser();
    private Locale locale;
    public String countryName;


    public Service(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
       // locale =
    }

    public String getWeather(String cityName) throws IOException {

        URL url = new URL(urlOpenWeather + cityName + "&appid=" + keyOpenWeather);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

        return bufferedReader.readLine();
    }

    public String getTemp(String cityName) throws IOException, ParseException {

        String weatherString = getWeather(cityName);

        JSONObject jsonObject = getJsonFromString(weatherString);
        JSONArray jsonArray = (JSONArray) jsonObject.get("main");


        System.out.println(jsonObject.keySet());
        System.out.println(jsonObject);
        return "temp";
    }

    public double getRateFor(String currencyCode) throws IOException, ParseException {  //zwraca kurs waluty danego kraju wobec waluty podanej jako argument,

        URL url = new URL(urlNbp + currencyCode + "/?format=json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

        JSONObject jsonObject = getJsonFromString(bufferedReader.readLine());
        JSONArray jsonArray = (JSONArray) jsonObject.get("rates");
        JSONObject jsonFinal = (JSONObject) jsonArray.get(0);

        return (double) jsonFinal.get("mid");
    }


    public double getNBPRate() throws Exception {  //zwraca kurs złotego wobec waluty danego kraju   (woec kraju który przegładam)
            //https://stackoverflow.com/questions/14155049/iso2-country-code-from-country-name
        // na podstawie coutry name ustalić wartość kursu wobec aktualnie przeglądanej sttrony na wiki

        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale.setDefault(Locale.ENGLISH);
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }

        //CurrencyManager.CurrManager();
        System.out.println(countries);

        return 0.0;
    }

    public JSONObject getJsonFromString(String jsonString) throws ParseException {

        return (JSONObject) jsonParser.parse(jsonString);
    }

}
