package com.example.theunknownvariable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProjectileMotion extends Application {


    @Override
    public void start(Stage primaryStage) {
        UserInterface view = new UserInterface(primaryStage); // Use the StackPane-based view
        Scene scene = new Scene(view, 1366, 768);
        primaryStage.setTitle("Kinematics Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
