package com.ru.pizzeria.rupizzeria;

import java.util.ArrayList;

public class Order
{
    private String phoneNumber;
    private ArrayList<Pizza> pizzas;

    public Order(String phoneNumber)
    {
        //contains an order number
        this.phoneNumber = phoneNumber;
        pizzas = new ArrayList<Pizza>();

    }

    public void addPizza(Pizza pizza)
    {
        this.pizzas.add(pizza);
    }

    public void removePizza(Pizza pizza)
    {
        this.pizzas.remove(pizza);
    }

    public void printAllOrders() {
        for(int i = 0; i < pizzas.size(); i++) {
            System.out.println(pizzas.get(i));
        }
    }

}
