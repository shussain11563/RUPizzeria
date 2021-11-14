package com.ru.pizzeria.rupizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RuPizzaCurrentOrderController implements Initializable
{
    Order currentOrder;

    @FXML
    private TextArea customerPhoneNumber;

    @FXML
    private ListView<String> orderListView;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Button removeSelectedPizzaButton;

    @FXML
    private TextArea salesTaxTextArea;

    @FXML
    private TextArea subtotalTextArea;

    @FXML
    private TextArea orderTotalTextArea;

    private RuPizzeriaController mainController;

    private Order order;

    private StoreOrders storeOrders;

    //
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

    public void setMainController(RuPizzeriaController controller)
    {
        mainController = controller;
    }

    public void safeInitialize()
    {
        this.currentOrder = mainController.getCurrentOrder();
        this.storeOrders = mainController.getStoreOrder();
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

    public void calculateOrderTotal() {

    }

    public void calculateSubtotal() {

    }

    public void calculateSalesTax() {

    }

    public void setPhoneNumberTextArea(String phoneNumber) {
        customerPhoneNumber.setText(phoneNumber);
    }

    public void setSubtotalTextArea(String subtotal) {
        subtotalTextArea.setText(subtotal);
    }
    
    public void setOrderTotalTextArea(String total) {
        orderTotalTextArea.setText(total);
    }


    @FXML
    void placeOrder(ActionEvent event)
    {
         //
        this.storeOrders.addOrder(this.order);

        //add to store order
        clear(); //fix clear

    }

    private void processCost()
    {
        //update all things
        //make everything uneditable
    }

    @FXML
    void removeSelectedPizza(ActionEvent event)
    {
        //ArrayList<Pizza> temp = new ArrayList<Pizza>();

        //get clicked from listview -> pizza


        //remove that pizza

        //recalculate prices

        //

    }

}
