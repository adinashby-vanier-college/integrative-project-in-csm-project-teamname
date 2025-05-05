package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Model.LensGameModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LensGameControllerTest {

    @Test
    public void testCorrectRange() {
        assertTrue(LensGameModel.isInCorrectRange(-3.5));
        assertFalse(LensGameModel.isInCorrectRange(-5.0));
        assertFalse(LensGameModel.isInCorrectRange(-2.5));
    }

    @Test
    public void testLensType() {
        assertTrue(LensGameModel.isConverging(3.0));
        assertFalse(LensGameModel.isConverging(-3.0));

        assertTrue(LensGameModel.isDiverging(-3.0));
        assertFalse(LensGameModel.isDiverging(3.0));
    }
}
