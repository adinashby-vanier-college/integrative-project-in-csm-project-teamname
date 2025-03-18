package com.example.theunknownvariable;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Clues {

    private Button menuButton;

    private Stage stage;

    public Clues(Stage stage){
        this.stage = stage;
    }

    public Scene displayClues(){
        //Buttons
        menuButton = getMenuButton();
        eventHandling();
        //Background
        Image background = new Image("vintageBackground.jpg");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(768);
        backgroundView.setPreserveRatio(true);

        //--------Layout---------
        //Button
        VBox menuVBox = new VBox(menuButton);
        menuVBox.setAlignment(Pos.BOTTOM_RIGHT);

        //Labels and buttons
        StackPane menuPosition = new StackPane(menuButton);
        menuPosition.setAlignment(Pos.BOTTOM_RIGHT);

        StackPane pane = new StackPane(backgroundView,menuPosition);
        return new Scene(pane,1366,768);
    }
    public Button getMenuButton(){
        //Home menu button
        Image menuImage = new Image("menu3.png");
        ImageView menu = new ImageView(menuImage);
        menu.setFitWidth(100);
        menu.setPreserveRatio(true);
        menuButton = new Button("", menu);
        menuButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        menuButton.setOnMouseEntered(e -> menu.setOpacity(0.8));
        menuButton.setOnMouseExited(e -> menu.setOpacity(1.0));
        return menuButton;
    }

    public void eventHandling(){
        //Switch to game 3
        menuButton.setOnAction(event->{
            MainPage mainPage = new MainPage(stage);
            Scene scene = mainPage.displayMainPage();
            switchScenes(scene);
        });
    }

    public void switchScenes(Scene scene) {
        // Switch to the specified scene
        stage.setScene(scene);
        stage.centerOnScreen();
    }

}
