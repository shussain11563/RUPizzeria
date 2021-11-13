package com.ru.pizzeria.rupizzeria;

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

import java.net.URL;
import java.util.ResourceBundle;

public class RuPizzaStoreOrderController implements Initializable
{

    @FXML
    private Button cancelOrderButton;

    @FXML
    private ComboBox<?> customerPhoneNumberComboBox;

    @FXML
    private Button exportStoreOrdersButton;

    @FXML
    private TextArea orderTotalTextArea;

    @FXML
    private ListView<Order> storeOrderListView;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }


    @FXML
    void cancelOrder(ActionEvent event) {

    }

    @FXML
    void exportStoreOrders(ActionEvent event)
    {


    }

    void exportFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targeFile = chooser.showSaveDialog(stage); //get the reference of the target file


        //write code to write to the file.
        //order.export(targeFile);
    }








}
