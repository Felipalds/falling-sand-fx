package com.adair.cgdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label zoz;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onHereButtonClick() {
        if(zoz.getText().equals("ZOZ")) {
            zoz.setText("LUIZ");
        } else {
            zoz.setText("ZOZ");
        }
    }
}