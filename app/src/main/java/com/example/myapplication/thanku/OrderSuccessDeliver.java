package com.example.myapplication.thanku;

import java.util.ArrayList;

public class OrderSuccessDeliver {
    private ArrayList<OrderSuccess> OrderSuccess;

    public ArrayList<OrderSuccess> getOrderSuccess ()
    {
        return OrderSuccess;
    }

    public void setOrderSuccess (ArrayList<OrderSuccess> OrderSuccess)
    {
        this.OrderSuccess = OrderSuccess;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [OrderSuccess = "+OrderSuccess+"]";
    }
}
