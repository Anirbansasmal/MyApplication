package com.example.myapplication.Search;

import com.example.myapplication.dashbord.Product;

public class Product_user_search {
    private Product Product;

    public Product getProduct ()
    {
        return Product;
    }

    public void setProduct (Product Product)
    {
        this.Product = Product;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [product = "+Product+"]";
    }
}
