package gui.badweather;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GuiLogic extends Application {
    public void starGui() {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 840, 660);
        stage.setTitle("WeatherMoney");
        JTextArea textArea = new JTextArea();


        Button changeData = new Button("Change data");
        changeData.setOnAction(e -> {
          changeData.setRotate(25);
        });



        WebView browser = new WebView();

        String www = "https://pl.wikipedia.org/wiki/Warszawa";


        VBox box = new VBox();
        box.getChildren().add(changeData);
        //box.getChildren().add(textArea);


        browser.getEngine().load(www);
        root.setCenter(browser);
        root.setLeft(box);


        stage.setScene(scene);
        stage.show();


    }
}

