package com.example.myapplication.checkout.deliverfragment;

class CheckoutOrderGetSet {
    String productName;
    String productPrice;
    String productQty;

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName2) {
        this.productName = productName2;
    }

    public String getProductQty() {
        return this.productQty;
    }

    public void setProductQty(String productQty2) {
        this.productQty = productQty2;
    }

    public String getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(String productPrice2) {
        this.productPrice = productPrice2;
    }

    public CheckoutOrderGetSet(String productName2, String productQty2, String productPrice2) {
        this.productName = productName2;
        this.productQty = productQty2;
        this.productPrice = productPrice2;
    }
}
