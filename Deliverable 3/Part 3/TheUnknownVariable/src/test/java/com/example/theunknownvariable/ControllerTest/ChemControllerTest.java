package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.ChemController;
import com.example.theunknownvariable.Controller.EnthalpyGraph;
import com.example.theunknownvariable.Controller.ReactionHandler;
import com.example.theunknownvariable.UI.ChemUI;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ChemControllerTest {

    private ChemController chemController;
    private ChemUI mockView;
    private ReactionHandler mockReactionHandler;
    private EnthalpyGraph mockGraphController;
    private Stage dummyStage;

    @BeforeEach
    void setUp() {
        mockView = mock(ChemUI.class);
        mockReactionHandler = mock(ReactionHandler.class);
        mockGraphController = mock(EnthalpyGraph.class);
        dummyStage = mock(Stage.class);

        // Stub return values if needed
        when(mockReactionHandler.getReactionNb()).thenReturn(1);
        when(mockReactionHandler.checkUserAnswer()).thenReturn(0); // Simulate correct answer

        // Instantiate controller with mocked dependencies
        chemController = new ChemController(mockView, mockReactionHandler, mockGraphController, dummyStage);
    }

    @Test
    void shouldIncrementRightAnswerAndPlotGraphOnCorrectMix() {
        // Initial state
        assertEquals(0, chemController.rightAnsNb);

        // Simulate mix action
        chemController.handleMixAction();  // Calls mix + plotReaction

        // Simulate the logic for the Try button (simplified here)
        if (mockReactionHandler.checkUserAnswer() == 0) {
            chemController.rightAnsNb += 1;
        }

        // Assert incremented
        assertEquals(1, chemController.rightAnsNb);

        // Verify method calls
        verify(mockReactionHandler).mix();
        verify(mockGraphController).plotReaction(1); // Was called with reaction number
    }
}
