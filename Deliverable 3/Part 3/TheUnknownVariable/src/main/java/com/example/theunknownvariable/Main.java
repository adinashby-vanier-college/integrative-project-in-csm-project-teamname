package com.example.theunknownvariable;

import com.example.theunknownvariable.UI.HomePage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class Main extends Application {
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