package com.example.theunknownvariable.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameOverScene extends Application {

    private int sceneIndex = 0;
    private VBox dialogueBox;
    private Label dialogueLabel;
    private ImageView background;
    private List<String> dialogueLines = new ArrayList<>();
    private List<String> backgroundImages = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
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

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Game Over");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupDialogue() {
        dialogueLines.add("You made the arrest. But something feels wrong...");
        backgroundImages.add("/gunshot.png");


        dialogueLines.add("Hours later, another body is found.");
        backgroundImages.add("/gunshot.png");


        dialogueLines.add("The real killer is still out there.");
        backgroundImages.add("/gunshot.png");


        dialogueLines.add("You lost.");
        backgroundImages.add("/gunshot.png");

    }

    private void advanceScene() {
        if (sceneIndex < dialogueLines.size()) {
            dialogueLabel.setText(dialogueLines.get(sceneIndex));
            String path = backgroundImages.get(sceneIndex);
            Image img = new Image(getClass().getResource(path).toExternalForm());
            background.setImage(img);
            sceneIndex++;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
