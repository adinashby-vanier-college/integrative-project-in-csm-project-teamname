package com.example.theunknownvariable.Controller;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class LensUI extends Application {
    public static HBox lensContainer;
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

        lensContainer = new HBox(lensBox);
        lensContainer.setAlignment(Pos.CENTER);

        // Scene setup
        StackPane root = new StackPane(lensContainer);
        Scene scene = new Scene(root, 300, 300, Color.WHITE);
        stage.setScene(scene);
        stage.setTitle("Thin Converging LensUI");
        stage.show();
    }

    public LensUI(String type){
        if (type == "c") {
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
            lensBox.setSpacing(-lensWidth - 21); // Adjust so they meet at the center
            lensBox.setRotate(-40.5);

            lensContainer = new HBox(lensBox);
            lensContainer.setAlignment(Pos.CENTER);
        } else {
            double radiusX = 200;
            double radiusY = 200;
            double lensWidth = 70;

            // Left arc (curving inward)
            Arc leftArc = new Arc(0, 0, radiusX, radiusY, 220, -40); // Inward curvature
            leftArc.setFill(Color.rgb(173, 216, 230, 0.5));
            leftArc.setStroke(Color.TRANSPARENT);
            leftArc.setType(ArcType.OPEN);

            // Right arc (curving inward)
            Arc rightArc = new Arc(0, 0, radiusX, radiusY, 20, -40); // Inward curvature
            rightArc.setFill(Color.rgb(173, 216, 230, 0.5));
            rightArc.setStroke(Color.TRANSPARENT);
            rightArc.setType(ArcType.OPEN);

            // Adjust alignment
            HBox lensBox = new HBox(leftArc, rightArc);
            lensBox.setSpacing(lensWidth - 50); // Adjust so they curve inward correctly
            lensBox.setRotate(-40.5);

            lensContainer = new HBox(lensBox);
            lensContainer.setAlignment(Pos.CENTER);
        }

    }

    public HBox getLensContainer() {
        return lensContainer;
    }

    public static void main(String[] args) {
        launch();
    }
}