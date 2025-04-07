package com.example.theunknownvariable.Model;

import java.util.Random;

public class MathProblem {
    private double[][] matrix;

    // Constructor
    public MathProblem(double[][] matrix) {
        this.matrix = matrix;
    }

    // Method to calculate determinant
    public double calculateDeterminant() {
        int size = matrix.length;

        if (size == 2) {
            // 2x2 matrix determinant: ad - bc
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        } else if (size == 3) {
            // 3x3 matrix determinant using Sarrus' Rule
            return (matrix[0][0] * matrix[1][1] * matrix[2][2])
                    + (matrix[0][1] * matrix[1][2] * matrix[2][0])
                    + (matrix[0][2] * matrix[1][0] * matrix[2][1])
                    - (matrix[0][2] * matrix[1][1] * matrix[2][0])
                    - (matrix[0][1] * matrix[1][0] * matrix[2][2])
                    - (matrix[0][0] * matrix[1][2] * matrix[2][1]);
        } else {
            throw new UnsupportedOperationException("Only 2x2 and 3x3 matrices are supported.");
        }
    }

    public static MathProblem generateMathProblem() {
        double[][] matrix = new double[3][3];
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = random.nextInt(10) + 1; // random number from 1 to 10
            }
        }

        return new MathProblem(matrix);
    }


    // Getter (if needed)
    public double[][] getMatrix() {
        return matrix;
    }
}
