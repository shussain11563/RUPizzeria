package com.ru.pizzeria.rupizzeria;

public class Hawaiian extends Pizza
{
    private static final double MIN_COST = 10.99;
    private static final int MIN_TOPPING = 2;

    public Hawaiian()
    {
        this.toppings.add(Topping.Pineapple);
        this.toppings.add(Topping.Ham);
    }
    @Override
    public double price()
    {


        return 0;
    }
}
