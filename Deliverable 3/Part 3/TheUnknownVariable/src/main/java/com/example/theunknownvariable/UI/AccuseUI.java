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

        Image backgroundImage = new Image("/suspectBackground.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1390);
        backgroundView.setFitHeight(790);
        backgroundView.setPreserveRatio(true);

        GridPane suspectGrid = new GridPane();
        suspectGrid.setHgap(80);
        suspectGrid.setVgap(60);
        suspectGrid.setAlignment(Pos.CENTER);

        for (int i = 0; i < NUM_SUSPECTS; i++) {
            Image suspectImage = new Image(getClass().getResource(SUSPECT_IMAGES[i]).toExternalForm());
            ImageView suspectImageView = new ImageView(suspectImage);
            suspectImageView.setFitWidth(200);
            suspectImageView.setFitHeight(200);
            suspectImageView.setPreserveRatio(true);

            Label suspectNameLabel = new Label(SUSPECT_NAMES[i]);
            suspectNameLabel.setPrefWidth(200);
            suspectNameLabel.setAlignment(Pos.CENTER);
            suspectNameLabel.setStyle(
                    "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: #2d2b2a;" +
                            "-fx-background-color: #cfc6bc;" +
                            "-fx-padding: 8px;" +
                            "-fx-background-radius: 6px;"
            );

            VBox suspectCard = new VBox(12);
            suspectCard.setAlignment(Pos.CENTER);
            suspectCard.setPadding(new Insets(12));
            suspectCard.setPrefSize(220, 300);
            suspectCard.setStyle(
                    "-fx-background-color: #2d2b2a;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-effect: dropshadow(gaussian, rgba(86,82,78,0.5), 10, 0, 0, 6);"
            );
            suspectCard.getChildren().addAll(suspectImageView, suspectNameLabel);

            suspectCard.setOnMouseEntered(e -> suspectCard.setStyle(
                    "-fx-background-color: #56524e;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-effect: dropshadow(gaussian, rgba(86,82,78,0.7), 12, 0, 0, 6);"
            ));
            suspectCard.setOnMouseExited(e -> suspectCard.setStyle(
                    "-fx-background-color: #2d2b2a;" +
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
                "    -fx-font-size: 37px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: rgb(22,42,51);");
        menuButton = getMenuButton();
        StackPane.setAlignment(menuButton, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(accuseLabel, Pos.TOP_CENTER); StackPane.setMargin(accuseLabel, new Insets(13,0,0,20));
        StackPane.setMargin(menuButton, new Insets(0, 20, 20, 0));

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
                "    -fx-font-size: 23px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: rgb(22,42,51)");
        areyousureLabel.setTextAlignment(TextAlignment.CENTER);

        Button yesButton = new Button("Yes!");
        yesButton.setStyle("-fx-background-color: rgb(178,219,238);\n" +
                "    -fx-text-fill: rgb(22,42,51);\n" +
                "    -fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-size: 20px;\n" +
                "    -fx-pref-width: 100px;\n" +
                "    -fx-pref-height: 50px;\n" +
                "    -fx-border-color: rgb(22,42,51);\n" +
                "    -fx-border-width: 3px;\n" +
                "    -fx-border-radius: 5px;\n" +
                "    -fx-background-radius: 5px;");


        yesButton.setOnAction(actionEvent -> {
            popupStage.close();

            Stage verdictStage = new Stage();
            verdictStage.initModality(Modality.APPLICATION_MODAL);
            verdictStage.setTitle("Verdict");

            String verdictMessage;
            boolean playerWon = suspectName.equals("Lea Mio") || suspectName.contains("Lea Mio");

            if (playerWon) {
                verdictMessage = "You got it! 🎉 Lea Mio was the culprit!";
            } else {
                verdictMessage = "Wrong! You let the culprit go...";
            }

            Label verdictLabel = new Label(verdictMessage);
            verdictLabel.setWrapText(true);
            verdictLabel.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                    "-fx-font-size: 20px;\n" +
                    "-fx-font-weight: bold;\n" +
                    "-fx-text-fill: rgb(22,42,51)");
            verdictLabel.setTextAlignment(TextAlignment.CENTER);

            Button okButton = new Button("OK");
            okButton.setStyle("-fx-background-color: rgb(178,219,238);\n" +
                    "-fx-text-fill: rgb(22,42,51);\n" +
                    "-fx-font-family: \"Times New Roman\";\n" +
                    "-fx-font-size: 20px;\n" +
                    "-fx-pref-width: 100px;\n" +
                    "-fx-pref-height: 50px;\n" +
                    "-fx-border-color: rgb(22,42,51);\n" +
                    "-fx-border-width: 3px;\n" +
                    "-fx-border-radius: 5px;\n" +
                    "-fx-background-radius: 5px;");

            okButton.setOnAction(e -> {
                verdictStage.close();
                if (!playerWon) {
                    GameOverUI gameOver = new GameOverUI();
                    Scene gameOverScene = gameOver.displayGameOverScreen(stage);
                    switchScenes(gameOverScene);
                }
                // You can add a win screen here if you'd like.
            });

            VBox verdictBox = new VBox(20, verdictLabel, okButton);
            verdictBox.setAlignment(Pos.CENTER);
            verdictBox.setPadding(new Insets(20));
            Scene verdictScene = new Scene(verdictBox, 400, 300);

            verdictStage.setScene(verdictScene);
            verdictStage.showAndWait();
        });



        VBox areyousureVBox = new VBox(30,areyousureLabel,yesButton);
        areyousureVBox.setAlignment(Pos.CENTER);
        areyousureVBox.setPadding(new Insets(20));


        Image backgroundImage = new Image("/suspect2Background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(400);
        backgroundImageView.setFitHeight(400);
        backgroundImageView.setPreserveRatio(true);
        HBox background = new HBox(backgroundImageView);
        background.setAlignment(Pos.CENTER);

        StackPane popupLayout = new StackPane(background, areyousureVBox);
        Scene popupScene = new Scene(popupLayout, 400, 300);

        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
}
