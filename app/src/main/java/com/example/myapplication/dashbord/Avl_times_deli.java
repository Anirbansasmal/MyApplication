package com.example.myapplication.dashbord;

import java.util.ArrayList;

public class Avl_times_deli {
    private ArrayList<Avl_times> avl_times;

    public ArrayList<Avl_times> getAvl_times ()
    {
        return avl_times;
    }

    public void setAvl_times (ArrayList<Avl_times> avl_times)
    {
        this.avl_times = avl_times;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [avl_times = "+avl_times+"]";
    }
}
