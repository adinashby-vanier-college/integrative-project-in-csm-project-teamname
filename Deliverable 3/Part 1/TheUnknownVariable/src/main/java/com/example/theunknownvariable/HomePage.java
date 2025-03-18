package com.example.theunknownvariable;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {

    //Buttons
    private Button menuButton;
    private Button suspectsButton;
    private Button quitButton;
    private Button accuseButton;

    //Stage
    private Stage stage;

    public HomePage(Stage stage){
        this.stage = stage;
    }
    public Scene displayHomePage(){
        //Background
        Image background = new Image("homeBackground.jpg");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(768);
        backgroundView.setPreserveRatio(true);

        //Buttons
        menuButton = buttonCustomization("play");
        suspectsButton = buttonCustomization("suspects");
        quitButton = buttonCustomization("quit");
        accuseButton = buttonCustomization("accuse");
        eventHandling();

        //--------Layout---------
        //Buttons
        VBox buttonVBox = new VBox(20,menuButton,suspectsButton,quitButton);
        buttonVBox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox accuseVBox = new VBox(accuseButton);
        accuseVBox.setAlignment(Pos.CENTER_RIGHT);
        HBox buttonsHBox = new HBox(270, buttonVBox,accuseVBox);
        buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);

        //Labels and buttons
        StackPane buttonsPosition = new StackPane(buttonsHBox);
        buttonsPosition.setAlignment(Pos.CENTER);

        StackPane pane = new StackPane(backgroundView,buttonsPosition);
        return new Scene(pane,1366,768);
    }
    public void eventHandling(){
        //Switch to game 3
        menuButton.setOnAction(event->{
            MainPage mainPage = new MainPage(stage);
            Scene scene = mainPage.displayMainPage();
            switchScenes(scene);
        });
        //Switch to game 3
        suspectsButton.setOnAction(event->{
            SuspectUI viewList = new SuspectUI(stage); // Use the StackPane-based view
            Scene scene = new Scene(viewList, 1366, 768);
            switchScenes(scene);
        });
        quitButton.setOnAction(event->{
            stage.close();
        });
    }

    public void displaySuspects(){

    }

    public void switchScenes(Scene scene) {
        // Switch to the specified scene
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public Button buttonCustomization(String button) {
        //----------Buttons------------
        Button empty = new Button();
        switch (button) {
            case "play" -> {
                //Button 1: Play
                Image playImage = new Image("playButton.jpg");
                ImageView play = new ImageView(playImage);
                play.setFitWidth(300);
                play.setPreserveRatio(true);
                menuButton = new Button("", play);
                menuButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                menuButton.setOnMouseEntered(e -> play.setOpacity(0.8));
                menuButton.setOnMouseExited(e -> play.setOpacity(1.0));
                return menuButton;
            }
            case "suspects" -> {
                //Button 2: Aluminium
                Image suspectsImage = new Image("suspectsButton.jpg");
                ImageView suspects = new ImageView(suspectsImage);
                suspects.setFitWidth(300);
                suspects.setPreserveRatio(true);
                suspectsButton = new Button("", suspects);
                suspectsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                suspectsButton.setOnMouseEntered(e -> suspects.setOpacity(0.8));
                suspectsButton.setOnMouseExited(e -> suspects.setOpacity(1.0));
                return suspectsButton;
            }
            case "quit" -> {
                //Button 3: Sodium Chloride
                Image quitImage = new Image("quitButton.jpg");
                ImageView quit = new ImageView(quitImage);
                quit.setFitWidth(300);
                quit.setPreserveRatio(true);
                quitButton = new Button("", quit);
                quitButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                quitButton.setOnMouseEntered(e -> quit.setOpacity(0.8));
                quitButton.setOnMouseExited(e -> quit.setOpacity(1.0));
                return quitButton;
            }
            case "accuse" -> {
                //Button 3: Sodium Chloride
                Image accuseImage = new Image("file:accuse.png");
                ImageView accuse = new ImageView(accuseImage);
                accuse.setFitWidth(250);
                accuse.setPreserveRatio(true);
                accuseButton = new Button("", accuse);
                accuseButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                accuseButton.setOnMouseEntered(e -> accuse.setOpacity(0.8));
                accuseButton.setOnMouseExited(e -> accuse.setOpacity(1.0));
                return accuseButton;
            }
            default -> {
                return empty;
            }
        }

    }



}
