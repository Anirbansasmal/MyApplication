package com.example.myapplication.ApiInterface;

import com.example.myapplication.Discount.Discount;
import com.example.myapplication.Search.Product_user_search;
import com.example.myapplication.cart.AddCartOrder;
import com.example.myapplication.cart.CartData;
import com.example.myapplication.checkout.deliverfragment.OrderConfirm;
import com.example.myapplication.checkout.deliverfragment.Order_product;
import com.example.myapplication.confirmorderdetail.OrderStatus;
import com.example.myapplication.dashbord.Avl_times_deli;
import com.example.myapplication.dashbord.Product_unit;
import com.example.myapplication.dashbord.Product_user;
import com.example.myapplication.dashbord.Slider_product;
import com.example.myapplication.dashbord.bulkorder.Bulk;
import com.example.myapplication.login.Login_sta;
import com.example.myapplication.orderhistory.activeorderfragment.User_Order;
import com.example.myapplication.orderhistory.oldorderfragment.Order_delivered;
import com.example.myapplication.productdetail.AddCart;
import com.example.myapplication.productdetail.Deltimeslotuser;
import com.example.myapplication.profile.AddProfile;
import com.example.myapplication.profile.View_Profile;
import com.example.myapplication.verifyotp.Login_otp;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("login")
    @FormUrlEncoded
    Call<Login_sta> response_login(
            @Header("Content-Type") String header,
            @Field("phoneNumber") String phoneNumber);

    @POST("otpVerify")
    @FormUrlEncoded
    Call<Login_otp> response_otpVerify(
            @Header("Content-Type") String header,
            @Field("phoneNumber") String phoneNumber,
            @Field("otp") String otp,
            @Field("time") String time);

    @GET("ProductView")
    Call<Product_user> response_ProductView(
        @Header("Authorization") String token
        );
//    response_ProductView_unit
@POST("productSearch")
@FormUrlEncoded
Call<Product_user_search> response_productSearch(
        @Header("Authorization") String token,
        @Field("search_key") String search_key);
//    response_ProductView_unit

    @POST("ProductView_unit")
    @FormUrlEncoded
    Call<Product_unit> response_ProductView_unit(
            @Header("Authorization") String token,
            @Field("p_id") String id);

    @GET("BannerRecived")
//    @FormUrlEncoded
    Call<Slider_product> response_ProductBanner(
            @Header("Authorization") String token);

    @GET("ProductView")
    Call<Product_user> response_ProductPopular(
            @Header("Authorization") String token);
//    @Multipart

    @POST("bulkorder")
    @FormUrlEncoded
    Call<Bulk> response_BulkOrder(
//                    @Field("approve_status")String approve_status,Part
                    @Field("name") String name,
                    @Field("email")String email,
                    @Field("phoneNumber")String phoneNumber,
                    @Field("product")String product,
                    @Field("p_qty")String p_qty,
                    @Field("delivery_date")String delivery_date,
                    @Field("deli_location")String deli_location,
                    @Field("deli_time")String deli_time,
                    @Field("note")String note,
                    @Header("Authorization") String token);

    @GET("ProductView")
//    @FormUrlEncoded
    Call<Product_user> response_ProductNewArrivel(
            @Header("Authorization") String token);

    @POST("ViewProfileUser")
    @FormUrlEncoded
    Call<View_Profile> response_UserProfile(
//            @Header("Content-Type") String Content,
            @Header("Authorization") String token,
            @Field("u_profileid") String u_id);


    @POST("ViewProfileUserAddress")
    @FormUrlEncoded
    Call<View_Profile> response_ViewProfileUserAddress(
//            @Header("Content-Type") String Content,
            @Header("Authorization") String token,
            @Field("u_profileid") String u_id,
            @Header("UserPin") String pin);
    @POST("AddProfile")
    @FormUrlEncoded
    Call<AddProfile> response_AddProfile(
            @Field("UserName") String name,
            @Field("u_id") String u_id,
            @Field("email")String email,
            @Field("UserPhone")String phoneNumber,
            @Field("UserPin")String product,
            @Field("age")String p_qty,
            @Field("UserAddress")String deli_location,
            @Header("Authorization") String token);

    @GET("FetchPinCode")
    Call<Avl_times_deli> response_FetchPinCode(
            @Header("Authorization") String token);

    @POST("UserDeliveryTimeSlot")
    @FormUrlEncoded
    Call<Deltimeslotuser> response_FetchDelTimeSlot(
            @Field("pin") String pin,
            @Header("Authorization") String token);

    @POST("EditProfile")
    @FormUrlEncoded
    Call<AddProfile> response_EditAddress(
            @Field("UserName") String name,
            @Field("u_id") String u_id,
            @Field("email")String email,
            @Field("UserPhone")String phoneNumber,
            @Field("UserPin")String product,
            @Field("age")String p_qty,
            @Field("UserAddress")String deli_location,
            @Field("u_profileid")String user_id,
//            @Header("Content-Type") String content,
            @Header("Authorization") String token);

    @POST("RateByCustomer")
    @FormUrlEncoded
    Call<AddProfile> response_RateUs(
            @Field("UserId") String u_id,
            @Field("UserRating") String rateing,
            @Field("UserNote") String UserNote,
            @Header("Authorization") String token);

    @GET("AboutUs")
    Call<Product_user> response_AboutUs(
            @Header("Authorization") String token);

    @POST("Support")
    Call<Product_user> response_Support(
            @Header("Authorization") String token);

    @GET("Orderhist")
    Call<Product_user> response_orderhist(
            @Header("Authorization") String token);

    @GET("Offers")
    Call<Product_user> response_Offers(
            @Header("Authorization") String token);

    @POST("DelAddress")
    Call<Product_user> response_DelAddress(
            @Header("Authorization") String token);

    @POST("addToCart")
    Call<AddCart> response_Cart(
//            @Field("p_details") String p_details,
//            @Field("product_qty") int product_qty,
//            @Field("p_unit") String p_unit,
//            @Field("time_slot") String time_slot,
//            @Field("order_frequency") ArrayList<String> order_frequency,
//            @Field("Username") String Username,
//            @Field("user_id") String user_id,
//            @Field("payment_amount") String payment_amount,
//            @Field("product_id") String product_id,
//            @Field("p_img") String p_img,
//            @Field("p_price") int p_price,
//            @Field("p_Gst") int p_GST,
            @Body JsonObject jsonObject,
            @Header("Content-Type") String content,
            @Header("Authorization") String token);
    @POST("productConfirmCart")
    Call<AddCart> response_PlaceOrder(@Field("user_id") String user_id,
                                      @Header("Authorization") String token);
    @POST("productConfirm")
//    @FormUrlEncoded
    Call<OrderStatus> response_productConfirm(
            @Body JsonObject jsonObject,
//            @Field("user_id") String user_id,
            @Header("Authorization") String token);
    @POST("productFetchOrder")
    @FormUrlEncoded
    Call<OrderConfirm> response_productFetchOrder(
            @Field("Order_id") String Order_id,
            @Header("Authorization") String token);
@POST("fetchFromCart")
@FormUrlEncoded
Call<CartData> response_fetchFromCart(
        @Field("user_id") String user_id,
        @Header("Authorization") String token);
@POST("productConfirmCancle")
@FormUrlEncoded
Call<AddCart> response_productConfirmCancle(
        @Field("Order_id") String user_id,
        @Field("product_confirmation") Boolean product_confirmation,
        @Header("Authorization") String token);
@POST("EditFromCart")
@FormUrlEncoded
Call<AddCart> response_EditFromCart(
        @Field("product_qty")String product_qty,
        @Field("user_id") String user_id,
        @Field("cart_id") String cart_id,
        @Field("p_price") String p_price,
        @Field("payment_amount") String payment_amount,
        @Field("product_amt_gst") String product_amt_gst,
        @Field("product_qty_total") String product_qty_total,
        @Field("p_discount_total") String p_discount_total,
        @Header("Authorization") String token);

@POST("productConfirmCart")
@FormUrlEncoded
Call<AddCartOrder> response_productConfirmCart(
        @Field("user_id") String user_id,
        @Field("product_confirmation") String product_confirmation,
        @Header("Authorization") String token);
@POST("productOrderConfirm")
@FormUrlEncoded
Call<AddCart> response_productOrderConfirm(
        @Field("Order_id") String Order_id,
        @Field("status") String status,
        @Header("Authorization") String token);
@POST("UserDiscountData")
@FormUrlEncoded
Call<Discount> response_UserDiscountData(
        @Field("user_id") String user_id,
        @Header("Authorization") String token);
    @POST("DeleteFromCart")
    @FormUrlEncoded
    Call<AddCart> response_DeleteCart(
            @Field("cart_id") String cart_id,
            @Header("Authorization") String token);
    @POST("productFetchOrderDeliveryActive")
    @FormUrlEncoded
    Call<User_Order> response_productFetchOrderDeliveryActive(
            @Field("user_id")String user_id,
            @Header("Authorization") String token);
    @POST("productFetchDelivered")
    @FormUrlEncoded
    Call<Order_delivered> response_productFetchDelivered(
            @Field("user_id")String user_id,
            @Header("Authorization") String token);
}
