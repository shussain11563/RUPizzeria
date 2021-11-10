package com.ru.pizzeria.rupizzeria;

public class PizzaMaker
{
    public static Pizza createPizza(String flavor)
    {
        Pizza pizza;


        if(flavor.equals(""))
        {

        }
        pizza = new Pepperoni();


        pizza = new Deluxe();


        pizza = new Hawaiian();



        return pizza;
//write the code for creating different instances of subtypes of pizza
    }


}