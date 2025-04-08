package com.example.theunknownvariable;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class AnimationHandler {
private boolean isAnimating;
private Timeline animation;

public void startAnimation() {
    isAnimating = true;
    animation = new Timeline(
            new KeyFrame(Duration.millis(100), e -> {
                System.out.println("Animating projectile...");
            })
    );
    animation.setCycleCount(10);
    animation.setOnFinished(e -> stopAnimation());
    animation.play();
}

public void stopAnimation() {
    isAnimating = false;
    System.out.println("Animation stopped.");
}

public boolean isAnimating() {
    return isAnimating;
}
}
