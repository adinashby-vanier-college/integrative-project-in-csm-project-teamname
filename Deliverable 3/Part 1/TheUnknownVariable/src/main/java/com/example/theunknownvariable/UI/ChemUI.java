package com.example.theunknownvariable.UI;

import com.example.theunknownvariable.Controller.EnthalpyGraph;
import Model.Tubes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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

    //Tubes
    private Tubes tube1;
    private Tubes tube2;

    //Labels
    private Label formulaLabel;
    private Label energyTypeLabel;
    private Label factLabel;

    //Graph
    private EnthalpyGraph graph;

    //Stage
    private Stage stage;

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
        Image background = new Image("hintBackgroundg4.png");
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
        eventHandling();

        //Background png
        Image background = new Image("background.png");
        ImageView backgroundView = new ImageView(background);

        //Becker image
        Image initialBeckerImage = new Image("beckerFINAL.png");
        ImageView initialBecker = new ImageView(initialBeckerImage);
        initialBecker.setFitWidth(200);
        initialBecker.setPreserveRatio(true);

        //Separator
        Image separatorImage = new Image("separator.png");
        ImageView separator = new ImageView(separatorImage);
        separator.setFitWidth(1500);
        VBox sepVBox = new VBox(separator);
        sepVBox.setAlignment(Pos.CENTER);

        //----------Layout-------------

        //Becker and Mix
        VBox becker = new VBox(30,mixButton,initialBecker);
        becker.setAlignment(Pos.CENTER);

        //Tube and buttons RIGHT
        VBox buttonGroup1 = new VBox(30,sub1Button,sub2Button,sub3Button);
        buttonGroup1.setAlignment(Pos.CENTER_RIGHT);

        HBox tubeGroup1 = new HBox(30,buttonGroup1,displayTube(10));
        tubeGroup1.setAlignment(Pos.CENTER_RIGHT);

        //Tube and buttons LEFT
        VBox buttonGroup2 = new VBox(30,sub4Button,sub5Button,sub6Button);
        buttonGroup2.setAlignment(Pos.CENTER_LEFT);

        HBox tubeGroup2 = new HBox(30,displayTube(-10),buttonGroup2);
        tubeGroup2.setAlignment(Pos.CENTER_LEFT);

        //Stack try button and separator
        HBox tryHBox = new HBox(tryButton);
        tryHBox.setAlignment(Pos.CENTER);
        StackPane tryStack = new StackPane(sepVBox,tryHBox);
        //Tools (upper half)
        HBox substancesAndTools = new HBox(80,tubeGroup1,becker,tubeGroup2);
        substancesAndTools.setAlignment(Pos.TOP_CENTER);
        VBox upperHalf = new VBox(substancesAndTools,tryStack);

        //Graph
        graph = new EnthalpyGraph();
        HBox graphHBox = graph.displayGraph();
        graphHBox.setAlignment(Pos.CENTER_RIGHT);
        graphHBox.setPadding(new Insets(50));

        //Info Labels
        VBox infoVBox = displayInfo();

        //Ressource Buttons
        VBox hintVBox = new VBox(hintButton,menuButton);
        hintVBox.setAlignment(Pos.CENTER_RIGHT);

        //TryButton

        //Information layout (lower half)
        HBox graphInfo = new HBox(100,graphHBox,infoVBox);
        graphInfo.setAlignment(Pos.CENTER);
        HBox infoHBox = new HBox(200, graphInfo,hintVBox);

        GridPane grid = new GridPane();
        grid.add(upperHalf,0,0);
        grid.add(infoHBox,0,1);
        grid.setVgap(10);

        StackPane pane = new StackPane(backgroundView,grid);
        return new Scene(pane,1366,768);
    }



    public VBox displayInfo(){
        //Labels text
        formulaLabel = new Label("Formula");
        energyTypeLabel = new Label("Energy Type");
        factLabel = new Label("Fact");

        // Style for all labels
        String labelStyle = "-fx-background-color: #c2b19c;" + // Rectangle background
                "-fx-background-radius: 10;" +   // Rounded corners
                "-fx-padding: 10 40;" +          // Padding inside the rectangle
                "-fx-font-size: 24px;" +         // Text size
                "-fx-font-family: 'Courier New';" + // Mystery font
                "-fx-text-fill: #2e2e2e;" +      // Light text color
                "-fx-font-weight: bold;" +       // Bold text
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0, 0, 5);"+
                "-fx-pref-width: 400px;"; // Subtle shadow


        // Apply style to each label
        formulaLabel.setStyle(labelStyle);
        energyTypeLabel.setStyle(labelStyle);
        factLabel.setStyle(labelStyle);

        //Layout
        VBox infoVBox = new VBox(20,formulaLabel,energyTypeLabel,factLabel);
        return infoVBox;
    }

    public VBox displayTube(int subNb){
        ImageView image = new ImageView();
        VBox vbox = new VBox();
        if (subNb==10){
            Image initialTubeImage = new Image("tubeFINAL.png");
            ImageView initialTube1View = new ImageView(initialTubeImage);
            initialTube1View.setFitHeight(320);
            initialTube1View.setPreserveRatio(true);
            VBox initialTube1 = new VBox(initialTube1View);
            initialTube1.setAlignment(Pos.BOTTOM_CENTER);
            return initialTube1;
        }
        if (subNb==-10){
            Image initialTubeImage = new Image("tubeFINAL.png");
            ImageView initialTube1View = new ImageView(initialTubeImage);
            initialTube1View.setScaleX(-1);
            initialTube1View.setFitHeight(320);
            initialTube1View.setPreserveRatio(true);
            VBox initialTube1 = new VBox(initialTube1View);
            initialTube1.setAlignment(Pos.BOTTOM_CENTER);
            return initialTube1;
        }
        else return vbox;
    }
    public void eventHandling(){
        //Switch to main page
        menuButton.setOnAction(event->{
            MainPage mainPage = new MainPage(stage);
            Scene scene = mainPage.displayMainPage();
            switchScenes(scene);
        });
        //Switch to main page
        hintButton.setOnAction(event->{
            Stage hintStage = new Stage();
            Scene scene = displayHint();
            hintStage.setScene(scene);
            hintStage.centerOnScreen();
            hintStage.showAndWait();
        });
    }

    public void switchScenes(Scene scene) {
        // Switch to the specified scene
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
