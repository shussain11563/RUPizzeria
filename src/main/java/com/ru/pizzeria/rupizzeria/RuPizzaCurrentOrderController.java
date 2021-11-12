package com.ru.pizzeria.rupizzeria;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class RuPizzaCurrentOrderController {

    @FXML
    private TextArea customerPhoneNumber;

    @FXML
    private TextArea orderTotalTextArea;

    @FXML
    private TextArea salesTaxTextArea;

    @FXML
    private TextArea subtotalTextArea;

    @FXML
    private ListView<String> orderListView;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Button removeSelectedPizzaButton;

}
