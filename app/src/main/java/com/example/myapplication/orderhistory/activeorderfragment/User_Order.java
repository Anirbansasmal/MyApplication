package com.example.myapplication.orderhistory.activeorderfragment;

import java.util.ArrayList;

public class User_Order {
    private ArrayList<Order_product_active> order_product_active;

    public ArrayList<Order_product_active> getOrder_product_active() {
        return order_product_active;
    }

    public void setOrder_product_active(ArrayList<Order_product_active> order_product_active) {
        this.order_product_active = order_product_active;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [order_product = "+order_product_active+"]";
    }
}
