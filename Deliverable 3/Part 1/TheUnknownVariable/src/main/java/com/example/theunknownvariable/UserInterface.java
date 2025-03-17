package com.example.demo1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserInterface extends StackPane {
    private static final double SCREEN_WIDTH = 1367;
    private static final double SCREEN_HEIGHT = 768;
    private static final double MAX_GUN_HEIGHT = 320;
    private static final double MIN_GUN_HEIGHT = 130;


    private Image GunImage;
    private Image Play;
    private Image Submit;
    private Image TargetImage;
    private Image Theory;
    private Image Menu;
    private Image ClueImage;
    private Image Line;
    private Slider heightSlider;
    private Pane root;

    public UserInterface() {

        root = new Pane();
        root.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        Image backgroundImage = new Image("/background.png");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(SCREEN_WIDTH);
        backgroundView.setFitHeight(SCREEN_HEIGHT);
        backgroundView.setPreserveRatio(false);
        root.getChildren().add(backgroundView);

        TargetImage = new Image("/target.png");
        GunImage = new Image("/gun.png");
        Play = new Image("/play.png");
        Submit = new Image("/Submit.png");
        Theory = new Image("/theory.png");
        Menu = new Image("/Menu.png");
        ClueImage = new Image("/clueImage.png");
        Line = new Image("/beigeline.png" );

        ImageView targetView = new ImageView(TargetImage);
        targetView.setFitWidth(180);
        targetView.setFitHeight(180);
        targetView.setPreserveRatio(true);
        targetView.setLayoutX(1200);
        targetView.setLayoutY(180);

        ImageView gunView = new ImageView(GunImage);
        gunView.setFitWidth(155);
        gunView.setFitHeight(155);
        gunView.setPreserveRatio(true);
        gunView.setLayoutX(150);
        gunView.setLayoutY(MAX_GUN_HEIGHT);


        heightSlider = new Slider(MIN_GUN_HEIGHT, MAX_GUN_HEIGHT, MAX_GUN_HEIGHT);
        heightSlider.setLayoutX(80);
        heightSlider.setLayoutY(130);
        heightSlider.setPrefWidth(25);
        heightSlider.setPrefHeight(250);
        heightSlider.setOrientation(javafx.geometry.Orientation.VERTICAL);
        heightSlider.setStyle("-fx-control-inner-background: #DDCDB1; ");

        heightSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            gunView.setLayoutY(newVal.doubleValue());
        });

        ImageView playView = new ImageView(Play);
        playView.setFitWidth(170);
        playView.setFitHeight(170);
        playView.setPreserveRatio(true);

        ImageView submitView = new ImageView(Submit);
        submitView.setFitWidth(170);
        submitView.setFitHeight(170);
        submitView.setPreserveRatio(true);

        TextField heightInput = new TextField();
        heightInput.setPromptText("Enter Suspect Height");
        heightInput.setPrefSize(200, 50);
        heightInput.setStyle("-fx-text-fill: #713939;" + "-fx-prompt-text-fill: #713939;" + "-fx-font-size: 16px; " + "-fx-background-color: #D1C5AB; " +
                "-fx-border-color: #713939; " + "-fx-background-radius: 10px; " + "-fx-border-radius: 10px; ");

        VBox vboxCenter = new VBox(10);
        vboxCenter.getChildren().addAll(playView, heightInput, submitView);
        vboxCenter.setAlignment(Pos.CENTER);
        vboxCenter.setLayoutX(SCREEN_WIDTH / 2 - 100);
        vboxCenter.setLayoutY(SCREEN_HEIGHT - 260);

        ImageView theoryView = new ImageView(Theory);
        theoryView.setFitWidth(100);
        theoryView.setFitHeight(100);
        HBox theoryBox = new HBox(theoryView);
        theoryView.setOnMouseClicked(event -> {
            showTheoryPopup();
        });
        theoryView.setOnMouseEntered(event -> {
            theoryView.setOpacity(0.7);
        });

        theoryView.setOnMouseExited(event -> {
            theoryView.setOpacity(1);
        });

        ImageView menuView = new ImageView(Menu);
        menuView.setFitWidth(100);
        menuView.setFitHeight(100);
        HBox menuBox = new HBox(menuView);
        menuBox.setPadding(new Insets(0, 0, 0, 5));

        VBox vboxBottomRight = new VBox(5);
        vboxBottomRight.getChildren().addAll(theoryBox, menuBox);
        vboxBottomRight.setLayoutX(SCREEN_WIDTH - 110);
        vboxBottomRight.setLayoutY(SCREEN_HEIGHT - 220);
        vboxBottomRight.setAlignment(Pos.CENTER);


        ImageView clueView = new ImageView(ClueImage);
        clueView.setFitWidth(150);
        clueView.setFitHeight(150);
        clueView.setPreserveRatio(true);
        clueView.setLayoutX(50);
        clueView.setLayoutY(610);

        ImageView lineView = new ImageView(Line);
        lineView.setFitWidth(800);
        lineView.setFitHeight(300);
        lineView.setPreserveRatio(true);
        lineView.setLayoutX(SCREEN_WIDTH / 5);
        lineView.setLayoutY(SCREEN_HEIGHT - 320);

        root.getChildren().addAll(heightSlider, gunView, targetView, vboxCenter, vboxBottomRight, clueView, lineView);

        getChildren().add(root);
    }

    public void displayTrajectory() {
    }

    public void enableSlider() {
       // heightSlider.setDisable(false);
    }

    public void disableSlider() {
       // heightSlider.setDisable(true);
    }

    private void showTheoryPopup() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Theory Explanation");

        Image backgroundImage = new Image("background.png");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(800);
        backgroundView.setFitHeight(400);
        backgroundView.setPreserveRatio(false);

        Label theoryLabel = new Label("The theory behind projectile motion...");
        theoryLabel.setWrapText(true);
        theoryLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-padding: 20px;");

        StackPane textContainer = new StackPane(theoryLabel);
        textContainer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6); -fx-background-radius: 15;");
        textContainer.setMaxWidth(800);
        textContainer.setPadding(new Insets(30));

        StackPane popupLayout = new StackPane();
        popupLayout.getChildren().addAll(backgroundView, textContainer);
        StackPane.setAlignment(textContainer, Pos.CENTER);

        Scene popupScene = new Scene(popupLayout, 800, 400);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
}