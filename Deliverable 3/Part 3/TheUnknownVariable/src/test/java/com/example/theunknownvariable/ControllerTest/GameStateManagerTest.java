package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.GameStateManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class GameStateManagerTest {

    private GameStateManager gameStateManager;

    @BeforeEach
    void setUp() throws Exception {
        // Reset singleton instance using reflection to avoid shared state across tests
        Field instanceField = GameStateManager.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);

        gameStateManager = GameStateManager.getInstance();
    }

    @Test
    void testSingletonInstance() {
        GameStateManager instance1 = GameStateManager.getInstance();
        GameStateManager instance2 = GameStateManager.getInstance();
        assertSame(instance1, instance2, "Should return the same singleton instance");
    }

    @Test
    void testUnlockClues() {
        assertFalse(gameStateManager.isClue1Unlocked());
        gameStateManager.unlockClue1();
        assertTrue(gameStateManager.isClue1Unlocked());

        assertFalse(gameStateManager.isClue2Unlocked());
        gameStateManager.unlockClue2();
        assertTrue(gameStateManager.isClue2Unlocked());

        assertFalse(gameStateManager.isClue3Unlocked());
        gameStateManager.unlockClue3();
        assertTrue(gameStateManager.isClue3Unlocked());

        assertFalse(gameStateManager.isClue4Unlocked());
        gameStateManager.unlockClue4();
        assertTrue(gameStateManager.isClue4Unlocked());
    }

    @Test
    void testLockGames() {
        assertFalse(gameStateManager.isGame1Locked());
        gameStateManager.lockGame1();
        assertTrue(gameStateManager.isGame1Locked());

        assertFalse(gameStateManager.isGame2Locked());
        gameStateManager.lockGame2();
        assertTrue(gameStateManager.isGame2Locked());

        assertFalse(gameStateManager.isGame3Locked());
        gameStateManager.lockGame3();
        assertTrue(gameStateManager.isGame3Locked());

        assertFalse(gameStateManager.isGame4Locked());
        gameStateManager.lockGame4();
        assertTrue(gameStateManager.isGame4Locked());
    }
}
