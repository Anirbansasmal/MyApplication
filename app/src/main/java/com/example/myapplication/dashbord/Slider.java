package com.example.myapplication.dashbord;

public class Slider {
    private String p_img;

    private String p_name;

    private String __v;

    private String p_details;

    private String _id;

    public String getP_img ()
    {
        return p_img;
    }

    public void setP_img (String p_img)
    {
        this.p_img = p_img;
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

    @Override
    public String toString()
    {
        return "ClassPojo [p_img = "+p_img+", p_name = "+p_name+", __v = "+__v+", p_details = "+p_details+", _id = "+_id+"]";
    }
}
