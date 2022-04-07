package gui.badweather;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.badweather.Service;

import java.util.concurrent.atomic.AtomicReference;


public class GuiLogic extends Application {
    private static final Service s = Main.getS();
    private final String urlWiki = "https://en.wikipedia.org/wiki/";
    private final AtomicReference<String> cityName = new AtomicReference<>("Warsaw");

    static void showExeption(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR, ex.toString());
        alert.show();
    }

    public void starGui() {
        launch();
    }

    public void core(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("WeatherMoney");

        WebView browser = new WebView();


        TextField textAreaCity = new TextField();

        textAreaCity.setPromptText("type city name");

        TextField textAreaCurrency = new TextField();
        textAreaCurrency.setPromptText("type iso currency");

        TextField textAreaCountry = new TextField();
        textAreaCountry.setPromptText("type country name");


        Label labelTempDesc = new Label();
        labelTempDesc.setText("Temp. in: " + cityName + " " + s.getTemp(cityName.toString()) + "(°C)");


        Button changeCity = new Button("Change city");
        changeCity.setOnAction(e -> {

            cityName.set(textAreaCity.getText());
            browser.getEngine().load(urlWiki + cityName);


            try {
                labelTempDesc.setText("Temp. in: " + s.correctName(cityName.toString()) + " " + s.getTemp(cityName.toString()) + "(°C)");
            } catch (Exception ex) {
                showExeption(ex);
            }

        });


        Label labelCurrency = new Label();
        labelCurrency.setText("Set currency");

        Button changeCurrency = new Button("Change Currency");

        AtomicReference<Exception> x = null;
        changeCurrency.setOnAction(e -> {

            try {
                labelCurrency.setText("Exchange rate for " + textAreaCurrency.getText()
                        + " is:\n" + (s.getRateFor(textAreaCurrency.getText())));
            } catch (Exception ex) {
                showExeption(ex);
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
                showExeption(ex);
            }


        });

        VBox box = new VBox();
        box.setMaxWidth(200);

        box.getChildren().addAll(changeCity, textAreaCity, labelTempDesc);

        box.getChildren().addAll(changeCurrency, textAreaCurrency, labelCurrency);

        box.getChildren().addAll(changeCountry, textAreaCountry, labelNbpRate);


        browser.getEngine().load(urlWiki + cityName);
        root.setCenter(browser);
        root.setLeft(box);

        stage.getIcons().add(new Image("C:\\Users\\janek\\OneDrive\\Pulpit\\Studia\\TPO\\Projekty\\2zadprog\\klienci_usług_sieciowych\\BadWeather\\BadWeather.jpg"));

        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void start(Stage stage) {

        try {
            core(stage);
        } catch (Exception e) {
            showExeption(e);
        }


    }
}

