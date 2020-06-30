package com.example.myapplication.orderhistory.oldorderfragment;

public class OldOrderGetSet {
    String oldOrderID;
    String oldOrderProductDesc;
    String oldOrderProductName;
    String oldOrderStatus;
    String oldOrderplacedDate;

    public String getOldOrderID() {
        return this.oldOrderID;
    }

    public String getOldOrderProductName() {
        return this.oldOrderProductName;
    }

    public String getOldOrderStatus() {
        return this.oldOrderStatus;
    }

    public String getOldOrderProductDesc() {
        return this.oldOrderProductDesc;
    }

    public String getOldOrderplacedDate() {
        return this.oldOrderplacedDate;
    }

    public OldOrderGetSet(String oldOrderID2, String oldOrderProductName2, String oldOrderStatus2, String oldOrderProductDesc2, String oldOrderplacedDate2) {
        this.oldOrderID = oldOrderID2;
        this.oldOrderProductName = oldOrderProductName2;
        this.oldOrderStatus = oldOrderStatus2;
        this.oldOrderProductDesc = oldOrderProductDesc2;
        this.oldOrderplacedDate = oldOrderplacedDate2;
    }
}
