package com.ru.pizzeria.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.*;

public class RuPizzaCustomizeController implements Initializable {
    /*
    Update Price when Adding/Removing Toppings
    Prompt user when removing orignal/essential toppings but price doesnt change
    Max number of toppings is 7
    Order Comfirmation when pressing add

    pr
     */

    private RuPizzeriaController mainController;

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

    public void setMainController(RuPizzeriaController controller)
    {

        mainController = controller;
    }

    @FXML
    void selectPizzaSize(ActionEvent event) {
        Size selectedItem = myComboBox.getSelectionModel().getSelectedItem();

        //System.out.println(selectedItem);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        //mainController.printHello();


        //CcomboBox
        //System.out.println(mainController.deluxePizzaButton.getText()); //BUG, ONLY PRINTS DELUXE PIZZA

        String pizzaFlavor = ""; //add to order
        Pizza pizza = PizzaMaker.createPizza(pizzaFlavor); //null
        if(pizza != null)
        {
            //
        }
        pizza = PizzaMaker.createPizza("deluxe");

        //DOES N0T MATTER WAHT PIZZA TYPE, WORKS UNIVERSALLY
        ArrayList<Topping> selectedToppings = pizza.getToppings();
        ArrayList<Topping> allToppings = new ArrayList<Topping>(Arrays.asList(Topping.values()));
        allToppings.removeAll(selectedToppings);
        ArrayList<Topping> additionalToppings = allToppings;
        System.out.println(allToppings); //gives you an arraylist of the nonselected toppings

        ObservableList<Topping> selectedToppingsList = FXCollections.observableArrayList(selectedToppings);
        selectedToppingsListView.setItems(FXCollections.observableList(selectedToppingsList));

        ObservableList<Topping> additionalToppingsList = FXCollections.observableArrayList(additionalToppings);
        additionalToppingsListView.setItems(FXCollections.observableList(additionalToppingsList));


        /*

         */


        ObservableList<Size> options =  FXCollections.observableList(Arrays.asList(Size.values()));
        myComboBox.setItems(options);
        myComboBox.setValue(Size.Small); //default value
    }

    public void setPizzaPicture(String text) {
        if(text.equals("Deluxe Pizza")) {
            String path = "file:resources/deluxePizza.jpg";
            Image image = new Image(path);
            pizzaImage.setImage(image);
        }
        else if(text.equals("Hawaiian Pizza")) {
            String path = "file:resources/hawaiianPizza.jpg";
            Image image = new Image(path);
            pizzaImage.setImage(image);

        }
        else if(text.equals("Pepperoni Pizza")) {
            String path = "file:resources/pepPizza.jpg";
            Image image = new Image(path);
            pizzaImage.setImage(image);
        }
    }

    public void setPizzaText(String text) {
        pizzaButton.setText(text);
    }

    public void setOrignalPizzaToppings(String text)
    {
        /*
        if(text.equals("Deluxe Pizza")) {
            ObservableList<String> items = FXCollections.observableArrayList("Sausage", "Onion", "Green Pepper", "Black Olives", "Diced Tomatoes");
            selectedToppingsListView.setItems(FXCollections.observableList(items));
        }
        else if(text.equals("Hawaiian Pizza")) {
            ObservableList<String> items = FXCollections.observableArrayList("Pineapple", "Ham");
            selectedToppingsListView.setItems(FXCollections.observableList(items));
        }
        else if(text.equals("Pepperoni Pizza")) {
            ObservableList<String> items = FXCollections.observableArrayList("Pepperoni");
            selectedToppingsListView.setItems(FXCollections.observableList(items));
        }

         */
    }

    //use obejct
    public void setAdditionalPizzaToppings(String text)
    {
        /*

        if(text.equals("Deluxe Pizza"))
        {
            ObservableList<String> items = FXCollections.observableArrayList("Pineapple", "Pepperoni",  "Ham", "Mushroom", "Chicken", "Beef",

                    "Salami", "Spinach");
            additionalToppingsListView.getItems().clear();
            additionalToppingsListView.setItems(FXCollections.observableList(items));
        }
        else if(text.equals("Hawaiian Pizza")) {
            ObservableList<String> items = FXCollections.observableArrayList("Pepperoni", "Mushroom", "Onion", "Sausage", "Chicken", "Beef",
                    "Salami", "Spinach", "Black Olives", "Green Pepper", "Diced Tomatoes");
            additionalToppingsListView.getItems().clear();

            additionalToppingsListView.setItems(FXCollections.observableList(items));
        }
        else if(text.equals("Pepperoni Pizza")) {
            ObservableList<String> items = FXCollections.observableArrayList("Pineapple", "Ham", "Mushroom", "Onion", "Sausage", "Chicken", "Beef",
                    "Salami", "Spinach", "Black Olives", "Green Pepper", "Diced Tomatoes");
            additionalToppingsListView.getItems().clear();
            additionalToppingsListView.setItems(FXCollections.observableList(items));
        }

         */
    }

    @FXML
    void addOrder(ActionEvent event) {
        return;
    }

    @FXML
    void removeToppings(ActionEvent event)
    {/*
        if(selectedToppingsListView.getSelectionModel().getSelectedItem() != null) {
            if(pizzaButton.getText().equals("Deluxe Pizza")) {
                if(checkDeluxeToppings(selectedToppingsListView.getSelectionModel().getSelectedItem())) {
                    additionalToppingsListView.getItems().add(selectedToppingsListView.getSelectionModel().getSelectedItem());
                    selectedToppingsListView.getItems().remove(selectedToppingsListView.getSelectionModel().getSelectedItem());
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Warning with Removing Toppings");
                    alert.setHeaderText("Removing Toppings");
                    alert.setContentText("You are removing essential toppings");
                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.get() == ButtonType.OK) {
                        additionalToppingsListView.getItems().add(selectedToppingsListView.getSelectionModel().getSelectedItem());
                        selectedToppingsListView.getItems().remove(selectedToppingsListView.getSelectionModel().getSelectedItem());
                    }
                }
            }
        }
        */

    }

    @FXML

    void addToppings(ActionEvent event)
    {
        if(additionalToppingsListView.getSelectionModel().getSelectedItem() != null)
        {
            if(selectedToppingsListView.getItems().size() < Pizza.MAX_TOPPINGS)
            {
                selectedToppingsListView.getItems().add(additionalToppingsListView.getSelectionModel().getSelectedItem());
                additionalToppingsListView.getItems().remove(additionalToppingsListView.getSelectionModel().getSelectedItem());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR WITH TOPPINGS");
                alert.setHeaderText("Maximum numbers of toppings");
                alert.setContentText("You can only have at most 7 toppings");
                alert.showAndWait();
            }

        }
    }
    public boolean checkDeluxeToppings(String selectedItem) {
        if(selectedItem.equals("Sausage") || selectedItem.equals("Onion") || selectedItem.equals("Green Pepper")
                || selectedItem.equals("Black Olives") || selectedItem.equals("Diced Tomatoes")) {


            return false;
        }
        return true;
    }


}
