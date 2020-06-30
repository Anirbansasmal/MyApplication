package com.example.myapplication.cart;

public class CartGetSet {
    String orderQuantity;
    String priceperunit;
    int productImage;
    String productName;
    String totalproductprice;

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName2) {
        this.productName = productName2;
    }

    public String getOrderQuantity() {
        return this.orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity2) {
        this.orderQuantity = orderQuantity2;
    }

    public String getPriceperunit() {
        return this.priceperunit;
    }

    public void setPriceperunit(String priceperunit2) {
        this.priceperunit = priceperunit2;
    }

    public String getTotalproductprice() {
        return this.totalproductprice;
    }

    public void setTotalproductprice(String totalproductprice2) {
        this.totalproductprice = totalproductprice2;
    }

    public int getProductImage() {
        return this.productImage;
    }

    public void setProductImage(int productImage2) {
        this.productImage = productImage2;
    }

    public CartGetSet(String productName2, String orderQuantity2, String priceperunit2, String totalproductprice2, int productImage2) {
        this.productName = productName2;
        this.orderQuantity = orderQuantity2;
        this.priceperunit = priceperunit2;
        this.totalproductprice = totalproductprice2;
        this.productImage = productImage2;
    }
}
