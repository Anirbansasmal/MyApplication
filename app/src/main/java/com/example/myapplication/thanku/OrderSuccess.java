package com.example.myapplication.thanku;

import com.example.myapplication.checkout.paymentfragment.Payment_request;

public class OrderSuccess {
    private String Order_id;

    private String delivery_date;

    private String address;

    private String _id;

    private String delivery_time;

    private String usr_pay_type;

    public String getOrder_id ()
    {
        return Order_id;
    }

    public void setOrder_id (String Order_id)
    {
        this.Order_id = Order_id;
    }

    public String getDelivery_date ()
    {
        return delivery_date;
    }

    public void setDelivery_date (String delivery_date)
    {
        this.delivery_date = delivery_date;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getDelivery_time ()
    {
        return delivery_time;
    }

    public void setDelivery_time (String delivery_time)
    {
        this.delivery_time = delivery_time;
    }

    public String getUsr_pay_type ()
    {
        return usr_pay_type;
    }

    public void setUsr_pay_type (String usr_pay_type)
    {
        this.usr_pay_type = usr_pay_type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Order_id = "+Order_id+", delivery_date = "+delivery_date+", address = "+address+", _id = "+_id+", delivery_time = "+delivery_time+", usr_pay_type = "+usr_pay_type+"]";
    }
}
