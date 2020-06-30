package com.example.myapplication.cart;

import com.example.myapplication.dashbord.Product_arrqty;
import com.example.myapplication.dashbord.Product_qty;

import java.util.List;

public class Cart_item {
    private String product_amt_gst;

    private String pincode;

    private String p_img;

    private String p_discount_total;

    private String order_frequency_count;

    public String getOrder_frequency_count() {
        return order_frequency_count;
    }

    public void setOrder_frequency_count(String order_frequency_count) {
        this.order_frequency_count = order_frequency_count;
    }

    public String getP_discount_total() {
        return p_discount_total;
    }

    public void setP_discount_total(String p_discount_total) {
        this.p_discount_total = p_discount_total;
    }

    private String p_unit;

    private String ProductName;

    private String user_address;

    private String payment_amount;

    private String p_details;

    private String p_price;

    private String product_qty_total;

    private String p_Gst;

    private String payment_type;

    private String Username;

    private String user_id;

    private String product_id;

    private String __v;

    private String product_qty;

    private String _id;

    private String[] order_frequency;

    private String time_slot;

    private String p_discount;

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

    public String getProductName ()
    {
        return ProductName;
    }

    public void setProductName (String ProductName)
    {
        this.ProductName = ProductName;
    }

    public String getUser_address ()
    {
        return user_address;
    }

    public void setUser_address (String user_address)
    {
        this.user_address = user_address;
    }

    public String getPayment_amount ()
    {
        return payment_amount;
    }

    public void setPayment_amount (String payment_amount)
    {
        this.payment_amount = payment_amount;
    }

    public String getP_details ()
    {
        return p_details;
    }

    public void setP_details (String p_details)
    {
        this.p_details = p_details;
    }

    public String getP_price ()
    {
        return p_price;
    }

    public void setP_price (String p_price)
    {
        this.p_price = p_price;
    }

    public String getProduct_qty_total ()
    {
        return product_qty_total;
    }

    public void setProduct_qty_total (String product_qty_total)
    {
        this.product_qty_total = product_qty_total;
    }

    public String getP_Gst ()
    {
        return p_Gst;
    }

    public void setP_Gst (String p_Gst)
    {
        this.p_Gst = p_Gst;
    }

    public String getPayment_type ()
    {
        return payment_type;
    }

    public void setPayment_type (String payment_type)
    {
        this.payment_type = payment_type;
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

    public String getTime_slot ()
    {
        return time_slot;
    }

    public void setTime_slot (String time_slot)
    {
        this.time_slot = time_slot;
    }

    public String getP_discount ()
    {
        return p_discount;
    }

    public void setP_discount (String p_discount)
    {
        this.p_discount = p_discount;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [product_amt_gst = "+product_amt_gst+", pincode = "+pincode+", p_img = "+p_img+", p_unit = "+p_unit+", ProductName = "+ProductName+", user_address = "+user_address+", payment_amount = "+payment_amount+", p_details = "+p_details+", p_price = "+p_price+", product_qty_total = "+product_qty_total+", p_Gst = "+p_Gst+", payment_type = "+payment_type+", Username = "+Username+", user_id = "+user_id+", product_id = "+product_id+", __v = "+__v+", product_qty = "+product_qty+", _id = "+_id+", order_frequency = "+order_frequency+", time_slot = "+time_slot+", p_discount = "+p_discount+"]";
    }
}
