package com.example.theunknownvariable.Model;

public class LensGameModel {
    public static boolean isInCorrectRange(double userAnswer) {
        return userAnswer >= -4.08 && userAnswer <= -2.99;
    }

    public static boolean isConverging(double lensValue) {
        return lensValue > 0;
    }

    public static boolean isDiverging(double lensValue) {
        return lensValue < 0;
    }
}
