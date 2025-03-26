package com.example.theunknownvariable.UI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

import java.io.File;

public class TestObject {
    private static final double LINE_LENGTH = 1200;
    private static final double LINE_Y = 50;
    private static final double CIRCLE_RADIUS = 10;
    private static final double SLIDER_MIN = 400;
    private static final double SLIDER_MAX = 1100;
    private static Circle movingCircle = new Circle(CIRCLE_RADIUS, Color.rgb(214,182,144));
    public static Slider positionSlider = new Slider(SLIDER_MIN, SLIDER_MAX, (LINE_LENGTH / 2)+100);



    public Slider getPositionSlider(){
        return positionSlider;
    }
    public HBox getSliderBox(){
        // Slider
//        positionSlider.getStylesheets().add("C:\\Users\\maria\\IdeaProjects\\LensGameGUI\\src\\main\\resources\\Styles\\LensGameStyle.css");
//        positionSlider.getStylesheets().add(
//                new File("Styles/LensGameStyle.css").toString()
//        );



        positionSlider.getStylesheets().add(
                getClass().getResource("/Styles/LensGameStyle.css").toExternalForm()
        );


        positionSlider.setMinWidth(770);
        positionSlider.setMaxWidth(770);

        HBox sliderbox = new HBox(positionSlider);
        sliderbox.setAlignment(Pos.CENTER_RIGHT);
        sliderbox.setPadding(new Insets(50, 150, 0, 0));

        return sliderbox;
    }
    public StackPane getObjectPane() {
        // Line
        Line line = new Line(0, LINE_Y, LINE_LENGTH, LINE_Y);
        line.setStroke(Color.rgb(160,40,40));
        line.setStrokeWidth(7);
        line.setStrokeLineCap(StrokeLineCap.ROUND);

        // Circle
        movingCircle.setCenterX(LINE_LENGTH / 2);
        movingCircle.setCenterY(LINE_Y);

        positionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            movingCircle.setTranslateX(newValue.doubleValue() - ((LINE_LENGTH / 2)+100));
        });


        // Layout
        StackPane layout = new StackPane(line, movingCircle);
        layout.setAlignment(Pos.CENTER);
        return layout;
    }
}
