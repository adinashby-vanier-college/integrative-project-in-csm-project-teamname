package com.example.theunknownvariable.UI;

import com.example.theunknownvariable.Controller.LogInController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class LogInUI {
    private final Stage stage;
    private final StackPane mainContainer = new StackPane();
    private final Label loginButton = new Label("Login");
    private final Label signUpButton = new Label("Sign Up");

    // Login Form UI
    public VBox loginForm = new VBox(10);
    public TextField loginUsernameField = new TextField();
    public PasswordField loginPasswordField = new PasswordField();
    public TextField visiblePasswordField = new TextField();
    public Label loginResultLabel = new Label();
    public Button loginSubmitButton = new Button("LOG IN");

    // Sign Up Form UI
    public VBox signUpForm = new VBox(10);
    public TextField signUpUsernameField = new TextField();
    public PasswordField signUpPasswordField = new PasswordField();
    public PasswordField confirmPasswordField = new PasswordField();
    public Label signUpResultLabel = new Label();
    public Button signUpSubmitButton = new Button("SIGN UP");

    private final StackPane drape = new StackPane();
    private final HBox background = new HBox();
    private String activeButton = "login";

    public LogInUI(Stage stage) {
        this.stage = stage;
    }

    public Scene createScene(LogInController controller) {
        // Set up shared styles
        loginUsernameField.setPromptText("Username");
        loginPasswordField.setPromptText("Password");
        visiblePasswordField.setPromptText("Password");
        visiblePasswordField.setManaged(false);
        visiblePasswordField.setVisible(false);

        Label eyeIcon = new Label("\uD83D\uDC41");
        eyeIcon.setStyle("-fx-font-size: 24px; -fx-cursor: hand; -fx-text-fill: #eee7d0");

        // Password toggle
        eyeIcon.setOnMouseEntered(e -> controller.showVisiblePassword());
        eyeIcon.setOnMouseExited(e -> controller.hideVisiblePassword());

        HBox passwordBox = new HBox(loginPasswordField, visiblePasswordField, eyeIcon);
        passwordBox.setSpacing(10);
        passwordBox.setAlignment(Pos.CENTER_LEFT);

        Label loginLabel = new Label("LOGIN");
        loginLabel.setStyle("-fx-font-size: 24px; -fx-font-family: serif; -fx-text-fill: #eee7d0");
        loginForm.setAlignment(Pos.CENTER_LEFT);
        loginForm.setPadding(new Insets(20));
        loginForm.getChildren().addAll(loginLabel, loginUsernameField, passwordBox, loginSubmitButton, loginResultLabel);

        Label signUpLabel = new Label("SIGNUP");
        signUpLabel.setStyle("-fx-font-size: 24px; -fx-font-family: serif; -fx-text-fill: #eee7d0");
        signUpForm.setAlignment(Pos.CENTER_LEFT);
        signUpForm.setPadding(new Insets(20));
        signUpUsernameField.setPromptText("Username");
        signUpPasswordField.setPromptText("Password");
        confirmPasswordField.setPromptText("Confirm Password");
        signUpForm.getChildren().addAll(signUpLabel, signUpUsernameField, signUpPasswordField, confirmPasswordField, signUpSubmitButton, signUpResultLabel);

        Label WelcomeLabel = new Label("Welcome User");
        WelcomeLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #003049; -fx-padding: 10; -fx-font-weight: bold");

        Label or = new Label("or");
        or.setStyle("-fx-font-size: 24px; -fx-text-fill: #003049; -fx-padding: 10");
        loginButton.setStyle("-fx-font-size: 24px; -fx-text-fill: #669BBC; -fx-padding: 10");
        signUpButton.setStyle("-fx-font-size: 24px; -fx-text-fill: #003049; -fx-padding: 10");

        HBox labels = new HBox(10, loginButton, or, signUpButton);
        labels.setAlignment(Pos.TOP_RIGHT);
        labels.setPadding(new Insets(80, 25, 0, 0));

        VBox welcomePage = new VBox(WelcomeLabel);
        welcomePage.setAlignment(Pos.TOP_RIGHT);
        welcomePage.setPadding(new Insets(30, 40, 0, 0));

        loginButton.setOnMouseClicked(e -> controller.showLoginForm());
        signUpButton.setOnMouseClicked(e -> controller.showSignUpForm());

        Rectangle bgRect = new Rectangle(300, 500);
        bgRect.setStroke(Color.BEIGE);
        Pane leftPane = new Pane(bgRect);

        background.getChildren().addAll(leftPane, loginForm);
        StackPane labelContainer = new StackPane(background);
        labelContainer.setPrefSize(600, 500);
        labelContainer.setStyle("-fx-background-color: #003049");

        Image image = new Image(String.valueOf(getClass().getResource("/loginBG.jpg")));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);

        drape.getChildren().addAll(imageView, welcomePage, labels);
        drape.setTranslateX(-300);
        drape.setPrefWidth(300);

        mainContainer.getChildren().addAll(labelContainer, drape);
        return new Scene(mainContainer);
    }

    public void animateSwitchTo(VBox newForm) {
        TranslateTransition forward = new TranslateTransition(Duration.seconds(2), drape);
        forward.setToX(0);
        forward.setOnFinished(e -> {
            background.getChildren().set(1, newForm);
            TranslateTransition backward = new TranslateTransition(Duration.seconds(2), drape);
            backward.setToX(-300);
            backward.play();
        });
        forward.play();
    }

    public void updateButtonStyles(String active) {
        this.activeButton = active;
        loginButton.setStyle("-fx-font-size: 24px; -fx-text-fill: " + (active.equals("login") ? "#669BBC" : "#003049") + "; -fx-padding: 10;");
        signUpButton.setStyle("-fx-font-size: 24px; -fx-text-fill: " + (active.equals("signup") ? "#669BBC" : "#003049") + "; -fx-padding: 10;");
    }

    public void showPasswordField(boolean visible) {
        visiblePasswordField.setVisible(visible);
        visiblePasswordField.setManaged(visible);
        loginPasswordField.setVisible(!visible);
        loginPasswordField.setManaged(!visible);
    }

    public String getLoginUsername() { return loginUsernameField.getText(); }
    public String getLoginPassword() {
        return loginPasswordField.isVisible() ? loginPasswordField.getText() : visiblePasswordField.getText();
    }
}
