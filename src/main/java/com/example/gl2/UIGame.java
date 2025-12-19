package com.example.gl2;

import OOPS.GameEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class UIGame extends Application {

    Label scoreLabel;
    Label timeLabel;
    int row = 3;
    int col = 6;
    ImageView[][] holes = new ImageView[row][col]; //ImageView array for UI to keep track what type is located where
    GameEngine ge;
    Thread gameThread;
    boolean gameRunning;

    Image hole = new Image(getClass().getResource("/hole.png").toExternalForm());//Getting it from the rescource file
    Label gameOver = new Label("Game Over"); //Label for Game Over

    public void start(Stage stage) {
        BorderPane rootPane = new BorderPane();
        rootPane.setStyle("-fx-padding: 20;");

        scoreLabel = new Label("Score: 0");  //Score Label for score
        timeLabel = new Label("Time: 0"); //Time Label for time

        HBox topBar = new HBox(scoreLabel, timeLabel);
        topBar.setSpacing(30);
        topBar.setStyle("-fx-alignment: center;");

        Image startImg = new Image(getClass().getResource("/start.png").toExternalForm());
        ImageView startImage = new ImageView(startImg);
        startImage.setFitWidth(150);
        startImage.setFitHeight(60);

        VBox topSection = new VBox(topBar, startImage, gameOver);
        topSection.setSpacing(15);
        topSection.setStyle("-fx-alignment: center;");
        rootPane.setTop(topSection);

        gameOver.setVisible(false);

        startImage.setOnMouseClicked(e -> {  //When the start is clicked it resets the UI
            resetUI();
            gameOver.setVisible(false);
            gameRunning = true;
            ge = new GameEngine(this); //creates a new instance of the game engine
            gameThread = new Thread(ge); //Creates a new game thread
            gameThread.start(); // starting the game thread
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-alignment: center;");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ImageView iv = new ImageView();
                iv.setFitWidth(120);
                iv.setFitHeight(120);

                int finalI = i;
                int finalJ = j;

                iv.setOnMouseClicked(e -> {
                    if (gameRunning && ge != null) //If the game is running and and ge is not null whack it
                        ge.handleWhack(finalI, finalJ);
                });

                holes[i][j] = iv;
                holes[i][j].setImage(hole);
                grid.add(iv, j, i);
            }
        }

        rootPane.setCenter(grid);

        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(); //Launching the UI
    }

    public void updateScore(int s) {
        scoreLabel.setText("Score: " + s);  //Updating the score in the UIGame
    }

    public void updateTimer(int t) {
        timeLabel.setText("Time: " + t); //Updating the timer
    }

    public void updateHoleImage(int r, int c, Image img) {
        holes[r][c].setImage(img);  //Updating the UI to display hole
    }

    public void resetUI() { //This resets the UI when the game ends and this resets the score and time for the game
        scoreLabel.setText("Score: 0");
        timeLabel.setText("Time: 60");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                holes[i][j].setImage(hole);  //This sets all the HoleOccupants again to a hole
            }
        }
    }

    public void showGameOver() {  //This updates the UI when time=0 is reached
        gameRunning = false;
        gameOver.setVisible(true);
    }
}

