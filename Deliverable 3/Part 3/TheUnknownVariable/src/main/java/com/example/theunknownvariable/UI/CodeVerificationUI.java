package com.example.theunknownvariable.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CodeVerificationUI {

    private static final String CORRECT_CODE = "CS2025";

    public Scene createCodeScene(Stage primaryStage) {
        // Load background image
        Image backgroundImage = new Image("loginBG.png");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(600);
        backgroundView.setFitHeight(400);
        backgroundView.setPreserveRatio(true);

        // Color palette
        String darkColor = "#342f28";
        String midColor = "#939086";
        String lightColor = "#e5e3da";

        // Label styling
        Label instructionLabel = new Label("Enter the code provided by your professor:");
        instructionLabel.setTextFill(Color.web(darkColor));
        instructionLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 22));

        // TextFields
        TextField visibleField = new TextField();
        visibleField.setPromptText("Enter code here");
        visibleField.setMaxWidth(400);
        visibleField.setFont(Font.font(14));
        visibleField.setStyle("-fx-background-color: " + midColor + "; -fx-text-fill: " + darkColor + ";");


        PasswordField hiddenField = new PasswordField();
        hiddenField.setPromptText("Enter code here");
        hiddenField.setMaxWidth(400);
        hiddenField.setFont(Font.font(14));
        hiddenField.setStyle("-fx-background-color: " + lightColor + "; -fx-text-fill: " + darkColor + ";");

        // Toggle checkbox
        CheckBox showCodeCheck = new CheckBox("Show code");
        showCodeCheck.setTextFill(Color.web(lightColor));
        showCodeCheck.setFont(Font.font("Times New Roman", 17));
        // Bind visibility
        visibleField.managedProperty().bind(showCodeCheck.selectedProperty());
        visibleField.visibleProperty().bind(showCodeCheck.selectedProperty());
        hiddenField.managedProperty().bind(showCodeCheck.selectedProperty().not());
        hiddenField.visibleProperty().bind(showCodeCheck.selectedProperty().not());
        visibleField.textProperty().bindBidirectional(hiddenField.textProperty());

        // Result label
        Label resultLabel = new Label();
        resultLabel.setTextFill(Color.web(lightColor));
        resultLabel.setFont(Font.font("Arial", 17));

        // Enter button
        Button enterButton = new Button("Enter");
        enterButton.setStyle(
                "-fx-background-color: " + midColor + ";" +
                        "-fx-text-fill: " + darkColor + ";" +
                        "-fx-font-size: 14;" +
                        "-fx-background-radius: 10;"
        );
        enterButton.setOnAction(e -> {
            String enteredCode = showCodeCheck.isSelected() ? visibleField.getText() : hiddenField.getText();
            if (enteredCode.trim().isEmpty()) {
                resultLabel.setText("⚠ Please enter the code.");
            } else if (CORRECT_CODE.equals(enteredCode)) {
                resultLabel.setText("✅ Code correct! You may continue.");
            } else {
                resultLabel.setText("❌ Incorrect code. Please try again.");
            }
        });

        // Layout
        VBox contentBox = new VBox(10, instructionLabel, hiddenField, visibleField, showCodeCheck, enterButton, resultLabel);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setStyle("-fx-background-color: rgba(52,47,40,0.6); -fx-padding: 20");

        StackPane root = new StackPane(backgroundView, contentBox);
        root.setPrefSize(500, 300);

        return new Scene(root);
    }
}