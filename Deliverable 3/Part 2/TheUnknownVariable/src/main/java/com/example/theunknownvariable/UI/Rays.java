package com.example.theunknownvariable.UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Rays {
    private double objectX;
    private double objectY;
    private double eyeX = 260;
    private double retinaX = 230;
    private double eyeTopY = 40;
    private double eyeBottomY = 60;
    private double convergenceX;

    private final Pane raysPane;
    private final Line topRay1;
    private final Line topRay2;
    private final Line bottomRay1;
    private final Line bottomRay2;

    public Rays(double objectX, double objectY) {
        this.objectX = objectX;
        this.objectY = objectY;
        this.raysPane = new Pane();

        //Line Pairs
        topRay1 = new Line();
        topRay1.setStroke(Color.rgb(235, 176, 176));
        topRay1.setStrokeWidth(2);

        topRay2 = new Line();
        topRay2.setStroke(Color.rgb(235, 176, 176));
        topRay2.setStrokeWidth(2);

        bottomRay1 = new Line();
        bottomRay1.setStroke(Color.rgb(176, 184, 235));
        bottomRay1.setStrokeWidth(2);

        bottomRay2 = new Line();
        bottomRay2.setStroke(Color.rgb(176, 184, 235));
        bottomRay2.setStrokeWidth(2);

        raysPane.getChildren().addAll(topRay1, topRay2, bottomRay1, bottomRay2);
        updateRays(objectX); // initial setup
    }

    //Draft second pair line behaviour
    private void calculateConvergence() {
        if (objectX > 900) {
            convergenceX = retinaX - 20;
        } else {
            convergenceX = retinaX;
        }
    }

    public void updateRays(double objectX) {
        this.objectX = objectX;
        calculateConvergence();

        // Update line coordinates dynamically:
        topRay1.setStartX(objectX);
        topRay1.setStartY(objectY);
        topRay1.setEndX(eyeX);
        topRay1.setEndY(eyeTopY);

        topRay2.setStartX(eyeX);
        topRay2.setStartY(eyeTopY);
        topRay2.setEndX(convergenceX);
        topRay2.setEndY(objectY);

        bottomRay1.setStartX(objectX);
        bottomRay1.setStartY(objectY);
        bottomRay1.setEndX(eyeX);
        bottomRay1.setEndY(eyeBottomY);

        bottomRay2.setStartX(eyeX);
        bottomRay2.setStartY(eyeBottomY);
        bottomRay2.setEndX(convergenceX);
        bottomRay2.setEndY(objectY);
    }

    public Pane getRays() {
        return raysPane;
    }
}
