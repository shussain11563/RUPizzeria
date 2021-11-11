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
import java.text.DecimalFormat;
import java.util.*;

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
    private Order newOrder;

    private String phoneNumber;

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

    public void setMainController(RuPizzeriaController controller)
    {

        mainController = controller;
    }

    @FXML
    void selectPizzaSize(ActionEvent event) {
        Size selectedSize = myComboBox.getSelectionModel().getSelectedItem();
        pizza.setSize(selectedSize);

        setPrice();

    }

    private void setPrice()
    {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        priceTextArea.setText(df.format(pizza.price()));
        priceTextArea.setEditable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        newOrder = new Order(phoneNumber);
        //mainController.printHello();


        //CcomboBox
        //System.out.println(mainController.deluxePizzaButton.getText()); //BUG, ONLY PRINTS DELUXE PIZZA

        //create pizza
        String pizzaFlavor = pizzaButton.getText();
        System.out.println(pizzaFlavor);
        //String pizzaFlavor = ""; //add to order
        Pizza pizza = PizzaMaker.createPizza(pizzaFlavor); //null
        this.pizza = pizza;
        if(this.pizza == null)
        {
            System.out.println("Line 76");
        }

        setPrice();

        //listview
        updateListView();



        //comboBox
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

    public void setPizzaPhoneNumber(String text) {
        phoneNumber = text;
    }

    @FXML
    void addOrder(ActionEvent event) {
        newOrder.addPizza(pizza);
        newOrder.printAllOrders();
        pizza = PizzaMaker.createPizza(pizzaButton.getText());
        setPrice();
        updateListView();

    }

    public void updateListView() {
        selectedToppings = this.pizza.getToppings();
        ArrayList<Topping> allToppings = new ArrayList<Topping>(Arrays.asList(Topping.values()));
        allToppings.removeAll(selectedToppings);
        additionalToppings = allToppings;
        //System.out.println(allToppings); //gives you an arraylist of the nonselected toppings

        ObservableList<Topping> selectedToppingsList = FXCollections.observableArrayList(selectedToppings);
        selectedToppingsListView.setItems(FXCollections.observableList(selectedToppingsList));
        ObservableList<Topping> additionalToppingsList = FXCollections.observableArrayList(additionalToppings);
        additionalToppingsListView.setItems(FXCollections.observableList(additionalToppingsList));
    }
    @FXML
    void removeToppings(ActionEvent event)
    {
        if(selectedToppingsListView.getSelectionModel().getSelectedItem() != null)
        {
            if(selectedToppingsListView.getItems().size() > 0)
            {
                if(checkDeluxeToppings(selectedToppingsListView.getSelectionModel().getSelectedItem())) {
                    showConfirmationRemoveEssentialToppings();
                }else {
                    callRemoveToppings();
                }

            }
            else if(selectedToppingsListView.getItems().size() <= 0) {
               showConfirmationNoToppingsOnPizza();
            }
        }
    }

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

    public void showConfirmationNoToppingsOnPizza() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning with Removing Toppings");
        alert.setHeaderText("Removing Toppings");
        alert.setContentText("No Toppings on Pizza");
        Optional<ButtonType> result = alert.showAndWait();
    }

    public void callRemoveToppings() {
        Topping topping = selectedToppingsListView.getSelectionModel().getSelectedItem();
        additionalToppingsListView.getItems().add(topping);
        selectedToppingsListView.getItems().remove(topping);
        this.pizza.removeTopping(topping);
        setPrice();
    }

    @FXML
    void addToppings(ActionEvent event)
    {
        if(additionalToppingsListView.getSelectionModel().getSelectedItem() != null)
        {
            if(selectedToppingsListView.getItems().size() < Pizza.MAX_TOPPINGS)
            {
                Topping topping = additionalToppingsListView.getSelectionModel().getSelectedItem();
                //System.out.println("Yo" + additionalToppingsListView.getSelectionModel().getSelectedItem());
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
    public boolean checkDeluxeToppings(Topping selectedItem) {
        if(selectedItem.toString().equals("Sausage") || selectedItem.toString().equals("Onion")
                || selectedItem.toString().equals("GreenPepper") || selectedItem.toString().equals("BlackOlives")
                || selectedItem.toString().equals("DicedTomatoes")) {
            return true;
        }
        return false;
    }


}
