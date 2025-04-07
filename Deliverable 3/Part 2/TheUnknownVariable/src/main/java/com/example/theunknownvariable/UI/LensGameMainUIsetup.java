package com.example.theunknownvariable.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Controller class for the Lens Game interface and logic
 */
public class LensGameMainUIsetup {
    // Root container for the scene
    static StackPane rootContainer = new StackPane();
    // Scene for the main lens game
    private static Scene lensGameScene;
    // Scene for the lens game instructions
    private static Scene lensGameInstructionsScene;
    // Stage reference for switching scenes
    private static Stage stage;

    // Constructor to set the stage
    public LensGameMainUIsetup(Stage stage) {
        this.stage = stage;
    }

    // Starts the lens game by showing the instruction scene first
    public void start(Stage stage) {
        lensGameScene = buildLensGameScene();
        lensGameInstructionsScene = buildLensGameInstructions();
        stage.setScene(lensGameInstructionsScene);
        stage.setTitle("LensUI Game");
        stage.show();
    }

    // Builds the main lens game scene layout and returns it
    public Scene buildLensGameScene() {
        // Set up background image
        Image backgroundImage = new Image(getClass().getResource("/Assets/background.png").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        HBox background = new HBox(backgroundImageView);
        background.setAlignment(Pos.CENTER);

        // Add ruler to the scene
        RulerMarker ruler = new RulerMarker();
        Canvas canvas = ruler.getRulerCanvas(770.0, 70.0);
        StackPane rulerStackPane = new StackPane(canvas);
        rulerStackPane.setAlignment(Pos.CENTER_RIGHT);
        rulerStackPane.setPadding(new Insets(0, 20, 20, 20));
        StackPane.setMargin(rulerStackPane, new Insets(230, 130, 0, 0));

        // Home button setup and action
        Button homeButton = new Button();
        homeButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        Image homeButtonImage = new Image(getClass().getResource("/Assets/home button.png").toExternalForm());
        ImageView homeButtonImageView = new ImageView(homeButtonImage);
        homeButtonImageView.setFitHeight(100);
        homeButtonImageView.setFitWidth(100);
        hoverBrightenessFX(homeButton, homeButtonImageView);
        homeButton.setGraphic(homeButtonImageView);
        homeButton.setOnAction(event -> {
            MainPage mainPage = new MainPage(stage);
            Scene scene = mainPage.displayMainPage();
            switchScenes(scene);
        });

        // Hint button setup and action
        Button hintButton = new Button();
        hintButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        Image hintButtonImage = new Image(getClass().getResource("/Assets/hint button2.png").toExternalForm());
        ImageView hintButtonImageView = new ImageView(hintButtonImage);
        hintButtonImageView.setFitHeight(100);
        hintButtonImageView.setFitWidth(100);
        hoverBrightenessFX(hintButton, hintButtonImageView);
        hintButton.setGraphic(hintButtonImageView);
        hintButton.setOnAction(event -> {
            Stage hintStage = new Stage();
            hintStage.setScene(getHintScene());
            hintStage.setTitle("LensUI Game Hint");
            hintStage.showAndWait();
        });

        // Add hint and home buttons to layout
        VBox hinthomeVbox = new VBox(10, hintButton, homeButton);
        hinthomeVbox.setAlignment(Pos.BOTTOM_RIGHT);
        hinthomeVbox.setPadding(new Insets(10));

        // Add draggable test object (simulates object distance)
        TestObject testObject = new TestObject();
        StackPane testObjectContainer = testObject.getObjectPane();
       // Pane testObjectContainer = testObject.getObjectPane();


        // Add eye illustration
        EyeUI eye = new EyeUI();
        VBox eyeBox = eye.getEyeBox();

        // Add empty rectangle container representing lens placement
        Rectangle rect = getPrescriptionLensRect();
        HBox rectBox = new HBox(30, rect);
        rectBox.setPadding(new Insets(0, 0, 0, 250));
        rectBox.setAlignment(Pos.CENTER_LEFT);

        // Combine eye, object, and lens placement
        StackPane mainAxis = new StackPane(eyeBox, testObjectContainer, rectBox);

        // Add slider for object positioning
        HBox sliderContainer = testObject.getSliderBox();
        sliderContainer.setAlignment(Pos.CENTER_RIGHT);
        sliderContainer.setPadding(new Insets(0, 20, 20, 20));

        // Add marker functionality to show position on ruler
        RulerMarker marker = new RulerMarker();
        marker.MarkerMaker();
        HBox markerHbox = new HBox(marker.getMarkerContainer());
        marker.markerButton.setOnAction(event -> {
            Slider slider = testObject.getPositionSlider();
            double sliderValue = slider.getValue();
            double thumbX = slider.lookup(".thumb").localToScreen(slider.lookup(".thumb").getBoundsInLocal()).getMinX();
            marker.updateMarker(thumbX, sliderValue);
        });

        // Top container with input field and add lens button
        HBox topBox = getTopContainer();
        topBox.setPadding(new Insets(100, 50, 50, 50));

        // Center content (eye and object)
        HBox eyeNobjectBox = new HBox(mainAxis);
        eyeNobjectBox.setAlignment(Pos.CENTER);
        eyeNobjectBox.setPadding(new Insets(10, 0, 0, 0));

        // Bottom area with slider, marker, and buttons
        VBox sliderMarkerRulerBox = new VBox(10, sliderContainer, markerHbox, rulerStackPane);
        sliderMarkerRulerBox.setAlignment(Pos.TOP_RIGHT);
        sliderMarkerRulerBox.setPadding(new Insets(20, 15, 0, 0));
        HBox buttonsBox = new HBox(hinthomeVbox);
        buttonsBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonsBox.setPadding(new Insets(20));
        HBox bottomBox = new HBox(10, marker.getMarkerButtonContainer(), sliderMarkerRulerBox, buttonsBox);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.setPadding(new Insets(0, 3, 10, 0));

        // Stack background and foreground elements
        VBox foreground = new VBox(topBox, eyeNobjectBox, bottomBox);
        rootContainer = new StackPane(background, foreground);
        return new Scene(rootContainer, 1366, 768);
    }

    // Adds hover effect to buttons (brightness increase on hover)
    public void hoverBrightenessFX(Button button, ImageView imageView) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0);
        imageView.setEffect(colorAdjust);
        button.setOnMouseEntered(e -> colorAdjust.setBrightness(0.2));
        button.setOnMouseExited(e -> colorAdjust.setBrightness(0));
    }

    // Creates a dashed rectangle to visually represent lens placement
    public Rectangle getPrescriptionLensRect() {
        Rectangle prescriptionLensRect = new Rectangle(0, 0, 70, 270);
        prescriptionLensRect.setFill(Color.TRANSPARENT);
        prescriptionLensRect.setStroke(Color.rgb(214, 182, 144));
        prescriptionLensRect.setStrokeWidth(3);
        prescriptionLensRect.getStrokeDashArray().setAll(10.0, 10.0);
        prescriptionLensRect.setStrokeType(StrokeType.CENTERED);
        return prescriptionLensRect;
    }

    // Creates the top HBox with text input and Add Lens button
    public HBox getTopContainer() {
        Button addLensButton = new Button("Add LensUI");
        addLensButton.getStylesheets().add(getClass().getResource("/Styles/LensGameStyle.css").toExternalForm());

        Label answerLabel = new Label("Enter eye prescription:");
        answerLabel.getStylesheets().add(getClass().getResource("/Styles/LensGameStyle.css").toExternalForm());

        TextField answerTextField = new TextField();
        answerTextField.getStylesheets().add(getClass().getResource("/Styles/LensGameStyle.css").toExternalForm());
        // Only allow valid numeric input
        answerTextField.textProperty().addListener((observable, oldText, newText) -> {
            if (!newText.matches("-?\\d*\\.?\\d*")) {
                answerTextField.setText(oldText);
            }
        });

        HBox box = new HBox(20, answerLabel, answerTextField, addLensButton);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    // Builds the hint pop-up scene explaining lens physics
    public Scene getHintScene() {
        Image backgroundImage = new Image(getClass().getResource("/Assets/hintBackground.png").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setFitHeight(620);
        HBox background = new HBox(backgroundImageView);
        background.setAlignment(Pos.CENTER);

        // Text content for hint
        TextArea textArea = new TextArea("EyeUI prescriptions often include..." /* truncated for brevity in comment */);
        textArea.setEditable(false);
        textArea.getStylesheets().add(getClass().getResource("/Styles/LensGameStyle.css").toExternalForm());

        HBox box = new HBox(textArea);
        box.setMaxSize(720, 440);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(120, 210, 10, 10));
        StackPane stack = new StackPane(background, box);

        return new Scene(stack, 1000, 620);
    }

    // Builds the lens game instructions scene shown at start
    public Scene buildLensGameInstructions() {
        Image backgroundImage = new Image(getClass().getResource("/Assets/instructionsBackgroundEmpty.png").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setFitHeight(768);
        HBox background = new HBox(backgroundImageView);
        background.setAlignment(Pos.CENTER);

        Button iUnderstandButton = new Button();
        iUnderstandButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        Image iUnderstandButtonImage = new Image(getClass().getResource("/Assets/iUnderstandButton.png").toExternalForm());
        ImageView iUnderstandButtonImageView = new ImageView(iUnderstandButtonImage);
        iUnderstandButtonImageView.setPreserveRatio(true);
        iUnderstandButtonImageView.setFitHeight(90);
        hoverBrightenessFX(iUnderstandButton, iUnderstandButtonImageView);
        iUnderstandButton.setGraphic(iUnderstandButtonImageView);
        iUnderstandButton.setOnAction(event -> {
            Scene scene = getLensGameScene();
            switchScenes(scene);
        });

        // Instruction text
        Font cinzelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Cinzel-Regular.ttf"), 24);
        Label instructions = new Label("The culprit dropped some sort of lens on the crime scene!..." /* text truncated */);
        instructions.setFont(cinzelFont);
        instructions.setStyle("-fx-text-fill: white; -fx-text-alignment: center;");

        VBox instructionsBox = new VBox(30, instructions, iUnderstandButton);
        instructionsBox.setAlignment(Pos.CENTER);
        instructionsBox.setPadding(new Insets(10));

        StackPane root = new StackPane(background, instructionsBox);
        root.setAlignment(Pos.CENTER);
        lensGameInstructionsScene = new Scene(root, 1366, 768);
        return lensGameInstructionsScene;
    }

    // Helper function to switch between scenes on the same stage
    public void switchScenes(Scene scene) {
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    // Getter for the lens game scene (creates it if not yet created)
    public static Scene getLensGameScene() {
        return lensGameScene != null ? lensGameScene : new LensGameMainUIsetup(stage).buildLensGameScene();
    }

    // Getter for the instructions scene (creates it if not yet created)
    public static Scene getLensGameInstructionsScene() {
        if (lensGameInstructionsScene == null) {
            lensGameInstructionsScene = new LensGameMainUIsetup(stage).buildLensGameInstructions();
        }
        return lensGameInstructionsScene;
    }
}
