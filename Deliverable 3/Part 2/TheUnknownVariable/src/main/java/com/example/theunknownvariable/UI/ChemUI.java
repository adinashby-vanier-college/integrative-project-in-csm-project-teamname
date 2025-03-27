package com.example.theunknownvariable.UI;


import com.example.theunknownvariable.Controller.EnthalpyGraph;

import com.example.theunknownvariable.Controller.GameStateManager;
import com.example.theunknownvariable.Controller.ReactionHandler;
import com.example.theunknownvariable.Model.Substance;
import com.example.theunknownvariable.Model.Tubes;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import java.io.File;
import java.util.Random;

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

    //Tracking user's progress
    private Label rightAnswers;
    private Label wrongAnswers;
    private int rightAnsNb=0;
    private int wrongAnsNb=0;
    private Clues clues;

    //Tubes and becker
    private Tubes tube1;
    private Tubes tube2;
    private ImageView tube1ImageView;
    private ImageView tube2ImageView;
    private ImageView becker;

    //Graph
    private EnthalpyGraph graph;
    private ReactionHandler reactionHandler;

    //Tube number (left and right)
    private int tube1nb;
    private int tube2nb;

    //Button groups
    private Button selectedButtonGroup1 = null;
    private Button selectedButtonGroup2 = null;

    //Stage and scene
    private Stage stage;
    private Scene gameScene;

    public ChemUI(Stage stage){
        this.stage = stage;
    }

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
    public Scene displayInstructions(){
        //Background png
        Image background = new Image("instructionsBackground.png");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(768);
        backgroundView.setPreserveRatio(true);

        //Button
        instructionsButton = buttonCustomization("understand");
        StackPane understandStack = new StackPane(instructionsButton);
        understandStack.setAlignment(Pos.BOTTOM_CENTER);

        //Switch to game 3
        instructionsButton.setOnAction(event->{
            Scene scene = displayGame();
            switchScenes(scene);
        });

        //Layout
        StackPane stack = new StackPane(backgroundView,understandStack);

        return new Scene(stack,1366,768);

    }

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

    public Scene displayGame(){

        //Buttons
        sub1Button = buttonCustomization("luminol");
        sub2Button = buttonCustomization("aluminium");
        sub3Button = buttonCustomization("sc");
        sub4Button = buttonCustomization("sn");
        sub5Button = buttonCustomization("hp");
        sub6Button = buttonCustomization("io");
        mixButton = buttonCustomization("mix");
        hintButton = buttonCustomization("hint");
        menuButton = buttonCustomization("menu");
        tryButton = buttonCustomization("try");

        //Background png
        Image background = new Image("background.png");
        ImageView backgroundView = new ImageView(background);

        //Tracking user's progress UI
        ImageView rightAnsView = new ImageView(new Image("rightAnswers.png"));
        rightAnsView.setFitHeight(70);
        rightAnsView.setPreserveRatio(true);
        ImageView wrongAnsView = new ImageView(new Image("wrongAnswers.png"));
        wrongAnsView.setFitHeight(76);
        wrongAnsView.setPreserveRatio(true);
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

        // Initialize graph
        graph = new EnthalpyGraph();

        //Initialize clues scene
        clues = new Clues(stage);

        // Initialize reaction handler and pass the graph
        reactionHandler = new ReactionHandler(graph);

        // Create ReactionHandler and set the becker
        becker = getBeckerImageView();
        reactionHandler = new ReactionHandler(graph);
        reactionHandler.setBecker(becker);

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
        graph = new EnthalpyGraph();
        HBox graphHBox = graph.displayGraph(graph.getReactionNb());
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

        //TryButton

        //Information layout (lower half)
        HBox graphInfo = new HBox(50,graphHBox,infoVBox);
        graphInfo.setAlignment(Pos.CENTER);
        HBox infoHBox = new HBox(20, graphInfo,hintVBox);

        GridPane grid = new GridPane();
        grid.add(upperHalf,0,0);
        grid.add(infoHBox,0,1);
        grid.setVgap(10);

        //Handling events
        eventHandling();

        StackPane pane = new StackPane(backgroundView,grid);
        gameScene = new Scene(pane,1366,768);
        return gameScene;
    }



    public VBox displayInfo() {
        // Get existing labels from ReactionHandler (instead of creating new ones)
        Label formulaLabel = reactionHandler.getFormulaLabel();
        Label energyTypeLabel = reactionHandler.getEnergyTypeLabel();
        Label factLabel = reactionHandler.getFactLabel();

        // Layout
        return new VBox(20, formulaLabel, energyTypeLabel, factLabel);
    }



    public ImageView displayTube1() {
        tube1ImageView = new ImageView(new Image("tubeFINAL.png"));
        tube1ImageView.setFitHeight(320);
        tube1ImageView.setPreserveRatio(true);
        return tube1ImageView;
    }

    public ImageView displayTube2() {
        tube2ImageView = new ImageView(new Image("tubeFINAL.png"));
        tube2ImageView.setFitHeight(320);
        tube2ImageView.setPreserveRatio(true);
        tube2ImageView.setScaleX(-1);
        return tube2ImageView;
    }

    public void eventHandling() {
        // Switch to main page
        menuButton.setOnAction(event -> {
            MainPage mainPage = new MainPage(stage);
            Scene scene = mainPage.displayMainPage();
            switchScenes(scene);
        });

        // Hint Button
        hintButton.setOnAction(event -> {
            Stage hintStage = new Stage();
            Scene scene = displayHint();
            hintStage.setScene(scene);
            hintStage.centerOnScreen();
            hintStage.showAndWait();
        });

        // Group 1 Buttons
        handleButtonClick(sub1Button, 1, 0, true);
        handleButtonClick(sub2Button, 2, 0, true);
        handleButtonClick(sub3Button, 3, 0, true);

        // Group 2 Buttons
        handleButtonClick(sub4Button, 0, 1, false);
        handleButtonClick(sub5Button, 0, 2, false);
        handleButtonClick(sub6Button, 0, 3, false);

        // Mix Button
        mixButton.setOnAction(event -> {
            reactionHandler.mix(); // This updates reactionNb
            graph.plotReaction(reactionHandler.getReactionNb());
        });

        // Try Button
        tryButton.setOnAction(event ->{
            reactionHandler.mix(); // This updates reactionNb
            graph.plotReaction(reactionHandler.getReactionNb());
            if(reactionHandler.checkUserAnswer()==0){
                rightAnsNb+=1;
                rightAnswers.setText(rightAnsNb+"/3");
                displayImage(gameScene,"rightScenario.png",300,2,false);
            }
            else if(reactionHandler.checkUserAnswer()==2){
                displayImage(gameScene, "wrongScenario.png",300,2,false);
                wrongAnsNb+=1;
                wrongAnswers.setText(wrongAnsNb+"/3");
            }
            if (rightAnsNb==3){
                GameStateManager.getInstance().unlockClue3();
                displayImage(gameScene,"clueScene.png",800,5,true);
            }
            if (wrongAnsNb==3){
                displayImage(gameScene,"gameOver.png",800,5,true);
            }
        });
    }

    public void displayImage(Scene scene, String imageUrl,int width,int time,boolean flag) {
        // Create image view
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);

        // Center the image
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width);
        imageView.setX((scene.getWidth() - imageView.getFitWidth()) / 2);
        imageView.setY((scene.getHeight() - imageView.getFitHeight()) / 2);

        // Create a container that will overlay everything
        StackPane overlayPane = new StackPane();
        overlayPane.setStyle("-fx-background-color: rgba(0,0,0,0.5);"); // Semi-transparent background
        overlayPane.getChildren().add(imageView);

        // Add to scene
        if (scene.getRoot() instanceof Pane) {
            Pane root = (Pane) scene.getRoot();
            root.getChildren().add(overlayPane);

            // Set up removal after 3 seconds
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

    private void handleButtonClick(Button button, int newTube1, int newTube2, boolean isGroup1) {
        button.setOnAction(event -> {
            if (isGroup1) {
                // Reset previous selection in Group 1
                if (selectedButtonGroup1 != null) {
                    selectedButtonGroup1.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                }
                selectedButtonGroup1 = button;
            } else {
                // Reset previous selection in Group 2
                if (selectedButtonGroup2 != null) {
                    selectedButtonGroup2.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                }
                selectedButtonGroup2 = button;
            }

            // Apply highlight effect to the selected button
            button.setStyle("-fx-effect: dropshadow(gaussian, rgba(245, 245, 220, 0.8), 10, 0.5, 0, 0);" +
                    "-fx-background-color: transparent; -fx-border-color: transparent;");

            // Update tube images
            updateTubeImage(newTube1, newTube2);

            // Update the selected substance in the Substance instance
            if (isGroup1) {
                Substance.getInstance().setSubstanceNb1(newTube1);
            } else {
                Substance.getInstance().setSubstanceNb2(newTube2);
            }
        });
    }


    private void updateTubeImage(int newTube1, int newTube2) {
        if (newTube1 != 0) {
            tube1nb = newTube1;
            tube1ImageView.setImage(new Image("tubeFINAL" + tube1nb + ".png"));
        }
        if (newTube2 != 0) {
            tube2nb = newTube2;
            tube2ImageView.setImage(new Image("tubeFINAL" + (tube2nb+3) + ".png"));
        }
    }

    public ImageView getBeckerImageView(){
        //Becker image
        Image initialBeckerImage = new Image("beckerFINAL.png");
        becker = new ImageView(initialBeckerImage);
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
