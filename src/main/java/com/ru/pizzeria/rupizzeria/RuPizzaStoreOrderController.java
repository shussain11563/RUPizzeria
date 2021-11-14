package com.ru.pizzeria.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.FileNotFoundException;
import java.net.URL;
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
    private ListView<Order> storeOrderListView;

    private RuPizzeriaController mainController;

    private StoreOrders storeOrders;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void setMainController(RuPizzeriaController controller)
    {
        this.mainController = controller;
    }

    public void safeInitialize()
    {
        this.storeOrders = mainController.getStoreOrder();

        //make this a method
        ArrayList<String> phoneNumbers = new ArrayList<String>();
        ArrayList<Order> orders = this.storeOrders.getOrders();
        for(int i = 0; i < orders.size(); i++)
        {
            phoneNumbers.add(orders.get(i).getPhoneNumber());
        }
        ObservableList<String> phoneNumbersList = FXCollections.observableArrayList(phoneNumbers);
        this.customerPhoneNumberComboBox.setItems(phoneNumbersList);

        //make the first phone number one default
        //combo box menu

        /*
        ObservableList<Size> options =  FXCollections.observableList(Arrays.asList(Size.values()));
        myComboBox.setItems(options);
        myComboBox.setValue(Size.Small); //default value
        *
         */
    }


    @FXML
    void cancelOrder(ActionEvent event)
    {

    }

    @FXML
    void exportStoreOrders(ActionEvent event)
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targeFile = chooser.showSaveDialog(stage); //get the reference of the target file
        try
        {
            this.storeOrders.export(targeFile);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("FIle not hehrehwidjewiji");
            //put alert box!!!

        }


    }









}
