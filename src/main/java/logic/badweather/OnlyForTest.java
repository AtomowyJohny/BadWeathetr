package logic.badweather;


public class OnlyForTest {
    public static void main(String[] args) throws Exception {
/*

        String cityName = "Warsaw";


        String apiKey = "5928457613c5d7cbfc8b2c0564c44268";

        try {
            URL openApiWeather = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openApiWeather.openStream(), StandardCharsets.UTF_8));

            String fromApi = bufferedReader.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fromApi);
            System.out.println(jsonObject.get("visibility"));

//            WebView browser = new WebView();
//            WebEngine webEngine = browser.getEngine();
//            String www  = "https://pl.wikipedia.org/wiki/Warszawa";
//            webEngine.load(www);

        } catch (Exception e) {
            e.printStackTrace();
        }
*/

        Service s = new Service("Poland");
       /// CurrencyManager.CurrManager();

        // System.out.println(s.getWeather("Warsaw"));

        // System.out.println(s.getJsonFromString(s.getWeather("Warsaw")).get("visibility"));




/*
        JSONObject jsonObject= (JSONObject) s.getJsonFromString(s.getWeather("Warsaw")).get("main");

        System.out.println(jsonObject.get("temp"));
*/

       // s.getNBPRate();


       // System.out.println( s.getRateFor("USD"));
        // System.out.println(s.getRateFor("USD"));

/*
        WeatherMoneyApp1 w1 = new WeatherMoneyApp1();

        w1.launchGui();


*/

        s.getNBPRate();

    }
}
