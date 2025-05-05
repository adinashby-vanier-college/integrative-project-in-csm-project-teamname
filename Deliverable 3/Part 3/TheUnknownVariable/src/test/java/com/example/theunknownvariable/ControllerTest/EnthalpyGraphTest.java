package com.example.theunknownvariable.ControllerTest;

import com.example.theunknownvariable.Controller.EnthalpyGraph;
import com.example.theunknownvariable.UI.ChemUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class EnthalpyGraphTest {

    private ChemUI mockView;
    private EnthalpyGraph graph;

    @BeforeEach
    void setUp() {
        mockView = mock(ChemUI.class);
        graph = new EnthalpyGraph(mockView);
    }

    @Test
    void testUpdateReactionNbCallsPlotReaction() {
        graph.updateReactionNb(1);
        verify(mockView).clearData();
        verify(mockView).setYAxisRange(-250, 250, 50);
        verify(mockView, atLeastOnce()).addDataPoint(anyInt(), anyInt());
    }

    @Test
    void testYAxisRangeThermiteReaction() {
        graph.updateReactionNb(6);  // Thermite
        verify(mockView).setYAxisRange(-500, 500, 100);
    }

    @Test
    void testGetReactionNb() {
        graph.updateReactionNb(4);
        assert(graph.getReactionNb() == 4);
    }

    @Test
    void testPlotReaction1DataPoints() {
        graph.plotReaction(1);
        verify(mockView).addDataPoint(0, 0);
        verify(mockView).addDataPoint(1, 50);
        verify(mockView).addDataPoint(2, 150);
        verify(mockView).addDataPoint(3, -100);
        verify(mockView).addDataPoint(4, -100);
    }

    @Test
    void testPlotReaction3ZeroEnergy() {
        graph.plotReaction(3);
        verify(mockView, times(5)).addDataPoint(anyInt(), eq(0));
    }

    @Test
    void testPlotReaction9Typical() {
        graph.plotReaction(9);
        verify(mockView).addDataPoint(0, 0);
        verify(mockView).addDataPoint(1, 30);
        verify(mockView).addDataPoint(2, 70);
        verify(mockView).addDataPoint(3, -30);
        verify(mockView).addDataPoint(4, -30);
    }
}
