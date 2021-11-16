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

/**
 * RuPizzeriaController is a class that handles all the events driven by the I/O in the application.
 * @author Sharia Hussain, David Lam
 */
public class RuPizzeriaController implements Initializable {

    @FXML
    private TextField customerPhoneNumber;

    @FXML
    private ImageView deluxeImage;

    @FXML
    private ImageView hawaiianImage;

    @FXML
    private ImageView pepperoniImage;

    private StoreOrders storeOrders;

    private Order currentOrder;

    /**
     * Initalizes key elements before the scene is shown such as StoreOrder.
     * @param location location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.storeOrders = new StoreOrders();
    }

    /**
     * Returns the StoreOrder.
     * @return the current store order
     */
    public StoreOrders getStoreOrder()
    {
        return this.storeOrders;
    }

    /**
     * Opens the Current Order Window View.
     * @param event the event object that is connected and responds to the UI component
     * @throws IOException throw error regarding I/O failure
     */
    @FXML
    void openCurrentOrdersWindow(ActionEvent event) throws IOException {
        if(this.currentOrder != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("currentOrderView.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            RuPizzaCurrentOrderController setController = fxmlLoader.getController();
            setController.setMainController(this);
            setController.safeInitialize();

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 900, 700));
            stage.setTitle("Customize Your Pizza");
            stage.show();
        }else {
            errorNoCurrentOrderAlert();
        }
    }

    /**
     * Opens the Store Order Window View.
     * @param event the event object that is connected and responds to the UI component
     * @throws IOException throw error regarding I/O failure
     */
    @FXML
    void openManageStoreOrdersWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("storeOrdersView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        RuPizzaStoreOrderController setController = fxmlLoader.getController();
        setController.setMainController(this);
        setController.safeInitialize();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 900, 700));
        stage.setTitle("Store Orders");
        stage.show();
    }

    /**
     * Setter method that sets the current order.
     * @param order the order to set.
     */
    public void setCurrentOrder(Order order)
    {
        this.currentOrder = order;
    }


    /**
     * Handles processing the customization view and handles the creation of the order.
     * @param pizzaText the type of pizza to order
     * @param pizzaImage the image of the pizza to order
     * @throws IOException throw error regarding I/O failure
     */
    public void openPizzaCustomizationView(String pizzaText, Image pizzaImage) throws IOException {
        if(this.storeOrders.find(customerPhoneNumber.getText().trim())!=null) {
            errorDuplicatePhoneNumber();
            return;
        }
        boolean isValid = checkPhoneNumber(customerPhoneNumber.getText().trim());
        boolean isSameNumber = this.currentOrder != null && (this.currentOrder.getPhoneNumber().equals(customerPhoneNumber.getText().trim()));

        if((isValid && this.currentOrder == null) || (isValid == true && isSameNumber == false))
        {
            this.currentOrder = new Order(this.customerPhoneNumber.getText().trim());
        }

        if(checkPhoneNumber(customerPhoneNumber.getText().trim()))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Pizza Order");
            alert.setContentText("Click to Continue with Order");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pizzaCustomizeView.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                RuPizzaCustomizeController setController = fxmlLoader.getController();
                setController.setMainController(this);
                setController.setPizzaText(pizzaText);
                setController.setPizzaPicture(pizzaImage);
                setController.safeInitialize();
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 900, 700));
                stage.setTitle("Store Orders");
                stage.show();
            }
        }
        else
        {
            errorInvalidPhoneNumberAlert();
        }
    }

    /**
     * Getter method that returns the current order.
     * @return currentOrder, the current active order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Opens the Pizza Customization View to customize pizza for a Deluxe Pizza.
     * @param event the event object that is connected and responds to the UI component
     * @throws IOException throw error regarding I/O failure
     */
    @FXML
    void openDeluxeCustomizePizzaWindow(ActionEvent event) throws IOException
    {
        openPizzaCustomizationView("Deluxe Pizza", deluxeImage.getImage());
    }

    /**
     * Opens the Pizza Customization View to customize pizza for a Hawaiian Pizza.
     * @param event the event object that is connected and responds to the UI component
     * @throws IOException throw error regarding I/O failure
     */
    @FXML
    void openHawaiianCustomizePizzaWindow(ActionEvent event) throws IOException
    {
        openPizzaCustomizationView("Hawaiian Pizza", hawaiianImage.getImage());
    }

    /**
     * Opens the Pizza Customization View to customize pizza for a Pepperoni Pizza.
     * @param event the event object that is connected and responds to the UI component
     * @throws IOException throw error regarding I/O failure
     */
    @FXML
    void openPepperoniCustomizePizzaWindow(ActionEvent event) throws IOException
    {
        openPizzaCustomizationView("Pepperoni Pizza", pepperoniImage.getImage());
    }

    /**
     * Shows alert box when there is a phone number that already ordered
     * tries to order again.
     */
    private void errorDuplicatePhoneNumber()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Phone Number");
        alert.setContentText("Phone Number Has Already Ordered");
        alert.showAndWait();
    }

    /**
     * Shows alert box when there is a phone number that is invalid.
     */
    private void errorInvalidPhoneNumberAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Phone Number");
        alert.setContentText("Phone Number Not Valid");
        alert.showAndWait();
    }

    /**
     * Displays alert box when manipulating an order that does not exist.
     */
    private void errorNoCurrentOrderAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Current Order");
        alert.setContentText("There is no current order.");
        alert.showAndWait();
    }

    /**
     * Validates whether a phoneNumber is valid or not.
     * @param text the phone number from the total text area.
     * @return true if a phone number is valid, false otherwise.
     */
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