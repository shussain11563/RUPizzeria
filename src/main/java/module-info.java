module com.ru.pizzeria.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens com.ru.pizzeria.rupizzeria to javafx.fxml;
    exports com.ru.pizzeria.rupizzeria;
}