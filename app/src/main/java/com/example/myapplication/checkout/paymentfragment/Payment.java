package com.example.myapplication.checkout.paymentfragment;

public class Payment {
    private ResponseData responseData;

    public ResponseData getResponseData ()
    {
        return responseData;
    }

    public void setResponseData (ResponseData responseData)
    {
        this.responseData = responseData;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responseData = "+responseData+"]";
    }
}
