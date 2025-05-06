package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.UI.CodeVerificationUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CodeVerification extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CodeVerificationUI codeUI = new CodeVerificationUI();
        Scene codeScene = codeUI.createCodeScene(primaryStage);
        primaryStage.setScene(codeScene);
        primaryStage.setTitle("Code Verification");
        primaryStage.show();
    }
}
