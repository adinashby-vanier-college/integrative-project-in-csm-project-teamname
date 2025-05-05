package com.example.theunknownvariable.UI;

import com.example.theunknownvariable.Controller.CluesController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Clues {

    private final Label game1Clue = createClueLabel();
    private final Label game2Clue = createClueLabel();
    private final Label game3Clue = createClueLabel();
    private final Label game4Clue = createClueLabel();
    private final Button menuButton;
    private final CluesController controller;

    public Clues(Stage stage) {
        this.controller = new CluesController(stage, this);
        this.menuButton = createMenuButton();
        setupEventHandlers();
    }

    public Scene displayClues() {
        updateClues();

        Image background = new Image("vintageBackground.jpg");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(768);
        backgroundView.setPreserveRatio(true);

        VBox gameLabel1 = new VBox(game1Clue);
        VBox gameLabel2 = new VBox(game2Clue);
        VBox gameLabel3 = new VBox(game3Clue);
        VBox gameLabel4 = new VBox(game4Clue);
        HBox clueLabelsHBox = new HBox(108, gameLabel1, gameLabel2, gameLabel3, gameLabel4);
        VBox clueContainer = new VBox(clueLabelsHBox);
        StackPane menuPosition = new StackPane(menuButton);

        clueLabelsHBox.setAlignment(Pos.CENTER);
        gameLabel1.setAlignment(Pos.CENTER);
        gameLabel2.setAlignment(Pos.CENTER);
        gameLabel3.setAlignment(Pos.CENTER);
        gameLabel4.setAlignment(Pos.CENTER);
        clueContainer.setTranslateY(100);
        clueContainer.setAlignment(Pos.CENTER);
        menuPosition.setAlignment(Pos.BOTTOM_RIGHT);

        return new Scene(new StackPane(backgroundView, clueContainer, menuPosition), 1366, 768);
    }

    private void setupEventHandlers() {
        menuButton.setOnAction(e -> controller.handleMenuButtonClick());
    }

    public void updateClues() {
        game1Clue.setText(controller.getClueText(1));
        game2Clue.setText(controller.getClueText(2));
        game3Clue.setText(controller.getClueText(3));
        game4Clue.setText(controller.getClueText(4));
    }

    private Label createClueLabel() {
        Label label = new Label("Clue starts here");
        label.setStyle("-fx-alignment: center;-fx-font-family: 'Georgia';-fx-font-weight: bold;-fx-font-size: 23;");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private Button createMenuButton() {
        ImageView icon = new ImageView(new Image("menu3.png"));
        icon.setFitWidth(100);
        icon.setPreserveRatio(true);
        Button button = new Button("", icon);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setOnMouseEntered(e -> icon.setOpacity(0.8));
        button.setOnMouseExited(e -> icon.setOpacity(1.0));
        return button;
    }
}
