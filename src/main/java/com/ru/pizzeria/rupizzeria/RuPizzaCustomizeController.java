package com.ru.pizzeria.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class RuPizzaCustomizeController implements Initializable {

    @FXML
    private ComboBox<String> myComboBox;

    @FXML
    private ImageView pizzaImage;

    @FXML
    private Button pizzaButton;

    @FXML
    void selectPizzaSize(ActionEvent event) {
        String temp = myComboBox.getSelectionModel().getSelectedItem().toString();
        System.out.println(temp);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = FXCollections.observableArrayList("Small", "Medium", "Large");
        myComboBox.setItems(options);
    }

    public void setPizzaPicture(String text) {
        System.out.print(text);
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

}
