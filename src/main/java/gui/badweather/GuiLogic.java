package gui.badweather;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.badweather.Service;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


public class GuiLogic extends Application {
    private final String urlWiki = "https://en.wikipedia.org/wiki/";
    private final AtomicReference<String> cityName = new AtomicReference<>("Warsaw");
    private static  Service s = Main.getS();

    public void starGui() {

        launch();
    }



    @Override
    public void start(Stage stage) throws Exception {



        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 840, 660);
        stage.setTitle("WeatherMoney");

        WebView browser = new WebView();


        TextArea textAreaCity = new TextArea();
        textAreaCity.setMaxHeight(20);

        TextArea textAreaCurrency = new TextArea();
        textAreaCity.setMaxHeight(20);

        TextArea textAreaCountry = new TextArea();
        textAreaCity.setMaxHeight(20);


        Label labelTempDesc = new Label();
        labelTempDesc.setText("Temp. in: " + cityName + " " + s.getTemp(cityName.toString()) + "(°C)");


        Button changeCity = new Button("Change city");
        changeCity.setOnAction(e -> {

            cityName.set(textAreaCity.getText());
            browser.getEngine().load(urlWiki + cityName);

            try {
                labelTempDesc.setText("Temp. in: " + s.correctName(cityName.toString()) + " " + s.getTemp(cityName.toString()) + "(°C)");
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }

        });


        Label labelCurrency = new Label();
        labelCurrency.setText("Set currency");

        Button changeCurrency = new Button("Change Currency");
        changeCurrency.setOnAction(e -> {
            try {
                labelCurrency.setText("Exchange rate for " + textAreaCurrency.getText()
                        + " is:\n" + (s.getRateFor(textAreaCurrency.getText())));
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
        });

        Label labelNbpRate = new Label();
        labelNbpRate.setText("NbpRate: " + s.getNBPRate());

        Button changeCountry = new Button("Change Country");
        changeCountry.setOnAction(e -> {
          s.setCountryName(textAreaCountry.getText());
            try {

                labelNbpRate.setText("NbpRate: " + s.getNBPRate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        });

        VBox box = new VBox();
        box.setMaxWidth(150);

        box.getChildren().addAll(changeCity, textAreaCity, labelTempDesc);

        box.getChildren().addAll(changeCurrency, textAreaCurrency, labelCurrency);

        box.getChildren().addAll(changeCountry, textAreaCountry,labelNbpRate);


        browser.getEngine().load(urlWiki + cityName);
        root.setCenter(browser);
        root.setLeft(box);

        stage.getIcons().add(new Image("C:\\Users\\janek\\OneDrive\\Pulpit\\Studia\\TPO\\Projekty\\2zadprog\\klienci_usług_sieciowych\\BadWeather\\BadWeather.jpg"));

        stage.setScene(scene);
        stage.show();


    }
}

