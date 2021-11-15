package com.ru.pizzeria.rupizzeria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeluxeTest {

    @Test
    void price() {
        Deluxe deluxe1 = new Deluxe();
        Deluxe deluxe2 = new Deluxe();
        Deluxe deluxe3 = new Deluxe();
        Deluxe deluxe4 = new Deluxe();
        Deluxe deluxe5 = new Deluxe();

        Topping pineapple = Topping.Pineapple;
        Topping spinach = Topping.Spinach;
        Topping onion = Topping.Onion;
        Topping greenPepper = Topping.GreenPepper;
        Topping blackOlives = Topping.BlackOlives;
        Topping dicedTomatoes = Topping.DicedTomatoes;
        Topping chicken = Topping.Chicken;

        deluxe2.addTopping(pineapple);

        deluxe3.addTopping(pineapple);
        deluxe3.addTopping(spinach);

        deluxe4.removeTopping(onion);
        deluxe4.removeTopping(greenPepper);
        deluxe4.removeTopping(blackOlives);
        deluxe4.removeTopping(dicedTomatoes);

        deluxe5.addTopping(chicken);

        double price1 = deluxe1.price();
        double price2 = deluxe2.price();
        double price3 = deluxe3.price();
        double price4 = deluxe4.price();
        double price5 = deluxe5.price();

        assertEquals(12.99, price1);
        assertEquals(14.48, price2);
        assertEquals(15.97, price3);
        assertEquals(12.99, price4);
        assertEquals(14.48, price5);
    }
}