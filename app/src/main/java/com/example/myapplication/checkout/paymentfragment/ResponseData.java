package com.example.myapplication.checkout.paymentfragment;

public class ResponseData {
    private Payment_request payment_request;

    private String success;

    public Payment_request getPayment_request ()
    {
        return payment_request;
    }

    public void setPayment_request (Payment_request payment_request)
    {
        this.payment_request = payment_request;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [payment_request = "+payment_request+", success = "+success+"]";
    }
}
