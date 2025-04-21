package com.example.theunknownvariable.Controller;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

public class EnthalpyGraph {
    private LineChart<Number, Number> lineChart;
    private XYChart.Series<Number, Number> series;
    private int reactionNb;
    private NumberAxis yAxis;

    public EnthalpyGraph() {
        //Initialize series
        series = new XYChart.Series<>();
        yAxis = new NumberAxis();
    }

    public HBox displayGraph(int reactionNb){
        this.reactionNb = reactionNb;
        // Create the x and y axes
        final NumberAxis xAxis = new NumberAxis(0, 4, 0.5);
        this.yAxis = new NumberAxis(-250, 250, 50);
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Energy (kJ/mol)");
        xAxis.lookup(".axis-label").setStyle("-fx-text-fill: #e8ceb0;");
        yAxis.lookup(".axis-label").setStyle("-fx-text-fill: #e8ceb0;");

        // Axis and title CSS
        String axisStyle = "-fx-tick-label-fill: #e8ceb0;" +
                "-fx-font-size: 14px;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-weight: bold;";

        xAxis.setStyle(axisStyle);
        yAxis.setStyle(axisStyle);
        lineChart = new LineChart<>(xAxis, yAxis);

        // Chart title CSS
        lineChart.lookup(".chart-title").setStyle("-fx-text-fill: #e8ceb0;");

        // Remove legend
        lineChart.setLegendVisible(false);

        // Graph grid lines CSS
        lineChart.setStyle("-fx-background-color: transparent;");
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        lineChart.lookup(".chart-vertical-grid-lines").setStyle("-fx-stroke: #555555;");
        lineChart.lookup(".chart-horizontal-grid-lines").setStyle("-fx-stroke: #555555;");

        // Add the data series
        series = new XYChart.Series<>();
        series.setName("Energy");
        lineChart.getData().add(series);

        // Plot reaction
        plotReaction(reactionNb);

        // Adjust size of graph
        lineChart.setPrefSize(600, 800);

        // Wrap in HBox
        HBox chartHBox = new HBox(lineChart);
        chartHBox.setPrefWidth(600);
        chartHBox.setPrefHeight(800);

        return chartHBox;
    }

    // Get the current reaction to plot graph
    public void updateReactionNb(int reactionNb){
        this.reactionNb = reactionNb;
        plotReaction(reactionNb);
    }

    //Return current reaction
    public int getReactionNb(){
        return reactionNb;
    }

    // Plot graph depending on reaction user initiated
    public void plotReaction(int reactionNb) {
        //Clear previous data
        series.getData().clear();

        // Reset to default range for most reactions
        yAxis.setLowerBound(-250);
        yAxis.setUpperBound(250);
        yAxis.setTickUnit(50);

        // Special case for reaction 6 (thermite reaction)
        if (reactionNb == 6) {
            yAxis.setLowerBound(-500);
            yAxis.setUpperBound(500);
            yAxis.setTickUnit(100);
        }

        // Show data for different reactions
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
        series.getData().add(new XYChart.Data<>(1, 50)); // Reactants energy
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 150)); // Peak
        // Products
        series.getData().add(new XYChart.Data<>(3, -100)); // Products energy
        series.getData().add(new XYChart.Data<>(4, -100)); // End
    }

    // Case 2: Luminol and hydrogen peroxide reaction (chemiluminescence)
    private void plotReaction2() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 50)); // Reactants energy
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 150)); // Peak
        // Products
        series.getData().add(new XYChart.Data<>(3, -50)); // Products energy
        series.getData().add(new XYChart.Data<>(4, -50)); // End
    }

    // Case 3: Luminol and iron oxide reaction
    private void plotReaction3() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 0)); // Reactants energy
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 0)); // Peak
        // Products
        series.getData().add(new XYChart.Data<>(3, 0)); // Products energy
        series.getData().add(new XYChart.Data<>(4, 0)); // End
    }

    // Case 4: Aluminium and silver nitrate reaction (aluminium displacement)
    private void plotReaction4() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 100)); // Reactants energy
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 200)); // Peak
        // Products
        series.getData().add(new XYChart.Data<>(3, -150)); // Products energy
        series.getData().add(new XYChart.Data<>(4, -150)); // End
    }

    // Case 5: Aluminium and hydrogen peroxide reaction
    private void plotReaction5() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 80)); // Reactants energy
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 160)); // Peak
        // Products
        series.getData().add(new XYChart.Data<>(3, -120)); // Products energy
        series.getData().add(new XYChart.Data<>(4, -120)); // End
    }

    private void plotReaction6() {
        // Plot data points
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 200)); // Reactants energy
        series.getData().add(new XYChart.Data<>(2, 500)); // Activation energy peak
        series.getData().add(new XYChart.Data<>(3, -400)); // Products energy
        series.getData().add(new XYChart.Data<>(4, -400)); // End
    }

    // Case 7: Sodium chloride and silver nitrate reaction (precipitation reaction)
    private void plotReaction7() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 10)); // Reactants energy
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 30)); // Peak
        // Products
        series.getData().add(new XYChart.Data<>(3, -10)); // Products energy
        series.getData().add(new XYChart.Data<>(4, -10)); // End
    }

    // Case 8: Sodium chloride and hydrogen peroxide reaction
    private void plotReaction8() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 20)); // Reactants energy
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 50)); // Peak
        // Products
        series.getData().add(new XYChart.Data<>(3, -20)); // Products energy
        series.getData().add(new XYChart.Data<>(4, -20)); // End
    }

    // Case 9: Sodium chloride and iron oxide reaction (Salt corrosion)
    private void plotReaction9() {
        // Reactants
        series.getData().add(new XYChart.Data<>(0, 0)); // Start
        series.getData().add(new XYChart.Data<>(1, 30)); // Reactants energy
        // Activation energy
        series.getData().add(new XYChart.Data<>(2, 70)); // Peak
        // Products
        series.getData().add(new XYChart.Data<>(3, -30)); // Products energy
        series.getData().add(new XYChart.Data<>(4, -30)); // End
    }

}
