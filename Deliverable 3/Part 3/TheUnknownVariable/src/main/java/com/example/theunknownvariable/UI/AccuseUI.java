package com.example.theunknownvariable.UI;

import com.example.theunknownvariable.Controller.SuspectReader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AccuseUI extends StackPane {

    private static final int NUM_SUSPECTS = 6;
    private static final String[] SUSPECT_NAMES = {
            "Anita Bath", "Lea Mio", "Mohammed", "Picasso", "Sara", "Suspect Name"
    };
    private static final String[] SUSPECT_NAMESB = {
            "Anita Bath", "Lea Mio", "Mohammed Abu Bakr", "Pablo Diego José Francisco de Paula Juan Nepomuceno " +
            "Crispín Crispiniano María de los Remedios de la Santísima Trinidad Ruiz Picasso", "Sara", "Suspect Name"
    };
    private static final String[] SUSPECT_IMAGES = {
            "/Anita.png", "/Lea.png",
            "/Mohammad.png", "/Picasso.png",
            "/Sara.png", "/Suspect.png"
    };

    private Stage stage;
    private Button menuButton;

    public AccuseUI(Stage stage) {
        this.stage = stage;
        StackPane root = new StackPane();
        root.setPrefSize(1366, 768);

        Image backgroundImage = new Image("/accuseBG.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1390);
        backgroundView.setFitHeight(790);
        backgroundView.setPreserveRatio(true);

        GridPane suspectGrid = new GridPane();
        suspectGrid.setHgap(50);
        suspectGrid.setVgap(30);
        suspectGrid.setAlignment(Pos.CENTER);

        for (int i = 0; i < NUM_SUSPECTS; i++) {
            Image suspectImage = new Image(getClass().getResource(SUSPECT_IMAGES[i]).toExternalForm());
            ImageView suspectImageView = new ImageView(suspectImage);
            suspectImageView.setFitWidth(175);
            suspectImageView.setFitHeight(175);
            suspectImageView.setPreserveRatio(true);

            Label suspectNameLabel = new Label(SUSPECT_NAMES[i]);
            suspectNameLabel.setPrefWidth(175);
            suspectNameLabel.setPrefHeight(20);
            suspectNameLabel.setAlignment(Pos.CENTER);
            suspectNameLabel.setStyle(
                    "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
//                            "-fx-text-fill: #2d2b2a;" +
                            "-fx-text-fill: #2f0d0d;" +
                            "-fx-background-color: #cfc6bc;" +
                            "-fx-padding: 8px;" +
                            "-fx-background-radius: 6px;"
            );

            VBox suspectCard = new VBox(11);
            suspectCard.setAlignment(Pos.CENTER);
            suspectCard.setPadding(new Insets(8));
            suspectCard.setPrefSize(200, 280);
            suspectCard.setStyle(
//                    "-fx-background-color: #2d2b2a;" +
                    "-fx-background-color: #250202;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-effect: dropshadow(gaussian, rgba(86,82,78,0.5), 10, 0, 0, 6);"
            );
            suspectCard.getChildren().addAll(suspectImageView, suspectNameLabel);

            suspectCard.setOnMouseEntered(e -> suspectCard.setStyle(
//                    "-fx-background-color: #56524e;" +
                    "-fx-background-color: #4b0707;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-effect: dropshadow(gaussian, rgba(86,82,78,0.7), 12, 0, 0, 6);"
            ));
            suspectCard.setOnMouseExited(e -> suspectCard.setStyle(
//                    "-fx-background-color: #2d2b2a;" +
                    "-fx-background-color: #250202;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-effect: dropshadow(gaussian, rgba(86,82,78,0.5), 10, 0, 0, 6);"
            ));

            int finalI = i;
            suspectCard.setOnMouseClicked(event -> areYouSureWindow(SUSPECT_NAMESB[finalI]));

            int row = i / 3;
            int col = i % 3;
            suspectGrid.add(suspectCard, col, row);
        }
        Label accuseLabel = new Label("Who is the culprit?");
        accuseLabel.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-size: 45px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: rgb(37,2,2);");
        menuButton = getMenuButton();
        StackPane.setAlignment(menuButton, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(accuseLabel, Pos.TOP_CENTER); StackPane.setMargin(accuseLabel, new Insets(23,0,0,20));
        StackPane.setMargin(menuButton, new Insets(0, 10, 10, 0));

        root.getChildren().addAll(backgroundView, suspectGrid, menuButton, accuseLabel);
        getChildren().add(root);
    }

    public Button getMenuButton() {
        Image menuImage = new Image("menu2.png");
        ImageView menu = new ImageView(menuImage);
        menu.setFitWidth(100);
        menu.setPreserveRatio(true);

        Button menuButton = new Button("", menu);
        menuButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        menuButton.setOnMouseEntered(e -> menu.setOpacity(0.8));
        menuButton.setOnMouseExited(e -> menu.setOpacity(1.0));

        menuButton.setOnAction(event -> {
            HomePage homePage = new HomePage(stage);
            Scene scene = homePage.displayHomePage();
            switchScenes(scene);
        });

        return menuButton;
    }

    public void switchScenes(Scene scene) {
        Stage stage = (Stage) getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }

    private void areYouSureWindow(String suspectName) {

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Accusing " + suspectName);

        Label areyousureLabel = new Label("Are you sure...?");
        areyousureLabel.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-size: 33px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: rgb(19,1,1);");

        areyousureLabel.setTextAlignment(TextAlignment.CENTER);

        Button yesButton = new Button("Yes!");
        yesButton.setStyle("-fx-background-color: rgb(238,178,178);\n" +
                "    -fx-text-fill: rgb(51,22,22);\n" +
                "    -fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-size: 20px;\n" +
                "    -fx-pref-width: 100px;\n" +
                "    -fx-pref-height: 50px;\n" +
                "    -fx-border-color: rgb(51,22,22);\n" +
                "    -fx-border-width: 3px;\n" +
                "    -fx-border-radius: 5px;\n" +
                "    -fx-background-radius: 5px;");


        yesButton.setOnAction(actionEvent -> {
            popupStage.close();

            Image okIMG = new Image("/okBG.jpg");
            ImageView okIV = new ImageView(okIMG);
            okIV.setFitWidth(740);
            okIV.setFitHeight(494);
            okIV.setPreserveRatio(true);
            HBox bg = new HBox(okIV);
            bg.setAlignment(Pos.CENTER);

            Stage verdictStage = new Stage();
            verdictStage.initModality(Modality.APPLICATION_MODAL);
            verdictStage.setTitle("Verdict");

            String verdictMessage;
            boolean playerWon = suspectName.equals("Lea Mio") || suspectName.contains("Lea Mio");

//            if (playerWon) {
//                verdictMessage = "You got it! 🎉 Lea Mio was the culprit!";
//            } else {
//                verdictMessage = "Wrong! You let the culprit go...";
//            }

//            Label verdictLabel = new Label(verdictMessage);
            Label verdictLabel = new Label("You can't go back\nonce you click OK...");
            verdictLabel.setWrapText(true);
            verdictLabel.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                    "-fx-font-size: 27px;\n" +
                    "-fx-font-weight: bold;\n" +
                    "-fx-text-fill: rgb(19,1,1)");
            verdictLabel.setTextAlignment(TextAlignment.CENTER);

            Button okButton = new Button("OK");
            okButton.setStyle("-fx-background-color: rgb(238,178,178);\n" +
                    "-fx-text-fill: rgb(51,22,22);\n" +
                    "-fx-font-family: \"Times New Roman\";\n" +
                    "-fx-font-size: 20px;\n" +
                    "-fx-pref-width: 100px;\n" +
                    "-fx-pref-height: 50px;\n" +
                    "-fx-border-color: rgb(51,22,22);\n" +
                    "-fx-border-width: 3px;\n" +
                    "-fx-border-radius: 5px;\n" +
                    "-fx-background-radius: 5px;");

            okButton.setOnAction(e -> {
                verdictStage.close();
                if (playerWon) {
                    LeaArrestScene arrest = new LeaArrestScene();
                    Scene winningScene = arrest.buildArrestScene();
                    switchScenes(winningScene);

                } else {
//                    GameOverUI gameOver = new GameOverUI();
//                    Scene gameOverScene = gameOver.displayGameOverScreen(stage);
//                    switchScenes(gameOverScene);
                    GameOverScene gameOver = new GameOverScene();
                    Scene losingScene = gameOver.buildGameOverScene();
                    switchScenes(losingScene);
                }
            });

            VBox verdictBox = new VBox(20, verdictLabel, okButton);
            verdictBox.setAlignment(Pos.CENTER);
            verdictBox.setPadding(new Insets(20));
            StackPane verdictStack = new StackPane(bg,verdictBox);
            verdictStack.setAlignment(Pos.CENTER);
            Scene verdictScene = new Scene(verdictStack, 400, 300);

            verdictStage.setScene(verdictScene);
            verdictStage.showAndWait();
        });



        VBox areyousureVBox = new VBox(40,areyousureLabel,yesButton);
        areyousureVBox.setAlignment(Pos.CENTER);
        areyousureVBox.setPadding(new Insets(20));


        Image backgroundImage = new Image("/sureBG.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1280/2);
        backgroundImageView.setFitHeight(854/2);
        backgroundImageView.setPreserveRatio(true);
        HBox background = new HBox(backgroundImageView);
        background.setAlignment(Pos.CENTER);

        StackPane popupLayout = new StackPane(background, areyousureVBox);
        Scene popupScene = new Scene(popupLayout, 400, 300);

        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
}
