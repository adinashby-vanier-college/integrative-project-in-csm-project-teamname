package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.UI.AccuseUI;
import com.example.theunknownvariable.UI.HomePage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mariasTester extends Application {
    //    @Override
//    public void start(Stage stage) throws Exception {
//        LensGameController lensGameMain = new LensGameController(stage);
//        Scene scene = lensGameMain.getLensGameScene();
//        stage.setScene(scene);
//        stage.show();
//    }
    @Override
    public void start(Stage stage) throws Exception {
        AccuseUI viewList = new AccuseUI(stage);
        Scene scene = new Scene(viewList, 1366, 768);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }



}
