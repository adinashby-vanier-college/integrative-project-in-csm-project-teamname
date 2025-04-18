package com.example.theunknownvariable;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Lens extends Application {
    @Override
    public void start(Stage stage) {
        // Large radius to make the lens thin
        double radiusX = 200;
        double radiusY = 200;
        double lensWidth = 70; // Width of the lens

        // Left arc (left side of the lens)
        Arc leftArc = new Arc(0, 0, radiusX, radiusY, 120, 40); // Start at 120° to curve outward
        leftArc.setFill(Color.rgb(173, 216, 230, 0.5)); // Light blue, 50% transparent
        leftArc.setStroke(Color.TRANSPARENT);
        leftArc.setType(ArcType.OPEN);

        // Right arc (right side of the lens)
        Arc rightArc = new Arc(0, 0, radiusX, radiusY, 300, 40); // Start at 300° to curve outward
        rightArc.setFill(Color.rgb(173, 216, 230, 0.5));
        rightArc.setStroke(Color.TRANSPARENT);
        rightArc.setType(ArcType.OPEN);

        // Align arcs properly in HBox
        HBox lensBox = new HBox(leftArc, rightArc);
        lensBox.setSpacing(-lensWidth -21); // Adjust so they meet at the center
        lensBox.setRotate(-40.5);

        HBox box = new HBox(lensBox);
        box.setAlignment(Pos.CENTER);

        // Scene setup
        StackPane root = new StackPane(box);
        Scene scene = new Scene(root, 300, 300, Color.WHITE);
        stage.setScene(scene);
        stage.setTitle("Thin Converging Lens");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}