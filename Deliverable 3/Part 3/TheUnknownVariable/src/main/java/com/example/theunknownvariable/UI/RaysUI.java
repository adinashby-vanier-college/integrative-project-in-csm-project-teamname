package com.example.theunknownvariable.UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class RaysUI {

    private static final double SLIDER_MIN = 400;
    private static final double SLIDER_MAX = 1100;
    private double objectX;
    private double objectY;
    private double crystallinX = 185;
    private double retinaX = 30;
    private double eyeTopY = 95;
    private double eyeBottomY = 175;
    private double convergenceX;

    private final Pane raysPane;
    private final Line topRay1;
    private final Line topRay2;
    private final Line bottomRay1;
    private final Line bottomRay2;

    public RaysUI(double objectX, double objectY) {
        this.objectX = objectX;
        this.objectY = objectY;
        this.raysPane = new Pane();

        //Line Pairs
        topRay1 = new Line();
        topRay1.setStroke(Color.rgb(235, 176, 176));
        topRay1.setStrokeWidth(3);

        topRay2 = new Line();
        topRay2.setStroke(Color.rgb(235, 176, 176));
        topRay2.setStrokeWidth(3);

        bottomRay1 = new Line();
        bottomRay1.setStroke(Color.rgb(176, 184, 235));
        bottomRay1.setStrokeWidth(3);

        bottomRay2 = new Line();
        bottomRay2.setStroke(Color.rgb(176, 184, 235));
        bottomRay2.setStrokeWidth(3);

        raysPane.getChildren().addAll(topRay1, topRay2, bottomRay1, bottomRay2);
        updateRays(objectX); // initial setup
    }

    //Second pair
    private void calculateConvergence() {
        if (objectX <= SLIDER_MIN) {
            convergenceX = retinaX - 100;
        } else if (objectX <= 900) {

            double t = (objectX - SLIDER_MIN) / (900.0 - SLIDER_MIN);
            convergenceX = (retinaX - 100) * (1 - t) + retinaX * t;
        } else if (objectX <= SLIDER_MAX) {

            double t = (objectX - 900.0) / (SLIDER_MAX - 900.0);
            double frontConvergenceX = retinaX + 20;
            convergenceX = retinaX * (1 - t) + frontConvergenceX * t;
        } else {
            convergenceX = retinaX + 20;
        }
    }





    public void updateRays(double objectX) {
        this.objectX = objectX;
        calculateConvergence();

        // Update line coordinates dynamically:
        topRay1.setStartX(objectX-87);
        topRay1.setStartY(objectY);
        topRay1.setEndX(crystallinX);
        topRay1.setEndY(eyeTopY);

        topRay2.setStartX(crystallinX);
        topRay2.setStartY(eyeTopY);
        topRay2.setEndX(convergenceX);
        topRay2.setEndY(objectY);

        bottomRay1.setStartX(objectX-87);
        bottomRay1.setStartY(objectY);
        bottomRay1.setEndX(crystallinX);
        bottomRay1.setEndY(eyeBottomY);

        bottomRay2.setStartX(crystallinX);
        bottomRay2.setStartY(eyeBottomY);
        bottomRay2.setEndX(convergenceX);
        bottomRay2.setEndY(objectY);
    }

    public Pane getRays() {
        return raysPane;
    }
}
