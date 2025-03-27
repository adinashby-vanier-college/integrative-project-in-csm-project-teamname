package com.example.theunknownvariable;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RulerMarker {
    private static final double rulerSIZE = 40;

    public static void main(String[] args) {

    }



//--------------RULER------------------
    public void drawRuler(GraphicsContext gc, double width, double height) {
        //Remove 12.5 on each side and scale the width for 20 cm
        double cmToPx = (width - 25) / rulerSIZE;
        double leftPadding = 12;
        //Top border pixels
        double topBorderY = 2;
        //SPace between ticks and numbers
        double numberOffset = 13;

        // Draw border
        gc.setStroke(Color.rgb(236,221,216)); // Brown color for ruler border
        gc.setLineWidth(3);
        gc.strokeRoundRect(1, 1, width - 2, height - 2, 10, 10);

        // Draw tick marks and numbers
        gc.setStroke(Color.rgb(214,182,144));
        gc.setLineWidth(2);
        gc.setFill(Color.rgb(214,182,144));
        gc.setFont(new Font("Times New Roman", 13));


        for (int i = 0; i <= rulerSIZE*10; i++) { //cm size in mm
            double x = leftPadding + i * (cmToPx / 10.0);
            double y1 = topBorderY; //Start tic from border
            double y2; //End tic height

//            if (i % 10 == 0) { //1cm tic
//                y2 = y1 + 15;
//                gc.fillText(Integer.toString(i / 10), x - 4, y2 + numberOffset + 2);
//            } else if (i % 5 == 0) { //5mm tic
//                y2 = y1 + 10;
//            } else { //1mm tic
//                y2 = y1 + 5;
//            }
//
//            gc.strokeLine(x, y1, x, y2);

            y2 = y1;
            if (i % 10 == 0) { // 1 cm tic
                y2 = y1 + 15;
                gc.fillText(Integer.toString(i / 10), x - 4, y2 + numberOffset + 2);
            } else if (i % 5 == 0) { // 5 mm tic
                y2 = y1 + 10;
            }
            gc.strokeLine(x, y1, x, y2);

        }
    }
    public Canvas getRulerCanvas(Double w,Double h){
        RulerMarker ruler = new RulerMarker();
        Canvas canvas = new Canvas(w, h);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ruler.drawRuler(gc, w, h);
        return canvas;
    }

//--------------MARKER------------------
    public static Button markerButton = new Button("Add near point");
    public static VBox markerButtonContainer = new VBox(markerButton);
    public static HBox markerContainer = new HBox();

    public VBox getMarkerButtonContainer(){
        markerButton.getStylesheets().add(
                getClass().getResource("/Styles/LensGameStyle.css").toExternalForm()
        );
        markerButtonContainer.setAlignment(Pos.CENTER_LEFT);
        markerButtonContainer.setPadding(new Insets(0,60,0,0));
        return  markerButtonContainer;
    }
    public static Label currentPosLabel = new Label("0.6");
    public static Double markerX = 0.0;
    public static Double markerY = 0.0;
    public static StackPane markerPane = new StackPane();
    public void MarkerMaker(){
        currentPosLabel.setStyle(" -fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-size: 20px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: rgb(241, 226, 221);");
        Image markerPNG = new Image(getClass().getResource("/Assets/marker.png").toExternalForm());
        ImageView markerImageView = new ImageView(markerPNG);
        markerImageView.setFitHeight(90);
        markerImageView.setFitWidth(90);
        markerImageView.setPreserveRatio(true);
        VBox labelVbox = new VBox(currentPosLabel);
        labelVbox.setAlignment(Pos.CENTER);
        labelVbox.setPadding(new Insets(0,0,30,0));
        markerPane.getChildren().addAll(labelVbox, markerImageView);
    }

    public void updateMarker(Double x, Double val){
        String formattedValue = String.format("%.1f", posTOcm(x));
        currentPosLabel.setText(formattedValue);
        markerX = val;
        markerPane.setLayoutX(markerX);
        markerContainer.requestLayout();
    }

    public Double posTOcm(Double pos){
        Double totalPX = 1107.0-367;
        Double PXperCM = totalPX/rulerSIZE;
        Double cmPosition = (pos - 367) / PXperCM;
        return cmPosition;
    }
    public HBox getMarkerContainer(){
        markerContainer.getChildren().addAll(markerPane);
        return  markerContainer;
    }

}
