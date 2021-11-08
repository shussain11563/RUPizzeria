package com.ru.pizzeria.rupizzeria;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RuPizzeriaController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}