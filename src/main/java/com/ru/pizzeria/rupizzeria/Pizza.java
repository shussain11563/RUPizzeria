package com.ru.pizzeria.rupizzeria;

import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Pizza
{
    static final double SALES_TAX_RATE = 6.625;
    static final int MAX_TOPPINGS = 7;
    static final double SIZE_INCREASE_COST = 2;
    static final double ADDITIONAL_TOPPINGS_COST = 1.49;
    double price;

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

    public void setSize(Size size)
    {
        this.size = size;
    }

    public double getPrice(){
        return this.price;
    }
    public double calculateSalesTax()
    {
        return 0;
        //r

    }



    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public double calculateSizeOfPizza()
    {
        double sizeCost = 0;


        //make this a whole method??
        switch (this.size)
        {
            case Small:
                sizeCost = 0;
                break;
            case Medium:
                sizeCost += SIZE_INCREASE_COST;
                break;
            case Large:
                sizeCost += (SIZE_INCREASE_COST + SIZE_INCREASE_COST);
                break;
        }
        return sizeCost;
    }

    @Override
    public String toString()
    {
        String pizzaType = this.getClass().getSimpleName() + " Pizza";
        DecimalFormat df = new DecimalFormat("#,##0.00");

        String toppings = "";

        for(int i = 0; i < this.toppings.size(); i++)
        {
            toppings += this.toppings.get(i) + ", ";
        }
        return String.format("%s, %s$%s", pizzaType, toppings, df.format(this.price()));

    }
    //add Toppings
    //remove toppings

    //check if no repeating tippings
    //max toppings



    //create toString

}