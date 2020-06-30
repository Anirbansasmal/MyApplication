package com.example.myapplication.dashbord;

public class NewArrivalsGetSet {
    private String p_id = "";
    private String p_name = "";
    private String p_details = "";
    private String p_mfg="";
    private String p_exp = "";
    private String p_type = "";
    private String p_price = "";
    private String ProductQty="";
    private String p_img;

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_details() {
        return p_details;
    }

    public void setP_details(String p_details) {
        this.p_details = p_details;
    }

    public String getP_mfg() {
        return p_mfg;
    }

    public void setP_mfg(String p_mfg) {
        this.p_mfg = p_mfg;
    }

    public String getP_exp() {
        return p_exp;
    }

    public void setP_exp(String p_exp) {
        this.p_exp = p_exp;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getProductQty() {
        return ProductQty;
    }

    public void setProductQty(String productQty) {
        ProductQty = productQty;
    }

    public String getP_img() {
        return p_img;
    }

    public void setP_img(String p_img) {
        this.p_img = p_img;
    }





    public NewArrivalsGetSet(String p_id, String p_name, String p_details,
                             String p_mfg, String p_exp, String p_type,
                             String p_price, String ProductQty, String p_img) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_details = p_details;
        this.p_mfg = p_mfg;
        this.p_exp = p_exp;
        this.p_type = p_type;
        this.p_price = p_price;
        this.ProductQty = ProductQty;
        this.p_img = p_img;
    }
}
