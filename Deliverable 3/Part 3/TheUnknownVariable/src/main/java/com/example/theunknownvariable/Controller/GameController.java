package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.UI.Clues;
import com.example.theunknownvariable.UI.MainPage;
import com.example.theunknownvariable.UI.ChemUI;
import com.example.theunknownvariable.UI.HomePage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class GameController extends Application {
    @Override

    public void start(Stage stage) throws IOException {

        //Display the first scene of the game: The home page
        HomePage homePage = new HomePage(stage);
        Scene scene = homePage.displayHomePage();
        stage.setTitle("The Unknown Variable");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}