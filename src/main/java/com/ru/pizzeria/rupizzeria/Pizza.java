package com.ru.pizzeria.rupizzeria;

import java.util.ArrayList;

public abstract class Pizza
{
    //static final SALES_TAX_RATE = 6.625; (IN PERCENTAGE)
    //MAX TOPPINGS

    //static final MAX_TOPPINGS = 7;
    //static final
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;
    public abstract double price();
}