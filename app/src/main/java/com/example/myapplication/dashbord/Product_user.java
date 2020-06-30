package com.example.myapplication.dashbord;

public class Product_user {
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
