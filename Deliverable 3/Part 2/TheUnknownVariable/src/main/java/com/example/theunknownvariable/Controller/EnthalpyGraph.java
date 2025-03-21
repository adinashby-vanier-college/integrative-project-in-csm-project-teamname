package com.example.theunknownvariable.Controller;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

public class EnthalpyGraph {
    private LineChart<Number, Number> lineChart;
    private XYChart.Series<Number, Number> series;

    public HBox displayGraph(){
        // Create the x and y axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Energy (kJ/mol)");

        // Change the axis and title colors to #e8ceb0
        String axisStyle = "-fx-tick-label-fill: #e8ceb0;" +   // Numbers color
                "-fx-font-size: 14px;" +            // Font size
                "-fx-font-family: 'Courier New';" + // Mystery font
                "-fx-font-weight: bold;";           // Bold text

        xAxis.setStyle(axisStyle);
        yAxis.setStyle(axisStyle);
        lineChart = new LineChart<>(xAxis, yAxis);

        // Change chart title color
        lineChart.lookup(".chart-title").setStyle("-fx-text-fill: #e8ceb0;");

        // Remove the legend (optional for cleaner design)
        lineChart.setLegendVisible(false);

        // Remove white grid lines and make them darker
        lineChart.setStyle("-fx-background-color: transparent;");
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        lineChart.lookup(".chart-vertical-grid-lines").setStyle("-fx-stroke: #555555;");
        lineChart.lookup(".chart-horizontal-grid-lines").setStyle("-fx-stroke: #555555;");

        // Add the data series
        series = new XYChart.Series<>();
        series.setName("Energy");
        lineChart.getData().add(series);

        // Wrap in HBox
        HBox chartHBox = new HBox(lineChart);

        return chartHBox;
    }

}
