package com.example.myapplication.productdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Deltimeslotuser {
    private String message;

    private List<Deltimeslot> deltimeslot;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public List<Deltimeslot> getDeltimeslot ()
    {
        return deltimeslot;
    }

    public void setDeltimeslot (List<Deltimeslot> deltimeslot)
    {
        this.deltimeslot = deltimeslot;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", deltimeslot = "+deltimeslot+"]";
    }
}
