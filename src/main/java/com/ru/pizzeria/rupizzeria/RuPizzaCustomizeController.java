package com.ru.pizzeria.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
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

    //private Order newOrder;

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

    private Order currentOrder;

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




        //CcomboBox
        //System.out.prinetln(mainController.deluxePizzaButton.getText()); //BUG, ONLY PRINTS DELUXE PIZZA

        /*
       //create pizza
        String pizzaFlavor = pizzaButton.getText();
        Pizza pizza = PizzaMaker.createPizza(pizzaFlavor); //null
        this.pizza = pizza;
        if(this.pizza == null)
        {
            System.out.println("Line 76");
        }

        */



        //comboBox
        ObservableList<Size> options =  FXCollections.observableList(Arrays.asList(Size.values()));
        myComboBox.setItems(options);
        myComboBox.setValue(Size.Small); //default value

        /*

        //listview
        ArrayList<Topping> selectedToppings = this.pizza.getToppings();
        ArrayList<Topping> allToppings = new ArrayList<Topping>(Arrays.asList(Topping.values()));
        allToppings.removeAll(selectedToppings);
        ArrayList<Topping> additionalToppings = allToppings;
        System.out.println(allToppings); //gives you an arraylist of the nonselected toppings
        ObservableList<Topping> selectedToppingsList = FXCollections.observableArrayList(selectedToppings);
        selectedToppingsListView.setItems(FXCollections.observableList(selectedToppingsList));
        ObservableList<Topping> additionalToppingsList = FXCollections.observableArrayList(additionalToppings);
        additionalToppingsListView.setItems(FXCollections.observableList(additionalToppingsList));

        //displays default price
        setPrice();

         */

    }


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

    public void setPizzaPicture(Image image)
    {
        this.pizzaImage.setImage(image);
    }

    public void setPizzaText(String text) {
        pizzaButton.setText(text);
    }

    public void setPizzaPhoneNumber(String text) {
        phoneNumber = text;
    }

    //change this method
    @FXML
    void addOrder(ActionEvent event)throws IOException
    {
        this.currentOrder.addPizza(this.pizza);



        //newOrder.printAllOrders();
        //pizza = PizzaMaker.createPizza(pizzaButton.getText());
        // reset toppings clearView()

        setPrice();
        String pizzaFlavor = pizzaButton.getText();
        Pizza pizza = PizzaMaker.createPizza(pizzaFlavor);
        this.pizza = pizza;

        //updateListView();
    }



    public void updateListView()
    {
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

    //make less redundant
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

    public boolean checkHawaiianToppings(Topping selectedItem) {
        if(selectedItem.toString().equals("Pineapple") || selectedItem.toString().equals("Ham")){
            return true;
        }
        return false;
    }

    public boolean checkPepperoniToppings(Topping selectedItem) {
        if(selectedItem.toString().equals("Pepperoni")) {
            return true;
        }
        return false;
    }


}
