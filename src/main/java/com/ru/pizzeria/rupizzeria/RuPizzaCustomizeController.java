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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RuPizzaCustomizeController implements Initializable {
    /*
    Update Price when Adding/Removing Toppings
    Prompt user when removing orignal/essential toppings but price doesnt change
    Max number of toppings is 7
    Order Comfirmation when pressing add

     */
    @FXML
    private Button pizzaButton;

    @FXML
    private ComboBox<String> myComboBox;

    @FXML
    private ImageView pizzaImage;

    @FXML
    private TextArea priceTextArea;

    @FXML
    private ListView<String> selectedToppingsListView;

    @FXML
    private ListView<String> additionalToppingsListView;

    @FXML
    void selectPizzaSize(ActionEvent event) {
        String temp = myComboBox.getSelectionModel().getSelectedItem().toString();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = FXCollections.observableArrayList("Small", "Medium", "Large");
        myComboBox.setItems(options);
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

    public void setOrignalPizzaToppings(String text) {
        if(text.equals("Deluxe Pizza")) {
            List<String> values = Arrays.asList("Sausage", "Onion", "Green Pepper", "Black Olives", "Diced Tomatoes");
            selectedToppingsListView.setItems(FXCollections.observableList(values));
        }
        else if(text.equals("Hawaiian Pizza")) {
            List<String> values = Arrays.asList("Pineapple", "Ham");
            selectedToppingsListView.setItems(FXCollections.observableList(values));
        }
        else if(text.equals("Pepperoni Pizza")) {
            List<String> values = Arrays.asList("Pepperoni");
            selectedToppingsListView.setItems(FXCollections.observableList(values));
        }
    }

    public void setAdditionalPizzaToppings(String text) {
        if(text.equals("Deluxe Pizza")) {
            List<String> values = Arrays.asList("Pineapple", "Pepperoni",  "Ham", "Mushroom", "Chicken", "Beef",
                    "Salami", "Spinach");
            additionalToppingsListView.setItems(FXCollections.observableList(values));
        }
        else if(text.equals("Hawaiian Pizza")) {
            List<String> values = Arrays.asList("Pepperoni", "Mushroom", "Onion", "Sausage", "Chicken", "Beef",
                    "Salami", "Spinach", "Black Olives", "GreenPepper", "DicedTomatoes");
            additionalToppingsListView.setItems(FXCollections.observableList(values));
        }
        else if(text.equals("Pepperoni Pizza")) {
            List<String> values = Arrays.asList("Pineapple", "Ham", "Mushroom", "Onion", "Sausage", "Chicken", "Beef",
                    "Salami", "Spinach", "Black Olives", "GreenPepper", "DicedTomatoes");
            additionalToppingsListView.setItems(FXCollections.observableList(values));
        }
    }

    @FXML
    void addOrder(ActionEvent event) {

    }

    @FXML
    void removeToppings(ActionEvent event) {

    }

    @FXML
    void addToppings(ActionEvent event) {

    }

}
