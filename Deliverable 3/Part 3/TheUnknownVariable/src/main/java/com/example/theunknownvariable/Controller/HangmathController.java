package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.Model.MathProblem;
import java.util.HashSet;
import java.util.Set;

public class HangmathController {
    private static final int MAX_ERRORS = 6;
    private int errorCount = 0;
    private final String targetWord = "blue";
    private final Set<Character> revealedLetters = new HashSet<>();

    private MathProblem currentProblem;

    public HangmathController() {
        generateNewProblem();
    }

    public void generateNewProblem() {
        currentProblem = MathProblem.generateMathProblem();
    }

    public double getCorrectAnswer() {
        return currentProblem.calculateDeterminant();
    }

    public double[][] getMatrix() {
        return currentProblem.getMatrix();
    }

    public boolean checkAnswer(double userAnswer) {
        return Math.abs(userAnswer - getCorrectAnswer()) < 0.0001;
    }

    public void registerCorrectGuess(char letter) {
        revealedLetters.add(letter);
    }

    public boolean isLetterRevealed(char letter) {
        return revealedLetters.contains(letter);
    }

    public boolean allLettersRevealed() {
        for (char c : targetWord.toCharArray()) {
            if (!revealedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public void registerWrongGuess() {
        errorCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public boolean isGameOver() {
        return errorCount >= MAX_ERRORS;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public boolean checkGameWon() {
        return allLettersRevealed();
    }

    public double[][] getCurrentMatrix() {
        return currentProblem.getMatrix();
    }

}
