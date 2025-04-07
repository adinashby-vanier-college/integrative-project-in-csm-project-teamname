package com.example.theunknownvariable.UI;
// Hangman.java
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Hangman {

    public Hangman(Pane pane) {
        drawHangman(pane);
    }

    private void drawHangman(Pane pane) {
        Line base = new Line(50, 600, 350, 600); // Larger base
        base.setStrokeWidth(15); // Thicker line
        base.setStroke(Color.BEIGE);

        Line pole = new Line(150, 150, 150, 600); // Taller pole
        pole.setStrokeWidth(15);
        pole.setStroke(Color.BEIGE);

        Line beam = new Line(150, 150, 400, 150); // Longer beam
        beam.setStrokeWidth(15);
        beam.setStroke(Color.BEIGE);

        Line rope = new Line(400, 150, 400, 350); // Longer rope
        rope.setStrokeWidth(15);
        rope.setStroke(Color.BEIGE);

        pane.getChildren().addAll(base, pole, beam, rope);
    }
}
