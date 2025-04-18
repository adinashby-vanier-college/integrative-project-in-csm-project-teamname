package com.example.theunknownvariable.Controller;

import com.example.theunknownvariable.Model.Point2;
import com.example.theunknownvariable.UI.game2UI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ProjectileHandler2 {
    private Pane root;
    private ImageView gunView, bulletView, paperView;
    private Slider heightSlider;
    private Button playButton;
    private game2UI game;
    private Timeline animation;
    private Point2 bulletPosition;

    // Physics-related properties
    private static final double GRAVITY = 980;
    private static final double VELOCITY = 1200;
    private static final double ANGLE = Math.toRadians(20);
    private static final double TIME_STEP = 0.016;
    private static final double TARGET_X = 1200;
    private static final double TARGET_Y = 180;
    private double vx, vy;
    private boolean hasPlayedGame = false;  // Track if the game has been completed

    public ProjectileHandler2(Pane root, ImageView gunView, ImageView bulletView,
                              Slider heightSlider, Button playButton, ImageView paperView, game2UI game) {
        this.root = root;
        this.gunView = gunView;
        this.bulletView = bulletView;
        this.heightSlider = heightSlider;
        this.playButton = playButton;
        this.paperView = paperView;
        this.game = game;

        // Initially disable paper click until the target is hit
        paperView.setDisable(true);
    }


     //Starts the projectile motion animation when the play button is clicked.
    public void startProjectileMotion() {
        disableControls();
        initializeBulletPosition();
        setupProjectileMotion();
    }


     //Disables the height slider and play button while the animation runs.
    private void disableControls() {
        heightSlider.setDisable(true);
        playButton.setDisable(true);
    }

     //Initializes the bullet's position at the gun's location.
    private void initializeBulletPosition() {
        bulletView.setLayoutX(gunView.getLayoutX() + 130);
        bulletView.setLayoutY(gunView.getLayoutY() + 20);
        root.getChildren().add(bulletView);
        bulletPosition = new Point2(bulletView.getLayoutX(), bulletView.getLayoutY());
        vx = VELOCITY * Math.cos(ANGLE);
        vy = -VELOCITY * Math.sin(ANGLE);
    }

    //Sets up and starts the projectile animation.
    private void setupProjectileMotion() {
        animation = new Timeline(new KeyFrame(Duration.seconds(TIME_STEP), event -> {
            updateBulletPosition();
            checkCollision();
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }


     //Updates the bullet's position using physics equations.
    private void updateBulletPosition() {
        vy += GRAVITY * TIME_STEP;
        bulletPosition.updatePosition(vx * TIME_STEP, vy * TIME_STEP);
        bulletView.setLayoutX(bulletPosition.getX());
        bulletView.setLayoutY(bulletPosition.getY());
    }

      //Checks if the bullet hits the target or goes out of bounds.
    private void checkCollision() {
        if (isBulletHittingTarget()) {
            animation.stop();
            updatePaperView();
            startCleanupTimer();
        } else if (isBulletOutOfBounds()) {
            animation.stop();
            startCleanupTimer();
        }
    }


     //Determines if the bullet has hit the target.
    private boolean isBulletHittingTarget() {
        double bulletX = bulletPosition.getX();
        double bulletY = bulletPosition.getY();
        return (bulletX + 70 >= TARGET_X + 89 && bulletX + 50 <= TARGET_X + 90) &&
                (bulletY + 27 >= TARGET_Y + 90 && bulletY + 25 <= TARGET_Y + 93);
    }


     //Checks if the bullet has gone out of the screen bounds.
    private boolean isBulletOutOfBounds() {
        return bulletPosition.getY() >= 768 || bulletPosition.getX() >= 1230;
    }

     //Changes the paper image to indicate that the target has been hit.
    private void updatePaperView() {
        root.getChildren().remove(paperView);
        paperView.setImage(new Image("/paper2.png"));
        root.getChildren().add(paperView);
        paperView.setDisable(false);
    }

     //Starts a timer to clean up the bullet and re-enable controls after a delay.
    private void startCleanupTimer() {
        Timeline cleanup = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            root.getChildren().remove(bulletView);
            heightSlider.setDisable(false);
            playButton.setDisable(false);
        }));
        cleanup.setCycleCount(1);
        cleanup.play();

        setupPaperClickEvent();
        setupPaperHoverEffects();
    }

     //Sets up the event to open the quiz when clicking the paper.
    private void setupPaperClickEvent() {
        paperView.setOnMouseClicked(event -> {
            if (hasPlayedGame) {
                game.showFinalResult();
            } else {
                openGame2Quiz();
                hasPlayedGame = true;
            }
        });
    }

     //Adds hover effects to the paper image.
    private void setupPaperHoverEffects() {
        paperView.setOnMouseEntered(event -> onImageHover());
        paperView.setOnMouseExited(event -> onImageExit());
    }

     //Changes the appearance of the paper when hovered over.
    private void onImageHover() {
        paperView.setOpacity(0.7);
        paperView.setScaleX(1.1);
        paperView.setScaleY(1.1);
    }

      //Restores the paper’s appearance when the cursor leaves.
    private void onImageExit() {
        paperView.setOpacity(1.0);
        paperView.setScaleX(1.0);
        paperView.setScaleY(1.0);
    }

     //Opens the Game 2 quiz UI.
    private void openGame2Quiz() {
        game.showQuiz();
    }
}