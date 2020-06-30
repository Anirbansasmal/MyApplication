package com.example.myapplication.dashbord;

import java.util.ArrayList;
import java.util.List;

public class Product_unit {
    private List<Unit> Unit;

    public List<Unit> getUnit ()
    {
        return Unit;
    }

    public void setUnit (List<Unit> unit)
    {
        this.Unit = unit;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [unit = "+Unit+"]";
    }
}
