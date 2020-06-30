package com.example.myapplication.orderhistory.oldorderfragment;

public class Delivered {
    private String Order_id;

    private String delivery_date;

    private String product_qty;

    private String _id;

    private String delivery_time;

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

    public String getProduct_qty ()
    {
        return product_qty;
    }

    public void setProduct_qty (String product_qty)
    {
        this.product_qty = product_qty;
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

    @Override
    public String toString()
    {
        return "ClassPojo [Order_id = "+Order_id+", delivery_date = "+delivery_date+", product_qty = "+product_qty+", _id = "+_id+", delivery_time = "+delivery_time+"]";
    }
}
