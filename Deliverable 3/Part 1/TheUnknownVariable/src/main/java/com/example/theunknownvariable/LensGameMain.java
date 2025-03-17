package com.example.theunknownvariable;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class LensGameMain extends Application {
    static StackPane rootContainer = new StackPane();

    @Override
    public void start(Stage stage) {
        //background
        Image backgroundImage = new Image("src/main/resources/Assets/background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        HBox background = new HBox(backgroundImageView);
        background.setAlignment(Pos.CENTER);

        //ruler
        RulerMarker ruler = new RulerMarker();
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
        Image homeButtonImage = new Image("C:\\Users\\maria\\IdeaProjects\\LensGameGUI\\src\\main\\resources\\Assets\\home button.png");
        ImageView homeButtonImageView = new ImageView(homeButtonImage);
        homeButtonImageView.setFitHeight(100);
        homeButtonImageView.setFitWidth(100);
        hoverBrightenessFX(homeButton, homeButtonImageView);
        homeButton.setGraphic(homeButtonImageView);

        Button hintButton = new Button();
        hintButton.setOnAction(event -> {
            Stage hintStage = new Stage();
            hintStage.setScene(getHintScene());
            hintStage.setTitle("Lens Game Hint");
            hintStage.showAndWait();
        });
        hintButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        Image hintButtonImage = new Image("C:\\Users\\maria\\IdeaProjects\\LensGameGUI\\src\\main\\resources\\Assets\\hint button2.png");
        ImageView hintButtonImageView = new ImageView(hintButtonImage);
        hintButtonImageView.setFitHeight(100);
        hintButtonImageView.setFitWidth(100);
        hoverBrightenessFX(hintButton, hintButtonImageView);
        hintButton.setGraphic(hintButtonImageView);

        VBox hinthomeVbox = new VBox(10, hintButton, homeButton);
        hinthomeVbox.setAlignment(Pos.BOTTOM_RIGHT);
        hinthomeVbox.setPadding(new Insets(10));

        //test object
        TestObject testObject = new TestObject();
        StackPane testObjectContainer = testObject.getObjectPane();

        //Eye
        Eye eye = new Eye();
        VBox eyeBox = eye.getEyeBox();

        //Empty rectangle container for lens
        Rectangle rect = getPrescriptionLensRect();
        HBox rectBox = new HBox(30, rect);
        rectBox.setPadding(new Insets(0,0,0,250));
        rectBox.setAlignment(Pos.CENTER_LEFT);

        StackPane mainAxis = new StackPane(eyeBox, testObjectContainer, rectBox);

        //Slider
        HBox sliderContainer = testObject.getSliderBox();
        sliderContainer.setAlignment(Pos.CENTER_RIGHT);
        sliderContainer.setPadding(new Insets(0,20,20,20));

        //marker
        RulerMarker marker = new RulerMarker();
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
        stage.setScene(lensGameScene);
        stage.setTitle("Lens Game");
        stage.show();
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
        Button addLensButton = new Button("Add Lens");
        addLensButton.getStylesheets().add(
                getClass().getResource("/Styles/LensGameStyle.css").toExternalForm()
        );
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

        HBox box = new HBox(20, answerLabel, answerTextField, addLensButton);
        box.setAlignment(Pos.CENTER);
        return box;

    }
    public Scene getHintScene(){
        TextArea textArea = new TextArea("Eye prescriptions often include a measurement expressing the strength of a lens needed to correct a person's vision. That measurement is in Diopter units, which are the inverse of the focal length of a lens. The focal length of a lens is the distance from the lens to the point where it focuses light, and this distance is inversely proportional to the strength of the lens. To find a prescription, one must understand the relationship between the near point, far point, and the focal length of the corrective lens.\n\n" +
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
        Scene scene = new Scene(box, 1000, 500);
        return scene;
    }
    public static Scene lensGameScene = new Scene(rootContainer, 1366, 768);
    public static Scene getLensGaneScene(){
        return lensGameScene;
    }
    public static void main(String[] args) {
        launch();
    }
}
