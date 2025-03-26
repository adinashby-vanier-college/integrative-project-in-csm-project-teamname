package com.example.theunknownvariable.Controller;

import Model.Substance;
import com.example.theunknownvariable.UI.ChemUI;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ReactionHandler {

    //Number that tracks reaction (1-9)
    private int reactionNb;

    //Labels
    private Label formulaLabel;
    private Label energyTypeLabel;
    private Label factLabel;

    private Stage stage;

    //Flags for right solutions
    boolean flag2 = false;
    boolean flag6 = false;
    boolean flag7 = false;

    //Becker
    private ImageView becker;

    //Graph
    private EnthalpyGraph graph; // Store reference to graph

    public ReactionHandler(EnthalpyGraph graph) {
        this.graph = graph;
        this.reactionNb = 0;

        // Initialize the labels once
        this.formulaLabel = new Label("Formula");
        this.energyTypeLabel = new Label("Energy Type");
        this.factLabel = new Label("Fact");

        // Apply styling to labels
        String labelStyle = "-fx-background-color: #c2b19c;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10 40;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-text-fill: #2e2e2e;" +
                "-fx-font-weight: bold;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 10, 0, 0, 5);" +
                "-fx-pref-width: 630px;"+"-fx-alignment: center;";

        formulaLabel.setStyle(labelStyle+"-fx-font-size: 20px;");
        energyTypeLabel.setStyle(labelStyle+"-fx-font-size: 22px;");
        factLabel.setStyle(labelStyle+"-fx-font-size: 18px;");
    }


    public void mix(){
        //Substances
        int substanceNb1 = Substance.getInstance().getSubstanceNb1();
        int substanceNb2 = Substance.getInstance().getSubstanceNb2();

        if(substanceNb1==1 && substanceNb2==1){
            setReactionNb(1);
            graph.updateReactionNb(1);
        }
        else if(substanceNb1==1 && substanceNb2==2){
            setReactionNb(2);
        }
        else if(substanceNb1==1 && substanceNb2==3){
            setReactionNb(3);
        }
        else if(substanceNb1==2 && substanceNb2==1){
            setReactionNb(4);
        }
        else if(substanceNb1==2 && substanceNb2==2){
            setReactionNb(5);
        }
        else if(substanceNb1==2 && substanceNb2==3){
            setReactionNb(6);
        }
        else if(substanceNb1==3 && substanceNb2==1){
            setReactionNb(7);
        }
        else if(substanceNb1==3 && substanceNb2==2){
            setReactionNb(8);
        }
        else if(substanceNb1==3 && substanceNb2==3){
            setReactionNb(9);
        }

        //Update dynamically graph, labels and becker
        graph.plotReaction(reactionNb);
        updateLabels(reactionNb);
        updateBeckerImage(reactionNb);
    }
    public void setReactionNb(int reactionNb){
        this.reactionNb = reactionNb;
    }
    public int getReactionNb(){
        return this.reactionNb;
    }
    // Getters for existing labels
    public Label getFormulaLabel() {
        return formulaLabel;
    }

    public Label getEnergyTypeLabel() {
        return energyTypeLabel;
    }

    public Label getFactLabel() {
        return factLabel;
    }
    // Setter for the becker ImageView
    public void setBecker(ImageView becker) {
        this.becker = becker;
    }

    private void updateBeckerImage(int reaction) {
        if (reaction != 0 && becker != null) { // Check if becker is set
            this.reactionNb = reaction;
            becker.setImage(new Image("becker" + reaction + ".png")); // Update the beaker
        }
    }
    public void updateLabels(int reaction) {
        this.reactionNb = reaction;

        switch (reaction) {
            case 1 -> { // Luminol and silver nitrate reaction (Silver mirror test)
                formulaLabel.setText("C8H7N3O2 + Ag(NH3)2 → Ag + C8H6N3O2 + NH4");
                energyTypeLabel.setText("Slightly exothermic");
                factLabel.setText("This reaction forms a reflective silver mirror,\noften used in forensic science!");
            }
            case 2 -> { // Luminol and hydrogen peroxide reaction (Chemiluminescence)
                formulaLabel.setText("C8H7N3O2 + H2O2 → catalyst → catalyst + light");
                energyTypeLabel.setText("Exothermic");
                factLabel.setText("This reaction produces a blue glow! It makes \nfireflies glow!");
            }
            case 3 -> { // Luminol and iron oxide reaction
                formulaLabel.setText("C8H7N3O2 + Fe2O3 → No reaction");
                energyTypeLabel.setText("No energy released");
                factLabel.setText("Weak surface absorption");
            }
            case 4 -> { // Aluminium and silver nitrate reaction (Aluminium displacement)
                formulaLabel.setText("Al + 3AgNO3 → Al(NO3)3 + 3Ag");
                energyTypeLabel.setText("Exothermic");
                factLabel.setText("Aluminium displaces silver from solution, \nforming shiny silver crystals!");
            }
            case 5 -> { // Aluminium and hydrogen peroxide reaction
                formulaLabel.setText("2Al + 3H2O2 → 2Al(OH)3 + 3H2");
                energyTypeLabel.setText("Exothermic");
                factLabel.setText("This reaction releases hydrogen gas and heat, \nsometimes causing foaming.");
            }
            case 6 -> { // Aluminium and iron oxide reaction (Thermite reaction)
                formulaLabel.setText("2Al + Fe2O3 → 2Fe + Al2O3");
                energyTypeLabel.setText("Highly exothermic");
                factLabel.setText("The thermite reaction produces molten iron, an \nincredibly hot liquid (1205°C-1370°C)...");
            }
            case 7 -> { // Sodium chloride and silver nitrate reaction (Precipitation)
                formulaLabel.setText("NaCl + AgNO3 → NaNO3 + AgCl↓");
                energyTypeLabel.setText("Slightly exothermic");
                factLabel.setText("In photography, silver chloride darkens when exposed \nto light, capturing images on film");
            }
            case 8 -> { // Sodium chloride and hydrogen peroxide reaction
                formulaLabel.setText("NaCl + H2O2 → Possible slow oxidation reactions");
                energyTypeLabel.setText("Very slightly exothermic");
                factLabel.setText("Hydrogen peroxide decomposes over time in the \npresence of chloride ions.");
            }
            case 9 -> { // Sodium chloride and iron oxide reaction (Salt corrosion)
                formulaLabel.setText("Fe + O2 + H2O + NaCl → Fe2O3·xH2O (Rust)");
                energyTypeLabel.setText("Slightly exothermic");
                factLabel.setText("Saltwater speeds up rusting, damaging metals \nover time.");
            }
            default -> { // Default case for no reaction
                formulaLabel.setText("Formula");
                energyTypeLabel.setText("Energy Type");
                factLabel.setText("Fact");
            }
        }
    }

    public int checkUserAnswer(){
        if(reactionNb==2&& !flag2){
            flag2=true;
            return 0;
        }
        else if(reactionNb==6&& !flag6){
            flag6=true;
            return 0;
        }
        else if(reactionNb==7&& !flag7){
            flag7=true;
            return 0;
        }
        else if(reactionNb==2&& flag2)return 1;
        else if(reactionNb==6&& flag6)return 1;
        else if(reactionNb==7&& flag7) return 1;
        else return 2;
    }


}
