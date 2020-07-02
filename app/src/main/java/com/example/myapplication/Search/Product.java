package com.example.myapplication.Search;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private ArrayList<Product_qty> Product_qty;

    private ArrayList<Product_arrqty> Product_arrqty;

    public ArrayList<Product_qty> getProduct_qty ()
    {
        return Product_qty;
    }

    public void setProduct_qty (ArrayList<Product_qty> Product_qty)
    {
        this.Product_qty = Product_qty;
    }

    public ArrayList<Product_arrqty> getProduct_arrqty ()
    {
        return Product_arrqty;
    }

    public void setProduct_arrqty (ArrayList<Product_arrqty> product_arrqty)
    {
        this.Product_arrqty = product_arrqty;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Product_qty = "+Product_qty+", product_arrqty = "+Product_arrqty+"]";
    }
}
