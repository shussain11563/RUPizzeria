package com.ru.pizzeria.rupizzeria;

public class Pepperoni extends Pizza
{
    //make private
    private static final double MIN_COST = 8.99;
    private static final int MIN_TOPPING = 1;


    public Pepperoni()
    {
        toppings.add(Topping.Pepperoni);
        //this.size =

    }
    @Override
    public double price()
    {
        //min cost
        double runningCost = 0;
        runningCost += MIN_COST;

        double sizeCost = 0;

        switch (this.size)
        {
            case small:
                sizeCost = 0;
            case medium:
                sizeCost += SIZE_INCREASE_COST;
            case large:
                sizeCost += (SIZE_INCREASE_COST + SIZE_INCREASE_COST);
        }

        runningCost += sizeCost;

        for(int i = this.MIN_TOPPING; i < toppings.size(); i++)
        {
            runningCost += Pizza.ADDITIONAL_TOPPINGS_COST;
        }

        return runningCost;


        // add sales tax
    }
}
