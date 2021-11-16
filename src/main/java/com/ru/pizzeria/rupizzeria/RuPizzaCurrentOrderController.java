package com.ru.pizzeria.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

/**
 * RuPizzaCurrentOrderController is a class that handles all the events driven by the I/O in the application
 * involving Store Orders.
 * @author Sharia Hussain, David Lam
 */
public class RuPizzaCurrentOrderController {

    private Order currentOrder;

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

    /**
     * Used to Information Share between the Main Menu.
     * @param controller RuPizzeriaController reference
     */
    public void setMainController(RuPizzeriaController controller) {
        mainController = controller;
    }

    /**
     * Psuedo-Initialize method required to pass information and initialize key information such
     * as the list views and prices.
     */
    public void safeInitialize() {
        this.currentOrder = mainController.getCurrentOrder();
        this.storeOrders = mainController.getStoreOrder();

        ObservableList<Pizza> pizzasList = FXCollections.observableArrayList(this.currentOrder.getPizzas());
        this.orderListView.setItems(FXCollections.observableList(pizzasList));
        processCost();
        updatePrices();
    }

    /**
     * Resets the Text Areas of the Scene and Resets the Order.
     */
    private void clear() {
        this.salesTaxTextArea.clear();
        this.subtotalTextArea.clear();
        this.orderTotalTextArea.clear();
        this.customerPhoneNumber.clear();

        this.orderListView.getItems().clear();
        this.currentOrder = null; //???
        this.mainController.setCurrentOrder(null);
    }

    /**
     * Calculates the order total of all the pizzas using the
     * subtotal and the sales tax.
     */
    public void calculateOrderTotal() {
        this.orderTotal = this.subtotal + this.salesTax;
        this.currentOrder.setTotalPrice(this.orderTotal);
    }

    /**
     * Calculates the subtotal of all the pizzas in the current order.
     */
    public void calculateSubtotal() {
        double subtotal = 0;

        ArrayList<Pizza> pizzas = this.currentOrder.getPizzas();
        for(int i = 0; i < pizzas.size(); i++) {
            subtotal += pizzas.get(i).price();
        }

        this.subtotal = subtotal;
    }

    /**
     * Calculates the sales tax of the order.
     */
    public void calculateSalesTax() {
        this.salesTax = (Pizza.SALES_TAX_RATE/100) * subtotal;
    }

    /**
     * Truncates and rounds a double and puts them in a string.
     * @param value the value to truncate and represent in currency form.
     * @return string representation of the value in currency form.
     */
    public String priceToString(double value) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("$%s", df.format(value));
    }

    /**
     * Prints the phone number into the text area.
     * @param phoneNumber the phone number of the order
     */
    public void setPhoneNumberTextArea(String phoneNumber) {
        customerPhoneNumber.setText(phoneNumber);
    }

    /**
     * Prints the subtotal into the text area.
     * @param subtotal the string representation of the subtotal of the order.
     */
    public void setSubtotalTextArea(String subtotal) {
        subtotalTextArea.setText(subtotal);
    }

    /**
     * Prints the sales tax total into the text area.
     * @param salesTax the string representation of the sales tax of the order.
     */
    public void setSalesTaxTextArea(String salesTax) {
        salesTaxTextArea.setText(salesTax);
    }

    /**
     * Prints the order total into the text area.
     * @param total the total price of the order.
     */
    public void setOrderTotalTextArea(String total) {
        orderTotalTextArea.setText(total);
    }

    /**
     * Method that places an order into store order.
     * @param event the event object that is connected and responds to the UI component
     */
    @FXML
    void placeOrder(ActionEvent event) {
        showConfirmationForOrderToBePlaced();
    }

    /**
     * Method that removes the selected pizza and recalculates costs and prices.
     * @param event the event object that is connected and responds to the UI component.
     */
    @FXML
    void removeSelectedPizza(ActionEvent event) {
        if(orderListView.getSelectionModel().getSelectedItem() != null) {
            if(orderListView.getItems().size() > 0) {
                callRemovePizza();
            }
            else {
                showNoPizzasInOrder();
            }
            processCost();
            updatePrices();
        }
        else {
            showNoPizzasSelected();
        }

    }
    /**
     * Method that calls all the costs that need to be calculated
     */
    private void processCost() {
        calculateSubtotal();
        calculateSalesTax();
        calculateOrderTotal();
    }
    /**
     * Updates the prices of the subtotal, salesTax, and orderTotal
     */
    private void updatePrices() {
        setPhoneNumberTextArea(this.currentOrder.getPhoneNumber());
        setSubtotalTextArea(priceToString(subtotal));
        setSalesTaxTextArea(priceToString(salesTax));
        setOrderTotalTextArea(priceToString(orderTotal));
    }

    /**
     * Calls the remove pizza in the order and updates the list view
     */
    public void callRemovePizza() {
        Pizza pizza = orderListView.getSelectionModel().getSelectedItem();
        orderListView.getItems().remove(pizza);
        this.currentOrder.removePizza(pizza);
    }

    /**
     * Alert box when removing the last pizza
     */
    public void showNoPizzasInOrder() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Warning with Removing Pizzas From Order");
        alert.setHeaderText("Removing Pizzas");
        alert.setContentText("There are no pizzas in the order!");
        Optional<ButtonType> result = alert.showAndWait();
    }

    /**
     * Alert box when no pizza is selected
     */
    public void showNoPizzasSelected() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Warning with Removing Pizzas From Order");
        alert.setHeaderText("Removing Pizzas");
        alert.setContentText("No pizzas selected!");
        Optional<ButtonType> result = alert.showAndWait();
    }

    /**
     * Alert box to show a confirmation when the order is to be placed
     */
    public void showConfirmationForOrderToBePlaced()
    {
        if(this.currentOrder != null && this.currentOrder.getPizzas().size() > 0) {
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
        alert.setContentText("No current order or there is no pizzas in the current order.");
        alert.showAndWait();
    }
}
