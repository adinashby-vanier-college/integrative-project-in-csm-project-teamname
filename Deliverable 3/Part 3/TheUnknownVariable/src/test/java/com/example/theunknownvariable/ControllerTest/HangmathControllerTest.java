package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.HangmathController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HangmathControllerTest {

    private HangmathController controller;

    @BeforeEach
    public void setUp() {
        controller = new HangmathController();
    }

    @Test
    public void testMatrixIsGenerated() {
        double[][] matrix = controller.getCurrentMatrix();
        assertNotNull(matrix);
        assertEquals(3, matrix.length);
        assertEquals(3, matrix[0].length);
    }

    @Test
    public void testCheckAnswerReturnsTrueForCorrectAnswer() {
        double correctAnswer = controller.getCorrectAnswer();
        assertTrue(controller.checkAnswer(correctAnswer));
    }

    @Test
    public void testCheckAnswerReturnsFalseForIncorrectAnswer() {
        double incorrectAnswer = controller.getCorrectAnswer() + 5;
        assertFalse(controller.checkAnswer(incorrectAnswer));
    }

    @Test
    public void testRegisterWrongGuessIncreasesErrorCount() {
        int before = controller.getErrorCount();
        controller.registerWrongGuess();
        assertEquals(before + 1, controller.getErrorCount());
    }

    @Test
    public void testIsGameOverReturnsTrueAfterMaxErrors() {
        for (int i = 0; i < 6; i++) {
            controller.registerWrongGuess();
        }
        assertTrue(controller.isGameOver());
    }

    @Test
    public void testRegisterCorrectGuessAndRevealCheck() {
        controller.registerCorrectGuess('b');
        assertTrue(controller.isLetterRevealed('b'));
        assertFalse(controller.isLetterRevealed('z'));
    }

    @Test
    public void testCheckGameWonTrueIfAllLettersGuessed() {
        String word = controller.getTargetWord();
        for (char c : word.toCharArray()) {
            controller.registerCorrectGuess(c);
        }
        assertTrue(controller.checkGameWon());
    }

    @Test
    public void testCheckGameWonFalseIfNotAllLettersGuessed() {
        controller.registerCorrectGuess('b');
        controller.registerCorrectGuess('l');
        assertFalse(controller.checkGameWon());
    }

    @Test
    public void testGenerateNewProblemChangesMatrix() {
        double[][] oldMatrix = controller.getCurrentMatrix();
        controller.generateNewProblem();
        double[][] newMatrix = controller.getCurrentMatrix();

        // Just check that it's not the same reference (different matrix object)
        assertNotSame(oldMatrix, newMatrix);
    }
}
