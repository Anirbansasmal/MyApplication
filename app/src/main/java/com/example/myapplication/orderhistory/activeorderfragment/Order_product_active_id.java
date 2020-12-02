package com.example.myapplication.orderhistory.activeorderfragment;

public class Order_product_active_id {
    private String _id;

    private String Order_id;
    public String get_id ()
    {
        return _id;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(String order_id) {
        Order_id = order_id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [_id = "+_id+"]";
    }
}
