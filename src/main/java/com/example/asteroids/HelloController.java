package com.example.asteroids;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ImageView bg1, bg2, rocket, asteroid1, asteroid2, asteroid3, flame;

    @FXML
    private Group groupRocket;

    @FXML
    private Label lblGameOver, lblScore;

    private double offset = 0;
    private int score = 0;
    private double asteroid_1_x = 900 + Math.random() * 300;
    private double asteroid_2_x = 900 + Math.random() * 300;
    private double asteroid_3_x = 900 + Math.random() * 300;
    private double speed = 0;

    @FXML
    public void initialize() {
        lblGameOver.setVisible(false);
        fireAnimation();

        Image background = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        Image imageAsteroid = new Image(getClass().getResourceAsStream("/images/asteroid.png"));
        rocket.setImage(new Image(getClass().getResourceAsStream("/images/rocket.png")));
        flame.setImage(new Image(getClass().getResourceAsStream("/images/flame.png")));

        asteroid1.setImage(imageAsteroid);
        asteroid1.setLayoutY(Math.random() * 540);
        asteroid2.setImage(imageAsteroid);
        asteroid2.setLayoutY(Math.random() * 540);
        asteroid3.setImage(imageAsteroid);
        asteroid3.setLayoutY(Math.random() * 540);

        bg1.setImage(background);
        bg2.setImage(background);
        bg2.setLayoutX(900);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                speed += 0.2;

                if (checkCollision() == true){
                    lblGameOver.setVisible(true);
                    return;
                }

                lblGameOver.setVisible(false);

                offset -= 1+speed; // offset = offset - 1;
                if (offset <= -900) {
                    offset = 0;
                }

                asteroid_1_x -= 2+speed;
                asteroid_2_x -= 2.25+speed;
                asteroid_3_x -= 1.75+speed;

                if (asteroid_1_x <= -100) {
                    asteroid_1_x = 900 + Math.random() * 300;
                    asteroid1.setLayoutY(Math.random() * 540);
                    score++;
                }
                asteroid1.setLayoutX(asteroid_1_x);
                asteroid1.setRotate(asteroid_1_x);

                if (asteroid_2_x <= -100) {
                    asteroid_2_x = 900 + Math.random() * 300;
                    asteroid2.setLayoutY(Math.random() * 540);
                    score++;
                }
                asteroid2.setLayoutX(asteroid_2_x);
                asteroid2.setRotate(asteroid_2_x);

                if (asteroid_3_x <= -100) {
                    asteroid_3_x = 900 + Math.random() * 300;
                    asteroid3.setLayoutY(Math.random() * 540);
                    score++;
                }
                asteroid3.setLayoutX(asteroid_3_x);
                asteroid3.setRotate(asteroid_3_x);
                bg1.setLayoutX(offset);
                bg2.setLayoutX(offset + 900);

                lblScore.setText(String.valueOf(score));
            }
        };
        timer.start();

    }

    public void handleKeyPressed(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.UP && groupRocket.getLayoutY() >= -300 && checkCollision() == false) {
            groupRocket.setLayoutY(groupRocket.getLayoutY() - 10);
            groupRocket.setRotate(-15);

        }
        if (keyEvent.getCode() == KeyCode.DOWN && groupRocket.getLayoutY() <= 240 && checkCollision() == false) {
            groupRocket.setLayoutY(groupRocket.getLayoutY() + 10);
            groupRocket.setRotate(15);
        }
    }

    public void handleKeyReleased(KeyEvent keyEvent) {
        groupRocket.setRotate(0);
    }

    public boolean checkCollision() {
        if (groupRocket.getBoundsInParent().intersects(asteroid1.getBoundsInParent())) {
            return true;
        }
        if (groupRocket.getBoundsInParent().intersects(asteroid2.getBoundsInParent())) {
            return true;
        }
        if (groupRocket.getBoundsInParent().intersects(asteroid3.getBoundsInParent())) {
            return true;
        }

        return false;
    }
    public void fireAnimation(){
        FadeTransition fire =new FadeTransition(Duration.millis(120), flame);
        fire.setFromValue(0.3);
        fire.setToValue(1.2);
        fire.setCycleCount(Animation.INDEFINITE);
        fire.setAutoReverse(true);
        fire.play();
    }
}