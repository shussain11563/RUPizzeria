package com.ru.pizzeria.rupizzeria;

public class Pepperoni extends Pizza
{
    //make private
    private static final double MIN_COST = 8.99;
    private static final int MIN_TOPPING = 1;


    public Pepperoni()
    {
        toppings.add(Topping.Pepperoni);
        this.size = Size.Small;

    }
    @Override
    public double price()
    {
        //min cost
        double runningCost = 0;
        runningCost += MIN_COST;

        double sizeCost = calculateSizeOfPizza();

        runningCost += sizeCost;

        for(int i = this.MIN_TOPPING; i < toppings.size(); i++)
        {
            runningCost += Pizza.ADDITIONAL_TOPPINGS_COST;
        }

        return runningCost;


        // add sales tax
    }

}
