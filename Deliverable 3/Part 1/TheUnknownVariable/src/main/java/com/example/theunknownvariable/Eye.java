package com.example.theunknownvariable;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Eye  {

    public static void main(String[] args) {

    }


    public VBox getEyeBox(){
        Image i = new Image(getClass().getResource("/Assets/eye.png").toExternalForm());
        ImageView iv = new ImageView(i);
        iv.setPreserveRatio(true);
        iv.setFitWidth(250);

        return new VBox(iv);

    }

}
