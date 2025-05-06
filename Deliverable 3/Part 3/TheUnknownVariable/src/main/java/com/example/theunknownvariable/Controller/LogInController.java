package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.PasswordUtils;
import com.example.theunknownvariable.UI.LogInUI;
import javafx.stage.Stage;

import java.io.*;

public class LogInController {
    private final LogInUI view;
    private final Stage stage;

    public LogInController(Stage stage) {
        this.view = new LogInUI(stage);
        this.stage = stage;

        view.loginSubmitButton.setOnAction(e -> handleLogin());
        view.signUpSubmitButton.setOnAction(e -> handleSignUp());
    }

    public void showLoginForm() {
        view.animateSwitchTo(view.loginForm);
        view.updateButtonStyles("login");
        clearLoginFields();
    }

    public void showSignUpForm() {
        view.animateSwitchTo(view.signUpForm);
        view.updateButtonStyles("signup");
        clearSignUpFields();
    }

    public void showVisiblePassword() {
        view.visiblePasswordField.setText(view.loginPasswordField.getText());
        view.showPasswordField(true);
    }

    public void hideVisiblePassword() {
        view.loginPasswordField.setText(view.visiblePasswordField.getText());
        view.showPasswordField(false);
    }

    public void handleLogin() {
        String username = view.getLoginUsername();
        String password = view.getLoginPassword();

        if (username.isEmpty() || password.isEmpty()) {
            view.loginResultLabel.setText("Please fill in both fields.");
            view.loginResultLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (checkCredentials(username, password)) {
            view.loginResultLabel.setText("Login successful!");
            view.loginResultLabel.setStyle("-fx-text-fill: green;");
            // Load your next scene here
        } else {
            view.loginResultLabel.setText("Invalid credentials.");
            view.loginResultLabel.setStyle("-fx-text-fill: red;");
        }
    }

    public void handleSignUp() {
        String username = view.signUpUsernameField.getText();
        String password = view.signUpPasswordField.getText();
        String confirm = view.confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            view.signUpResultLabel.setText("All fields are required.");
            view.signUpResultLabel.setStyle("-fx-text-fill: red;");
        } else if (!password.equals(confirm)) {
            view.signUpResultLabel.setText("Passwords do not match.");
            view.signUpResultLabel.setStyle("-fx-text-fill: red;");
        } else if (usernameExists(username)) {
            view.signUpResultLabel.setText("Username already exists.");
            view.signUpResultLabel.setStyle("-fx-text-fill: red;");
        } else {
            // ✅ Hash the password before saving
            String hashedPassword = PasswordUtils.hashPassword(password);
            saveUser(username, hashedPassword);

            view.signUpResultLabel.setText("Sign-Up successful!");
            view.signUpResultLabel.setStyle("-fx-text-fill: green;");
            // Load your next scene here
        }
    }

    // ✅ Compare the hashed password input with the stored one
    private boolean checkCredentials(String username, String password) {
        String hashedInput = PasswordUtils.hashPassword(password);
        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(hashedInput)) {
                    return true;
                }
            }
        } catch (IOException ignored) {}
        return false;
    }

    private boolean usernameExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(",")[0].equals(username)) return true;
            }
        } catch (IOException ignored) {}
        return false;
    }

    private void saveUser(String username, String hashedPassword) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.csv", true))) {
            writer.write(username + "," + hashedPassword + "\n");
        } catch (IOException ignored) {}
    }

    private void clearLoginFields() {
        view.loginUsernameField.clear();
        view.loginPasswordField.clear();
        view.visiblePasswordField.clear();
        view.loginResultLabel.setText("");
    }

    private void clearSignUpFields() {
        view.signUpUsernameField.clear();
        view.signUpPasswordField.clear();
        view.confirmPasswordField.clear();
        view.signUpResultLabel.setText("");
    }

    public LogInUI getView() {
        return view;
    }
}
