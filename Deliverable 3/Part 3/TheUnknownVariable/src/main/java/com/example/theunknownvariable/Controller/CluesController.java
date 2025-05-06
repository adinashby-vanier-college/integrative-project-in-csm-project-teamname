package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.UI.Clues;
import com.example.theunknownvariable.UI.MainPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CluesController {

    private final Stage stage;
    private final Clues view;

    public CluesController(Stage stage, Clues view) {
        this.stage = stage;
        this.view = view;
    }

    public void handleMenuButtonClick() {
        MainPage mainPage = new MainPage(stage);
        Scene scene = mainPage.displayMainPage();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    public String getClueText(int clueNumber) {
        GameStateManager manager = GameStateManager.getInstance();
        return switch (clueNumber) {
            case 1 -> manager.isClue1Unlocked()
                    ? "Based on the\nlens power,\nour killer seems\nto be myopic..."
                    : "Clue starts here";
            case 2 -> manager.isClue2Unlocked()
                    ? "They are\n neither the\n tallest nor the\n shortest in\n the room.\nTheir shadow,\n cast at dawn,\n stretched over a\n meter and a half."
                    : "Clue starts here";
            case 3 -> manager.isClue3Unlocked()
                    ? "Based on\nthe 3 solutions,\nthe killer\nworks in a\nfield related\nto tech,\nguns, or\ninvestigation\nof crime."
                    : "Clue starts here";
            case 4 -> manager.isClue4Unlocked()
                    ? "Based on\n the word\n \"blue\", we\ncan assume that\n the killer\n has blue eyes."
                    : "Clue starts here";
            default -> "Invalid Clue";
        };
    }
}
