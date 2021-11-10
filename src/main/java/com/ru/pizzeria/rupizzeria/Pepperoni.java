package com.ru.pizzeria.rupizzeria;

public class Pepperoni extends Pizza
{
    //make private
    static final double MIN_COST = 8.99;
    static final int MIN_TOPPING = 1;


    public Pepperoni()
    {
        toppings.add(Topping.Pepperoni);
        //this.size =

    }
    @Override
    public double price()
    {
        double runningCost = 0;
        runningCost += MIN_COST;

        for(int i = this.MIN_TOPPING; i < toppings.size(); i++)
        {
            runningCost += Pizza.ADDITIONAL_TOPPINGS_COST;
        }

        return runningCost;
    }
}
