package com.example.theunknownvariable.Controller;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

public class EnthalpyGraph {
    private LineChart<Number, Number> lineChart;
    private XYChart.Series<Number, Number> series;
    private int reactionNb;

    public EnthalpyGraph() {
        series = new XYChart.Series<>(); // Initialize series to prevent null pointer exceptions
    }

    public HBox displayGraph(int reactionNb){
        this.reactionNb = reactionNb;
        // Create the x and y axes
        final NumberAxis xAxis = new NumberAxis(0, 4, 0.5); // Adjusted range for better spacing
        final NumberAxis yAxis = new NumberAxis(-250, 250, 50); // Ensure proper scaling
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Energy (kJ/mol)");
        xAxis.lookup(".axis-label").setStyle("-fx-text-fill: #e8ceb0;");
        yAxis.lookup(".axis-label").setStyle("-fx-text-fill: #e8ceb0;");

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

        // Remove the legend
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

        //Plot reaction
        plotReaction(reactionNb);

        //Adjust size
        lineChart.setPrefSize(600, 800); // Adjust width and height as needed

        // Wrap in HBox
        HBox chartHBox = new HBox(lineChart);
        chartHBox.setPrefWidth(600);  // Adjust width
        chartHBox.setPrefHeight(800); // Adjust height

        return chartHBox;
    }

    public void updateReactionNb(int reactionNb){
        this.reactionNb = reactionNb;
        plotReaction(reactionNb);
    }
    public int getReactionNb(){
        return reactionNb;
    }

    public void plotReaction(int reactionNb) {
        series.getData().clear(); // Clear previous data

        // Simulate data for different reactions
        switch (reactionNb) {
            case 1:
                plotReaction1();
                break;
            case 2:
                plotReaction2();
                break;
            case 3:
                plotReaction3();
                break;
            case 4:
                plotReaction4();
                break;
            case 5:
                plotReaction5();
                break;
            case 6:
                plotReaction6();
                break;
            case 7:
                plotReaction7();
                break;
            case 8:
                plotReaction8();
                break;
            case 9:
                plotReaction9();
                break;
        }
    }

    // Case 1: Luminol and silver nitrate reaction (Silver mirror test)
    private void plotReaction1() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 50)); // Reactants energy (estimated)
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 150)); // Peak (estimated activation energy)
        // Products
        series.getData().add(new XYChart.Data<>(3, -100)); // Products energy (estimated)
        series.getData().add(new XYChart.Data<>(4, -100)); // End
    }

    // Case 2: Luminol and hydrogen peroxide reaction (chemiluminescence)
    private void plotReaction2() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 50)); // Reactants energy (estimated)
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 150)); // Peak (estimated activation energy)
        // Products
        series.getData().add(new XYChart.Data<>(3, -50)); // Products energy (estimated)
        series.getData().add(new XYChart.Data<>(4, -50)); // End
    }

    // Case 3: Luminol and iron oxide reaction
    private void plotReaction3() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 0)); // Reactants energy (estimated)
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 0)); // Peak (estimated activation energy)
        // Products
        series.getData().add(new XYChart.Data<>(3, 0)); // Products energy (estimated)
        series.getData().add(new XYChart.Data<>(4, 0)); // End
    }

    // Case 4: Aluminium and silver nitrate reaction (aluminium displacement)
    private void plotReaction4() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 100)); // Reactants energy (estimated)
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 200)); // Peak (estimated activation energy)
        // Products
        series.getData().add(new XYChart.Data<>(3, -150)); // Products energy (estimated)
        series.getData().add(new XYChart.Data<>(4, -150)); // End
    }

    // Case 5: Aluminium and hydrogen peroxide reaction
    private void plotReaction5() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 80)); // Reactants energy (estimated)
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 160)); // Peak (estimated activation energy)
        // Products
        series.getData().add(new XYChart.Data<>(3, -120)); // Products energy (estimated)
        series.getData().add(new XYChart.Data<>(4, -120)); // End
    }

    // Case 6: Aluminium and iron oxide reaction (thermite reaction)
    private void plotReaction6() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 200)); // Reactants energy (estimated)
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 500)); // Peak (estimated activation energy)
        // Products
        series.getData().add(new XYChart.Data<>(3, -400)); // Products energy (estimated)
        series.getData().add(new XYChart.Data<>(4, -400)); // End
    }

    // Case 7: Sodium chloride and silver nitrate reaction (precipitation reaction)
    private void plotReaction7() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 10)); // Reactants energy (estimated)
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 30)); // Peak (estimated activation energy)
        // Products
        series.getData().add(new XYChart.Data<>(3, -10)); // Products energy (estimated)
        series.getData().add(new XYChart.Data<>(4, -10)); // End
    }

    // Case 8: Sodium chloride and hydrogen peroxide reaction
    private void plotReaction8() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 20)); // Reactants energy (estimated)
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 50)); // Peak (estimated activation energy)
        // Products
        series.getData().add(new XYChart.Data<>(3, -20)); // Products energy (estimated)
        series.getData().add(new XYChart.Data<>(4, -20)); // End
    }

    // Case 9: Sodium chloride and iron oxide reaction (Salt corrosion)
    private void plotReaction9() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 30)); // Reactants energy (estimated)
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 70)); // Peak (estimated activation energy)
        // Products
        series.getData().add(new XYChart.Data<>(3, -30)); // Products energy (estimated)
        series.getData().add(new XYChart.Data<>(4, -30)); // End
    }

}
