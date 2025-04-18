package com.example.theunknownvariable.Model;

public class Point2 {
    private double x;
    private double y;

    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getter methods for x and y
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Method to update position
    public void updatePosition(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }
}
