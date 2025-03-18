package com.example.theunknownvariable;// GameUI.java
// GameUI.java
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class GameUI extends Application {

//    @Override
//    public void start(Stage primaryStage) {
//
//    }
@Override
public void start(Stage primaryStage) {
    // Create the instructions screen
    VBox instructionsScreen = createInstructionsScreen(primaryStage);

    // Set up the instructions scene
    Scene instructionsScene = new Scene(instructionsScreen, 1366, 768);
    primaryStage.setTitle("Hangman Game - Instructions");
    primaryStage.setScene(instructionsScene);
    primaryStage.show();
}

    private VBox createInstructionsScreen(Stage primaryStage) {
        VBox layout = new VBox(20); // Spacing between elements
        layout.setPadding(new Insets(50));
        layout.setStyle("-fx-alignment: center; " +
                "-fx-background-image: url('math_instructions.png'); " +
                "-fx-background-size: cover;"); // Background image for the instructions screen

        // Add instructions text
        Label instructions = new Label("Welcome to the Hangman Game!\n\n"
                + "Instructions:\n"
                + "1. Guess the letters of the word.\n"
                + "2. You have a limited number of attempts.\n"
                + "3. Avoid making too many mistakes, or you'll lose!\n\n"
                + "Click the button below to start.");
        instructions.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-text-alignment: center;");

        // Add the "Yes, I Understand" button with an image
        Button proceedButton = new Button();
        proceedButton.setStyle("-fx-background-image: url('understand.png'); -fx-background-size: cover; "
                + "-fx-background-color: transparent; -fx-padding: 0;");
        proceedButton.setMinSize(200, 100); // Adjust the button size to fit the image

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
        newWindow.setTitle("New Window");
        newWindow.setScene(new Scene(new Pane(), 300, 200));
        newWindow.show();
    }

    private VBox createBottomOptions() {
        VBox bottomOptions = new VBox(20); // Added spacing between buttons
        bottomOptions.setStyle("-fx-alignment: bottom-right;"); // Keep alignment at the bottom-right

        Button homeButton = new Button();
        homeButton.setStyle("-fx-background-image: url('home_button.png'); -fx-background-size: cover; -fx-background-color: transparent;");
        homeButton.setMinSize(90, 90);

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

    // Method to display the theory window
    private void showTheoryWindow() {
        Stage theoryWindow = new Stage();
        theoryWindow.setTitle("Theory");

        // Create a VBox layout for the content
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.setStyle(
                "-fx-background-image: url('hintBackground.png'); " +
                        "-fx-background-size: cover; " +
                        "-fx-background-color: rgba(0, 0, 0, 0.6); " // Add a dark overlay
        );

        // Add white-colored placeholder text
        Label theoryText = new Label("Note that we require that f(c) exists in order for x = c to actually be a critical point. This is an important, and often overlooked, point. What this is really saying is that all critical points must be in the domain of the function. If a point is not in the domain of the function then it is not a critical point.\n" +
                "\n" +
                "Note as well that, at this point, we only work with real numbers and so any complex numbers that might arise in finding critical points (and they will arise on occasion) will be ignored. There are portions of calculus that work a little differently when working with complex numbers and so in a first calculus class such as this we ignore complex numbers and only work with real numbers. Calculus with complex numbers is beyond the scope of this course and is usually taught in higher level mathematics courses.\n" +
                "\n" +
                "The main point of this section is to work some examples finding critical points. So, let’s work some examples.");
        theoryText.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); // White font with a readable size

        // Add text to the VBox layout
        content.getChildren().add(theoryText);

        // Create and set the scene
        Scene scene = new Scene(content, 400, 200);
        theoryWindow.setScene(scene);
        theoryWindow.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
