package com.example.myapplication.checkout.deliverfragment;

import java.util.List;

public class OrderConfirm {
    private List<Order_product> order_product;

    public List<Order_product> getOrder_product ()
    {
        return order_product;
    }

    public void setOrder_product (List<Order_product> order_product)
    {
        this.order_product = order_product;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [order_product = "+order_product+"]";
    }
}
