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

public class RuPizzaCurrentOrderController {

    Order currentOrder;

    @FXML
    private TextArea customerPhoneNumber;

    @FXML
    private ListView<Pizza> orderListView;

    @FXML
    private TextArea salesTaxTextArea;

    @FXML
    private TextArea subtotalTextArea;

    @FXML
    private TextArea orderTotalTextArea;

    private RuPizzeriaController mainController;

    private StoreOrders storeOrders;

    private double salesTax;
    private double orderTotal;
    private double subtotal;

    public void setMainController(RuPizzeriaController controller) {
        mainController = controller;
    }

    public void safeInitialize() {
        this.currentOrder = mainController.getCurrentOrder();
        this.storeOrders = mainController.getStoreOrder();

        ObservableList<Pizza> pizzasList = FXCollections.observableArrayList(this.currentOrder.getPizzas());
        this.orderListView.setItems(FXCollections.observableList(pizzasList));
        processCost();
        updatePrices();
    }

    private void clear() {
        this.salesTaxTextArea.clear();
        this.subtotalTextArea.clear();
        this.orderTotalTextArea.clear();
        this.customerPhoneNumber.clear();

        this.orderListView.getItems().clear();
        this.currentOrder = null; //???
        this.mainController.setCurrentOrder(null);
    }

    public void calculateOrderTotal() {
        this.orderTotal = this.subtotal + this.salesTax;
        this.currentOrder.setTotalPrice(this.orderTotal);
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
        this.salesTax = (Pizza.SALES_TAX_RATE/100) * subtotal;
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

    @FXML
    void removeSelectedPizza(ActionEvent event) {
        if(orderListView.getSelectionModel().getSelectedItem() != null) {
            if(orderListView.getItems().size() > 1) {
                callRemovePizza();
            }else {
                showNoPizzasInOrder();
            }
            processCost();
            updatePrices();
        }
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

    /**
     * Alert box to show a confirmation when the order is to be placed
     */
    public void showConfirmationForOrderToBePlaced() {
        if(this.currentOrder != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning with Placing Order");
            alert.setHeaderText("Place the order");
            alert.setContentText("You are about to place an order");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                this.storeOrders.addOrder(this.currentOrder);
                clear();
            }
        }else {
            errorNoCurrentOrderAlert();
        }
    }

    /**
     * Alert box when there is no Current Order to be Placed
     */
    private void errorNoCurrentOrderAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Current Order");
        alert.setContentText("There is not current order.");
        alert.showAndWait();
    }
}
