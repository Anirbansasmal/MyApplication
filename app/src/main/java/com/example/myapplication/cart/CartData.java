package com.example.myapplication.cart;

import com.example.myapplication.dashbord.Product_arrqty;
import com.example.myapplication.dashbord.Product_qty;

import java.util.List;

public class CartData {
    private List<Cart_item> cart_item;
    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Cart_item> getCart_item ()
    {
        return cart_item;
    }

    public void setCart_item (List<Cart_item> cart_item)
    {
        this.cart_item = cart_item;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cart_item = "+cart_item+"]";
    }
}
