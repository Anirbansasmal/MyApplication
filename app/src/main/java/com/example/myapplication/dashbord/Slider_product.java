package com.example.myapplication.dashbord;

import java.util.ArrayList;
import java.util.List;

public class Slider_product {
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
