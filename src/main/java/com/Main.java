package com;

import com.controller.CSBaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/CSBasePane.fxml"));
            AnchorPane layout = loader.load();
            Scene scene = new Scene(layout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("华山医院北院");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/cs.png")));
            CSBaseController.stage = primaryStage;
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
