package com.ru.pizzeria.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RuPizzaStoreOrderController implements Initializable
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //REMOVE THIS METHOD
    }

    public void setMainController(RuPizzeriaController controller)
    {
        this.mainController = controller;
    }

    public void safeInitialize()
    {
        this.storeOrders = mainController.getStoreOrder();
        populatePhoneNumber();
    }

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


    @FXML
    void cancelOrder(ActionEvent event)
    {
        String phoneNumber = this.customerPhoneNumberComboBox.getSelectionModel().getSelectedItem();

        storeOrders.removeOrder(storeOrders.find(phoneNumber));

        this.storeOrderListView.getItems().clear();

        populatePhoneNumber();
        this.orderTotalTextArea.clear();

        //update

        //set new thing
    }

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

    private void errorNoFileFound()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with File");
        alert.setContentText("File was not found.");
        alert.showAndWait();
    }









}
