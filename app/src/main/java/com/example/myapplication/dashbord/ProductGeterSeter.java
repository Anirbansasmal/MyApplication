package com.example.myapplication.dashbord;

public class ProductGeterSeter {
    private String discount;
    private String discount_price;
    private int image_drawable;
    private String name;
    private String price;

    public ProductGeterSeter(String name2, String price2, String discount2, String discount_price2, int image_drawable2) {
        this.name = name2;
        this.price = price2;
        this.discount = discount2;
        this.discount_price = discount_price2;
        this.image_drawable = image_drawable2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price2) {
        this.price = price2;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount2) {
        this.discount = discount2;
    }

    public String getDiscount_price() {
        return this.discount_price;
    }

    public void setDiscount_price(String discount_price2) {
        this.discount_price = discount_price2;
    }

    public int getImage_drawable() {
        return this.image_drawable;
    }

    public void setImage_drawable(int image_drawable2) {
        this.image_drawable = image_drawable2;
    }
}
