package com.example.myapplication.orderhistory.activeorderfragment;

import java.util.ArrayList;

public class User_Order_detail {
    private ArrayList<String> order_product_active_id;

    public ArrayList<String> getOrder_product_active_id ()
    {
        return order_product_active_id;
    }

    public void setOrder_product_active_id (ArrayList<String> order_product_active_id)
    {
        this.order_product_active_id = order_product_active_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [order_product_active_id = "+order_product_active_id+"]";
    }
}
