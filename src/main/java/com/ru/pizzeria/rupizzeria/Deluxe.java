package com.ru.pizzeria.rupizzeria;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Deluxe extends Pizza {
    private static final double MIN_COST = 12.99;
    private static final int MIN_TOPPING = 5;

    public Deluxe() {
        //use collection
        this.toppings.add(Topping.Sausage);
        this.toppings.add(Topping.Onion);
        this.toppings.add(Topping.GreenPepper);
        this.toppings.add(Topping.BlackOlives);
        this.toppings.add(Topping.DicedTomatoes);
        this.size = Size.Small;
    }

    public void test()
    {
        ArrayList<Topping> toppingsSelected = this.toppings;
        ArrayList<Topping> allToppings = new ArrayList<Topping>(Arrays.asList(Topping.values()));

        allToppings.removeAll(toppingsSelected);
        System.out.println(allToppings); //gives you an arraylist of the nonselected toppings

        //we get an arraylist

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
        price = runningCost;
        return runningCost;
    }

    public static void main(String[] arg)
    {
        Deluxe pizza = new Deluxe();
        System.out.println(pizza.price());
    }
}
