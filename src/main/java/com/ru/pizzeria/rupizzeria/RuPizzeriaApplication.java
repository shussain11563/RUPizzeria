package com.ru.pizzeria.rupizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is the driver class that performs the execution of the application with JavaFX.
 * This class is responsible for loading and displaying the Main Menu of the Pizzeria.
 * @author Sharia Hussain, David Lam
 */
public class RuPizzeriaApplication extends Application
{
    /**
     * Method that runs the JavaFX loader commands and shows the applications.
     * Loads and displays the Main Menu Scene.
     * @param stage object to show the application
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RuPizzeriaApplication.class.getResource("pizzeriaView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("RUPizzeria");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that launches the JavaFX applications
     * @param args input arguments from command line
     */
    public static void main(String[] args) {
        launch();
    }
}