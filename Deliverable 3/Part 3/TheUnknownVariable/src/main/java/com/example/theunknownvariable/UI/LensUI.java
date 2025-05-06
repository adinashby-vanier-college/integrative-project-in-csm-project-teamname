package com.example.theunknownvariable.UI;
import javafx.application.Application;
import javafx.geometry.Insets;
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
        double radiusX = 100;
        double radiusY = 200;
        double lensWidth = 70;

// Left arc (curving outward)
        Arc leftArc = new Arc(-lensWidth / 2, 0, radiusX, radiusY, 120, 40);
        leftArc.setFill(Color.rgb(173, 216, 230, 0.5));
        leftArc.setStroke(Color.TRANSPARENT);
        leftArc.setType(ArcType.OPEN);

// Right arc (curving outward)
        Arc rightArc = new Arc(lensWidth / 2, 0, radiusX, radiusY, 20, 40);
        rightArc.setFill(Color.rgb(173, 216, 230, 0.5));
        rightArc.setStroke(Color.TRANSPARENT);
        rightArc.setType(ArcType.OPEN);

// Properly align the arcs inside the lens container
        HBox lensBox = new HBox(leftArc, rightArc);
        lensBox.setSpacing(-radiusX / 2); // arcs closer together
        lensBox.setRotate(0);

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
            double lensWidth = 70;

            // Left arc (left side of the lens)
            Arc leftArc = new Arc(0, 0, radiusX, radiusY, 120, 40); // Start at 120° to curve outward
            leftArc.setFill(Color.rgb(173, 216, 230, 0.5));
            leftArc.setStroke(Color.TRANSPARENT);
            leftArc.setType(ArcType.OPEN);

            // Right arc (right side of the lens)
            Arc rightArc = new Arc(0, 0, radiusX, radiusY, 300, 40); // Start at 300° to curve outward
            rightArc.setFill(Color.rgb(173, 216, 230, 0.5));
            rightArc.setStroke(Color.TRANSPARENT);
            rightArc.setType(ArcType.OPEN);

            // Align arcs properly in HBox
            HBox lensBox = new HBox(leftArc, rightArc);
            lensBox.setSpacing(-lensWidth - 21); // meet at the center
            lensBox.setRotate(-40.5);

            lensContainer = new HBox(lensBox);
            lensContainer.setAlignment(Pos.CENTER);
        } else {
            double radiusX = 55;
            double radiusY = 110;
            double lensWidth = 70;


            Arc leftArc = new Arc(-lensWidth / 2, 0, radiusX, radiusY, 140, 80);
            leftArc.setFill(Color.rgb(173, 216, 230, 0.5));
            leftArc.setStroke(Color.TRANSPARENT);
            leftArc.setType(ArcType.OPEN);


            Arc rightArc = new Arc(lensWidth / 2, 0, radiusX, radiusY, 320, 80);
            rightArc.setFill(Color.rgb(173, 216, 230, 0.5));
            rightArc.setStroke(Color.TRANSPARENT);
            rightArc.setType(ArcType.OPEN);


            HBox lensBox = new HBox(leftArc, rightArc);
            lensBox.setSpacing(-radiusX / 2 - 1);

            lensContainer = new HBox(lensBox);
            lensContainer.setAlignment(Pos.CENTER);
            lensContainer.setPadding(new Insets(0,65,45,0));

        }

    }

    public HBox getLensContainer() {
        return lensContainer;
    }

    public static void main(String[] args) {
        launch();
    }
}