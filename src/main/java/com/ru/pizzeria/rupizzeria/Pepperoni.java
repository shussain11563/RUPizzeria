package com.ru.pizzeria.rupizzeria;

public class Pepperoni extends Pizza
{
    public Pepperoni()
    {
        toppings.add(Topping.Pineapple);
        //add 1 to counter

    }
    @Override
    public double price() {
        return 0;
    }
}
