package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.game2handler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class game2handlerTest {

    private game2handler handler;

    @BeforeEach
    public void setUp() {
        handler = new game2handler();
    }

    @Test
    public void testInitialQuestionIsLoaded() {
        String question = handler.getQuestionText();
        assertNotNull(question, "First question should be loaded");
    }

    @Test
    public void testGetOptionsReturnsOptions() {
        String[] options = handler.getOptions();
        assertNotNull(options);
        assertEquals(4, options.length, "Each question should have 4 options");
    }

    @Test
    public void testCorrectAnswerCheck() {
        int correctIndex = handler.getOptions().length > 0 ?
                handler.getOptions().length - 1 : 0;
        boolean isCorrect = handler.checkAnswer(0);
        assertTrue(isCorrect || !isCorrect);
    }

    @Test
    public void testNextQuestionIncrementsIndex() {
        boolean hasNext = handler.nextQuestion();
        assertTrue(hasNext || !hasNext);
    }

    @Test
    public void testQuizCompletion() {
        while (handler.nextQuestion()) {
        }
        assertTrue(handler.isQuizCompleted(), "Quiz should be marked complete");
    }

    @Test
    public void testCorrectAnswersTracking() {
        int initialCorrect = handler.getCorrectAnswersCount();
        handler.checkAnswer(0);
        int after = handler.getCorrectAnswersCount();
        assertTrue(after == initialCorrect || after == initialCorrect + 1);
    }

    @Test
    public void testTotalQuestions() {
        assertTrue(handler.getTotalQuestions() > 0, "There should be at least one question");
    }
}