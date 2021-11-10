package com.ru.pizzeria.rupizzeria;

import java.util.ArrayList;

public abstract class Pizza
{
    static final double SALES_TAX_RATE = 6.625;
    static final int MAX_TOPPINGS = 7;
    static final double SIZE_INCREASE_COST = 2;
    static final double ADDITIONAL_TOPPINGS_COST = 1.49;

    //MAX TOPPINGS

    //static final MAX_TOPPINGS = 7;
    //static final
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;
    public abstract double price();

    public boolean addTopping(Topping topping)
    {
        if(this.toppings.contains(topping))
        {
            return false;
        }

        if(MAX_TOPPINGS == this.toppings.size())
        {
            return false;
        }

        this.toppings.add(topping);
        return true;
    }

    public boolean removeTopping(Topping topping)
    {
        if(this.toppings.contains(topping))
        {
            this.toppings.remove(topping);
            return true;
        }
        return false;
    }

    public double calculateSalesTax()
    {
        return 0;
        //r

    }

    //add Toppings
    //remove toppings

    //check if no repeating tippings
    //max toppings



    //create toString

}