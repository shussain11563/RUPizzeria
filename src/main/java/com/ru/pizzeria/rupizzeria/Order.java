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

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    //use phonenumber
    public void addPizza(Pizza pizza)
    {
        this.pizzas.add(pizza);
    }

    public void removePizza(Pizza pizza)
    {
        this.pizzas.remove(pizza);
    }


    /*
    public void printAllOrders() {
        for(int i = 0; i < pizzas.size(); i++) {
            System.out.print(i + ": ");
            System.out.print(pizzas.get(i).price + " ");
        }
    }

    @Override
    public String toString()
    {
        String pizzas = "";
        for(int i = 0; i < this.pizzas.size(); i++)
        {
            pizzas += this.pizzas.get(i).toString() + "\n";

        }

        return pizzas;
    }

     */
}
