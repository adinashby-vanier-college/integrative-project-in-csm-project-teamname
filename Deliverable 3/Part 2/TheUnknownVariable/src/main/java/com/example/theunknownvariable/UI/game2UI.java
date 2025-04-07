package com.example.theunknownvariable.UI;

import com.example.theunknownvariable.Controller.game2handler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class game2UI {
    private game2handler controller;
    private Label questionLabel;
    private RadioButton[] optionButtons;
    private ToggleGroup optionsGroup;
    private Button submitButton;
    private Label feedbackLabel;
    private Stage quizStage;

    public game2UI() {
        controller = new game2handler();
        quizStage = new Stage();
        quizStage.initModality(Modality.APPLICATION_MODAL);
        quizStage.setTitle("Projectile Motion Quiz");

        StackPane root = createRootLayout();
        Scene scene = new Scene(root, 600, 400);
        quizStage.setScene(scene);

        loadQuestion();
    }

    /**
     * Displays the quiz window.
     */
    public void showQuiz() {
        quizStage.showAndWait();
    }

    /**
     * Creates the root layout with background and quiz content.
     */
    private StackPane createRootLayout() {
        StackPane root = new StackPane();

        // Load background image
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/game2B.png")));
        background.setFitWidth(640);
        background.setFitHeight(440);
        background.setPreserveRatio(true);

        VBox content = new VBox(20);
        content.setStyle("-fx-padding: 40px; -fx-alignment: center;");

        questionLabel = createQuestionLabel();
        optionButtons = createOptionButtons();
        submitButton = createSubmitButton();
        feedbackLabel = createFeedbackLabel();

        content.getChildren().addAll(questionLabel, submitButton, feedbackLabel);
        content.getChildren().addAll(optionButtons);

        root.getChildren().addAll(background, content);
        return root;
    }

    /**
     * Creates and styles the question label.
     */
    private Label createQuestionLabel() {
        Label label = new Label();
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #713939;");
        return label;
    }

    /**
     * Creates radio buttons for answer options.
     */
    private RadioButton[] createOptionButtons() {
        RadioButton[] buttons = new RadioButton[4];
        optionsGroup = new ToggleGroup();

        for (int i = 0; i < 4; i++) {
            buttons[i] = new RadioButton();
            buttons[i].setToggleGroup(optionsGroup);
            buttons[i].setStyle("-fx-font-size: 16px; -fx-text-fill: #713939; -fx-font-weight: bold;");
        }

        return buttons;
    }

    /**
     * Creates the submit button with an image.
     */
    private Button createSubmitButton() {
        ImageView submitImage = new ImageView(new Image(getClass().getResourceAsStream("/Submit.png")));
        submitImage.setFitWidth(150);
        submitImage.setFitHeight(150);
        submitImage.setPreserveRatio(true);
        Button button = new Button("", submitImage);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setOnAction(e -> handleAnswer());
        button.setOnMouseEntered(e -> submitImage.setOpacity(0.7));
        button.setOnMouseExited(e -> submitImage.setOpacity(1.0));
        return button;
    }

    /**
     * Creates a feedback label for answer validation.
     */
    private Label createFeedbackLabel() {
        Label label = new Label();
        label.setStyle("-fx-font-size: 14px; -fx-text-fill: #713939;");
        return label;
    }

    /**
     * Loads the current question and options into the UI.
     */
    private void loadQuestion() {
        questionLabel.setText(controller.getQuestionText());

        String[] options = controller.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
        }

        feedbackLabel.setText("");
        optionsGroup.selectToggle(null);
    }

    /**
     * Handles answer submission and transitions to the next question or final result.
     */
    private void handleAnswer() {
        RadioButton selected = (RadioButton) optionsGroup.getSelectedToggle();
        if (selected == null) {
            feedbackLabel.setText("Please select an answer!");
            return;
        }

        int selectedIndex = getSelectedAnswerIndex(selected);

        if (controller.checkAnswer(selectedIndex)) {
            feedbackLabel.setText("Correct!");
        } else {
            feedbackLabel.setText("Incorrect!");
        }

        if (controller.nextQuestion()) {
            loadQuestion();
        } else {
            showFinalResult();
        }
    }

    /**
     * Determines the selected answer's index.
     */
    private int getSelectedAnswerIndex(RadioButton selected) {
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i] == selected) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Displays the final quiz results and provides a clue if enough answers are correct.
     */
    public void showFinalResult() {
        VBox resultLayout = new VBox(20);
        resultLayout.setStyle("-fx-padding: 40px; -fx-alignment: center; -fx-background-color: #D1C5AB;");

        String resultText = "You got " + controller.getCorrectAnswersCount() + "/" + controller.getTotalQuestions() + " correct!";
        Label resultLabel = new Label(resultText);
        resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #713939;");

        Label clueLabel = new Label();
        if (controller.getCorrectAnswersCount() >= 3) {
            clueLabel.setText("Here is your final clue: The victim's height was 180cm");
        } else {
            clueLabel.setText("You got less than 3 correct. No final clue.");
        }
        clueLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #713939;");

        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-font-size: 14px; -fx-background-color: #713939; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-border-radius: 5px;");
        returnButton.setOnAction(e -> quizStage.close());

        resultLayout.getChildren().addAll(resultLabel, clueLabel, returnButton);

        Scene resultScene = new Scene(resultLayout, 600, 300);
        quizStage.setScene(resultScene);
        quizStage.show();
    }
}
