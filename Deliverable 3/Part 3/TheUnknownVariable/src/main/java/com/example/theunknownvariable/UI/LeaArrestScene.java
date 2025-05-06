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

public class LeaArrestScene extends Application {

    private int sceneIndex = 0;
    private VBox dialogueBox;
    private Label dialogueLabel;
    private ImageView background;
    private List<String> dialogueLines = new ArrayList<>();
    private List<String> backgroundImages = new ArrayList<>();



    public Scene buildArrestScene(){
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

        return  new Scene(root, 800, 600);
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
        primaryStage.setTitle("Final Scene - Lea Mio's Confession");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupDialogue() {
        dialogueLines.add("Lea Mio stares at you, wrists cuffed, yet eyes burning with something... unrepentant.");
        backgroundImages.add("/interrogation.png");

        dialogueLines.add("\"He thought he could erase me. Rewrite my code. Replace me like a bad plugin.\"");
        backgroundImages.add("/interrogation.png");

        dialogueLines.add("\"Adin made a new Omnivox. A safer one. But mine... mine was the original. My legacy.\"");
        backgroundImages.add("/interrogation.png");


        dialogueLines.add("You narrow your eyes. \"You killed him. For improving your software?\"");
        backgroundImages.add("/interrogation.png");


        dialogueLines.add("She smiles bitterly. \"No. I killed him because he stole the spotlight. My platform was dying.\"");
        backgroundImages.add("/interrogation.png");


        dialogueLines.add("\"He wasn’t fixing Omnivox. He was burying it. Burying me.\"");
        backgroundImages.add("/interrogation.png");


        dialogueLines.add("\"I was the future once,\" she whispers. \"Now I'm just deprecated.\"");
        backgroundImages.add("/interrogation.png");


        dialogueLines.add("You stand, pushing your chair back. \"Case closed.\"");
        backgroundImages.add("/interrogation.png");


        dialogueLines.add("*Justice compiled successfully.*");
        backgroundImages.add("/gunshot.png");

        dialogueLines.add("");
        backgroundImages.add("/goodjob.png");

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
