package com.example.theunknownvariable.ModelTest;

import com.example.theunknownvariable.Model.Point2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Point2Test {

    @Test
    void testConstructorAndGetters() {
        Point2 point = new Point2(10.5, 20.5);
        assertEquals(10.5, point.getX(), 0.0001);
        assertEquals(20.5, point.getY(), 0.0001);
    }

    @Test
    void testUpdatePosition() {
        Point2 point = new Point2(5.0, 5.0);
        point.updatePosition(3.0, -2.0);
        assertEquals(8.0, point.getX(), 0.0001);
        assertEquals(3.0, point.getY(), 0.0001);
    }

    @Test
    void testMultipleUpdates() {
        Point2 point = new Point2(0, 0);
        point.updatePosition(1.5, 2.5);
        point.updatePosition(-0.5, -1.0);
        assertEquals(1.0, point.getX(), 0.0001);
        assertEquals(1.5, point.getY(), 0.0001);
    }
}