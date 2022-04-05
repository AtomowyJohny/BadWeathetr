package gui.badweather;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.badweather.Service;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


public class GuiLogic extends Application {
    public void starGui() {
        launch();
    }

    private final String urlWiki = "https://en.wikipedia.org/wiki/";
    private AtomicReference<String> cityName = new AtomicReference<>("Warsaw");
    @Override
    public void start(Stage stage) throws IOException, ParseException {

        Service s = new Service(cityName.toString());

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 840, 660);
        stage.setTitle("WeatherMoney");

        TextArea textArea = new TextArea();
        textArea.setPrefSize(150.0, 400.0);


        WebView browser = new WebView();
        Label tempDesc = new Label();
        tempDesc.setText("Temp. in: " + cityName+" "+s.getTemp(cityName.toString())+"(°C)");

        Label tempReading = new Label();
        //tempReading.setText();


        Button changeData = new Button("Change data");
        changeData.setOnAction(e -> {
          cityName.set(textArea.getText());

            browser.getEngine().load(urlWiki+cityName);
           // tempDesc.setText("Temp. in: " + cityName);
            try {
                tempDesc.setText("Temp. in: " + cityName+" "+s.getTemp(cityName.toString())+"(°C)");
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
        });


        VBox box = new VBox();




        box.getChildren().addAll(changeData,textArea, tempDesc,tempReading);



        browser.getEngine().load(urlWiki+cityName);
        root.setCenter(browser);
        root.setLeft(box);


        stage.setScene(scene);
        stage.show();




    }
}

