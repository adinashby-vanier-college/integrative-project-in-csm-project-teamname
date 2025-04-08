package com.example.theunknownvariable.UI;

import com.example.theunknownvariable.Controller.GameStateManager;
import com.example.theunknownvariable.Controller.ProjectileHandler2;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserInterface2 extends StackPane {
    // Screen dimensions and gun height limits
    private static final double SCREEN_WIDTH = 1367;
    private static final double SCREEN_HEIGHT = 768;
    private static final double MAX_GUN_HEIGHT = 320;
    private static final double MIN_GUN_HEIGHT = 130;

    // UI components and stage
    private Stage stage;
    private Pane root;
    private Slider heightSlider;
    private Button menuButton, instructionsButton, playButton, submitButton, theoryButton;
    private ImageView gunView, bulletView, paperView, Tmotion;
    private int incorrectAttempts = 0; // Track incorrect user guesses
    private TextField heightInput;

    public UserInterface2(Stage stage) {
        this.stage = stage;
        initializeUI();       // Set up UI layout
        eventHandling();      // Handle navigation & theory
        initializeHandlers(); // Handle game logic
    }

    // Sets up the visual layout and UI components
    private void initializeUI() {
        root = new Pane();
        root.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        // Add background
        ImageView backgroundView = createImageView("/background.png", SCREEN_WIDTH, SCREEN_HEIGHT, false);
        root.getChildren().add(backgroundView);

        // Title motion banner
        Tmotion = createImageView("tmotion.png", 450, 100, true);
        Tmotion.setLayoutX(SCREEN_WIDTH / 3);
        Tmotion.setLayoutY(30);

        // Gun and bullet visuals
        gunView = createImageView("/gun.png", 155, 155, true);
        gunView.setLayoutX(150);
        gunView.setLayoutY(MAX_GUN_HEIGHT); // Start at max height

        bulletView = createImageView("/bullet.png", 70, 70, true);
        bulletView.setLayoutX(gunView.getLayoutX());
        bulletView.setLayoutY(gunView.getLayoutY());

        // Target and visual guides
        ImageView targetView = createImageView("/target.png", 180, 180, true);
        targetView.setLayoutX(1200);
        targetView.setLayoutY(180);

        ImageView lineView = createImageView("/beigeline.png", 800, 300, true);
        lineView.setLayoutX(SCREEN_WIDTH / 5);
        lineView.setLayoutY(SCREEN_HEIGHT - 320);

        // Paper background for inputs
        paperView = createImageView("/paper.png", 350, 300, true);
        paperView.setLayoutX(20);
        paperView.setLayoutY(SCREEN_HEIGHT - 260);
        root.getChildren().add(paperView);

        // Slider for adjusting gun height
        heightSlider = createSlider(MIN_GUN_HEIGHT, MAX_GUN_HEIGHT, MAX_GUN_HEIGHT);
        heightSlider.valueProperty().addListener((obs, oldVal, newVal) -> gunView.setLayoutY(newVal.doubleValue()));

        // Buttons and input fields
        playButton = createButton("/play.png", 170, 170);
        submitButton = createButton("/Submit.png", 170, 170);
        theoryButton = createButton("/theory.png", 120, 120);
        menuButton = createButton("/Menu.png", 120, 120);

        // Text field for height input
        heightInput = new TextField();
        heightInput.setPromptText("Enter Suspect Height");
        heightInput.setPrefSize(200, 50);
        heightInput.setStyle("-fx-text-fill: #713939; -fx-prompt-text-fill: #713939; -fx-font-size: 16px; " +
                "-fx-background-color: #D1C5AB; -fx-border-color: #713939; -fx-background-radius: 10px; -fx-border-radius: 10px;");

        // Center layout (play, input, submit)
        VBox vboxCenter = new VBox(10, playButton, heightInput, submitButton);
        vboxCenter.setAlignment(Pos.CENTER);
        vboxCenter.setLayoutX(SCREEN_WIDTH / 2 - 100);
        vboxCenter.setLayoutY(SCREEN_HEIGHT - 260);

        // Bottom-right layout (theory and menu)
        VBox vboxBottomRight = new VBox(theoryButton, menuButton);
        vboxBottomRight.setLayoutX(SCREEN_WIDTH - 145);
        vboxBottomRight.setLayoutY(SCREEN_HEIGHT - 250);
        vboxBottomRight.setAlignment(Pos.CENTER);

        // Add everything to root and display
        root.getChildren().addAll(heightSlider, gunView, targetView, lineView, vboxCenter, vboxBottomRight, Tmotion);
        getChildren().add(root);
    }

    // Set up event listeners for theory and menu navigation
    private void eventHandling() {
        menuButton.setOnAction(event -> switchScenes(new MainPage(stage).displayMainPage()));
        theoryButton.setOnAction(event -> showTheoryPopup());
    }

    // Initialize game logic, including play and submit functionality
    private void initializeHandlers() {
        game2UI gameUI = new game2UI();
        ProjectileHandler2 projectileHandler = new ProjectileHandler2(root, gunView, bulletView, heightSlider, playButton, paperView, gameUI);

        // Ensure bullet is only added during animation
        root.getChildren().remove(bulletView);

        // Start animation
        playButton.setOnAction(event -> {
            projectileHandler.startProjectileMotion();
        });

        // Handle suspect height input and clue logic
        submitButton.setOnAction(event -> {
            try {
                double suspectHeight = Double.parseDouble(heightInput.getText());

                if (suspectHeight >= 162 && suspectHeight <= 163) {
                    GameStateManager.getInstance().unlockClue2(); // Give clue
                    GameStateManager.getInstance().lockGame2();   // Lock game
                    displayImage(getScene(), "clueScene.png", 800, 5, true); // Show clue image
                } else {
                    displayImage(getScene(), "wrongScenario.png", 300, 2, false); // Show wrong message
                    incorrectAttempts++;
                }

                // Game over after 3 incorrect tries
                if (incorrectAttempts == 3) {
                    GameStateManager.getInstance().lockGame2();
                    displayImage(getScene(), "gameOver.png", 800, 5, true);
                }

            } catch (NumberFormatException e) {
                displayImage(getScene(), "invalidInput.png", 300, 2, false); // Input not a number
            }
        });
    }

    // Display a theory popup window with explanation image
    private void showTheoryPopup() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Theory Explanation");

        ImageView backgroundView = createImageView("hintBackgroundg2.png", 1000, 640, false);
        StackPane popupLayout = new StackPane(backgroundView);

        Scene popupScene = new Scene(popupLayout, 1000, 620);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }

    // Utility to create an ImageView with given parameters
    public static ImageView createImageView(String path, double width, double height, boolean preserveRatio) {
        ImageView imageView = new ImageView(new Image(path));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(preserveRatio);
        return imageView;
    }

    // Utility to create a styled image button
    private Button createButton(String imagePath, double width, double height) {
        ImageView imageView = createImageView(imagePath, width, height, true);
        Button button = new Button("", imageView);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setOnMouseEntered(e -> imageView.setOpacity(0.7));
        button.setOnMouseExited(e -> imageView.setOpacity(1.0));
        return button;
    }

    // Create vertical slider
    private Slider createSlider(double min, double max, double value) {
        Slider slider = new Slider(min, max, value);
        slider.setLayoutX(80);
        slider.setLayoutY(130);
        slider.setPrefWidth(25);
        slider.setPrefHeight(250);
        slider.setOrientation(javafx.geometry.Orientation.VERTICAL);
        slider.setStyle("-fx-control-inner-background: #DDCDB1;");
        return slider;
    }

    // Instructions scene layout
    public Scene displayInstructions() {
        Image background = new Image("instructionsBackgroundg2.png");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(768);
        backgroundView.setPreserveRatio(true);

        Image understand = new Image("instructionsButton.png");
        ImageView understandV = new ImageView(understand);
        understandV.setFitWidth(250);
        understandV.setPreserveRatio(true);
        instructionsButton = new Button("", understandV);
        instructionsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        instructionsButton.setOnMouseEntered(e -> understandV.setOpacity(0.8));
        instructionsButton.setOnMouseExited(e -> understandV.setOpacity(1.0));

        StackPane understandStack = new StackPane(instructionsButton);
        understandStack.setAlignment(Pos.BOTTOM_CENTER);

        // On click, load main game UI
        instructionsButton.setOnAction(event -> {
            UserInterface2 view = new UserInterface2(stage);
            Scene scene = new Scene(view, 1366, 768);
            switchScenes(scene);
        });

        StackPane stack = new StackPane(backgroundView, understandStack);
        return new Scene(stack, 1366, 768);
    }

    // Switch scenes on the same stage
    private void switchScenes(Scene scene) {
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    // Display image overlay with optional scene switch after delay
    public void displayImage(Scene scene, String imageUrl, int width, int time, boolean flag) {
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);

        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width);
        imageView.setX((scene.getWidth() - imageView.getFitWidth()) / 2);
        imageView.setY((scene.getHeight() - imageView.getFitHeight()) / 2);

        StackPane overlayPane = new StackPane();
        overlayPane.setStyle("-fx-background-color: rgba(0,0,0,0.5);");
        overlayPane.getChildren().add(imageView);

        if (scene.getRoot() instanceof Pane) {
            Pane root = (Pane) scene.getRoot();
            root.getChildren().add(overlayPane);

            PauseTransition delay = new PauseTransition(Duration.seconds(time));
            delay.setOnFinished(event -> {
                root.getChildren().remove(overlayPane);
                if (flag) {
                    MainPage mainPage = new MainPage(stage);
                    switchScenes(mainPage.displayMainPage());
                }
            });
            delay.play();
        }
    }
}