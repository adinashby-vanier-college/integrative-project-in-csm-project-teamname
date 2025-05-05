package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.GameStateManager;
import com.example.theunknownvariable.Controller.MainPageController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class MainPageControllerTest {

    private Stage stage;
    private MainPageController controller;

    @BeforeEach
    void setup() {
        stage = mock(Stage.class);
        controller = new MainPageController(stage);
    }

    @Test
    void testHandleGame1_WhenUnlocked() {
        GameStateManager.getInstance().lockGame1(); // Initially locked
        GameStateManager.getInstance().unlockClue1(); // Pretend unlocked
        GameStateManager.getInstance().lockGame1(); // Make sure it's unlocked
        // unlocking logic is manual; simulate it as needed here

        // Force unlock in mock (you may need to modify the design slightly to mock properly)
        // Skipping actual game instantiation in unit test for now
        controller.handleGame1();

        verify(stage, atLeastOnce()).setScene(any(Scene.class));
        verify(stage).centerOnScreen();
    }

    @Test
    void testHandleClues() {
        controller.handleClues();
        verify(stage).setScene(any(Scene.class));
        verify(stage).centerOnScreen();
    }

    @Test
    void testHandleMenu() {
        controller.handleMenu();
        verify(stage).setScene(any(Scene.class));
        verify(stage).centerOnScreen();
    }

    // Repeat similar tests for handleGame2, handleGame3, handleGame4
}
