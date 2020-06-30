package com.example.myapplication.dashbord;

import java.util.List;

public class Unit {
    private String p_qty;

    private String p_unit;

    private String p_name;

    private String __v;

    private String p_details;

    private String _id;

    private String p_price;

    private String p_id;

    private String p_GST;

    public String getP_qty ()
    {
        return p_qty;
    }

    public void setP_qty (String p_qty)
    {
        this.p_qty = p_qty;
    }

    public String getP_unit ()
    {
        return p_unit;
    }

    public void setP_unit (String p_unit)
    {
        this.p_unit = p_unit;
    }

    public String getP_name ()
    {
        return p_name;
    }

    public void setP_name (String p_name)
    {
        this.p_name = p_name;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getP_details ()
    {
        return p_details;
    }

    public void setP_details (String p_details)
    {
        this.p_details = p_details;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getP_price ()
    {
        return p_price;
    }

    public void setP_price (String p_price)
    {
        this.p_price = p_price;
    }

    public String getP_id ()
    {
        return p_id;
    }

    public void setP_id (String p_id)
    {
        this.p_id = p_id;
    }

    public String getP_GST ()
    {
        return p_GST;
    }

    public void setP_GST (String p_GST)
    {
        this.p_GST = p_GST;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [p_qty = "+p_qty+", p_unit = "+p_unit+", p_name = "+p_name+", __v = "+__v+", p_details = "+p_details+", _id = "+_id+", p_price = "+p_price+", p_id = "+p_id+", p_GST = "+p_GST+"]";
    }
}
