package com.example.myapplication.dashbord;

import java.util.ArrayList;
import java.util.List;

public class Product_arrqty {
    private List<String> pincode;

    private String p_img;

    private String p_name;

    private String p_mfg;

    private String p_exp;

    private String p_type;

    private String ProductQty;

    private String p_details;

    private String _id;

    private String p_popularity;

    private String p_price;

    public List getPincode ()
    {
        return pincode;
    }

    public void setPincode (List<String> pincode)
    {
        this.pincode = pincode;
    }

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

    public String getP_mfg ()
    {
        return p_mfg;
    }

    public void setP_mfg (String p_mfg)
    {
        this.p_mfg = p_mfg;
    }

    public String getP_exp ()
    {
        return p_exp;
    }

    public void setP_exp (String p_exp)
    {
        this.p_exp = p_exp;
    }

    public String getP_type ()
    {
        return p_type;
    }

    public void setP_type (String p_type)
    {
        this.p_type = p_type;
    }

    public String getProductQty ()
    {
        return ProductQty;
    }

    public void setProductQty (String ProductQty)
    {
        this.ProductQty = ProductQty;
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

    public String getP_popularity ()
    {
        return p_popularity;
    }

    public void setP_popularity (String p_popularity)
    {
        this.p_popularity = p_popularity;
    }

    public String getP_price ()
    {
        return p_price;
    }

    public void setP_price (String p_price)
    {
        this.p_price = p_price;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pincode = "+pincode+", p_img = "+p_img+", p_name = "+p_name+", p_mfg = "+p_mfg+", p_exp = "+p_exp+", p_type = "+p_type+", ProductQty = "+ProductQty+", p_details = "+p_details+", _id = "+_id+", p_popularity = "+p_popularity+", p_price = "+p_price+"]";
    }
}
