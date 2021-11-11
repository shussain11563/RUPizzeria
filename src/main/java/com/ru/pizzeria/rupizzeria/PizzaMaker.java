package com.ru.pizzeria.rupizzeria;


public class PizzaMaker
{
    public static Pizza createPizza(String flavor)
    {
        Pizza pizza = null;

        if(flavor.toLowerCase().equals("pepperoni"))
        {
            pizza = new Pepperoni();
        }
        else if(flavor.toLowerCase().equals("deluxe"))
        {
            pizza = new Deluxe();
        }
        else if(flavor.toLowerCase().equals("hawaiian"))
        {
            pizza = new Hawaiian();
        }

        return pizza;
//write the code for creating different instances of subtypes of pizza
    }


}