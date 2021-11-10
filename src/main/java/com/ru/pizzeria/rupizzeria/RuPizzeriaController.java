package com.ru.pizzeria.rupizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class RuPizzeriaController {
    @FXML
    private Button deluxePizzaButton;

    @FXML
    private Button hawaiianPizzaButton;

    @FXML
    private Button pepperoniPizzaButton;

    @FXML
    void openCurrentOrdersWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("current-order-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 900, 700));
        stage.setTitle("Customize Your Pizza");
        stage.show();
    }

    @FXML
    void openManageStoreOrdersWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("store-orders-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 900, 700));
        stage.setTitle("Store Orders");
        stage.show();
    }

    @FXML
    void openDeluxeCustomizePizzaWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pizza-customize-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        RuPizzaCustomizeController setController = fxmlLoader.getController();
        setController.setPizzaText(deluxePizzaButton.getText());
        setController.setPizzaPicture(deluxePizzaButton.getText());
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 900, 700));
        stage.setTitle("Store Orders");
        stage.show();
    }

    @FXML
    void openHawaiianCustomizePizzaWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pizza-customize-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        RuPizzaCustomizeController setController = fxmlLoader.getController();
        setController.setPizzaText(hawaiianPizzaButton.getText());
        setController.setPizzaPicture(hawaiianPizzaButton.getText());
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 900, 700));
        stage.setTitle("Store Orders");
        stage.show();
    }

    @FXML
    void openPepperoniCustomizePizzaWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pizza-customize-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        RuPizzaCustomizeController setController = fxmlLoader.getController();
        setController.setPizzaText(pepperoniPizzaButton.getText());
        setController.setPizzaPicture(pepperoniPizzaButton.getText());
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 900, 700));
        stage.setTitle("Store Orders");
        stage.show();
    }


}