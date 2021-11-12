package com.ru.pizzeria.rupizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RuPizzeriaController implements Initializable {
    @FXML
    private Button deluxePizzaButton;

    @FXML
    private Button hawaiianPizzaButton;

    @FXML
    private Button pepperoniPizzaButton;

    @FXML
    private TextField customerPhoneNumber;

    @FXML
    private ImageView deluxeImage;

    @FXML
    private ImageView hawaiianImage;

    @FXML
    private ImageView pepperoniImage;

    private StoreOrders storeOrders;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        this.storeOrders = new StoreOrders();
        //pizzaImageViewTest.getImage();
        //Order orderTest = new Order("11111144444");

        //order
        //initialize

        //getter

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


    public void openPizzaCustomizationView(String pizzaText, Image pizzaImage) throws IOException
    {
        if(checkPhoneNumber(customerPhoneNumber.getText().trim())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Pizza Order");
            alert.setContentText("Click to Continue with Order");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pizza-customize-view.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                RuPizzaCustomizeController setController = fxmlLoader.getController();

                setController.setPizzaText(pizzaText);
                setController.setPizzaPicture(pizzaImage);
         

                setController.setPizzaPhoneNumber(customerPhoneNumber.getText());
                setController.safeInitialize(); //safe initalizerTest


                Stage stage = new Stage();
                stage.setScene(new Scene(root, 900, 700));
                stage.setTitle("Store Orders");
                stage.show();

            }
        }
        else
        {
            errorAlert();
        }


    }

    @FXML
    void openDeluxeCustomizePizzaWindow(ActionEvent event) throws IOException
    {
        openPizzaCustomizationView("Deluxe Pizza", deluxeImage.getImage());
    }

    @FXML
    void openHawaiianCustomizePizzaWindow(ActionEvent event) throws IOException
    {
        openPizzaCustomizationView("Hawaiian Pizza", hawaiianImage.getImage());
    }

    @FXML
    void openPepperoniCustomizePizzaWindow(ActionEvent event) throws IOException
    {
        openPizzaCustomizationView("Pepperoni Pizza", pepperoniImage.getImage());
    }

    private void errorAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Phone Number");
        alert.setContentText("Phone Number Not Valid");
        alert.showAndWait();
    }

    private static boolean checkPhoneNumber(String text)
    {
        if(text.length() != 10)
            return false;

        //make the 10 a static final constant
        for(int i = 0; i < text.length(); i++)
        {
            if(!Character.isDigit(text.charAt(i)))
            {
                return false;
            }
        }

        return true;

    }

}