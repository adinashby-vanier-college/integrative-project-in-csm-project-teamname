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

public class lensTester extends Application {
    public static HBox lensContainer;
    @Override
    public void start(Stage stage) {
        double radiusX = 100;
        double radiusY = 200;
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
        lensBox.setSpacing(-radiusX / 2 );

        lensContainer = new HBox(lensBox);
        lensContainer.setAlignment(Pos.CENTER);


        // Scene setup
        StackPane root = new StackPane(lensContainer);
        Scene scene = new Scene(root, 300, 300, Color.WHITE);
        stage.setScene(scene);
        stage.setTitle("Thin Converging LensUI");
        stage.show();
    }



    public HBox getLensContainer() {
        return lensContainer;
    }

    public static void main(String[] args) {
        launch();
    }
}