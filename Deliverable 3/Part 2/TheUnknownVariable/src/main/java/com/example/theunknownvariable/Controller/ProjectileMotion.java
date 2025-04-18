package com.example.theunknownvariable.Controller;
import com.example.theunknownvariable.UI.UserInterface2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProjectileMotion extends Application {


    @Override
    public void start(Stage primaryStage) {
        UserInterface2 view = new UserInterface2(primaryStage); // Use the StackPane-based view
        Scene scene = new Scene(view, 1366, 768);
        primaryStage.setTitle("Kinematics Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
