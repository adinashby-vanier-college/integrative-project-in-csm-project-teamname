package com.example.theunknownvariable.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {

    private final Button menuButton;
    private final Button suspectsButton;
    private final Button quitButton;
    private final Button accuseButton;
    private final Stage stage;

    public HomePage(Stage stage) {
        this.stage = stage;
        this.menuButton = createButton("play");
        this.suspectsButton = createButton("suspects");
        this.quitButton = createButton("quit");
        this.accuseButton = createButton("accuse");
    }

    public Scene displayHomePage() {
        Image background = new Image("homeBackground.jpg");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(768);
        backgroundView.setPreserveRatio(true);

        VBox buttonVBox = new VBox(20, menuButton, suspectsButton, quitButton);
        buttonVBox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox accuseVBox = new VBox(accuseButton);
        accuseVBox.setAlignment(Pos.CENTER_RIGHT);
        HBox buttonsHBox = new HBox(270, buttonVBox, accuseVBox);
        buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);

        StackPane buttonsPosition = new StackPane(buttonsHBox);
        buttonsPosition.setAlignment(Pos.CENTER);

        StackPane pane = new StackPane(backgroundView, buttonsPosition);
        return new Scene(pane, 1366, 768);
    }

    public Button getMenuButton() { return menuButton; }
    public Button getSuspectsButton() { return suspectsButton; }
    public Button getQuitButton() { return quitButton; }
    public Button getAccuseButton() { return accuseButton; }

    private Button createButton(String type) {
        Button button = new Button();
        Image image;
        ImageView imageView;

        switch (type) {
            case "play" -> {
                image = new Image("playButton.jpg");
                imageView = new ImageView(image);
                imageView.setFitWidth(300);
                imageView.setPreserveRatio(true);
                button = new Button("", imageView);
            }
            case "suspects" -> {
                image = new Image("suspectsButton.jpg");
                imageView = new ImageView(image);
                imageView.setFitWidth(300);
                imageView.setPreserveRatio(true);
                button = new Button("", imageView);
            }
            case "quit" -> {
                image = new Image("quitButton.jpg");
                imageView = new ImageView(image);
                imageView.setFitWidth(300);
                imageView.setPreserveRatio(true);
                button = new Button("", imageView);
            }
            case "accuse" -> {
                if (com.example.theunknownvariable.Controller.GameStateManager.getInstance().isGame1Locked()
                        && com.example.theunknownvariable.Controller.GameStateManager.getInstance().isGame2Locked()
                        && com.example.theunknownvariable.Controller.GameStateManager.getInstance().isGame3Locked()
                        && com.example.theunknownvariable.Controller.GameStateManager.getInstance().isGame4Locked()) {
                    image = new Image("accuseFolder.jpeg");
                } else {
                    image = new Image("accuse.png");
                }
                imageView = new ImageView(image);
                imageView.setFitWidth(250);
                imageView.setPreserveRatio(true);
                button = new Button("", imageView);
            }
        }

        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        imageView = (ImageView) button.getGraphic();
        ImageView finalImageView = imageView;
        button.setOnMouseEntered(e -> finalImageView.setOpacity(0.8));
        ImageView finalImageView1 = imageView;
        button.setOnMouseExited(e -> finalImageView1.setOpacity(1.0));
        return button;
    }
}
