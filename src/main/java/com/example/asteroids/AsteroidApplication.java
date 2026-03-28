package com.example.asteroids;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AsteroidApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AsteroidApplication.class.getResource("mainMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Asteroids");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
//        GameController controller = fxmlLoader.getController();
//        scene.setOnKeyPressed(controller::handleKeyPressed);
//        scene.setOnKeyReleased(controller::handleKeyReleased);
    }

    public static void main(String[] args) {
        launch();
    }
}