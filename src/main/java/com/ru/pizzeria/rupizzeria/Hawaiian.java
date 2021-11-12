package com.ru.pizzeria.rupizzeria;

public class Hawaiian extends Pizza
{
    private static final double MIN_COST = 10.99;
    private static final int MIN_TOPPING = 2;

    public Hawaiian()
    {
        this.toppings.add(Topping.Pineapple);
        this.toppings.add(Topping.Ham);
        this.size = Size.Small;
    }
    @Override
    public double price()
    {
        double runningCost = 0;
        runningCost += MIN_COST;

        double sizeCost = calculateSizeOfPizza();

        runningCost += sizeCost;

        for(int i = this.MIN_TOPPING; i < toppings.size(); i++)
        {
            runningCost += Pizza.ADDITIONAL_TOPPINGS_COST;
        }

        return runningCost;
    }
}
