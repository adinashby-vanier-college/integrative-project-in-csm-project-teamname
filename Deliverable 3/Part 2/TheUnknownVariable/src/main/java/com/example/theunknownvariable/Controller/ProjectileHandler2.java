package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.Model.Point2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ProjectileHandler2 {
    private Pane root;
    private ImageView gunView;
    private ImageView bulletView;
    private Timeline animation;
    private Point2 bulletPosition;
    private Slider heightSlider;
    private Button playButton;
    private double vx, vy;
    private double gravity = 980;
    private double velocity = 1200;
    private double angle = Math.toRadians(20);
    private double timeStep = 0.016;
    private final double targetY = 800;

    public ProjectileHandler2(Pane root, ImageView gunView, ImageView bulletView, Slider heightSlider, Button playButton) {
        this.root = root;
        this.gunView = gunView;
        this.bulletView = bulletView;
        this.heightSlider = heightSlider;
        this.playButton = playButton;
    }

    public void startProjectileMotion() {



        heightSlider.setDisable(true);
        playButton.setDisable(true);


        bulletView.setLayoutX(gunView.getLayoutX() + 130);
        bulletView.setLayoutY(gunView.getLayoutY() + 20);
        root.getChildren().add(bulletView);

        bulletPosition = new Point2(gunView.getLayoutX() + 130, gunView.getLayoutY() + 20);

        vx = velocity * Math.cos(angle);
        vy = -velocity * Math.sin(angle);

        animation = new Timeline(new KeyFrame(Duration.seconds(timeStep), event -> {
            vy += gravity * timeStep;

            bulletPosition.updatePosition(vx * timeStep, vy * timeStep);

            bulletView.setLayoutX(bulletPosition.getX());
            bulletView.setLayoutY(bulletPosition.getY());

            if (bulletPosition.getY() >= targetY || bulletPosition.getX() >= 1230) {
                animation.stop();
                startCleanupTimer();
            }
        }));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    private void startCleanupTimer() {
        Timeline cleanupTimeline = new Timeline(new KeyFrame(Duration.seconds(4), cleanupEvent -> {
            root.getChildren().remove(bulletView);
            heightSlider.setDisable(false);
            playButton.setDisable(false);
        }));
        cleanupTimeline.play();
    }
}