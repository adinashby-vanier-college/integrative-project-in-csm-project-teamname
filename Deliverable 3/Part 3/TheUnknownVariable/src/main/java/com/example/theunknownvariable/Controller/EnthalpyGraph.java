package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.UI.ChemUI;

public class EnthalpyGraph {
    private final ChemUI view;
    private int reactionNb;

    public EnthalpyGraph(ChemUI view) {
        this.view = view;
    }


    // Get the current reaction to plot graph
    public void updateReactionNb(int reactionNb) {
        this.reactionNb = reactionNb;
        plotReaction(reactionNb);
    }

    // Return current reaction
    public int getReactionNb() {
        return reactionNb;
    }

    // Plot graph depending on reaction user initiated
    public void plotReaction(int reactionNb) {
        // Clear previous data
        view.clearData();

        // Reset to default range for most reactions
        view.setYAxisRange(-250, 250, 50);

        // Special case for reaction 6 (thermite reaction)
        if (reactionNb == 6) {
            view.setYAxisRange(-500, 500, 100);
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
        view.addDataPoint(0, 0); // Start
        view.addDataPoint(1, 50); // Reactants energy
        // Activation energy
        view.addDataPoint(2, 150); // Peak
        // Products
        view.addDataPoint(3, -100); // Products energy
        view.addDataPoint(4, -100); // End
    }

    // Case 2: Luminol and hydrogen peroxide reaction (chemiluminescence)
    private void plotReaction2() {
        // Reactants
        view.addDataPoint(0, 0); // Start
        view.addDataPoint(1, 50); // Reactants energy
        // Activation energy
        view.addDataPoint(2, 150); // Peak
        // Products
        view.addDataPoint(3, -50); // Products energy
        view.addDataPoint(4, -50); // End
    }

    // Case 3: Luminol and iron oxide reaction
    private void plotReaction3() {
        // Reactants
        view.addDataPoint(0, 0); // Start
        view.addDataPoint(1, 0); // Reactants energy
        // Activation energy
        view.addDataPoint(2, 0); // Peak
        // Products
        view.addDataPoint(3, 0); // Products energy
        view.addDataPoint(4, 0); // End
    }

    // Case 4: Aluminium and silver nitrate reaction (aluminium displacement)
    private void plotReaction4() {
        // Reactants
        view.addDataPoint(0, 0); // Start
        view.addDataPoint(1, 100); // Reactants energy
        // Activation energy
        view.addDataPoint(2, 200); // Peak
        // Products
        view.addDataPoint(3, -150); // Products energy
        view.addDataPoint(4, -150); // End
    }

    // Case 5: Aluminium and hydrogen peroxide reaction
    private void plotReaction5() {
        // Reactants
        view.addDataPoint(0, 0); // Start
        view.addDataPoint(1, 80); // Reactants energy
        // Activation energy
        view.addDataPoint(2, 160); // Peak
        // Products
        view.addDataPoint(3, -120); // Products energy
        view.addDataPoint(4, -120); // End
    }

    private void plotReaction6() {
        // Plot data points
        view.addDataPoint(0, 0); // Start
        view.addDataPoint(1, 200); // Reactants energy
        view.addDataPoint(2, 500); // Activation energy peak
        view.addDataPoint(3, -400); // Products energy
        view.addDataPoint(4, -400); // End
    }

    // Case 7: Sodium chloride and silver nitrate reaction (precipitation reaction)
    private void plotReaction7() {
        // Reactants
        view.addDataPoint(0, 0); // Start
        view.addDataPoint(1, 10); // Reactants energy
        // Activation energy
        view.addDataPoint(2, 30); // Peak
        // Products
        view.addDataPoint(3, -10); // Products energy
        view.addDataPoint(4, -10); // End
    }

    // Case 8: Sodium chloride and hydrogen peroxide reaction
    private void plotReaction8() {
        // Reactants
        view.addDataPoint(0, 0); // Start
        view.addDataPoint(1, 20); // Reactants energy
        // Activation energy
        view.addDataPoint(2, 50); // Peak
        // Products
        view.addDataPoint(3, -20); // Products energy
        view.addDataPoint(4, -20); // End
    }

    // Case 9: Sodium chloride and iron oxide reaction (Salt corrosion)
    private void plotReaction9() {
        // Reactants
        view.addDataPoint(0, 0); // Start
        view.addDataPoint(1, 30); // Reactants energy
        // Activation energy
        view.addDataPoint(2, 70); // Peak
        // Products
        view.addDataPoint(3, -30); // Products energy
        view.addDataPoint(4, -30); // End
    }
}