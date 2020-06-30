package com.example.myapplication.checkout.deliverfragment;

public class Order_product {
    private String Order_id;

    private String product_amt_gst;

    private String pincode;

    private String product_name;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    private String address;

    private String p_img;

    private String p_unit;

    private String payment_details;

    private String payment_amount;

    private String product_amt_total;

    private String delivery_time;

    private String p_price;

    private String product_confirmation;

    private String delivery_date;

    private String Username;

    private String user_id;

    private String product_id;

    private String __v;

    private String product_qty;

    private String _id;

    private String[] order_frequency;

    public String getOrder_id ()
    {
        return Order_id;
    }

    public void setOrder_id (String Order_id)
    {
        this.Order_id = Order_id;
    }

    public String getProduct_amt_gst ()
    {
        return product_amt_gst;
    }

    public void setProduct_amt_gst (String product_amt_gst)
    {
        this.product_amt_gst = product_amt_gst;
    }

    public String getPincode ()
    {
        return pincode;
    }

    public void setPincode (String pincode)
    {
        this.pincode = pincode;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getP_img ()
    {
        return p_img;
    }

    public void setP_img (String p_img)
    {
        this.p_img = p_img;
    }

    public String getP_unit ()
    {
        return p_unit;
    }

    public void setP_unit (String p_unit)
    {
        this.p_unit = p_unit;
    }

    public String getPayment_details ()
    {
        return payment_details;
    }

    public void setPayment_details (String payment_details)
    {
        this.payment_details = payment_details;
    }

    public String getPayment_amount ()
    {
        return payment_amount;
    }

    public void setPayment_amount (String payment_amount)
    {
        this.payment_amount = payment_amount;
    }

    public String getProduct_amt_total ()
    {
        return product_amt_total;
    }

    public void setProduct_amt_total (String product_amt_total)
    {
        this.product_amt_total = product_amt_total;
    }

    public String getDelivery_time ()
    {
        return delivery_time;
    }

    public void setDelivery_time (String delivery_time)
    {
        this.delivery_time = delivery_time;
    }

    public String getP_price ()
    {
        return p_price;
    }

    public void setP_price (String p_price)
    {
        this.p_price = p_price;
    }

    public String getProduct_confirmation ()
    {
        return product_confirmation;
    }

    public void setProduct_confirmation (String product_confirmation)
    {
        this.product_confirmation = product_confirmation;
    }

    public String getDelivery_date ()
    {
        return delivery_date;
    }

    public void setDelivery_date (String delivery_date)
    {
        this.delivery_date = delivery_date;
    }

    public String getUsername ()
    {
        return Username;
    }

    public void setUsername (String Username)
    {
        this.Username = Username;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getProduct_id ()
    {
        return product_id;
    }

    public void setProduct_id (String product_id)
    {
        this.product_id = product_id;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
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

    public String[] getOrder_frequency ()
    {
        return order_frequency;
    }

    public void setOrder_frequency (String[] order_frequency)
    {
        this.order_frequency = order_frequency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Order_id = "+Order_id+", product_amt_gst = "+product_amt_gst+", pincode = "+pincode+", address = "+address+", p_img = "+p_img+", p_unit = "+p_unit+", payment_details = "+payment_details+", payment_amount = "+payment_amount+", product_amt_total = "+product_amt_total+", delivery_time = "+delivery_time+", p_price = "+p_price+", product_confirmation = "+product_confirmation+", delivery_date = "+delivery_date+", Username = "+Username+", user_id = "+user_id+", product_id = "+product_id+", __v = "+__v+", product_qty = "+product_qty+", _id = "+_id+", order_frequency = "+order_frequency+"]";
    }
}
