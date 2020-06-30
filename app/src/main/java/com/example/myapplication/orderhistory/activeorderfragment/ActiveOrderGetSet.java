package com.example.myapplication.orderhistory.activeorderfragment;

public class ActiveOrderGetSet {
    String orderID;
    String orderProduct;
    String orderProductDesc;
    String orderStatus;

    public String getOrderID() {
        return this.orderID;
    }

    public void setOrderID(String orderID2) {
        this.orderID = orderID2;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus2) {
        this.orderStatus = orderStatus2;
    }

    public String getOrderProduct() {
        return this.orderProduct;
    }

    public void setOrderProduct(String orderProduct2) {
        this.orderProduct = orderProduct2;
    }

    public String getOrderProductDesc() {
        return this.orderProductDesc;
    }

    public void setOrderProductDesc(String orderProductDesc2) {
        this.orderProductDesc = orderProductDesc2;
    }

    public ActiveOrderGetSet(String orderID2, String orderStatus2, String orderProduct2, String orderProductDesc2) {
        this.orderID = orderID2;
        this.orderStatus = orderStatus2;
        this.orderProduct = orderProduct2;
        this.orderProductDesc = orderProductDesc2;
    }
}
