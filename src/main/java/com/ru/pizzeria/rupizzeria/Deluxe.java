package com.ru.pizzeria.rupizzeria;

public class Deluxe extends Pizza
{
    private static final double MIN_COST = 12.99;
    private static final int MIN_TOPPING = 5;

    public Deluxe()
    {
        //use collection
        this.toppings.add(Topping.Sausage);
        this.toppings.add(Topping.Onion);
        this.toppings.add(Topping.GreenPepper);
        this.toppings.add(Topping.BlackOlives);
        this.toppings.add(Topping.DicedTomatoes);
    }

    @Override
    public double price()
    {
        return 0;
    }
}
