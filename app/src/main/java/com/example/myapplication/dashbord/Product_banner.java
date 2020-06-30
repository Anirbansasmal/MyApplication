package com.example.myapplication.dashbord;

import java.util.List;

public class Product_banner {
//    private List<Product_qty> Product_qty;

    private List<Slider> Slider;

    public List<Slider> getSlider ()
    {
        return Slider;
    }

    public void setSlider (List<Slider> Slider)
    {
        this.Slider = Slider;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Slider = "+Slider+"]";
    }
}
