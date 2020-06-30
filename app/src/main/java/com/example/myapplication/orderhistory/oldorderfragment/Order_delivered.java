package com.example.myapplication.orderhistory.oldorderfragment;

import java.util.ArrayList;

public class Order_delivered {
    private ArrayList<Delivered> delivered;

    public ArrayList<Delivered> getDelivered ()
    {
        return delivered;
    }

    public void setDelivered (ArrayList<Delivered> delivered)
    {
        this.delivered = delivered;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [delivered = "+delivered+"]";
    }
}
