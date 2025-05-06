package com.example.theunknownvariable.UI;

import com.example.theunknownvariable.Model.Substance;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

//This class contains the interface of game 3 chemistry and basic actions related to the interface components
public class ChemUI {

    //Buttons Variables
    private Button sub1Button;
    private Button sub2Button;
    private Button sub3Button;
    private Button sub4Button;
    private Button sub5Button;
    private Button sub6Button;
    private Button mixButton;
    private Button hintButton;
    private Button menuButton;
    private Button tryButton;
    private Button instructionsButton;

    //Labels
    private Label formulaLabel;
    private Label energyTypeLabel;
    private Label factLabel;

    //Tracking user's progress
    private Label rightAnswers;
    private Label wrongAnswers;
    private int rightAnsNb=0;
    private int wrongAnsNb=0;

    //Tubes and becker
    private ImageView tube1ImageView;
    private ImageView tube2ImageView;
    private ImageView becker;

    //Graph components
    private LineChart<Number, Number> lineChart;
    private XYChart.Series<Number, Number> series;
    private NumberAxis yAxis;


    //Button groups
    private Button selectedButtonGroup1 = null;
    private Button selectedButtonGroup2 = null;

    //Stage and scene
    private final Stage stage;
    private Scene gameScene;

    // Constructor
    public ChemUI(Stage stage){
        this.stage = stage;
        // Buttons
        this.sub1Button = buttonCustomization("luminol");
        this.sub2Button = buttonCustomization("aluminium");
        this.sub3Button = buttonCustomization("sc");
        this.sub4Button = buttonCustomization("sn");
        this.sub5Button = buttonCustomization("hp");
        this.sub6Button = buttonCustomization("io");
        this.mixButton = buttonCustomization("mix");
        this.hintButton = buttonCustomization("hint");
        this.menuButton = buttonCustomization("menu");
        this.tryButton = buttonCustomization("try");
        this.instructionsButton = buttonCustomization("understand");

        // Labels
        this.formulaLabel = new Label("Formula");
        this.energyTypeLabel = new Label("Energy Type");
        this.factLabel = new Label("Fact");

        //Graph components
        series = new XYChart.Series<>();
        yAxis = new NumberAxis();

        //Tubes view
        this.tube1ImageView = new ImageView(new Image("tubeFINAL.png"));
        this.tube2ImageView = new ImageView(new Image("tubeFINAL.png"));

        //Becker
        becker = new ImageView(new Image("beckerFINAL.png"));
    }

    // Initialization
    public void initialize(){
        labelCustomization();
        displayInstructions();
        displayGame();
    }

    // Customize buttons' style and effects
    public Button buttonCustomization(String button){
        //----------Buttons------------
        Button empty = new Button();
        switch (button) {
            case "luminol" -> {
                //Button 1: Luminol
                Image luminolImage = new Image("luminolPNG.png");
                ImageView luminol = new ImageView(luminolImage);
                luminol.setFitWidth(150);
                luminol.setPreserveRatio(true);
                sub1Button = new Button("", luminol);
                sub1Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                sub1Button.setOnMouseEntered(e -> luminol.setOpacity(0.8));
                sub1Button.setOnMouseExited(e -> luminol.setOpacity(1.0));
                return sub1Button;
            }
            case "aluminium" -> {
                //Button 2: Aluminium
                Image aluminiumImage = new Image("aluminiumPNG.png");
                ImageView aluminium = new ImageView(aluminiumImage);
                aluminium.setFitWidth(150);
                aluminium.setPreserveRatio(true);
                sub2Button = new Button("", aluminium);
                sub2Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                sub2Button.setOnMouseEntered(e -> aluminium.setOpacity(0.8));
                sub2Button.setOnMouseExited(e -> aluminium.setOpacity(1.0));
                return sub2Button;
            }
            case "sc" -> {
                //Button 3: Sodium Chloride
                Image scImage = new Image("scPNG.png");
                ImageView sc = new ImageView(scImage);
                sc.setFitWidth(150);
                sc.setPreserveRatio(true);
                sub3Button = new Button("", sc);
                sub3Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                sub3Button.setOnMouseEntered(e -> sc.setOpacity(0.8));
                sub3Button.setOnMouseExited(e -> sc.setOpacity(1.0));
                return sub3Button;
            }
            case "sn" -> {
                //Button 4: Silver Nitrate
                Image snImage = new Image("snPNG.png");
                ImageView sn = new ImageView(snImage);
                sn.setFitWidth(150);
                sn.setPreserveRatio(true);
                sub4Button = new Button("", sn);
                sub4Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                sub4Button.setOnMouseEntered(e -> sn.setOpacity(0.8));
                sub4Button.setOnMouseExited(e -> sn.setOpacity(1.0));
                return sub4Button;
            }
            case "hp" -> {
                //Button 5: Hydrogen Peroxide
                Image hpImage = new Image("hpPNG.png");
                ImageView hp = new ImageView(hpImage);
                hp.setFitWidth(150);
                hp.setPreserveRatio(true);
                sub5Button = new Button("", hp);
                sub5Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                sub5Button.setOnMouseEntered(e -> hp.setOpacity(0.8));
                sub5Button.setOnMouseExited(e -> hp.setOpacity(1.0));
                return sub5Button;
            }
            case "io" -> {
                //Button 6: Iron Oxide
                Image ioImage = new Image("ioPNG.png");
                ImageView io = new ImageView(ioImage);
                io.setFitWidth(150);
                io.setPreserveRatio(true);
                sub6Button = new Button("", io);
                sub6Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                sub6Button.setOnMouseEntered(e -> io.setOpacity(0.8));
                sub6Button.setOnMouseExited(e -> io.setOpacity(1.0));
                return sub6Button;
            }
            case "mix" -> {
                //Button Mix
                Image mixImage = new Image("mix.png");
                ImageView mix = new ImageView(mixImage);
                mix.setFitWidth(350);
                mix.setPreserveRatio(true);
                mixButton = new Button("", mix);
                mixButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                mixButton.setOnMouseEntered(e -> mix.setOpacity(0.8));
                mixButton.setOnMouseExited(e -> mix.setOpacity(1.0));
                return mixButton;
            }
            case "hint" -> {
                //Hint Button
                Image hintImage = new Image("image2.png");
                ImageView hint = new ImageView(hintImage);
                hint.setFitWidth(100);
                hint.setPreserveRatio(true);
                hintButton = new Button("", hint);
                hintButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                hintButton.setOnMouseEntered(e -> hint.setOpacity(0.8));
                hintButton.setOnMouseExited(e -> hint.setOpacity(1.0));
                return hintButton;
            }
            case "menu" -> {
                //Home menu button
                Image menuImage = new Image("menuButton.png");
                ImageView menu = new ImageView(menuImage);
                menu.setFitWidth(100);
                menu.setPreserveRatio(true);
                menuButton = new Button("", menu);
                menuButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                menuButton.setOnMouseEntered(e -> menu.setOpacity(0.8));
                menuButton.setOnMouseExited(e -> menu.setOpacity(1.0));
                return menuButton;
            }
            case "try" -> {
                //Try button
                Image tryImage = new Image("tryButton.png");
                ImageView tryView = new ImageView(tryImage);
                tryView.setFitWidth(150);
                tryView.setPreserveRatio(true);
                tryButton = new Button("", tryView);
                tryButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                tryButton.setOnMouseEntered(e -> tryView.setOpacity(0.8));
                tryButton.setOnMouseExited(e -> tryView.setOpacity(1.0));
                return tryButton;
            }
            case "understand" -> {
                //Understand button
                Image understand = new Image("instructionsButton.png");
                ImageView understandV = new ImageView(understand);
                understandV.setFitWidth(250);
                understandV.setPreserveRatio(true);
                instructionsButton = new Button("", understandV);
                instructionsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                instructionsButton.setOnMouseEntered(e -> understandV.setOpacity(0.8));
                instructionsButton.setOnMouseExited(e -> understandV.setOpacity(1.0));
                return instructionsButton;
            }
            default -> {
                return empty;
            }
        }

    }

    // Label Customization
    public void labelCustomization(){
        // Apply styling to labels
        String labelStyle = "-fx-background-color: #c2b19c;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10 40;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-text-fill: #2e2e2e;" +
                "-fx-font-weight: bold;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0, 0, 5);" +
                "-fx-pref-width: 630px;"+"-fx-alignment: center;";

        formulaLabel.setStyle(labelStyle+"-fx-font-size: 20px;");
        energyTypeLabel.setStyle(labelStyle+"-fx-font-size: 22px;");
        factLabel.setStyle(labelStyle+"-fx-font-size: 18px;");
    }

    // Getters
    public Button getSub1Button() {return sub1Button;}
    public Button getSub2Button() {return sub2Button;}
    public Button getSub3Button() {return sub3Button;}
    public Button getSub4Button() {return sub4Button;}
    public Button getSub5Button() {return sub5Button;}
    public Button getSub6Button() {return sub6Button;}
    public Button getInstructionsButton() {return instructionsButton;}
    public Button getMenuButton() {return menuButton;}
    public Button getHintButton() {return hintButton;}
    public Button getMixButton() {return mixButton;}
    public Button getTryButton() {return tryButton;}
    public ImageView getBecker(){ return becker; }
    public Label getRightAnswers() {return rightAnswers;}
    public Label getWrongAnswers() {return wrongAnswers;}
    public Scene getGameScene() {return gameScene;}
    public Label getFormulaLabel() {return formulaLabel;}
    public Label getEnergyTypeLabel() {
        return energyTypeLabel;
    }
    public Label getFactLabel() {
        return factLabel;
    }
    public ImageView getTube1ImageView() {return tube1ImageView;}
    public ImageView getTube2ImageView() {return tube2ImageView;}

    //Interface of instructions scene
    public Scene displayInstructions(){
        //Background png
        Image background = new Image("instructionsBackground.png");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(768);
        backgroundView.setPreserveRatio(true);

        //Button
        StackPane understandStack = new StackPane(this.instructionsButton);
        understandStack.setAlignment(Pos.BOTTOM_CENTER);

        //Layout
        StackPane stack = new StackPane(backgroundView,understandStack);

        return new Scene(stack,1366,768);

    }

    //Interface of hint window
    public Scene displayHint(){
        //Background png
        Image background = new Image("hintBackground.png");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(640);
        backgroundView.setPreserveRatio(true);

        //Layout
        StackPane stack = new StackPane(backgroundView);

        return new Scene(stack,1000,620);
    }

    //Interface of the chemistry game
    public Scene displayGame(){

        //Background png
        Image background = new Image("background.png");
        ImageView backgroundView = new ImageView(background);

        //----------Tracking user's progress UI------------
        //Right answer png
        ImageView rightAnsView = new ImageView(new Image("rightAnswers.png"));
        rightAnsView.setFitHeight(70);
        rightAnsView.setPreserveRatio(true);
        //Wrong answer png
        ImageView wrongAnsView = new ImageView(new Image("wrongAnswers.png"));
        wrongAnsView.setFitHeight(76);
        wrongAnsView.setPreserveRatio(true);
        //Labels
        rightAnswers = new Label("0/3");
        rightAnswers.setStyle("-fx-text-fill:white;-fx-font-size:19px;-fx-font-family:'Verdana';");
        wrongAnswers = new Label("0/3");
        wrongAnswers.setStyle("-fx-text-fill:white;-fx-font-size:19px;-fx-font-family:'Verdana';");
        StackPane rightAnsPane = new StackPane(rightAnsView,rightAnswers);
        StackPane wrongAnsPane = new StackPane(wrongAnsView,wrongAnswers);


        //Separator
        Image separatorImage = new Image("separator.png");
        ImageView separator = new ImageView(separatorImage);
        separator.setFitWidth(1500);
        VBox sepVBox = new VBox(separator);
        sepVBox.setAlignment(Pos.CENTER);

        //-------Initialization--------
        Clues clues = new Clues(stage);
        displayTube1();
        displayTube2();
        //Create ReactionHandler and set the becker
        becker = getBeckerImageView();

        //----------Layout-------------

        //Mix and tracking labels
        HBox mixAndLabels = new HBox(wrongAnsPane,mixButton,rightAnsPane);
        mixAndLabels.setAlignment(Pos.CENTER);

        //Becker and Mix
        VBox beckerVBox = new VBox(30,mixAndLabels, becker);
        beckerVBox.setAlignment(Pos.CENTER);

        //Tube and buttons RIGHT
        VBox buttonGroup1 = new VBox(30,sub1Button,sub2Button,sub3Button);
        buttonGroup1.setAlignment(Pos.CENTER_RIGHT);

        VBox tube1 = new VBox(displayTube1());
        tube1.setAlignment(Pos.BOTTOM_CENTER);

        HBox tubeGroup1 = new HBox(30,buttonGroup1, tube1);
        tubeGroup1.setAlignment(Pos.CENTER_RIGHT);

        //Tube and buttons LEFT
        VBox buttonGroup2 = new VBox(30,sub4Button,sub5Button,sub6Button);
        buttonGroup2.setAlignment(Pos.CENTER_LEFT);

        VBox tube2 = new VBox(displayTube2());
        tube2.setAlignment(Pos.BOTTOM_CENTER);

        HBox tubeGroup2 = new HBox(30, tube2,buttonGroup2);
        tubeGroup2.setAlignment(Pos.CENTER_LEFT);

        //Stack try button and separator
        HBox tryHBox = new HBox(tryButton);
        tryHBox.setAlignment(Pos.CENTER);
        StackPane tryStack = new StackPane(sepVBox,tryHBox);

        //Tools (upper half)
        HBox substancesAndTools = new HBox(tubeGroup1, beckerVBox,tubeGroup2);
        substancesAndTools.setAlignment(Pos.TOP_CENTER);
        VBox upperHalf = new VBox(substancesAndTools,tryStack);

        //Graph
        HBox graphHBox = displayGraph();
        graphHBox.setAlignment(Pos.CENTER_RIGHT);
        graphHBox.setPadding(new Insets(0));
        Insets old = graphHBox.getPadding();
        int bottomPadding = 10;
        int leftPadding = 70;
        graphHBox.setPadding(new Insets(old.getTop(), old.getRight() , bottomPadding, leftPadding));


        //Info Labels
        VBox infoVBox = displayInfo();

        //Ressource Buttons
        VBox hintVBox = new VBox(hintButton,menuButton);
        hintVBox.setAlignment(Pos.CENTER_RIGHT);


        //Information layout (lower half)
        HBox graphInfo = new HBox(50,graphHBox,infoVBox);
        graphInfo.setAlignment(Pos.CENTER);
        HBox infoHBox = new HBox(20, graphInfo,hintVBox);

        GridPane grid = new GridPane();
        grid.add(upperHalf,0,0);
        grid.add(infoHBox,0,1);
        grid.setVgap(10);


        StackPane pane = new StackPane(backgroundView,grid);
        gameScene = new Scene(pane,1366,768);
        return gameScene;
    }

    //Interface of information boxes and labels
    public VBox displayInfo() {
        // Layout
        return new VBox(20, formulaLabel, energyTypeLabel, factLabel);
    }

    //Tube 1 appearance
    public ImageView displayTube1() {
        tube1ImageView.setFitHeight(320);
        tube1ImageView.setPreserveRatio(true);
        return tube1ImageView;
    }

    //Tube 2 appearance
    public ImageView displayTube2() {
        tube2ImageView.setFitHeight(320);
        tube2ImageView.setPreserveRatio(true);
        tube2ImageView.setScaleX(-1);
        return tube2ImageView;
    }

    public HBox displayGraph() {
        // Create the x and y axes
        final NumberAxis xAxis = new NumberAxis(0, 4, 0.5);
        this.yAxis = new NumberAxis(-250, 250, 50);
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Energy (kJ/mol)");
        xAxis.lookup(".axis-label").setStyle("-fx-text-fill: #e8ceb0;");
        yAxis.lookup(".axis-label").setStyle("-fx-text-fill: #e8ceb0;");

        // Axis and title CSS
        String axisStyle = "-fx-tick-label-fill: #e8ceb0;" +
                "-fx-font-size: 14px;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-weight: bold;";

        xAxis.setStyle(axisStyle);
        yAxis.setStyle(axisStyle);
        lineChart = new LineChart<>(xAxis, yAxis);

        // Chart title CSS
        lineChart.lookup(".chart-title").setStyle("-fx-text-fill: #e8ceb0;");

        // Remove legend
        lineChart.setLegendVisible(false);

        // Graph grid lines CSS
        lineChart.setStyle("-fx-background-color: transparent;");
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        lineChart.lookup(".chart-vertical-grid-lines").setStyle("-fx-stroke: #555555;");
        lineChart.lookup(".chart-horizontal-grid-lines").setStyle("-fx-stroke: #555555;");

        // Add the data series
        series = new XYChart.Series<>();
        series.setName("Energy");
        lineChart.getData().add(series);

        // Adjust size of graph
        lineChart.setPrefSize(600, 800);

        // Wrap in HBox
        HBox chartHBox = new HBox(lineChart);
        chartHBox.setPrefWidth(600);
        chartHBox.setPrefHeight(800);

        return chartHBox;
    }

    public void clearData() {
        // Clear previous data
        series.getData().clear();
    }

    public void addDataPoint(double x, double y) {
        series.getData().add(new XYChart.Data<>(x, y));
    }

    public void setYAxisRange(double lower, double upper, double tick) {
        yAxis.setLowerBound(lower);
        yAxis.setUpperBound(upper);
        yAxis.setTickUnit(tick);
    }

    //Method to display image depending on outcome of the users' progress
    public void displayImage(Scene scene, String imageUrl,int width,int time,boolean flag) {
        // Image view
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);

        //Center the image
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width);
        imageView.setX((scene.getWidth() - imageView.getFitWidth()) / 2);
        imageView.setY((scene.getHeight() - imageView.getFitHeight()) / 2);

        //Container
        StackPane overlayPane = new StackPane();
        overlayPane.setStyle("-fx-background-color: rgba(0,0,0,0.5);"); // Semi-transparent background
        overlayPane.getChildren().add(imageView);

        //Add to scene
        if (scene.getRoot() instanceof Pane) {
            Pane root = (Pane) scene.getRoot();
            root.getChildren().add(overlayPane);

            //Remove image after time ends
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

    public ImageView getBeckerImageView(){
        //Becker image
        becker.setFitWidth(200);
        becker.setPreserveRatio(true);
        return becker;
    }

    public void switchScenes(Scene scene) {
        // Switch to the specified scene
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
