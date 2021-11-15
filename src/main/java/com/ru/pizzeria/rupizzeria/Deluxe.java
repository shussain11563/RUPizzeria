package com.ru.pizzeria.rupizzeria;

import java.util.Arrays;

public class Deluxe extends Pizza {
    private static final double MIN_COST = 12.99;
    private static final int MIN_TOPPING = 5;

    public Deluxe() {

        //this.toppings = Arrays.asList(Topping.Sausage,Topping.Onion,)
        //use collection
        //make it a one liner
        this.toppings.add(Topping.Sausage);
        this.toppings.add(Topping.Onion);
        this.toppings.add(Topping.GreenPepper);
        this.toppings.add(Topping.BlackOlives);
        this.toppings.add(Topping.DicedTomatoes);
        this.size = Size.Small;

        //List<String> places = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
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
        this.price = runningCost;
        return runningCost;
    }



}
