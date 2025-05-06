package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.Model.Substance;
import com.example.theunknownvariable.UI.ChemUI;
import com.example.theunknownvariable.UI.MainPage;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class ChemController {
    private final ChemUI view;
    private final ReactionHandler reactionController;
    private final EnthalpyGraph graphController;

    private Button selectedButtonGroup1 = null;
    private Button selectedButtonGroup2 = null;
    public int rightAnsNb = 0;
    public int wrongAnsNb = 0;

    private final Stage stage;

    public ChemController(ChemUI view, ReactionHandler reactionController, EnthalpyGraph graphController, Stage stage) {
        this.view = view;
        this.reactionController = reactionController;
        this.graphController = graphController;
        this.stage = stage;
        initialize();
        eventHandling();
    }

    private void initialize() {
        eventHandling();
    }

    public void eventHandling() {
        // Safely handle Menu button
        if (view.getMenuButton() != null) {
            view.getMenuButton().setOnAction(event -> {
                MainPage mainPage = new MainPage(stage);
                Scene scene = mainPage.displayMainPage();
                view.switchScenes(scene);
            });
        }

        // Safely handle Hint button
        if (view.getHintButton() != null) {
            view.getHintButton().setOnAction(event -> {
                Stage hintStage = new Stage();
                Scene scene = view.displayHint();
                hintStage.setScene(scene);
                hintStage.centerOnScreen();
                hintStage.showAndWait();
            });
        }

        // Safely handle Group 1 buttons
        if (view.getSub1Button() != null) {
            handleButtonClick(view.getSub1Button(), 1, 0, true);
        }
        if (view.getSub2Button() != null) {
            handleButtonClick(view.getSub2Button(), 2, 0, true);
        }
        if (view.getSub3Button() != null) {
            handleButtonClick(view.getSub3Button(), 3, 0, true);
        }

        // Safely handle Group 2 buttons
        if (view.getSub4Button() != null) {
            handleButtonClick(view.getSub4Button(), 0, 1, false);
        }
        if (view.getSub5Button() != null) {
            handleButtonClick(view.getSub5Button(), 0, 2, false);
        }
        if (view.getSub6Button() != null) {
            handleButtonClick(view.getSub6Button(), 0, 3, false);
        }

        // Safely handle Mix button
        if (view.getMixButton() != null && reactionController != null && graphController != null) {
            view.getMixButton().setOnAction(event -> {
                reactionController.mix();
                graphController.plotReaction(reactionController.getReactionNb());
            });
        }

        // Safely handle Instructions button
        if (view.getInstructionsButton() != null) {
            view.getInstructionsButton().setOnAction(event -> {
                System.out.println("Yess button is clicked and we know that");
                Scene scene = view.displayGame();
                view.switchScenes(scene);
            });
        }

        // Safely handle Try button
        if (view.getTryButton() != null && reactionController != null && graphController != null && view != null) {
            view.getTryButton().setOnAction(event -> {
                reactionController.mix();
                graphController.plotReaction(reactionController.getReactionNb());

                //Check if the reaction is correct
                if(reactionController.checkUserAnswer() == 0) {
                    rightAnsNb += 1;
                    if (view.getRightAnswers() != null) {
                        view.getRightAnswers().setText(rightAnsNb + "/3");
                    }
                    view.displayImage(view.getGameScene(), "rightScenario.png", 300, 2, false);
                }
                //Check if the reaction is incorrect
                else if(reactionController.checkUserAnswer() == 2) {
                    view.displayImage(view.getGameScene(), "wrongScenario.png", 300, 2, false);
                    wrongAnsNb += 1;
                    if (view.getWrongAnswers() != null) {
                        view.getWrongAnswers().setText(wrongAnsNb + "/3");
                    }
                }

                //Check if user got all the good answers
                if (rightAnsNb == 3 && GameStateManager.getInstance() != null) {
                    GameStateManager.getInstance().unlockClue3();
                    GameStateManager.getInstance().lockGame3();
                    view.displayImage(view.getGameScene(), "clueScene.png", 800, 5, true);
                }

                //Check if user used 3 wrong attempts
                if (wrongAnsNb == 3 && GameStateManager.getInstance() != null) {
                    GameStateManager.getInstance().lockGame3();
                    view.displayImage(view.getGameScene(), "gameOver.png", 800, 5, true);
                }
            });
        }
    }
    private void handleButtonClick(Button button, int newTube1, int newTube2, boolean isGroup1) {
        button.setOnAction(event -> {
            if (isGroup1) {
                // Reset previous selection in Group 1
                if (selectedButtonGroup1 != null) {
                    selectedButtonGroup1.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                }
                selectedButtonGroup1 = button;
            } else {
                // Reset previous selection in Group 2
                if (selectedButtonGroup2 != null) {
                    selectedButtonGroup2.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                }
                selectedButtonGroup2 = button;
            }

            // Apply highlight effect to the selected button
            button.setStyle("-fx-effect: dropshadow(gaussian, rgba(245, 245, 220, 0.8), 10, 0.5, 0, 0);" +
                    "-fx-background-color: transparent; -fx-border-color: transparent;");

            // Update tube images
            updateTubeImage(newTube1, newTube2);

            // Update the selected substance in the Substance instance
            if (isGroup1) {
                Substance.getInstance().setSubstanceNb1(newTube1);
            } else {
                Substance.getInstance().setSubstanceNb2(newTube2);
            }
        });
    }

    public void updateTubeImage(int newTube1, int newTube2) {
        if (newTube1 != 0) {
            //Tube number (left and right)
            view.getTube1ImageView().setImage(new Image("tubeFINAL" + newTube1 + ".png"));
        }
        if (newTube2 != 0) {
            view.getTube2ImageView().setImage(new Image("tubeFINAL" + (newTube2 +3) + ".png"));
        }
    }

    // In ChemController.java
    public void handleMixAction() {
        reactionController.mix();
        graphController.plotReaction(reactionController.getReactionNb());
    }

    public void handleSubstanceSelection(int substanceNb, boolean isGroup1) {
        if (isGroup1) {
            Substance.getInstance().setSubstanceNb1(substanceNb);
        } else {
            Substance.getInstance().setSubstanceNb2(substanceNb);
        }
    }

}
