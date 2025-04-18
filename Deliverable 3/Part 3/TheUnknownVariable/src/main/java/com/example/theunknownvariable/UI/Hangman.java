package com.example.theunknownvariable.UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;

public class Hangman {
    private int limbCount = 0; // Tracks the number of limbs added

    public Hangman(Pane pane) {
        drawHangman(pane);
    }

    private void drawHangman(Pane pane) {
        Line base = new Line(50, 600, 350, 600);
        base.setStrokeWidth(15);
        base.setStroke(Color.BEIGE);

        Line pole = new Line(150, 150, 150, 600);
        pole.setStrokeWidth(15);
        pole.setStroke(Color.BEIGE);

        Line beam = new Line(150, 150, 400, 150);
        beam.setStrokeWidth(15);
        beam.setStroke(Color.BEIGE);

        Line rope = new Line(400, 150, 400, 250);
        rope.setStrokeWidth(15);
        rope.setStroke(Color.BEIGE);

        pane.getChildren().addAll(base, pole, beam, rope);
    }

    public void addLimb(Pane pane) {
        switch (limbCount) {
            case 0: // Head
                Circle head = new Circle(400, 275, 25);
                head.setStroke(Color.BLACK);
                head.setFill(Color.BEIGE);
                pane.getChildren().add(head);
                break;
            case 1: // Body
                Line body = new Line(400, 300, 400, 400);
                body.setStrokeWidth(5);
                body.setStroke(Color.BLACK);
                pane.getChildren().add(body);
                break;
            case 2: // Left arm
                Line leftArm = new Line(400, 350, 350, 300);
                leftArm.setStrokeWidth(5);
                leftArm.setStroke(Color.BLACK);
                pane.getChildren().add(leftArm);
                break;
            case 3: // Right arm
                Line rightArm = new Line(400, 350, 450, 300);
                rightArm.setStrokeWidth(5);
                rightArm.setStroke(Color.BLACK);
                pane.getChildren().add(rightArm);
                break;
            case 4: // Left leg
                Line leftLeg = new Line(400, 400, 350, 500);
                leftLeg.setStrokeWidth(5);
                leftLeg.setStroke(Color.BLACK);
                pane.getChildren().add(leftLeg);
                break;
            case 5: // Right leg
                Line rightLeg = new Line(400, 400, 450, 500);
                rightLeg.setStrokeWidth(5);
                rightLeg.setStroke(Color.BLACK);
                pane.getChildren().add(rightLeg);
                break;
            default:
                System.out.println("All limbs are already added!");
                break;
        }
        limbCount++;
    }
}