package com.example.theunknownvariable.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameOverScene extends Application {

    private int sceneIndex = 0;
    private VBox dialogueBox;
    private Label dialogueLabel;
    private ImageView background;
    private List<String> dialogueLines = new ArrayList<>();
    private List<String> backgroundImages = new ArrayList<>();
    private MediaPlayer mediaPlayer;

    public Scene buildGameOverScene(){

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

        return new Scene(root, 800, 600);

    }

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
        backgroundImages.add("/detective_pondering.png");


        dialogueLines.add("Hours later, another body is found.");
        backgroundImages.add("/crime_scene.png");


        dialogueLines.add("The real killer is still out there.");
        backgroundImages.add("/crime_scene.png");


        dialogueLines.add("You lost.");
        backgroundImages.add("/gameoverImage.jpg");

    }

    private void advanceScene() {
        if (sceneIndex < dialogueLines.size()) {
            dialogueLabel.setText(dialogueLines.get(sceneIndex));
            String path = backgroundImages.get(sceneIndex);
            Image img = new Image(getClass().getResource(path).toExternalForm());
            background.setImage(img);
            sceneIndex++;
        }
        if (sceneIndex==dialogueLines.size()-1)playBackgroundMusic();

    }

    private void playBackgroundMusic() {
        try {
            URL resource = getClass().getResource("/fail.mp3");
            if (resource == null) {
                throw new IllegalArgumentException("Audio file not found!");
            }

            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0.7); // Set desired volume
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
