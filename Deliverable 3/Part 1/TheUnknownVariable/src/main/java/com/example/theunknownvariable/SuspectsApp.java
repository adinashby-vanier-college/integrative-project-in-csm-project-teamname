package com.example.theunknownvariable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SuspectsApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        SuspectUI viewList = new SuspectUI(primaryStage); // Use the StackPane-based view
        Scene scene = new Scene(viewList, 1366, 768);
        primaryStage.setTitle("Suspects");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}