package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.CluesController;
import com.example.theunknownvariable.Controller.GameStateManager;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CluesControllerTest {

    private CluesController controller;

    @BeforeEach
    void setUp() {
        Stage dummyStage = mock(Stage.class);
        controller = new CluesController(dummyStage, null);
    }

    @Test
    void testDefaultClueTextsWhenLocked() {
        assertEquals("Clue starts here", controller.getClueText(1));
        assertEquals("Clue starts here", controller.getClueText(2));
        assertEquals("Clue starts here", controller.getClueText(3));
        assertEquals("Clue starts here", controller.getClueText(4));
    }

    @Test
    void testUnlockedClueText() {
        GameStateManager.getInstance().unlockClue1();
        assertTrue(controller.getClueText(1).contains("myopic"));
    }

    @Test
    void testInvalidClueNumberReturnsDefault() {
        assertEquals("Invalid Clue", controller.getClueText(99));
    }
}
