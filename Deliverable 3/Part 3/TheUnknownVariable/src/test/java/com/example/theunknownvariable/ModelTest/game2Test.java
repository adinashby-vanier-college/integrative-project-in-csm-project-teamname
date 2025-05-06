package com.example.theunknownvariable.ModelTest;

import com.example.theunknownvariable.Model.game2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class game2Test {

    private game2 quiz;

    @BeforeEach
    void setUp() {
        quiz = new game2();
    }

    @Test
    void testLoadQuestionValidIndex() {
        quiz.loadQuestion(0);
        assertEquals("What is the shape of a projectile’s trajectory?", quiz.getQuestionText());
        assertArrayEquals(new String[]{"Linear", "Parabolic", "Exponential", "Circular"}, quiz.getOptions());
        assertEquals(1, quiz.getCorrectAnswerIndex());  // "Parabolic"
    }

    @Test
    void testLoadQuestionMiddleIndex() {
        quiz.loadQuestion(2);
        assertEquals("At what angle is the range of a projectile maximum?", quiz.getQuestionText());
        assertArrayEquals(new String[]{"30°", "45°", "60°", "90°"}, quiz.getOptions());
        assertEquals(1, quiz.getCorrectAnswerIndex());  // "45°"
    }

    @Test
    void testLoadQuestionOutOfBoundsDoesNotThrow() {
        assertDoesNotThrow(() -> quiz.loadQuestion(100));
        assertNull(quiz.getQuestionText());
        assertNull(quiz.getOptions());
    }
}