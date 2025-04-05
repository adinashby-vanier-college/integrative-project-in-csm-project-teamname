package com.example.theunknownvariable.Model;

public class game2 {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    // Array of quiz questions
    public static final String[] QUESTIONS = {
            "What is the shape of a projectile’s trajectory?",
            "What factor does NOT affect projectile motion?",
            "At what angle is the range of a projectile maximum?",
            "Which component of velocity remains constant in projectile motion?",
            "If the initial velocity is doubled, how does the range change?"
    };

    // Answer choices corresponding to each question
    public static final String[][] OPTIONS = {
            {"Linear", "Parabolic", "Exponential", "Circular"},
            {"Gravity", "Initial velocity", "Mass of projectile", "Angle of launch"},
            {"30°", "45°", "60°", "90°"},
            {"Horizontal", "Vertical", "Total velocity", "None"},
            {"Doubles", "Triples", "Quadruples", "Remains same"}
    };

    // Index of correct answers in the OPTIONS array
    public static final int[] CORRECT_ANSWERS = {1, 2, 1, 0, 2};

    /**
     * Loads a question and its corresponding options and answer index.
     */
    public void loadQuestion(int questionIndex) {
        if (questionIndex < QUESTIONS.length) {
            questionText = QUESTIONS[questionIndex];
            options = OPTIONS[questionIndex];
            correctAnswerIndex = CORRECT_ANSWERS[questionIndex];
        }
    }

    /**
     * Retrieves the current question text. Returns the question text.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Retrieves the answer choices for the current question. Returns an array of answer choices.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Retrieves the index of the correct answer for the current question. Returns the correct answer index.
     */
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}