package com.example.theunknownvariable.UITest;

import com.example.theunknownvariable.UI.ChemUI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class ChemUITest {
    private ChemUI chemUI;
    private Stage stage;

    @Start
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        chemUI = new ChemUI(stage);
        chemUI.initialize();
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    void testInitialization(FxRobot robot) {
        // Verify buttons are created
        assertNotNull(chemUI.getSub1Button(), "Substance 1 button should be initialized");
        assertNotNull(chemUI.getMixButton(), "Mix button should be initialized");
        assertNotNull(chemUI.getMenuButton(), "Menu button should be initialized");

        // Verify button styles
        assertThat(chemUI.getSub1Button()).hasStyle("-fx-background-color: transparent;");
        assertThat(chemUI.getMixButton()).hasStyle("-fx-background-color: transparent;");
    }

    @Test
    void testButtonCustomization() {
        Button testButton = chemUI.buttonCustomization("luminol");
        assertNotNull(testButton, "Button customization should return a button");
        assertNotNull(testButton.getGraphic(), "Button should have an image");
        assertTrue(testButton.getGraphic() instanceof ImageView, "Graphic should be ImageView");
    }

    @Test
    void testLabelCustomization() {
        chemUI.labelCustomization();

        assertNotNull(chemUI.getFormulaLabel().getStyle(), "Formula label should have style");
        assertNotNull(chemUI.getEnergyTypeLabel().getStyle(), "Energy label should have style");
        assertTrue(chemUI.getFormulaLabel().getStyle().contains("#c2b19c"), "Should have background color");
    }

    @Test
    void testSceneCreation() {
        Scene instructions = chemUI.displayInstructions();
        assertNotNull(instructions, "Instructions scene should be created");
        assertEquals(1366, instructions.getWidth(), 0.1, "Should have correct width");

        Scene gameScene = chemUI.displayGame();
        assertNotNull(gameScene, "Game scene should be created");
        assertNotNull(gameScene.getRoot(), "Scene should have a root node");
    }

    @Test
    void testTubeDisplay() {
        ImageView tube1 = chemUI.displayTube1();
        assertNotNull(tube1, "Tube 1 should be created");
        assertEquals(320, tube1.getFitHeight(), 0.1, "Should have correct height");

        ImageView tube2 = chemUI.displayTube2();
        assertNotNull(tube2, "Tube 2 should be created");
        assertEquals(-1, tube2.getScaleX(), 0.1, "Should be flipped horizontally");
    }

    @Test
    void testGraphDisplay() {
        HBox graph = chemUI.displayGraph();
        assertNotNull(graph, "Graph should be created");
        assertEquals(600, graph.getPrefWidth(), 0.1, "Should have correct width");

        // Test graph data methods
        chemUI.clearData();
        chemUI.addDataPoint(1.0, 50.0);
        chemUI.setYAxisRange(-100, 100, 25);

        // Can't easily verify visual changes without more TestFX magic
    }

    @Test
    void testGetBeckerImageView() {
        ImageView becker = chemUI.getBeckerImageView();
        assertNotNull(becker, "Becker should be created");
        assertEquals(200, becker.getFitWidth(), 0.1, "Should have correct width");
        assertTrue(becker.isPreserveRatio(), "Should preserve ratio");
    }

    @Test
    void testSwitchScenes(FxRobot robot) {
        Scene testScene = new Scene(new VBox(), 800, 600);
        chemUI.switchScenes(testScene);

        robot.sleep(1000); // Wait for scene switch
        assertEquals(testScene, stage.getScene(), "Stage should have new scene");
    }

    @Test
    void testAllButtonsExist(FxRobot robot) {
        // Verify all substance buttons
        assertNotNull(chemUI.getSub1Button());
        assertNotNull(chemUI.getSub2Button());
        assertNotNull(chemUI.getSub3Button());
        assertNotNull(chemUI.getSub4Button());
        assertNotNull(chemUI.getSub5Button());
        assertNotNull(chemUI.getSub6Button());

        // Verify action buttons
        assertNotNull(chemUI.getMixButton());
        assertNotNull(chemUI.getTryButton());
        assertNotNull(chemUI.getMenuButton());
    }

    @Test
    void testInfoDisplay() {
        VBox infoBox = chemUI.displayInfo();
        assertNotNull(infoBox, "Info box should be created");
        assertEquals(3, infoBox.getChildren().size(), "Should contain three labels");
    }
}