package com.example.theunknownvariable.UI;

import com.example.theunknownvariable.Controller.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//This class contains the interface of the main page, where games and clues are found
public class MainPage {

    //Buttons
    private Button g1Button;
    private Button g2Button;
    private Button g3Button;
    private Button g4Button;
    private Button menuButton;
    private Button cluesButton;

    //Stage
    private Stage stage;

    //Constructor
    public MainPage(Stage stage){
        this.stage = stage;
    }

    //Scene of the interface
    public Scene displayMainPage(){
        //Buttons
        g1Button = buttonCustomization("g1");
        g2Button = buttonCustomization("g2");
        g3Button = buttonCustomization("g3");
        g4Button = buttonCustomization("g4");
        menuButton = buttonCustomization("menu");
        cluesButton = buttonCustomization("clues");

        //Event handling
        eventHandling();

        //Background
        Image background = new Image("mainPageBackground.png");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitWidth(1366);
        backgroundView.setPreserveRatio(true);

        //---------Layout----------
        //Tools Buttons
        VBox menu = new VBox(menuButton);
        menu.setAlignment(Pos.CENTER_RIGHT);
        VBox clues = new VBox(cluesButton);
        clues.setAlignment(Pos.CENTER_RIGHT);

        GridPane grid = new GridPane();
        grid.add(g1Button,1,1);
        grid.add(g2Button,2,3);
        grid.add(g3Button,3,2);
        grid.add(g4Button,0,2);
        grid.add(menu,3,3);
        grid.add(clues,0,3);

        StackPane pane = new StackPane(backgroundView,grid);
        return new Scene(pane,1366,768);
    }

    //Handles the intercation with interface's components
    public void eventHandling(){
        /*
        For each game, check if it game is unlocked (never completed before) and proceed
        switch to the game scene
         */
        //Switch to game 1
        g1Button.setOnAction(event->{
            if(!GameStateManager.getInstance().isGame1Locked()){
                LensGameMain lensGameMain = new LensGameMain(stage);
                Scene scene = lensGameMain.getLensGameInstructionsScene();
                switchScenes(scene);
            }

        });
        //Switch to game 2
        g2Button.setOnAction(event->{
            if(!GameStateManager.getInstance().isGame2Locked()) {
                UserInterface2 projectileMotion = new UserInterface2(stage);
                Scene scene = projectileMotion.displayInstructions();
                switchScenes(scene);
            }
        });
        //Switch to game 3
        g3Button.setOnAction(event->{
            if(!GameStateManager.getInstance().isGame3Locked()) {
                ChemUI view = new ChemUI(stage);
                EnthalpyGraph graphController = new EnthalpyGraph(view);
                ReactionHandler reactionController = new ReactionHandler(view,graphController);

                ChemController controller = new ChemController(view,reactionController,graphController,stage);

                view.initialize();

                Scene scene = view.displayInstructions();
                switchScenes(scene);
            }
        });
        //Switch to game 4
        g4Button.setOnAction(event->{
            if(!GameStateManager.getInstance().isGame4Locked()) {
                MathGameUI mathGame = new MathGameUI(stage);
                Scene scene = mathGame.displayMathGame(stage);
                switchScenes(scene);
            }
        });

        //Switch to clue scene
        cluesButton.setOnAction(event->{
            Clues cluesScene = new Clues(stage);
            Scene scene = cluesScene.displayClues();
            switchScenes(scene);
        });

        //Switch to home
        menuButton.setOnAction(event->{
            HomePage homePage = new HomePage(stage);
            Scene scene = homePage.displayHomePage();
            switchScenes(scene);
        });
    }

    //Method that switch scenes
    public void switchScenes(Scene scene) {
        // Switch to the specified scene
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    //Customize buttons' style and effects
    public Button buttonCustomization(String button){
        Button empty = new Button();
        switch (button) {
            case "g1" -> {
                //Game 1 Button
                Image g1Image = new Image("gm1.png");
                ImageView g1 = new ImageView(g1Image);
                g1.setFitWidth(320);
                g1.setPreserveRatio(true);
                g1Button = new Button("", g1);
                g1Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                g1Button.setOnMouseEntered(e -> g1.setOpacity(0.8));
                g1Button.setOnMouseExited(e -> g1.setOpacity(1.0));
                return g1Button;
            }
            case "g2" -> {
                //Game 2 Button
                Image g2Image = new Image("gm2.png");
                ImageView g2 = new ImageView(g2Image);
                g2.setFitWidth(300);
                g2.setPreserveRatio(true);
                g2Button = new Button("", g2);
                g2Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                g2Button.setOnMouseEntered(e -> g2.setOpacity(0.8));
                g2Button.setOnMouseExited(e -> g2.setOpacity(1.0));
                return g2Button;
            }
            case "g3" -> {
                //Game 3 Button
                Image g3Image = new Image("gm3.png");
                ImageView g3 = new ImageView(g3Image);
                g3.setFitWidth(300);
                g3.setPreserveRatio(true);
                g3Button = new Button("", g3);
                g3Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                g3Button.setOnMouseEntered(e -> g3.setOpacity(0.8));
                g3Button.setOnMouseExited(e -> g3.setOpacity(1.0));
                return g3Button;
            }
            case "g4" -> {
                //Game 4 Button
                Image g4Image = new Image("gm4.png");
                ImageView g4 = new ImageView(g4Image);
                g4.setFitWidth(300);
                g4.setPreserveRatio(true);
                g4Button = new Button("", g4);
                g4Button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                g4Button.setOnMouseEntered(e -> g4.setOpacity(0.8));
                g4Button.setOnMouseExited(e -> g4.setOpacity(1.0));
                return g4Button;
            }
            case "menu" -> {
                //Home menu button
                Image menuImage = new Image("menu2.png");
                ImageView menu = new ImageView(menuImage);
                menu.setFitWidth(140);
                menu.setPreserveRatio(true);
                menuButton = new Button("", menu);
                menuButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                menuButton.setOnMouseEntered(e -> menu.setOpacity(0.8));
                menuButton.setOnMouseExited(e -> menu.setOpacity(1.0));
                return menuButton;
            }
            case "clues" -> {
                //Clues button
                Image cluesImage = new Image("clues.png");
                ImageView clues = new ImageView(cluesImage);
                clues.setFitWidth(250);
                clues.setPreserveRatio(true);
                cluesButton = new Button("", clues);
                cluesButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                cluesButton.setOnMouseEntered(e -> clues.setOpacity(0.8));
                cluesButton.setOnMouseExited(e -> clues.setOpacity(1.0));
                return cluesButton;
            }
            default -> {
                return empty;
            }
        }
    }

}
