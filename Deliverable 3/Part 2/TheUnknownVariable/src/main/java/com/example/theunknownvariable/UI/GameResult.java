package com.example.theunknownvariable.UI;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GameResult {
    public Scene yesClue(){
        Image yesClueImage = new Image("");
        ImageView yesClueView = new ImageView(yesClueImage);
        yesClueView.setFitHeight(1400);
        yesClueView.setPreserveRatio(true);
        StackPane pane = new StackPane(yesClueView);
        return new Scene(pane);
    }
    public Scene noClue(){
        Image noClueImage = new Image("");
        ImageView noClueView = new ImageView(noClueImage);
        noClueView.setFitHeight(1400);
        noClueView.setPreserveRatio(true);
        StackPane pane = new StackPane(noClueView);
        return new Scene(pane);
    }
}
