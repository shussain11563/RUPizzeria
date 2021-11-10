package com.ru.pizzeria.rupizzeria;

import java.util.ArrayList;

public class Order
{
    //add linked lis
    //
    //make it protected
    private String phoneNumber;
    private ArrayList<Pizza> pizzas;


    public Order(String phoneNumber)
    {
        //contains an order number
        this.phoneNumber = phoneNumber;
        pizzas = new ArrayList<Pizza>();

        //
        //String phoneNumbe

    }

    public void addPizza(Pizza pizza)
    {
        //this.pizzas.add(pizza);
    }

    public void removePizza(Pizza pizza)
    {
        //this.pizzas.remove(pizza);
    }


}
