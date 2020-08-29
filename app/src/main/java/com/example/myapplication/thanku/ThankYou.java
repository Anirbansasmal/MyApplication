package com.example.myapplication.thanku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.checkout.Checkout;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.dashbord.Product_unit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThankYou extends AppCompatActivity {
    TextView tyDeliveryDate;
    ProgressDialog progressDoalog;
    TextView tyDeliveryMode;
    TextView tyDeliveryTimeslot;
    TextView tyPaymentMethod,orderid,delivery_time,tyDeliveryAddress;
Button back_home;
    ApiInterface apiInterface;
    public static final String mypreference = "mypref";
    static String user_token;
    static String token,token_val,prname,unit,pincode,Order_id;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_thank_you);
        Order_id=getIntent().getStringExtra("Order_id");
        System.out.println("tyDeliveryModeOrder_id"+Order_id);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        shared();
//        getSupportActionBar().hide();
        back_home=findViewById(R.id.back_home);

        this.tyDeliveryMode = (TextView) findViewById(R.id.tyDeliveryMode);
//        this.tyDeliveryMode.setText(Html.fromHtml("<font color=#414141>Delivery Mode : </font> <font color=000000><b>One Time Order </B></font>"));
        this.tyDeliveryDate = (TextView) findViewById(R.id.tyDeliveryDate);
//        this.tyDeliveryDate.setText(Html.fromHtml("<font color=#414141>Delivery Date : </font> <font color=000000><b>10.12.2019</B></font>"));
        this.tyDeliveryTimeslot = (TextView) findViewById(R.id.tyDeliveryTimeslot);
//        this.tyDeliveryTimeslot.setText(Html.fromHtml("<font color=#414141>Delivery Time Slot : </font> <font color=000000><b>6AM - 7AM</B></font>"));
        this.tyPaymentMethod = (TextView) findViewById(R.id.tyPaymentMethod);
//        this.tyPaymentMethod.setText(Html.fromHtml("<font color=#414141>Payment Method : </font> <font color=000000><b>Cash on Delivery</B></font>"));
        orderid=findViewById(R.id.orderid);
        delivery_time=findViewById(R.id.delivery_time);
        tyDeliveryAddress=findViewById(R.id.tyDeliveryAddress);
        progressDoalog = new ProgressDialog(ThankYou.this);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Dashbord.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void shared() {
        try {
            SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_token="Berear "+token_val;
            order_success();
//            user_token = token_val;
        } catch (Exception e) {

        }
    }
    public void progress_show(){
        progressDoalog.show();
    }
    public void progress_dismiss(){
        progressDoalog.dismiss();
    }
    public void progress_message(){
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its product order placed detail loading....");
        progressDoalog.setTitle("Thank you for give some time");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }


    public void order_success() {
        Call<OrderSuccessDeliver> call = apiInterface.response_productOrderPlaced(user_token, Order_id);
        call.enqueue(new Callback<OrderSuccessDeliver>() {
            @Override
            public void onResponse(Call<OrderSuccessDeliver> call, Response<OrderSuccessDeliver> response) {
                System.out.println("tyDeliveryMode"+response.body());
                orderid.setText("OrderId :"+Order_id);
                tyDeliveryMode.setText(Html.fromHtml("<font color=#414141>Delivery Mode :"+response.body().getOrderSuccess().get(0).getUsr_pay_type()+" </font> <font color=000000><b></B></font>"));
                tyDeliveryDate.setText(Html.fromHtml("<font color=#414141>Delivery Date :"+response.body().getOrderSuccess().get(0).getDelivery_date()+" </font> <font color=000000><b></B></font>"));
                tyDeliveryTimeslot.setText(Html.fromHtml("<font color=#414141>Delivery Time Slot :"+response.body().getOrderSuccess().get(0).getDelivery_time()+" </font> <font color=000000><b></B></font>"));
                tyPaymentMethod.setText(Html.fromHtml("<font color=#414141>Payment Method :"+response.body().getOrderSuccess().get(0).getUsr_pay_type()+" </font> <font color=000000><b></B></font>"));
                delivery_time.setText(Html.fromHtml("<font color=#414141>Delivery Time Slot :"+response.body().getOrderSuccess().get(0).getDelivery_time()+" </font> <font color=000000><b></B></font>"));
                tyDeliveryAddress.setText(Html.fromHtml("<font color=#414141>"+response.body().getOrderSuccess().get(0).getAddress()+" </font> <font color=000000><b></B></font>"));


            }

            @Override
            public void onFailure(Call<OrderSuccessDeliver> call, Throwable t) {

            }
        });
    }
}
