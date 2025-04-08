package com.example.theunknownvariable;

import java.util.ArrayList;
import java.util.List;

public class ProjectileHandler {
    private double initialHeight = 50;
    private double finalAngle = 45;
    private double fixedDistance = 100;

    public List<Point> calculateTrajectory() {
        List<Point> trajectory = new ArrayList<>();
        double velocity = 20;
        double timeStep = 0.1;

        for (double t = 0; t < 5; t += timeStep) {
            double x = velocity * Math.cos(Math.toRadians(finalAngle)) * t;
            double y = initialHeight + (velocity * Math.sin(Math.toRadians(finalAngle)) * t) - (0.5 * 9.8 * t * t);
            if (y < 0) break;
            trajectory.add(new Point(x, y));
        }
        return trajectory;
    }

    public boolean checkCollision(Target target) {
        return target.isHit(calculateTrajectory());
    }
}