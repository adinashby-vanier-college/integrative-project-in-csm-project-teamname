package com.example.theunknownvariable;

import com.example.theunknownvariable.Controller.HomePageController;
import com.example.theunknownvariable.Controller.LogInController;
import com.example.theunknownvariable.UI.HomePage;
import com.example.theunknownvariable.UI.LogInUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

import java.io.IOException;

public class Main extends Application {

    private MediaPlayer mediaPlayer;

    @Override

    public void start(Stage stage) throws IOException {

        playBackgroundMusic();
        //Display the first scene of the game: The home page
        LogInController controller = new LogInController(stage);
        Scene loginScene = controller.getView().createScene(controller);
        HomePageController homePage = new HomePageController(stage);
        Scene scene = homePage.getScene();
        stage.setTitle("The Unknown Variable");
        stage.setScene(loginScene);
        stage.show();
    }

    private void playBackgroundMusic() {
        try {
            URL resource = getClass().getResource("/backgroundMusic.mp3");
            if (resource == null) {
                throw new IllegalArgumentException("Audio file not found!");
            }

            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop forever
            mediaPlayer.setVolume(0.3); // Set desired volume
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}