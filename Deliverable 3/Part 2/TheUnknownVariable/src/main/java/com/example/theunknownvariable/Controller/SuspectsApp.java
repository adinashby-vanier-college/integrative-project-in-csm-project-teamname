package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.UI.SuspectUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SuspectsApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        SuspectUI viewList = new SuspectUI(primaryStage);
        Scene scene = new Scene(viewList, 1366, 768);
        primaryStage.setTitle("Suspects");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}