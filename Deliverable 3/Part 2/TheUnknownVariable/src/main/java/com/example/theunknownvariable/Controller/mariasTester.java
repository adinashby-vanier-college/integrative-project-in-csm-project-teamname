package com.example.theunknownvariable.Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mariasTester extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        LensGameMain lensGameMain = new LensGameMain(stage);
        Scene scene = lensGameMain.getLensGameScene();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



}
