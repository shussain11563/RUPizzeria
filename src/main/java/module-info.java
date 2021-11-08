module com.ru.pizzeria.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ru.pizzeria.rupizzeria to javafx.fxml;
    exports com.ru.pizzeria.rupizzeria;
}