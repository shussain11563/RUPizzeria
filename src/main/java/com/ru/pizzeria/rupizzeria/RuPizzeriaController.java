package com.ru.pizzeria.rupizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.ObjDoubleConsumer;

public class RuPizzeriaController implements Initializable {
    @FXML
    private Button deluxePizzaButton;

    @FXML
    private Button hawaiianPizzaButton;

    @FXML
    private Button pepperoniPizzaButton;

    @FXML
    private TextField customerPhoneNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Order orderTest = new Order("11111144444");
        System.out.println("Hi");
        //order
        //initialize

    }

    private void printHello()
    {
        System.out.println("Hi");
    }

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
    void openPizzaWindow()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-customize-view.fxml"));
        RuPizzaCustomizeController pizzaView = loader.getController();

        pizzaView.setMainController(this);
    }

    @FXML
    void openDeluxeCustomizePizzaWindow(ActionEvent event) throws IOException {
        if(checkPhoneNumber(customerPhoneNumber.getText().trim())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Pizza Order");
            alert.setContentText("Click to Continue with Order");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pizza-customize-view.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                RuPizzaCustomizeController setController = fxmlLoader.getController();
                setController.setPizzaText(deluxePizzaButton.getText());
                setController.setPizzaPicture(deluxePizzaButton.getText());
                setController.setPizzaPhoneNumber(customerPhoneNumber.getText());

                Stage stage = new Stage();
                stage.setScene(new Scene(root, 900, 700));
                stage.setTitle("Store Orders");
                stage.show();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with Phone Number");
            alert.setContentText("Phone Number Not Valid");
            alert.showAndWait();
        }
    }

    @FXML
    void openHawaiianCustomizePizzaWindow(ActionEvent event) throws IOException {
        if(checkPhoneNumber(customerPhoneNumber.getText().trim())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Pizza Order");
            alert.setContentText("Click to Continue with Order");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
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

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with Phone Number");
            alert.setContentText("Phone Number Not Valid");
            alert.showAndWait();
        }

    }

    @FXML
    void openPepperoniCustomizePizzaWindow(ActionEvent event) throws IOException {
        if(checkPhoneNumber(customerPhoneNumber.getText().trim())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Pizza Order");
            alert.setContentText("Click to Continue with Order");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
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
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with Phone Number");
            alert.setContentText("Phone Number Not Valid");
            alert.showAndWait();
        }
    }

    private boolean checkPhoneNumber(String text) {
        try {
            double phoneNumber = Double.parseDouble(text);
            if(text.length() != 10)
                return false;
            return true;

        }catch(NumberFormatException e){
            return false;
        }
    }

}