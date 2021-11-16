package com.ru.pizzeria.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * RuPizzaStoreOrderController is a class that handles all the events driven by the I/O in the application
 * involving Store Orders.
 * @author Sharia Hussain, David Lam
 */
public class RuPizzaStoreOrderController
{

    @FXML
    private Button cancelOrderButton;

    @FXML
    private ComboBox<String> customerPhoneNumberComboBox;

    @FXML
    private Button exportStoreOrdersButton;

    @FXML
    private TextArea orderTotalTextArea;

    @FXML
    private ListView<Pizza> storeOrderListView;

    private RuPizzeriaController mainController;

    private StoreOrders storeOrders;


    /**
     * Used to Information Share between the Main Menu.
     * @param controller RuPizzeriaController reference
     */
    public void setMainController(RuPizzeriaController controller)
    {
        this.mainController = controller;
    }

    /**
     * Psuedo-Initialize method required to pass information and initialize key information such
     * as the list views and prices.
     */
    public void safeInitialize()
    {
        this.storeOrders = mainController.getStoreOrder();
        populatePhoneNumber();
    }

    /**
     * Populates the combox box with all the phone numbers in store orders.
     */
    private void populatePhoneNumber()
    {
        ArrayList<String> phoneNumbers = new ArrayList<String>();
        ArrayList<Order> orders = this.storeOrders.getOrders();

        for(int i = 0; i < orders.size(); i++)
        {
            phoneNumbers.add(orders.get(i).getPhoneNumber());
        }
        ObservableList<String> phoneNumbersList = FXCollections.observableArrayList(phoneNumbers);
        this.customerPhoneNumberComboBox.setItems(phoneNumbersList);
    }

    /**
     * Gets the phone number from the drop down and populates the list view with the current
     * order's information.
     * @param event the event object that is connected and responds to the UI component
     */
    @FXML
    void setPhoneNumber(ActionEvent event)
    {
        this.storeOrderListView.getItems().clear();
        String phoneNumber = this.customerPhoneNumberComboBox.getSelectionModel().getSelectedItem();
        if(phoneNumber != null)
        {
            Order order = this.storeOrders.find(phoneNumber);
            this.storeOrderListView.getItems().clear();



            ObservableList<Pizza> pizzasList = FXCollections.observableArrayList(order.getPizzas());
            this.storeOrderListView.setItems(FXCollections.observableList(pizzasList));

            DecimalFormat df = new DecimalFormat("#,##0.00");
            orderTotalTextArea.setText(df.format(order.getTotalPrice()));

        }
        //update after removing order
    }


    /**
     * Cancels an order from the store order when Cancel Order is pressed.
     * @param event the event object that is connected and responds to the UI component
     */
    @FXML
    void cancelOrder(ActionEvent event)
    {
        String phoneNumber = this.customerPhoneNumberComboBox.getSelectionModel().getSelectedItem();

        Order order = storeOrders.find(phoneNumber);
        if(order!=null)
        {
            storeOrders.removeOrder(storeOrders.find(phoneNumber));
        }


        this.storeOrderListView.getItems().clear();

        populatePhoneNumber();
        this.orderTotalTextArea.clear();

        //update

        //set new thing
    }

    /**
     * Opens Window to Navigate and Save an export of store orders to a file.
     * @param event the event object that is connected and responds to the UI component
     */
    @FXML
    void exportStoreOrders(ActionEvent event)
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        try
        {
            this.storeOrders.export(targetFile);
        }
        catch (FileNotFoundException e)
        {
            errorNoFileFound();
        }
    }

    /**
     * Shows alert box when there is an issue with a file object.
     */
    private void errorNoFileFound()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with File");
        alert.setContentText("File was not found.");
        alert.showAndWait();
    }

}
