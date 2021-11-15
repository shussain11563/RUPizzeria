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
import java.util.ArrayList;
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

    private Order currentOrder;

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

    public StoreOrders getStoreOrder()
    {
        return this.storeOrders;
    }

    @FXML
    void openCurrentOrdersWindow(ActionEvent event) throws IOException {
        if(this.currentOrder != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("current-order-view.fxml"));
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

    @FXML
    void openManageStoreOrdersWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("store-orders-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        RuPizzaStoreOrderController setController = fxmlLoader.getController();

        setController.setMainController(this);
        setController.safeInitialize();


        Stage stage = new Stage();
        stage.setScene(new Scene(root, 900, 700));
        stage.setTitle("Store Orders");
        stage.show();

        /*

                setController.setPizzaText(pizzaText);
                setController.setPizzaPicture(pizzaImage);
                //setController.setPizzaPhoneNumber(customerPhoneNumber.getText());

                setController.safeInitialize(); //safe initalizerTest

         */
    }

    /*
    //NOT FOUND
    private boolean orderInStoreOrder(String phoneNumber)
    {
        ArrayList<Order> orders = this.storeOrders.getOrders();
        for(int i = 0; i < orders.size() ; i++)
        {
            if(orders.get(i).getPhoneNumber().equals(phoneNumber))
            {
                return true;
                //System.out.println()
            }
        }

        return false;

    }

     */


    public void openPizzaCustomizationView(String pizzaText, Image pizzaImage) throws IOException
    {
        //if a new phone number is defined in the text field, create new order
        //check through store orders

        //hypothetical
        //we have 123-456-7890 and they put 7 pizzas into Order foo
        //but they do not order it
        // option 1 - keep the order up because the number is still up
        // option 2 -  a new number is put up, we reset the order
        // option 2 elegant, we check through storeOrder and see if it is not defined
        //option 2 elegant refined, everytime a new phone number is entered, we check store number and do elegant 2

        //problem with option 2 elegant

        // lets say 123-456-7890 put 7 pizzas in Order foo but the order was not placed
        // now lets say we put 1234567892 and get 7 orders

        //but how would we order the 7 pizzas without reset the order

        //chaneg th

        /*
        if order is null or is a new phone number
        --> create/reinstantiate order



         */



        /*
        if(!orderInStoreOrder(customerPhoneNumber.getText().trim()))
        {
            //check order too and see if its alreayd instantiated, maybet his method is not neccessary

            //reinstantiate


        }

        //

         */
       //remove the redundandant code from my implementation
        boolean isValid = checkPhoneNumber(customerPhoneNumber.getText().trim());
        boolean isSameNumber = this.currentOrder != null && (this.currentOrder.getPhoneNumber().equals(customerPhoneNumber.getText().trim()));

        if(this.currentOrder == null || (isValid == true && isSameNumber == false))
        {

            System.out.println(this.currentOrder == null);
            System.out.println((isValid == true && isSameNumber == false));


            this.currentOrder = new Order(this.customerPhoneNumber.getText().trim());
            System.out.println("Are we resetting?");

        }

        //we might need our store order method we deleteed to see if a duplicate phone number is being entered

        if(checkPhoneNumber(customerPhoneNumber.getText().trim()))
        {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Pizza Order");
            alert.setContentText("Click to Continue with Order");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pizza-customize-view.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                RuPizzaCustomizeController setController = fxmlLoader.getController();

                setController.setMainController(this);

                setController.setPizzaText(pizzaText);
                setController.setPizzaPicture(pizzaImage);
                //setController.setPizzaPhoneNumber(customerPhoneNumber.getText());

                setController.safeInitialize(); //safe initalizerTest

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

    public Order getCurrentOrder() {
        return currentOrder;
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

    private void errorInvalidPhoneNumberAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Phone Number");
        alert.setContentText("Phone Number Not Valid");
        alert.showAndWait();
    }

    private void errorNoCurrentOrderAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Current Order");
        alert.setContentText("There is not current order.");
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


    //??
    /*
    public void setCurrentOrder(Order newOrder) {
        currentOrder = newOrder;
    }

     */

}