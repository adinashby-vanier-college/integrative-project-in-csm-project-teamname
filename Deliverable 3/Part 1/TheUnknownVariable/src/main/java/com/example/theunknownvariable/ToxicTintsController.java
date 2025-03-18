package com.example.theunknownvariable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ToxicTintsController extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ChemUI userInterface = new ChemUI(stage);
        MainPage mainpage = new MainPage(stage);
        Clues cluesPage = new Clues(stage);
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