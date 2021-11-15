package com.ru.pizzeria.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

/**
 * RuPizzaCustomizeController is a class that handles all the events driven by the I/O in the application
 * involving Pizza Customization such as adding or removing toppings.
 * @author Sharia Hussain, David Lam
 */
public class RuPizzaCustomizeController implements Initializable {
    /*
    Update Price when Adding/Removing Toppings
    Prompt user when removing orignal/essential toppings but price doesnt change
    Max number of toppings is 7
    Order Comfirmation when pressing add

    pr
     */
    private ArrayList<Topping> selectedToppings;

    private ArrayList<Topping> additionalToppings;


    private RuPizzeriaController mainController;

    private Pizza pizza;

    @FXML
    private Button pizzaButton;

    @FXML
    private ComboBox<Size> myComboBox;

    @FXML
    private ImageView pizzaImage;

    @FXML
    private TextArea priceTextArea;

    @FXML
    private ListView<Topping> selectedToppingsListView;

    @FXML
    private ListView<Topping> additionalToppingsListView;

    private Order currentOrder;

    /**
     * Used to Information Share between the Main Menu.
     * @param controller RuPizzeriaController reference
     */
    public void setMainController(RuPizzeriaController controller)
    {
        mainController = controller;
    }

    /**
     * Sets the price when size of pizza is changed.
     * @param event the event object that is connected and responds to the UI component.
     */
    @FXML
    void selectPizzaSize(ActionEvent event) {
        Size selectedSize = myComboBox.getSelectionModel().getSelectedItem();
        pizza.setSize(selectedSize);

        setPrice();

    }

    /**
     * Sets the price of the pizza to the text area.
     */
    private void setPrice()
    {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        priceTextArea.setText(df.format(pizza.price()));
        priceTextArea.setEditable(false);
    }

    /**
     * Initalizes key elements before the scene is shown such as the Size Combo Box.
     * @param location location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<Size> options =  FXCollections.observableList(Arrays.asList(Size.values()));
        myComboBox.setItems(options);
        myComboBox.setValue(Size.Small);
    }

    /**
     * Psuedo-Initialize method required to pass information and initialize key information such
     * as the list views and prices.
     */
    public void safeInitialize()
    {
        this.currentOrder = mainController.getCurrentOrder();
        String pizzaFlavor = pizzaButton.getText();
        Pizza pizza = PizzaMaker.createPizza(pizzaFlavor); //null
        this.pizza = pizza;

        //newOrder = new Order(phoneNumber);

        //listview
        updateListView();

        //displays default price
        setPrice();

    }

    /**
     * Sets the picture of the pizza in the view.
     * @param image the image to set in the view
     */
    public void setPizzaPicture(Image image)
    {
        this.pizzaImage.setImage(image);
    }

    /**
     * Sets the text of the pizza in the view.
     * @param text the type of pizza, i.e. Deluxe, Pepperoni, Hawaiian
     */
    public void setPizzaText(String text) {
        pizzaButton.setText(text);
    }



    //change this method

    /**
     * Adds the pizza to an order and resets the view.
     * @param event the event object that is connected and responds to the UI component
     * @throws IOException throw error regarding I/O failure
     */
    @FXML
    void addOrder(ActionEvent event) throws IOException
    {
        this.currentOrder.addPizza(this.pizza);
        String pizzaFlavor = pizzaButton.getText();
        Pizza pizza = PizzaMaker.createPizza(pizzaFlavor);
        this.pizza = pizza;
        updateListView();
        setPrice();
        myComboBox.setValue(Size.Small);
    }


    /**
     * Updates the List View of the toppings.
     */
    public void updateListView()
    {
        selectedToppings = this.pizza.getToppings();
        ArrayList<Topping> allToppings = new ArrayList<Topping>(Arrays.asList(Topping.values()));
        allToppings.removeAll(selectedToppings);
        additionalToppings = allToppings;

        ObservableList<Topping> selectedToppingsList = FXCollections.observableArrayList(selectedToppings);
        selectedToppingsListView.setItems(FXCollections.observableList(selectedToppingsList));
        ObservableList<Topping> additionalToppingsList = FXCollections.observableArrayList(additionalToppings);
        additionalToppingsListView.setItems(FXCollections.observableList(additionalToppingsList));
    }

    //make less redundant

    /**
     * Method that handles the removal of a topping including an alert box.
     * @param event the event object that is connected and responds to the UI component
     */
    @FXML
    void removeToppings(ActionEvent event)
    {
        if(selectedToppingsListView.getSelectionModel().getSelectedItem() != null)
        {
            if(selectedToppingsListView.getItems().size() > 0)
            {
                if(pizzaButton.getText().equals("Deluxe Pizza")) {
                    if(checkDeluxeToppings(selectedToppingsListView.getSelectionModel().getSelectedItem())) {
                        showConfirmationRemoveEssentialToppings();
                    }else {
                        callRemoveToppings();
                    }
                } else if(pizzaButton.getText().equals("Hawaiian Pizza")) {
                    if(checkHawaiianToppings(selectedToppingsListView.getSelectionModel().getSelectedItem())) {
                        showConfirmationRemoveEssentialToppings();
                    }else {
                        callRemoveToppings();
                    }

                } else if(pizzaButton.getText().equals("Pepperoni Pizza")) {
                    if(checkPepperoniToppings(selectedToppingsListView.getSelectionModel().getSelectedItem())) {
                        showConfirmationRemoveEssentialToppings();
                    }else {
                        callRemoveToppings();
                    }
                }
            }
            else if(selectedToppingsListView.getItems().size() <= 0) {
               showConfirmationNoToppingsOnPizza();
            }
        }
    }

    /**
     * Shows alert box regarding removing an essential topping.
     */
    public void showConfirmationRemoveEssentialToppings() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning with Removing Toppings");
        alert.setHeaderText("Removing Toppings");
        alert.setContentText("You are removing essential toppings");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            callRemoveToppings();
        }
    }

    /**
     * Shows alert box when there is no toppings on pizza.
     */
    public void showConfirmationNoToppingsOnPizza() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning with Removing Toppings");
        alert.setHeaderText("Removing Toppings");
        alert.setContentText("No Toppings on Pizza");
        Optional<ButtonType> result = alert.showAndWait();
    }

    /**
     * Removes Toppings to the Pizza.
     * Adds topping to the pizza list view and removes them from the additional toppings view.
     * Sets price of the pizza after an event.
     */
    public void callRemoveToppings() {
        Topping topping = selectedToppingsListView.getSelectionModel().getSelectedItem();
        additionalToppingsListView.getItems().add(topping);
        selectedToppingsListView.getItems().remove(topping);
        this.pizza.removeTopping(topping);
        setPrice();
    }

    /**
     * Adds Toppings to the Pizza.
     * Adds topping to the pizza list view and removes them from the additional toppings view.
     * Sets price of the pizza after an event.
     * @param event the event object that is connected and responds to the UI component
     */
    @FXML
    void addToppings(ActionEvent event)
    {
        if(additionalToppingsListView.getSelectionModel().getSelectedItem() != null)
        {
            if(selectedToppingsListView.getItems().size() < Pizza.MAX_TOPPINGS)
            {
                Topping topping = additionalToppingsListView.getSelectionModel().getSelectedItem();
                selectedToppingsListView.getItems().add(topping);
                additionalToppingsListView.getItems().remove(topping);
                this.pizza.addTopping(topping);
                setPrice();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR WITH TOPPINGS");
                alert.setHeaderText("Maximum numbers of toppings");
                alert.setContentText("You can only have at most 7 toppings");
                alert.showAndWait();
            }

        }
    }

    /**
     * Checks whether a topping is essential for a Deluxe Pizza.
     * @param selectedItem the topping that is either essential or not.
     * @return true if essential, false otherwise
     */
    public boolean checkDeluxeToppings(Topping selectedItem) {
        if(selectedItem.toString().equals("Sausage") || selectedItem.toString().equals("Onion")
                || selectedItem.toString().equals("GreenPepper") || selectedItem.toString().equals("BlackOlives")
                || selectedItem.toString().equals("DicedTomatoes")) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether a topping is essential for a Hawaiian Pizza.
     * @param selectedItem the topping that is either essential or not.
     * @return true if essential, false otherwise
     */
    public boolean checkHawaiianToppings(Topping selectedItem) {
        if(selectedItem.toString().equals("Pineapple") || selectedItem.toString().equals("Ham")){
            return true;
        }
        return false;
    }

    /**
     * Checks whether a topping is essential for a Pepperoni Pizza.
     * @param selectedItem the topping that is either essential or not.
     * @return true if essential, false otherwise
     */
    public boolean checkPepperoniToppings(Topping selectedItem) {
        if(selectedItem.toString().equals("Pepperoni")) {
            return true;
        }
        return false;
    }


}
