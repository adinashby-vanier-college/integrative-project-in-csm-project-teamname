package com.example.theunknownvariable.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameOverUI {

    public Scene displayGameOverScreen(Stage stage) {
        Label message = new Label("GAME OVER");
        message.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: darkred;");

        VBox layout = new VBox(20, message);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, 1366, 768);
    }
}
