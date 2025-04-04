package com.example.theunknownvariable;// GameUI.java
// GameUI.java
import com.example.theunknownvariable.UI.Hangman;
import com.example.theunknownvariable.UI.MainPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.example.theunknownvariable.Model.MathProblem;


public class GameUI {

    private Stage stage;
    //    @Override
//    public void start(Stage primaryStage) {
//
//    }
    public GameUI(Stage stage) {
        this.stage = stage;
    }
    public Scene displayMathGame(Stage primaryStage) {
        // Create the instructions screen
        VBox instructionsScreen = createInstructionsScreen(primaryStage);

        // Set up the instructions scene
        return new Scene(instructionsScreen, 1366, 768);
    }

    private VBox createInstructionsScreen(Stage primaryStage) {
        VBox layout = new VBox(20); // Spacing between elements
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-alignment: center; " +
                "-fx-background-image: url('math_instructions.png'); " +
                "-fx-background-size: cover;"); // Background image for the instructions screen

        // Add custom font
        Font cinzelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Cinzel-Regular.ttf"), 24);

        // Add instructions text
        Label instructions = new Label("Welcome to the Hangman Game!\n\n"
                + "Instructions:\n"
                + "1. Click on an empty letter space.\n"
                + "2. Solve the problem to reveal a letter.\n"
                + "3. You have a limited number of attempts.\n"
                + "4. Avoid making too many mistakes, or you'll lose!\n\n"
                + "Click the button below to start.");

        instructions.setFont(cinzelFont); // Apply the custom font
        instructions.setStyle("-fx-text-fill: white; -fx-text-alignment: center;");

        // Add the "Yes, I Understand" button with an image
        Button proceedButton = new Button();
        proceedButton.setStyle("-fx-background-image: url('understand.png'); "
                + "-fx-background-size: 100% 100%; " // Scale image to fit the button
                + "-fx-background-color: transparent; -fx-padding: 0;");
        proceedButton.setPrefSize(300, 150); // Adjust size to prevent cutting off
        proceedButton.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE); // Prevent over-scaling

        // Add action to transition to the main game
        proceedButton.setOnAction(event -> {
            // Transition to the main game scene
            BorderPane mainGame = createMainGameUI(); // Create the main game UI
            Scene mainGameScene = new Scene(mainGame, 1366, 768);
            primaryStage.setScene(mainGameScene);
        });

        // Add elements to the layout
        layout.getChildren().addAll(instructions, proceedButton);
        return layout;
    }


    private BorderPane createMainGameUI() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-image: url('background_game.png'); -fx-background-size: cover;");

        // Hangman Section
        Pane hangmanPane = new Pane();
        Hangman hangman = new Hangman(hangmanPane);
        root.setLeft(hangmanPane);
        BorderPane.setMargin(hangmanPane, new Insets(50));

        // Word Section (Right)
        HBox wordBox = createWordBoxes();
        root.setRight(wordBox);
        BorderPane.setMargin(wordBox, new Insets(350, 50, 0, 20)); // Adjusted margin to balance layout

        // Bottom Options
        VBox bottomOptions = createBottomOptions();
        root.setBottom(bottomOptions);
        BorderPane.setMargin(bottomOptions, new Insets(0, 50, 200, 20)); // Reduced bottom margin

        return root;
    }


//        // Main Pane
//        BorderPane root = new BorderPane();
//        root.setStyle("-fx-background-image: url('background_game.png'); -fx-background-size: cover;");
//
//        // Hangman Section
//        Pane hangmanPane = new Pane();
//        Hangman hangman = new Hangman(hangmanPane);
//        root.setLeft(hangmanPane);
//        BorderPane.setMargin(hangmanPane, new Insets(50));
//
//        // Word Section (Right)
//        HBox wordBox = createWordBoxes();
//        root.setRight(wordBox);
//        BorderPane.setMargin(wordBox, new Insets(350, 50, 0, 20)); // Adjusted margin to balance layout
//
//
//        // Bottom Options
//        VBox bottomOptions = createBottomOptions();
//        root.setBottom(bottomOptions);
//        BorderPane.setMargin(bottomOptions, new Insets(0, 50, 200, 20)); // Reduced bottom margin
//
//
//        // Main Scene
//        Scene scene = new Scene(root, 1366, 768);
//        primaryStage.setTitle("Hangman Game");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    private HBox createWordBoxes() {
        HBox wordBox = new HBox(20); // Spacing between boxes
        wordBox.setStyle("-fx-alignment: center; -fx-padding: 20;");

        for (int i = 0; i < 7; i++) {
            StackPane box = new StackPane();
            box.setMinSize(70, 150); // Overall size of the letter box

            // Add background image with padding above the line
            Region background = new Region();
            background.setStyle("-fx-background-image: url('letter_box.jpg'); "
                    + "-fx-background-size: 70px 60px; " // Resize the image
                    + "-fx-background-repeat: no-repeat; "
                    + "-fx-background-position: center top;"); // Image placed at the top

            // Red underline positioned lower, with padding
            Line underline = new Line(0, 140, 70, 140); // Adjusted Y-coordinate for more separation
            underline.setStrokeWidth(4);
            underline.setStroke(Color.RED);

            // Invisible spacer region to create padding
            Region spacer = new Region();
            spacer.setMinHeight(10); // 10 pixels of padding between image and underline

            Region clickableArea = new Region();
            clickableArea.setStyle("-fx-background-color: transparent;");
            clickableArea.setMinSize(70, 150); // Match the box size

            clickableArea.setOnMouseClicked(event -> openNewWindow());

            // Add elements to the box (spacer added for padding)
            box.getChildren().addAll(background, spacer, underline, clickableArea);
            wordBox.getChildren().add(box);
        }
        return wordBox;
    }

    private void openNewWindow() {
        Stage newWindow = new Stage();
        newWindow.setTitle("Question Window");

        // Sample matrix (3x3 for example)
        MathProblem problem = MathProblem.generateMathProblem();
        double[][] matrix = problem.getMatrix();

        double correctAnswer = problem.calculateDeterminant();

        // Create a VBox layout for the content
        VBox content = new VBox(20);
        content.setStyle(
                "-fx-background-image: url('background_game.png'); " +
                        "-fx-background-size: cover; "
        );
        content.setPrefSize(1000, 620);
        content.setPadding(new Insets(10));
        content.setAlignment(Pos.CENTER);

        // Label for the question
        Label questionLabel = new Label("What is the determinant of this matrix?");
        questionLabel.setStyle("-fx-text-fill: white; -fx-font-size: 28px; -fx-font-weight: bold;");

        // Display the matrix as a GridPane
        GridPane matrixGrid = new GridPane();
        matrixGrid.setHgap(15);
        matrixGrid.setVgap(15);
        matrixGrid.setAlignment(Pos.CENTER);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Label cell = new Label(String.valueOf(matrix[i][j]));
                cell.setStyle("-fx-text-fill: white; -fx-font-size: 24px;");
                matrixGrid.add(cell, j, i);
            }
        }

        // TextField for user input
        TextField answerField = new TextField();
        answerField.setPromptText("Enter the determinant");
        answerField.setMaxWidth(200);

        // Button to submit the answer
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            try {
                double userAnswer = Double.parseDouble(answerField.getText().trim());
                if (Math.abs(userAnswer - correctAnswer) < 0.0001) {
                    System.out.println("Correct!");
                    newWindow.close();
                } else {
                    System.out.println("Incorrect. Try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        });

        // Add all elements to the layout
        content.getChildren().addAll(questionLabel, matrixGrid, answerField, submitButton);

        // Create and show the scene
        Scene scene = new Scene(content, 1000, 620);
        newWindow.setScene(scene);
        newWindow.show();
    }



    private VBox createBottomOptions() {
        VBox bottomOptions = new VBox(20); // Added spacing between buttons
        bottomOptions.setStyle("-fx-alignment: bottom-right;"); // Keep alignment at the bottom-right

        Button homeButton = new Button();
        homeButton.setStyle("-fx-background-image: url('home_button.png'); -fx-background-size: cover; -fx-background-color: transparent;");
        homeButton.setMinSize(90, 90);
        //Switch to home
        homeButton.setOnAction(event->{
            MainPage mainPage = new MainPage(stage);
            Scene scene = mainPage.displayMainPage();
            switchScenes(scene);
        });

        Button theoryButton = new Button();
        theoryButton.setStyle("-fx-background-image: url('theory_button.png'); -fx-background-size: cover; -fx-background-color: transparent;");
        theoryButton.setMinSize(90, 90);

        // Add event handler for the theory button
        theoryButton.setOnAction(event -> showTheoryWindow());

        // Adjust padding and add buttons
        bottomOptions.setPadding(new Insets(0, 50, 20, 20));
        bottomOptions.getChildren().addAll(theoryButton, homeButton);

        return bottomOptions;
    }
    public void switchScenes(Scene scene) {
        // Switch to the specified scene
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    // Method to display the theory window
    private void showTheoryWindow() {
        Stage theoryWindow = new Stage();
        theoryWindow.setTitle("Theory");

        // Create a VBox layout for the content
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.setStyle(
                "-fx-background-image: url('hintBackgroundg4.png'); " +
                        "-fx-background-size: cover; " +
                        "-fx-background-color: rgba(0, 0, 0, 0.6); " // Add a dark overlay
        );

        // Create and set the scene

        Scene scene = new Scene(content, 1000, 640);

        theoryWindow.setScene(scene);
        theoryWindow.show();
    }
}
