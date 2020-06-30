package com.example.myapplication.dashbord;

public class PopularProductGetSet {
    private String pp_description;
    private int pp_image_drawable;
    private String pp_name;
    private String pp_price;
    private String pp_quantity;

    public PopularProductGetSet(String pp_price2, String pp_description2, String pp_quantity2, String pp_name2, int pp_image_drawable2) {
        this.pp_price = pp_price2;
        this.pp_description = pp_description2;
        this.pp_quantity = pp_quantity2;
        this.pp_name = pp_name2;
        this.pp_image_drawable = pp_image_drawable2;
    }

    public String getPp_price() {
        return this.pp_price;
    }

    public void setPp_price(String pp_price2) {
        this.pp_price = pp_price2;
    }

    public String getPp_description() {
        return this.pp_description;
    }

    public void setPp_description(String pp_description2) {
        this.pp_description = pp_description2;
    }

    public String getPp_quantity() {
        return this.pp_quantity;
    }

    public void setPp_quantity(String pp_quantity2) {
        this.pp_quantity = pp_quantity2;
    }

    public String getPp_name() {
        return this.pp_name;
    }

    public void setPp_name(String pp_name2) {
        this.pp_name = pp_name2;
    }

    public int getPp_image_drawable() {
        return this.pp_image_drawable;
    }

    public void setPp_image_drawable(int pp_image_drawable2) {
        this.pp_image_drawable = pp_image_drawable2;
    }
}
