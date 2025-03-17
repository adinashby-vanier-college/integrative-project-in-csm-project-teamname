package com.example.demo1;

public class InputValidator {
    public boolean validateInput(double userInput, double correctValue) {
        return Math.abs(userInput - correctValue) < 0.01;
    }
}
