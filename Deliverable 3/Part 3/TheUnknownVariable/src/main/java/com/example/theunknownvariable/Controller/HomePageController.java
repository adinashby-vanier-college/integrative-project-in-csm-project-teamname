package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.UI.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageController {

    private final Stage stage;
    private final HomePage view;

    public HomePageController(Stage stage) {
        this.stage = stage;
        this.view = new HomePage(stage);
        attachEvents();
    }

    public Scene getScene() {
        return view.displayHomePage();
    }

    private void attachEvents() {
        view.getMenuButton().setOnAction(event -> {
            Scene mainPageScene = new MainPage(stage).displayMainPage();
            switchScene(mainPageScene);
        });

        view.getSuspectsButton().setOnAction(event -> {
            Scene suspectsScene = new Scene(new SuspectUI(stage), 1366, 768);
            switchScene(suspectsScene);
        });

        view.getQuitButton().setOnAction(event -> stage.close());

        view.getAccuseButton().setOnAction(event -> {
            if (GameStateManager.getInstance().isGame1Locked() &&
                    GameStateManager.getInstance().isGame2Locked() &&
                    GameStateManager.getInstance().isGame3Locked() &&
                    GameStateManager.getInstance().isGame4Locked()) {
                Scene accuseScene = new Scene(new AccuseUI(stage), 1366, 768);
                switchScene(accuseScene);
            }
        });
    }

    private void switchScene(Scene scene) {
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
