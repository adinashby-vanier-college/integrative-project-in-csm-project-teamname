package com.example.theunknownvariable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TutorialClass extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        LensGameMain lensGameMain = new LensGameMain(stage);
        Scene scene = lensGameMain.getLensGameScene();
        stage.setScene(scene);
        stage.show();
    }
    //THIS IS THE CLASS YOU WROTE AND WANT TO ADD
    public static void main(String[] args) {
        launch();
    }



}
