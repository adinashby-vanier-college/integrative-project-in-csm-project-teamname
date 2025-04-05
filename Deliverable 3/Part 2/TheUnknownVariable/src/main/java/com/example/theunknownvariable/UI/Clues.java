package com.example.theunknownvariable.UI;

import com.example.theunknownvariable.Controller.GameStateManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Clues {

    //Button
    private Button menuButton;

    //Clues label
    private Label game1Clue;
    private Label game2Clue;
    private Label game3Clue;
    private Label game4Clue;


    private Stage stage;

    public Clues(Stage stage) {
        this.stage = stage;

        game1Clue = new Label("Clue starts here");
        game2Clue = new Label("Clue starts here");
        game3Clue = new Label("Clue starts here");
        game4Clue = new Label("Clue starts here");

        String style = "-fx-alignment: center;-fx-font-family: 'Georgia';" +
                "-fx-font-weight: bold;-fx-font-size: 23;";

        game1Clue.setStyle(style);
        game2Clue.setStyle(style);
        game3Clue.setStyle(style);
        game4Clue.setStyle(style);
        // Ensure the text inside the label is centered
        game1Clue.setTextAlignment(TextAlignment.CENTER);
        game1Clue.setAlignment(Pos.CENTER);

        game2Clue.setTextAlignment(TextAlignment.CENTER);
        game2Clue.setAlignment(Pos.CENTER);

        game3Clue.setTextAlignment(TextAlignment.CENTER);
        game3Clue.setAlignment(Pos.CENTER);

        game4Clue.setTextAlignment(TextAlignment.CENTER);
        game4Clue.setAlignment(Pos.CENTER);


    }

    public Scene displayClues() {
        // Buttons
        menuButton = getMenuButton();
        eventHandling();

        // Background
        Image background = new Image("vintageBackground.jpg");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(768);
        backgroundView.setPreserveRatio(true);

        // -------- Layout --------
        // Button
        VBox menuVBox = new VBox(menuButton);
        menuVBox.setAlignment(Pos.BOTTOM_RIGHT);

        // Labels
        if (GameStateManager.getInstance().isClue1Unlocked()) {
            game1Clue.setText("Maria's Clue");
        }
        if (GameStateManager.getInstance().isClue2Unlocked()) {
            game2Clue.setText("Meghry's Clue");
        }
        if (GameStateManager.getInstance().isClue3Unlocked()) {
            game3Clue.setText("Based on\nthe 3 solutions,\nthe killer\nworks in a\nfield" +
                    " related\nto tech,\nguns, or\ninvestigation\nof crime.");
        }
        if (GameStateManager.getInstance().isClue4Unlocked()) {
            game4Clue.setText("Sara's clue");
        }
        VBox gameLabel1 = new VBox(game1Clue);
        gameLabel1.setAlignment(Pos.CENTER);
        VBox gameLabel2 = new VBox(game2Clue);
        gameLabel2.setAlignment(Pos.CENTER);
        VBox gameLabel3 = new VBox(game3Clue);
        gameLabel3.setAlignment(Pos.CENTER);
        VBox gameLabel4 = new VBox(game4Clue);
        gameLabel4.setAlignment(Pos.CENTER);

        // HBox for clues
        HBox clueLabelsHBox = new HBox(108, gameLabel1, gameLabel2, gameLabel3, gameLabel4);
        clueLabelsHBox.setAlignment(Pos.CENTER);

        // VBox wrapper to shift it lower
        VBox clueContainer = new VBox(clueLabelsHBox);
        clueContainer.setAlignment(Pos.CENTER);
        clueContainer.setTranslateY(100); // Moves it 50 pixels down

        StackPane menuPosition = new StackPane(menuButton);
        menuPosition.setAlignment(Pos.BOTTOM_RIGHT);

        StackPane pane = new StackPane(backgroundView, clueContainer, menuPosition);
        return new Scene(pane, 1366, 768);
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
