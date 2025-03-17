package com.example.demo1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SuspectUI extends StackPane {

    private static final int NUM_SUSPECTS = 6;
    private static final String[] SUSPECT_NAMES = {
            "Anita", "Lea", "Mohammad", "Picasso", "Sarah", "Suspect"
    };
    private static final String[] SUSPECT_IMAGES = {
            "/Anita.png", "/Lea.png",
            "/Mohammad.png", "/Picasso.png",
            "/Sarah.png", "/Suspect.png"
    };

    public SuspectUI() {
        StackPane root = new StackPane();
        root.setPrefSize(1366, 768);

        Image backgroundImage = new Image("/suspectBackground.png");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1366);
        backgroundView.setFitHeight(768);
        backgroundView.setPreserveRatio(false);


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
            suspectCard.setOnMouseClicked(event -> showSuspectInfo(SUSPECT_NAMES[finalI]));

            int row = i / 3;
            int col = i % 3;
            suspectGrid.add(suspectCard, col, row);
        }

        root.getChildren().addAll(backgroundView, suspectGrid);
        getChildren().add(root);
    }

    private void showSuspectInfo(String suspectName) {
        SuspectReader suspectReader = new SuspectReader();
        String suspectDetails = suspectReader.getSuspectInfo(suspectName);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(suspectName + " Info");

        Label infoLabel = new Label(suspectDetails);
        infoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-padding: 10px;");

        ScrollPane scrollPane = new ScrollPane(infoLabel);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(380, 280);

        StackPane popupLayout = new StackPane(scrollPane);
        popupLayout.setStyle("-fx-background-color: rgba(255, 255, 255, 1);");
        Scene popupScene = new Scene(popupLayout, 400, 300);

        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
}