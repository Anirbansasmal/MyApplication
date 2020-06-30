package com.example.myapplication.Discount;

import com.example.myapplication.dashbord.Product_arrqty;
import com.example.myapplication.dashbord.Product_qty;

import java.util.List;

public class product_discount {
    private String u_id;

    private String discount_val;

    private String offer_exp;

    private String d_id;

    private String offer_start;

    private String _id;

    private String offer_apply;

    public String getU_id ()
    {
        return u_id;
    }

    public void setU_id (String u_id)
    {
        this.u_id = u_id;
    }

    public String getDiscount_val ()
    {
        return discount_val;
    }

    public void setDiscount_val (String discount_val)
    {
        this.discount_val = discount_val;
    }

    public String getOffer_exp ()
    {
        return offer_exp;
    }

    public void setOffer_exp (String offer_exp)
    {
        this.offer_exp = offer_exp;
    }

    public String getD_id ()
    {
        return d_id;
    }

    public void setD_id (String d_id)
    {
        this.d_id = d_id;
    }

    public String getOffer_start ()
    {
        return offer_start;
    }

    public void setOffer_start (String offer_start)
    {
        this.offer_start = offer_start;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getOffer_apply ()
    {
        return offer_apply;
    }

    public void setOffer_apply (String offer_apply)
    {
        this.offer_apply = offer_apply;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [u_id = "+u_id+", discount_val = "+discount_val+", offer_exp = "+offer_exp+", d_id = "+d_id+", offer_start = "+offer_start+", _id = "+_id+", offer_apply = "+offer_apply+"]";
    }
}
