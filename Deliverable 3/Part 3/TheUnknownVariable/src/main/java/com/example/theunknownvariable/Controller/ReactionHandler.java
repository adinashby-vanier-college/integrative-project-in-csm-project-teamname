package com.example.theunknownvariable.Controller;


import com.example.theunknownvariable.Model.Substance;
import com.example.theunknownvariable.UI.ChemUI;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ReactionHandler {

    private final ChemUI view;

    //Number that tracks reaction (1-9)
    private int reactionNb;

    private Stage stage;

    //Flags for right solutions
    boolean flag2 = false;
    boolean flag6 = false;
    boolean flag7 = false;

    //Graph
    private EnthalpyGraph graph;

    public ReactionHandler(ChemUI view, EnthalpyGraph graph) {
        this.view = view;
        this.graph = graph;
        this.reactionNb = 0;

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

    // Setter and getter for number of the reaction
    public void setReactionNb(int reactionNb){
        this.reactionNb = reactionNb;
    }
    public int getReactionNb(){
        return this.reactionNb;
    }

    private void updateBeckerImage(int reaction) {
        if (reaction != 0 && view.getBecker() != null) { // Check if becker is set
            this.reactionNb = reaction;
            view.getBecker().setImage(new Image("becker" + reaction + ".png")); // Update the beaker
        }
    }
    public void updateLabels(int reaction) {
        this.reactionNb = reaction;

        switch (reaction) {
            case 1 -> { // Luminol and silver nitrate reaction (Silver mirror test)
                view.getFormulaLabel().setText("C8H7N3O2 + Ag(NH3)2 → Ag + C8H6N3O2 + NH4");
                view.getEnergyTypeLabel().setText("Slightly exothermic");
                view.getFactLabel().setText("This reaction forms a reflective silver mirror,\noften used in forensic science!");
            }
            case 2 -> { // Luminol and hydrogen peroxide reaction (Chemiluminescence)
                view.getFormulaLabel().setText("C8H7N3O2 + H2O2 → catalyst → catalyst + light");
                view.getEnergyTypeLabel().setText("Exothermic");
                view.getFactLabel().setText("This reaction produces a blue glow! It makes \nfireflies glow!");
            }
            case 3 -> { // Luminol and iron oxide reaction
                view.getFormulaLabel().setText("C8H7N3O2 + Fe2O3 → No reaction");
                view.getEnergyTypeLabel().setText("No energy released");
                view.getFactLabel().setText("Weak surface absorption");
            }
            case 4 -> { // Aluminium and silver nitrate reaction (Aluminium displacement)
                view.getFormulaLabel().setText("Al + 3AgNO3 → Al(NO3)3 + 3Ag");
                view.getEnergyTypeLabel().setText("Exothermic");
                view.getFactLabel().setText("Aluminium displaces silver from solution, \nforming shiny silver crystals!");
            }
            case 5 -> { // Aluminium and hydrogen peroxide reaction
                view.getFormulaLabel().setText("2Al + 3H2O2 → 2Al(OH)3 + 3H2");
                view.getEnergyTypeLabel().setText("Exothermic");
                view.getFactLabel().setText("This reaction releases hydrogen gas and heat, \nsometimes causing foaming.");
            }
            case 6 -> { // Aluminium and iron oxide reaction (Thermite reaction)
                view.getFormulaLabel().setText("2Al + Fe2O3 → 2Fe + Al2O3");
                view.getEnergyTypeLabel().setText("Highly exothermic");
                view.getFactLabel().setText("The thermite reaction produces molten iron, an \nincredibly hot liquid (1205°C-1370°C)...");
            }
            case 7 -> { // Sodium chloride and silver nitrate reaction (Precipitation)
                view.getFormulaLabel().setText("NaCl + AgNO3 → NaNO3 + AgCl↓");
                view.getEnergyTypeLabel().setText("Slightly exothermic");
                view.getFactLabel().setText("In photography, silver chloride darkens when\n exposed to light, capturing images on film \nand revealing the unseen.");
            }
            case 8 -> { // Sodium chloride and hydrogen peroxide reaction
                view.getFormulaLabel().setText("NaCl + H2O2 → Possible slow oxidation reactions");
                view.getEnergyTypeLabel().setText("Very slightly exothermic");
                view.getFactLabel().setText("Hydrogen peroxide decomposes over time in the \npresence of chloride ions.");
            }
            case 9 -> { // Sodium chloride and iron oxide reaction (Salt corrosion)
                view.getFormulaLabel().setText("Fe + O2 + H2O + NaCl → Fe2O3·xH2O (Rust)");
                view.getEnergyTypeLabel().setText("Slightly exothermic");
                view.getFactLabel().setText("Saltwater speeds up rusting, damaging metals \nover time.");
            }
            default -> { // Default case for no reaction
                view.getFormulaLabel().setText("Formula");
                view.getEnergyTypeLabel().setText("Energy Type");
                view.getFactLabel().setText("Fact");
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
