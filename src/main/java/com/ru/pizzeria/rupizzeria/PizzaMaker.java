package com.ru.pizzeria.rupizzeria;


public class PizzaMaker
{
    public static Pizza createPizza(String flavor)
    {
        Pizza pizza = null;
        //use switch cases

        if(flavor.toLowerCase().equals("pepperoni pizza"))
        {
            pizza = new Pepperoni();
        }
        else if(flavor.toLowerCase().equals("deluxe pizza"))
        {
            pizza = new Deluxe();
        }
        else if(flavor.toLowerCase().equals("hawaiian pizza"))
        {
            pizza = new Hawaiian();
        }

        return pizza;
//write the code for creating different instances of subtypes of pizza
    }


}