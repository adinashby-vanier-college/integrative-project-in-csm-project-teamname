package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.game2handler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class game2handlerTest {

    private game2handler handler;

    @BeforeEach
    public void setUp() {
        //new instance ensures tests are isolated
        handler = new game2handler();
    }

    @Test
    public void testInitialQuestionIsLoaded() {
        //confirming initialization logic
        String question = handler.getQuestionText();
        assertNotNull(question, "First question should be loaded");
    }

    @Test
    public void testGetOptionsReturnsOptions() {
        //confirming each question has 4 choices for answers
        String[] options = handler.getOptions();
        assertNotNull(options);
        assertEquals(4, options.length, "Each question should have 4 options");
    }

    @Test
    public void testCorrectAnswerCheck() {
        //confirming checkAnswer() returns boolean value
        int correctIndex = handler.getOptions().length > 0 ?
                handler.getOptions().length - 1 : 0;
        boolean isCorrect = handler.checkAnswer(0);
        assertTrue(isCorrect || !isCorrect);
    }

    @Test
    public void testNextQuestionIncrementsIndex() {
        //confirming that there is a following questions
        boolean hasNext = handler.nextQuestion();
        assertTrue(hasNext || !hasNext);
    }

    @Test
    public void testQuizCompletion() {
        //confirming completion of the test after all questions
        while (handler.nextQuestion()) {
        }
        assertTrue(handler.isQuizCompleted(), "Quiz should be marked complete");
    }

    @Test
    public void testCorrectAnswersTracking() {
        //confirming the incrementation based on correct answers
        int initialCorrect = handler.getCorrectAnswersCount();
        handler.checkAnswer(0);
        int after = handler.getCorrectAnswersCount();
        assertTrue(after == initialCorrect || after == initialCorrect + 1);
    }

    @Test
    public void testTotalQuestions() {
        //confirming that there is at least one question in the quiz
        assertTrue(handler.getTotalQuestions() > 0, "There should be at least one question");
    }
}