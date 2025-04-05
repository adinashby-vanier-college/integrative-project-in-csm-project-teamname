package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.Model.game2;

public class game2handler {
    private int currentQuestionIndex = 0;
    private int correctAnswersCount = 0;
    private boolean quizCompleted = false;
    private game2 quizData;

    /**
     * Initializes the quiz data and loads the first question.
     */
    public game2handler() {
        quizData = new game2();
        quizData.loadQuestion(currentQuestionIndex);
    }

    /**
     * Retrieves the text of the current question.
     */
    public String getQuestionText() {
        return quizData.getQuestionText();
    }

    /**
     * Retrieves the answer options for the current question.
     */
    public String[] getOptions() {
        return quizData.getOptions();
    }

    /**
     * Checks if the selected answer is correct.
     * @param selectedIndex The index of the selected answer.
     * @return True if the answer is correct, otherwise false.
     */
    public boolean checkAnswer(int selectedIndex) {
        if (selectedIndex == quizData.getCorrectAnswerIndex()) {
            correctAnswersCount++;
            return true;
        }
        return false;
    }

    /**
     * Moves to the next question if available.
     * @return True if there is another question, otherwise false.
     */
    public boolean nextQuestion() {
        if (currentQuestionIndex < game2.QUESTIONS.length - 1) {
            currentQuestionIndex++;
            quizData.loadQuestion(currentQuestionIndex);
            return true;
        } else {
            quizCompleted = true;
            return false;
        }
    }

    /**
     * Retrieves the number of correctly answered questions.
     */
    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    /**
     * Checks if the quiz is completed.
     */
    public boolean isQuizCompleted() {
        return quizCompleted;
    }

    /**
     * Retrieves the total number of questions in the quiz.
     */
    public int getTotalQuestions() {
        return game2.QUESTIONS.length;
    }
}
