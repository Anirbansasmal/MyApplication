package com.example.myapplication.cart;

public class AddCartOrder {
    private String message;

    private String status;

    private String order_id;

    private String product_confirmation;

    public String getProduct_confirmation() {
        return product_confirmation;
    }

    public void setProduct_confirmation(String product_confirmation) {
        this.product_confirmation = product_confirmation;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        order_id = order_id;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", status = "+status+"]";
    }
}
