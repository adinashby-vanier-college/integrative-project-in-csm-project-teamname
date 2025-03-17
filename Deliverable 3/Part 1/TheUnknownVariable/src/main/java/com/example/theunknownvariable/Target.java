package com.example.demo1;

import java.util.List;

public class Target {
    private Point position;

    public Target(Point position) {
        this.position = position;
    }

    public boolean isHit(List<Point> projectilePath) {
        for (Point p : projectilePath) {
            if (Math.abs(p.getX() - position.getX()) < 1 && Math.abs(p.getY() - position.getY()) < 1) {
                return true;
            }
        }
        return false;
    }
}
