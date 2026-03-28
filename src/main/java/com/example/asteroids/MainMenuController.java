package com.example.asteroids;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MainMenuController {
    public void newGame(ActionEvent actionEvent) throws IOException {
        AsteroidApplication.setRoot("game-view");
    }
}
