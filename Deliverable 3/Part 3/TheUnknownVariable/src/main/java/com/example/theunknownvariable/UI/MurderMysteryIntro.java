package com.example.theunknownvariable.UI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class MurderMysteryIntro {

    private StackPane root;
    private Scene scene;

    public Scene getScene() {
        setupScene();
        return scene;
    }


    private int sceneIndex = 0;
    private VBox dialogueBox;
    private Label dialogueLabel;
    private ImageView background;
    private List<String> dialogueLines = new ArrayList<>();
    private List<String> backgroundImages = new ArrayList<>();


    private Scene setupScene() {
        dialogueBox = new VBox();
        dialogueBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-padding: 20;");

        dialogueLabel = new Label();
        dialogueLabel.setWrapText(true);
        dialogueLabel.setFont(Font.font("Verdana", 20));
        dialogueLabel.setStyle("-fx-text-fill: white;");
        dialogueBox.getChildren().add(dialogueLabel);

        background = new ImageView();
        background.setFitWidth(800);
        background.setFitHeight(600);
        background.setPreserveRatio(false);

        StackPane root = new StackPane();
        root.getChildren().addAll(background, dialogueBox);

        root.setOnMouseClicked(e -> advanceScene());

        setupDialogue();
        advanceScene();

        this.scene = new Scene(root, 800, 600);
        return this.scene;
    }

    private void setupDialogue() {
        dialogueLines.add("After a long day of teaching loops and recursion, Adin Ashby finally returns home...");
        backgroundImages.add("/street.png");

        dialogueLines.add("\"Home, sweet—\"");
        backgroundImages.add("/door_open.png");

        dialogueLines.add("*Gunshot echoes. Adin Ashby collapses.*");
        backgroundImages.add("/gunshot.png");

        dialogueLines.add("The body was discovered the next morning. No witnesses. No clear motive. " +
                "Just a few clues where the body was discovered..");
        backgroundImages.add("/gunshot.png");

        dialogueLines.add("We’re stumped. But if anyone can crack this case... it’s you.");
        backgroundImages.add("/police_station.png");

        dialogueLines.add("You are the detective. Solve the case. Find the truth... Before the killer strikes again.");
        backgroundImages.add("/police_station.png");
    }

    private void advanceScene() {
        if (sceneIndex < dialogueLines.size()) {
            dialogueLabel.setText(dialogueLines.get(sceneIndex));
            background.setImage(new Image(getClass().getResource(backgroundImages.get(sceneIndex)).toExternalForm()));
            sceneIndex++;
        } else {
            // Intro is done — go to next scene
            if (onIntroFinished != null) {
                onIntroFinished.run();
            }
        }
    }

    private Runnable onIntroFinished;
    public void setOnIntroFinished(Runnable action) {
        this.onIntroFinished = action;
    }


}
