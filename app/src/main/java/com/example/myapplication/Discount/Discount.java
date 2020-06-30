package com.example.myapplication.Discount;

import com.example.myapplication.dashbord.Product_arrqty;
import com.example.myapplication.dashbord.Product_qty;

import java.util.ArrayList;
import java.util.List;

public class Discount {
    private ArrayList<product_discount> product_discount;

    public ArrayList<product_discount> getproduct_discount ()
    {
        return product_discount;
    }

    public void setproduct_discount (ArrayList<product_discount> product_discount)
    {
        this.product_discount = product_discount;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Product_arrqty = "+product_discount+"]";
    }
}
