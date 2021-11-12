package com.ru.pizzeria.rupizzeria;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


public class RuPizzaStoreOrderController {
    @FXML
    private Button cancelOrderButton;

    @FXML
    private Button exportStoreOrdersButton;

    @FXML
    private ComboBox<String> customerPhoneNumberComboBox;

    @FXML
    private TextArea orderTotalTextArea;

    @FXML
    private ListView<String> storeOrderListView;

}

