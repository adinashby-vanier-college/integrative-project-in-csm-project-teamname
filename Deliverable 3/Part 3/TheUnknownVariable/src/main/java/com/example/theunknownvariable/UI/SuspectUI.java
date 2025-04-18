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

public class SuspectUI extends StackPane {

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

    public SuspectUI(Stage stage) {
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
            suspectCard.setOnMouseClicked(event -> showSuspectInfo(SUSPECT_NAMESB[finalI]));

            int row = i / 3;
            int col = i % 3;
            suspectGrid.add(suspectCard, col, row);
        }

        menuButton = getMenuButton();
        StackPane.setAlignment(menuButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(menuButton, new Insets(0, 20, 20, 0));

        root.getChildren().addAll(backgroundView, suspectGrid, menuButton);
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

    private void showSuspectInfo(String suspectName) {
        SuspectReader suspectReader = new SuspectReader();
        String suspectDetails = suspectReader.getSuspectInfo(suspectName);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(suspectName + " Info");

        Label infoLabel = new Label(suspectDetails);
        infoLabel.setStyle(
                "-fx-font-family: 'Times New Roman';" +
                        "-fx-font-size: 20px;" +
                        "-fx-text-fill: #2d2b2a;" +
                        "-fx-padding: 15px;" +
                        "-fx-font-weight: bold;"
        );
        infoLabel.setTextAlignment(TextAlignment.CENTER);
        infoLabel.setWrapText(true);

        VBox infoBox = new VBox(infoLabel);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setPadding(new Insets(20));


        Image backgroundImage = new Image("/suspect2Background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(400);
        backgroundImageView.setFitHeight(400);
        backgroundImageView.setPreserveRatio(true);
        HBox background = new HBox(backgroundImageView);
        background.setAlignment(Pos.CENTER);

        StackPane popupLayout = new StackPane(background, infoBox);
        Scene popupScene = new Scene(popupLayout, 400, 300);

        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
}
