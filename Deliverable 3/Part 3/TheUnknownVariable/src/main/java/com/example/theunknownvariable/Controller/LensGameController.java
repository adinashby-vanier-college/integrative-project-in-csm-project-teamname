package com.example.theunknownvariable.Controller;
import com.example.theunknownvariable.UI.*;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

public class LensGameController {
    static StackPane rootContainer = new StackPane();
    private static Scene lensGameScene;
    private static Scene lensGameInstructionsScene;
    private HBox lensContainer = new HBox();
    private LensUI currentLens = null;

    private static Stage stage;
    public static boolean game1access = true;
    public static boolean game1clue = false;
    private static int attempts = 0;
    private static Label attemptsLabel = new Label("attempts: 0/3");;

    public LensGameController(Stage stage){
        this.stage = stage;
    }


    public Scene buildLensGameScene() {
        //background
        Image backgroundImage = new Image(getClass().getResource("/Assets/background.png").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        HBox background = new HBox(backgroundImageView);
        background.setAlignment(Pos.CENTER);

        //ruler
        RulerMarkerUI ruler = new RulerMarkerUI();
        Canvas canvas = ruler.getRulerCanvas(770.0, 70.0);
        StackPane rulerStackPane = new StackPane(canvas);
        rulerStackPane.setAlignment(Pos.CENTER_RIGHT);
        rulerStackPane.setPadding(new Insets(0,20,20,20));
        StackPane.setMargin(rulerStackPane, new Insets(230, 130, 0, 0));

        //home and hint button
        Button homeButton = new Button();
        homeButton.setOnAction(event ->{
        });

        homeButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        Image homeButtonImage = new Image(getClass().getResource("/Assets/home button.png").toExternalForm());
        ImageView homeButtonImageView = new ImageView(homeButtonImage);
        homeButtonImageView.setFitHeight(100);
        homeButtonImageView.setFitWidth(100);
        hoverBrightenessFX(homeButton, homeButtonImageView);
        homeButton.setGraphic(homeButtonImageView);
        homeButton.setOnAction(event->{
            MainPage mainPage = new MainPage(stage);
            Scene scene = mainPage.displayMainPage();
            switchScenes(scene);
        });

        Button hintButton = new Button();
        hintButton.setOnAction(event -> {
            Stage hintStage = new Stage();
            hintStage.setScene(getHintScene());
            hintStage.setTitle("Lens Game Hint");
            hintStage.showAndWait();
        });
        hintButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        Image hintButtonImage = new Image(getClass().getResource("/Assets/hint button2.png").toExternalForm());
        ImageView hintButtonImageView = new ImageView(hintButtonImage);
        hintButtonImageView.setFitHeight(100);
        hintButtonImageView.setFitWidth(100);
        hoverBrightenessFX(hintButton, hintButtonImageView);
        hintButton.setGraphic(hintButtonImageView);

        VBox hinthomeVbox = new VBox(10, hintButton, homeButton);
        hinthomeVbox.setAlignment(Pos.BOTTOM_RIGHT);
        hinthomeVbox.setPadding(new Insets(10));

        //test object
        TestObjectUI testObject = new TestObjectUI();
        //StackPane testObjectContainer = testObject.getObjectPane();
        Pane testObjectContainer = testObject.getObjectPane();


        //eye
        EyeUI eye = new EyeUI();
        VBox eyeBox = eye.getEyeBox();

        //Rectangle container for lens
        lensContainer.setScaleX(1.7);
        lensContainer.setScaleY(1.7);
        Rectangle rect = getPrescriptionLensRect();

        StackPane rectBox = new StackPane(rect, lensContainer);
        rectBox.setAlignment(Pos.CENTER_LEFT);
        rectBox.setPadding(new Insets(0, 0, 0, 250));
        lensContainer.setAlignment(Pos.CENTER);
        lensContainer.setPadding(new Insets(70,450,0,0));


        StackPane mainAxis = new StackPane(eyeBox, testObjectContainer, rectBox);

        //Slider
        HBox sliderContainer = testObject.getSliderBox();
        sliderContainer.setAlignment(Pos.CENTER_RIGHT);
        sliderContainer.setPadding(new Insets(0,20,20,20));

        //marker
        RulerMarkerUI marker = new RulerMarkerUI();
        marker.MarkerMaker();
        HBox markerHbox = new HBox(marker.getMarkerContainer());

        marker.markerButton.setOnAction(event -> {
            Slider slider = testObject.getPositionSlider();
            double sliderValue = slider.getValue();
            double thumbX = slider.lookup(".thumb").localToScreen(slider.lookup(".thumb").getBoundsInLocal()).getMinX();
            marker.updateMarker(thumbX, sliderValue);

        });

        //Layout
        HBox topBox = getTopContainer();
        topBox.setPadding(new Insets(100,50,50,50));

        HBox eyeNobjectBox = new HBox(mainAxis);
        eyeNobjectBox.setAlignment(Pos.CENTER);
        eyeNobjectBox.setPadding(new Insets(10,0,0,0));

        VBox sliderMarkerRulerBox = new VBox(10, sliderContainer, markerHbox, rulerStackPane);
        sliderMarkerRulerBox.setAlignment(Pos.TOP_RIGHT);
        sliderMarkerRulerBox.setPadding(new Insets(20,15,0,0));

        HBox buttonsBox = new HBox(hinthomeVbox);
        buttonsBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonsBox.setPadding(new Insets(20));

        HBox bottomBox = new HBox(10, marker.getMarkerButtonContainer(), sliderMarkerRulerBox, buttonsBox);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.setPadding(new Insets(0, 3,10,0));


        VBox foreground = new VBox(topBox, eyeNobjectBox,bottomBox);

        rootContainer = new StackPane(background, foreground);
        lensGameScene = new Scene(rootContainer, 1366,768);
        return lensGameScene;

    }
    public void hoverBrightenessFX(Button button, ImageView imageView){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0);
        imageView.setEffect(colorAdjust);
        button.setOnMouseEntered(e -> colorAdjust.setBrightness(0.2));
        button.setOnMouseExited(e -> colorAdjust.setBrightness(0));
    }
    public Rectangle getPrescriptionLensRect(){
        Rectangle prescriptionLensRect = new Rectangle(0, 0, 70, 270);
        prescriptionLensRect.setFill(Color.TRANSPARENT);
        prescriptionLensRect.setStroke(Color.rgb(214, 182, 144));
        prescriptionLensRect.setStrokeWidth(3);
        prescriptionLensRect.getStrokeDashArray().setAll(10.0, 10.0);
        prescriptionLensRect.setStrokeType(StrokeType.CENTERED);
        return  prescriptionLensRect;
    }
    public HBox getTopContainer(){
        attemptsLabel.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-size: 20px;\n" +
               // "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: rgb(241, 226, 221);");
        Label answerLabel = new Label("Enter eye prescription:");
        answerLabel.getStylesheets().add(
                getClass().getResource("/Styles/LensGameStyle.css").toExternalForm()
        );
        TextField answerTextField = new TextField();
        answerTextField.getStylesheets().add(
                getClass().getResource("/Styles/LensGameStyle.css").toExternalForm()
        );
        answerTextField.textProperty().addListener((observable, oldText, newText) -> {
            if (!newText.matches("-?\\d*\\.?\\d*")) {  // Regex allows only valid doubles
                answerTextField.setText(oldText);  // Revert to old text if invalid input
            }
        });
        Button addLensButton = new Button("Add Lens");
        addLensButton.setOnAction(event -> {
            double answerValue = Double.parseDouble(answerTextField.getText());
            addLens(answerValue);
            answerChecker(answerValue);

        });
        addLensButton.getStylesheets().add(
                getClass().getResource("/Styles/LensGameStyle.css").toExternalForm()
        );
        HBox addNattempt = new HBox(5,addLensButton,attemptsLabel);
        addNattempt.setAlignment(Pos.CENTER);
        HBox box = new HBox(20, answerLabel, answerTextField, addNattempt);
        box.setAlignment(Pos.CENTER);
        return box;

    }
    public Scene getHintScene(){
        //1000 x 620
        Image backgroundImage = new Image(getClass().getResource("/Assets/hintBackground.png").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true);
        HBox background = new HBox(backgroundImageView);
        backgroundImageView.setFitHeight(625);
        background.setAlignment(Pos.CENTER);

        TextArea textArea = new TextArea("EyeUI prescriptions often include a measurement expressing the strength of a lens needed to correct a person's vision. That measurement is in Diopter units, which are the inverse of the focal length of a lens. The focal length of a lens is the distance from the lens to the point where it focuses light, and this distance is inversely proportional to the strength of the lens. To find a prescription, one must understand the relationship between the near point, far point, and the focal length of the corrective lens.\n\n" +
                "To calculate a prescription, the lens formula is used:\n" +
                "\n" +
                "1/f = 1/do - 1/di\n" +
                "\n" +
                "Where: \n" +
                "- f is the focal length of the lens\n" +
                "- do is the object distance (near point)\n" +
                "- di is the image distance (where the corrected image should focus)\n" +
                "\n" +
                "The goal is to adjust the lens so that the image forms at the normal near point, typically 25 cm. For myopia (nearsightedness), a diverging lens (negative diopters) is needed to push the image further away. For hyperopia (farsightedness), a converging lens (positive diopters) is required to focus the image closer. \n\n" +
                "Some prescriptions also require cylindrical correction for astigmatism, which adjusts the curvature of the lens to correct uneven focusing... But our criminal seems to be a quite simple eye patient!");
        textArea.setEditable(false);
        textArea.getStylesheets().add(
                getClass().getResource("/Styles/LensGameStyle.css").toExternalForm()
        );

        HBox box  = new HBox(textArea);
        box.setMaxSize(720,440);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(120,210,10,10));
        StackPane stack = new StackPane(background, box);

        Scene scene = new Scene(stack, 1000, 620);
        return scene;
    }

    public Scene buildLensGameInstructions(){

        Image backgroundImage = new Image(getClass().getResource("/Assets/instructionsBackgroundEmpty.png").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true);
        HBox background = new HBox(backgroundImageView);
        backgroundImageView.setFitHeight(768);
        background.setAlignment(Pos.CENTER);

        Button iUnderstandButton = new Button();
        iUnderstandButton.setOnAction(event ->{
            Scene scene = getLensGameScene();
            switchScenes(scene);
        });

        iUnderstandButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        Image iUnderstandButtonImage = new Image(getClass().getResource("/Assets/iUnderstandButton.png").toExternalForm());
        ImageView iUnderstandButtonImageView = new ImageView(iUnderstandButtonImage);
        iUnderstandButtonImageView.setPreserveRatio(true);
        iUnderstandButtonImageView.setFitHeight(90);
        hoverBrightenessFX(iUnderstandButton, iUnderstandButtonImageView);
        iUnderstandButton.setGraphic(iUnderstandButtonImageView);

        Font cinzelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Cinzel-Regular.ttf"), 24);
        Label instructions = new Label("The culprit dropped some sort of lens on the crime scene!"
                +
                "\nI wonder if it can lead to a clue..." +
                "\nI can find the lens' strength (power) by using my science background!" +
                "\nIf I remember correctly: one can move an object around until its\n rays converge in the right spot. " +
                "\nThat gives the eye's near-point... " +
                "\nWith some calculations, I'll be able to find a prescription and\n add the lens to see if I got it right!");
//        instructions.getStylesheets().add(
//                getClass().getResource("/Styles/LensGameStyle.css").toExternalForm()
//        );
//        instructions.getStyleClass().add("instructions");
        instructions.setFont(cinzelFont); // Apply the custom font
        instructions.setStyle("-fx-text-fill: white; -fx-text-alignment: center;");

        VBox instructionsBox = new VBox(30, instructions, iUnderstandButton);
        instructionsBox.setAlignment(Pos.CENTER);
        instructionsBox.setPadding(new Insets(10));

        StackPane root = new StackPane(background, instructionsBox);
        root.setAlignment(Pos.CENTER);
        lensGameInstructionsScene = new Scene(root,1366,768);
        return lensGameInstructionsScene;
    }


    public void addLens(Double powerAns){
        if (powerAns < 0){
            addDivergingLens();
        } else {
            addConvergingLens();
        }
    }

    public void addConvergingLens() {
        lensContainer.getChildren().clear();
        currentLens = new LensUI("c");

        if (currentLens.getLensContainer() == null) {
           //debugging
           System.out.println("Error: Lens container is null!");
        } else {
            lensContainer.getChildren().add(currentLens.getLensContainer());
        }
    }


    public void addDivergingLens() {
        lensContainer.getChildren().clear(); // remove previous lens
        currentLens = new LensUI("d");
        lensContainer.getChildren().addAll(currentLens.getLensContainer());
    }



    public void answerChecker(Double powerAns) {
        Double lowerLimit = -1 / 0.245;
        Double upperLimit = -1 / 0.335;

        if (powerAns <= upperLimit && powerAns >= lowerLimit) {
            success();
        } else {
            updateAttempts();
        }
    }

    public void updateAttempts(){
        attempts++;
        attemptsLabel.setText("attempts: "+attempts+"/3");
        if (attempts == 3) {
            failure();
        }
        else {
            displayImage(lensGameScene, "wrongScenario.png",300,2,false);
        }
    }
    public void success(){
        game1access = false;
        game1clue = true;
        GameStateManager.getInstance().unlockClue1();
        GameStateManager.getInstance().lockGame1();

        displayImage(lensGameScene,"clueScene.png",800,5,false);
//
//        MainPage mainPage = new MainPage(stage);
//        Scene scene = mainPage.displayMainPage();

        // wait

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
                MainPage mainPage = new MainPage(stage);
                switchScenes(mainPage.displayMainPage());

        });
        delay.play();

//        switchScenes(scene);
    }
    public void failure(){
        game1access = false;
        game1clue = false;
        GameStateManager.getInstance().lockGame1();
        displayImage(lensGameScene,"gameOver.png",800,5,true);

//        displayImage(lensGameScene,"gameOver.png",800,5,true);
    }

    public static boolean isGame1access() {
        return game1access;
    }

    public static boolean isGame1clue() {
        return game1clue;
    }

    public void switchScenes(Scene scene) {
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    public static Scene getLensGameScene() {
        return lensGameScene != null ? lensGameScene : new LensGameController(stage).buildLensGameScene();
    }
    public static Scene getLensGameInstructionsScene() {
        if (lensGameInstructionsScene == null) {
            lensGameInstructionsScene = new LensGameController(stage).buildLensGameInstructions();
        }
        return lensGameInstructionsScene;
    }








    public void displayImage(Scene scene, String imageUrl,int width,int time,boolean flag) {
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
