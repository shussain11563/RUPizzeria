package com.ru.pizzeria.rupizzeria;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StoreOrders
{
    private ArrayList<Order> orders;

    public StoreOrders()
    {
        this.orders = new ArrayList<Order>();
    }

    public void addOrder(Order order)
    {
        this.orders.add(order);
    }

    public void removeOrder(Order order)
    {
        this.orders.remove(order);
    }



    /*
    @Override
    public String toString()
    {
        String  = "";
        for(int i = 0; i < this.pizzas.size(); i++)
        {
            pizzas += this.pizzas.get(i).toString() + "\n";

        }

        return pizzas;
    }

     */

    //not sure about signature
    public void export(File file) throws FileNotFoundException //throws FileNotFoundException
    {
        //add try catch for for closing and file not found but idk because printwriter creates a new file, so may not be neccessary?s
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println("----------- STORE ORDERS -----------\n");

        for(int i = 0; i < orders.size(); i++)
        {
            Order order = orders.get(i);
            printWriter.println(String.format("ORDER NUMBER: %s", order.getPhoneNumber()));
            //might just create a toString in order
            ArrayList<Pizza> pizzasInOrder = order.getPizzas();
            for(int j = 0; j < pizzasInOrder.size(); j++)
            {
                Pizza pizza = pizzasInOrder.get(j);
                printWriter.println(String.format("- %s", pizza.toString()));
            }
            printWriter.print("\n");

        }

        printWriter.close();

    }


    public Order find(String phoneNumber)
    {
        for(int i = 0; i < orders.size(); i++)
        {
            if(orders.get(i).getPhoneNumber().equals(phoneNumber))
            {
                return orders.get(i);
            }
        }

        return null;
    }

    //create toString for each

}
