package com.example.theunknownvariable.ModelTest;

import com.example.theunknownvariable.Model.Substance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubstanceTest {
    @Test
    public void testSingletonInstance() {
        Substance instance1 = Substance.getInstance();
        Substance instance2 = Substance.getInstance();
        assertSame(instance1, instance2, "Instances should be the same");
    }

    @Test
    public void testSetAndGetSubstanceNb1() {
        Substance substance = Substance.getInstance();
        substance.setSubstanceNb1(10);
        assertEquals(10, substance.getSubstanceNb1(), "SubstanceNb1 should be 10");
    }

    @Test
    public void testSetAndGetSubstanceNb2() {
        Substance substance = Substance.getInstance();
        substance.setSubstanceNb2(20);
        assertEquals(20, substance.getSubstanceNb2(), "SubstanceNb2 should be 20");
    }
}
