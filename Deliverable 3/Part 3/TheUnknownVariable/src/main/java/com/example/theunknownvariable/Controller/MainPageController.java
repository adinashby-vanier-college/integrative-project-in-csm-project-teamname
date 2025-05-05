package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.UI.*;
import javafx.stage.Stage;

public class MainPageController {

    private final Stage stage;

    public MainPageController(Stage stage) {
        this.stage = stage;
    }

    public void handleGame1() {
        if (!GameStateManager.getInstance().isGame1Locked()) {
            LensGameController lensGameMain = new LensGameController(stage);
            stage.setScene(lensGameMain.getLensGameInstructionsScene());
            stage.centerOnScreen();
        }
    }

    public void handleGame2() {
        if (!GameStateManager.getInstance().isGame2Locked()) {
            UserInterface2 ui2 = new UserInterface2(stage);
            stage.setScene(ui2.displayInstructions());
            stage.centerOnScreen();
        }
    }

    public void handleGame3() {
        if (!GameStateManager.getInstance().isGame3Locked()) {
            ChemUI view = new ChemUI(stage);
            EnthalpyGraph graph = new EnthalpyGraph(view);
            ReactionHandler handler = new ReactionHandler(view, graph);
            new ChemController(view, handler, graph, stage);
            view.initialize();
            stage.setScene(view.displayInstructions());
            stage.centerOnScreen();
        }
    }

    public void handleGame4() {
        if (!GameStateManager.getInstance().isGame4Locked()) {
            MathGameUI mathGame = new MathGameUI(stage);
            stage.setScene(mathGame.displayMathGame(stage));
            stage.centerOnScreen();
        }
    }

    public void handleClues() {
        Clues clues = new Clues(stage);
        stage.setScene(clues.displayClues());
        stage.centerOnScreen();
    }

    public void handleMenu() {
        HomePageController homePage = new HomePageController(stage);
        stage.setScene(homePage.getScene());
        stage.centerOnScreen();
    }
}
