package com.ru.pizzeria.rupizzeria;

public class Hawaiian extends Pizza
{
    static final double MIN_PRICE = 10.99;
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
