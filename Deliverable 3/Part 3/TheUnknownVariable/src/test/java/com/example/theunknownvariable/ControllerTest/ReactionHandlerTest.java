package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.EnthalpyGraph;
import com.example.theunknownvariable.Controller.ReactionHandler;
import com.example.theunknownvariable.Model.Substance;
import com.example.theunknownvariable.UI.ChemUI;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReactionHandlerTest {

    private ChemUI mockView;
    private EnthalpyGraph mockGraph;
    private ReactionHandler reactionHandler;

    private Label formulaLabel;
    private Label energyTypeLabel;
    private Label factLabel;
    private ImageView beckerImage;

    @BeforeEach
    void setUp() {
        mockView = mock(ChemUI.class);
        mockGraph = mock(EnthalpyGraph.class);

        formulaLabel = new Label();
        energyTypeLabel = new Label();
        factLabel = new Label();
        beckerImage = mock(ImageView.class);

        when(mockView.getFormulaLabel()).thenReturn(formulaLabel);
        when(mockView.getEnergyTypeLabel()).thenReturn(energyTypeLabel);
        when(mockView.getFactLabel()).thenReturn(factLabel);
        when(mockView.getBecker()).thenReturn(beckerImage);

        reactionHandler = new ReactionHandler(mockView, mockGraph);
    }

    @Test
    void testSetAndGetReactionNb() {
        reactionHandler.setReactionNb(5);
        assertEquals(5, reactionHandler.getReactionNb());
    }

    @Test
    void testUpdateLabelsReaction2() {
        reactionHandler.updateLabels(2);
        assertEquals("C8H7N3O2 + H2O2 → catalyst → catalyst + light", formulaLabel.getText());
        assertEquals("Exothermic", energyTypeLabel.getText());
        assertTrue(factLabel.getText().contains("blue glow"));
    }

    @Test
    void testUpdateLabelsDefault() {
        reactionHandler.updateLabels(0);
        assertEquals("Formula", formulaLabel.getText());
        assertEquals("Energy Type", energyTypeLabel.getText());
        assertEquals("Fact", factLabel.getText());
    }

    @Test
    void testCheckUserAnswerFlags() {
        reactionHandler.setReactionNb(2);
        assertEquals(0, reactionHandler.checkUserAnswer()); // First time
        assertEquals(1, reactionHandler.checkUserAnswer()); // Second time

        reactionHandler.setReactionNb(6);
        assertEquals(0, reactionHandler.checkUserAnswer());
        assertEquals(1, reactionHandler.checkUserAnswer());

        reactionHandler.setReactionNb(7);
        assertEquals(0, reactionHandler.checkUserAnswer());
        assertEquals(1, reactionHandler.checkUserAnswer());

        reactionHandler.setReactionNb(9);
        assertEquals(2, reactionHandler.checkUserAnswer()); // Wrong answer
    }

    @Test
    void testMixWithSubstance1And1() {
        Substance.getInstance().setSubstanceNb1(1);
        Substance.getInstance().setSubstanceNb2(1);
        reactionHandler.mix();
        assertEquals(1, reactionHandler.getReactionNb());
        verify(mockGraph).updateReactionNb(1);
        verify(mockGraph).plotReaction(1);
    }

    @Test
    void testMixWithInvalid() {
        Substance.getInstance().setSubstanceNb1(99);
        Substance.getInstance().setSubstanceNb2(99);
        reactionHandler.mix();
        assertEquals(0, reactionHandler.getReactionNb());
        verify(mockGraph).plotReaction(0);
    }
}
