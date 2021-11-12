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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class RuPizzaStoreOrderController implements Initializable {

    @FXML
    private TextArea customerPhoneNumber;

    @FXML
    private ListView<Pizza> orderListView;

    @FXML
    private TextArea orderTotalTextArea;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Button removeSelectedPizzaButton;

    @FXML
    private TextArea salesTaxTextArea;

    @FXML
    private TextArea subtotalTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //private ListView<Pizza> orderListView;

        //subtotal
        //sales tax
        //order total

        //might requrie
        //phone number
        //order


    }

    public void initTest()
    {
        //ArrayList<Pizza> pizzasInOrder =
        //ObservableList<Pizza> pizzas = FXCollections

    }

    //when add to order
    private void clear()
    {
        //make a private method that forced textArea to be uneditable
        this.salesTaxTextArea.clear();
        this.subtotalTextArea.clear();
        this.orderTotalTextArea.clear();
        this.customerPhoneNumber.clear();
    }





    @FXML
    void placeOrder(ActionEvent event)
    {


        clear();

    }

    @FXML
    void removeSelectedPizza(ActionEvent event)
    {

    }




}

