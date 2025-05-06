package com.example.theunknownvariable.ModelTest;

import com.example.theunknownvariable.Model.MathProblem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathProblemTest {

    @Test
    public void testCalculateDeterminant2x2() {
        double[][] matrix = {
                {4, 6},
                {3, 8}
        };
        MathProblem problem = new MathProblem(matrix);
        double expected = 14; // 32 - 18 = 14
        assertEquals(expected, problem.calculateDeterminant(), 0.0001);
    }

    @Test
    public void testCalculateDeterminant3x3() {
        double[][] matrix = {
                {1, 2, 3},
                {0, 4, 5},
                {1, 0, 6}
        };
        MathProblem problem = new MathProblem(matrix);
        double expected = 22; // 24 + 10 - 12 = 22
        assertEquals(22.0, problem.calculateDeterminant(), 0.0001);
    }

    @Test
    public void testGenerateMathProblemMatrixIs3x3() {
        MathProblem problem = MathProblem.generateMathProblem();
        double[][] matrix = problem.getMatrix();

        assertEquals(3, matrix.length);
        assertEquals(3, matrix[0].length); // assume square matrix
    }

    @Test
    public void testCalculateDeterminantUnsupportedSize() {
        double[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        MathProblem problem = new MathProblem(matrix);

        assertThrows(UnsupportedOperationException.class, problem::calculateDeterminant);
    }
}
