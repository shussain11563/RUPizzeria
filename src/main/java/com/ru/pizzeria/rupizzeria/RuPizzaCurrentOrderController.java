package com.ru.pizzeria.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class RuPizzaCurrentOrderController implements Initializable
{
    static final double SALES_TAX_RATE = 0.0625;
    Order currentOrder;

    @FXML
    private TextArea customerPhoneNumber;

    @FXML
    private ListView<Pizza> orderListView;

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

    private double salesTax;
    private double orderTotal;
    private double subtotal;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setMainController(RuPizzeriaController controller)
    {
        mainController = controller;
    }

    public void safeInitialize()
    {
        this.currentOrder = mainController.getCurrentOrder();
        this.storeOrders = mainController.getStoreOrder();

        //listview
        ObservableList<Pizza> pizzasList = FXCollections.observableArrayList(this.currentOrder.getPizzas());
        this.orderListView.setItems(FXCollections.observableList(pizzasList));
        processCost();
        updatePrices();
    }

    private void clear()
    {
        //make a private method that forced textArea to be uneditable
        this.salesTaxTextArea.clear();
        this.subtotalTextArea.clear();
        this.orderTotalTextArea.clear();
        this.customerPhoneNumber.clear();
        this.orderListView.getItems().clear();
    }

    public void calculateOrderTotal() {
        this.orderTotal = this.subtotal + this.salesTax;
    }

    public void calculateSubtotal() {
        double subtotal = 0;

        ArrayList<Pizza> pizzas = this.currentOrder.getPizzas();

        for(int i = 0; i < pizzas.size(); i++) {
            subtotal += pizzas.get(i).getPrice();
        }

        this.subtotal = subtotal;
    }

    public void calculateSalesTax() {
        this.salesTax = SALES_TAX_RATE * subtotal;
    }

    public String priceToString(double value) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("$%s", df.format(value));
    }

    public void setPhoneNumberTextArea(String phoneNumber) {
        customerPhoneNumber.setText(phoneNumber);
    }

    public void setSubtotalTextArea(String subtotal) {
        subtotalTextArea.setText(subtotal);
    }

    public void setSalesTaxTextArea(String salesTax) {
        salesTaxTextArea.setText(salesTax);
    }
    
    public void setOrderTotalTextArea(String total) {
        orderTotalTextArea.setText(total);
    }


    @FXML
    void placeOrder(ActionEvent event) {
        showConfirmationForOrderToBePlaced();
    }

    private void processCost() {
        calculateSubtotal();
        calculateSalesTax();
        calculateOrderTotal();
    }
    private void updatePrices() {
        setPhoneNumberTextArea(this.currentOrder.getPhoneNumber());
        setSubtotalTextArea(priceToString(subtotal));
        setSalesTaxTextArea(priceToString(salesTax));
        setOrderTotalTextArea(priceToString(orderTotal));
    }
    @FXML
    void removeSelectedPizza(ActionEvent event) {
        if(orderListView.getSelectionModel().getSelectedItem() != null) {
            if(orderListView.getItems().size() > 1) {
                callRemovePizza();
            }else {
                showNoPizzasInOrder();
            }
        }

        processCost();
        updatePrices();
    }

    public void callRemovePizza() {
        Pizza pizza = orderListView.getSelectionModel().getSelectedItem();
        orderListView.getItems().remove(pizza);
        this.currentOrder.removePizza(pizza);
    }

    public void showNoPizzasInOrder() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Warning with Removing Pizzas From Order");
        alert.setHeaderText("Removing Pizzas");
        alert.setContentText("You are removing the last Pizza in the Order");
        Optional<ButtonType> result = alert.showAndWait();
    }

    public void showConfirmationForOrderToBePlaced() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning with Placing Order");
        alert.setHeaderText("Place the order");
        alert.setContentText("You are about to place an order");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            this.storeOrders.addOrder(this.order);
            clear();
        }
    }

}
