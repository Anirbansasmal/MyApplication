package com.example.myapplication.profile;

public class AddressGetSet {
    String address;
    String addresscount;

    public String getAddresscount() {
        return this.addresscount;
    }

    public void setAddresscount(String addresscount2) {
        this.addresscount = addresscount2;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address2) {
        this.address = address2;
    }

    public AddressGetSet(String addresscount2, String address2) {
        this.addresscount = addresscount2;
        this.address = address2;
    }
}
