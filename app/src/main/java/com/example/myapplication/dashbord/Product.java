package com.example.myapplication.dashbord;

import java.util.List;

public class Product {
    private List<Product_qty> Product_qty;

    private List<Product_arrqty> Product_arrqty;

    public List<Product_qty> getProduct_qty ()
    {
        return Product_qty;
    }

    public void setProduct_qty (List<Product_qty> Product_qty)
    {
        this.Product_qty = Product_qty;
    }

    public List<Product_arrqty> getProduct_arrqty ()
    {
        return Product_arrqty;
    }

    public void setProduct_arrqty (List<Product_arrqty> product_arrqty)
    {
        this.Product_arrqty = product_arrqty;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Product_qty = "+Product_qty+", product_arrqty = "+Product_arrqty+"]";
    }
}
