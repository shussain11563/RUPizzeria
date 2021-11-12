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
    public void export(File file) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);

        for(int i = 0; i < orders.size(); i++)
        {
            //printWriter.println(orders.get(i).toString());
            //printWriter.println(orders.get());
        }
        printWriter.println();

        printWriter.close();

    }

    //create toString for each

}
