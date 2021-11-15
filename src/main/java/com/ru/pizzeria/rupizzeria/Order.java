package com.ru.pizzeria.rupizzeria;

import java.util.ArrayList;

public class Order
{
    private String phoneNumber;
    private ArrayList<Pizza> pizzas;
    private double totalPrice;

    public Order(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        pizzas = new ArrayList<Pizza>();
        totalPrice = 0;

    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice()
    {
        return this.totalPrice;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
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


}
