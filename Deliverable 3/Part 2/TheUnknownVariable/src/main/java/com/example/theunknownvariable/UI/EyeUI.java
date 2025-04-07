package com.example.theunknownvariable.UI;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class EyeUI {
    static final double eyeX = 260;
    static final double retinaX = 230;


    public static void main(String[] args) {

    }


    public VBox getEyeBox(){
        Image i = new Image(getClass().getResource("/Assets/eye.png").toExternalForm());
        ImageView iv = new ImageView(i);
        iv.setPreserveRatio(true);
        iv.setFitWidth(250);
        VBox vBox = new VBox(iv);
        vBox.setPadding(new Insets(10,0,0,0));

        return vBox;

    }

}
