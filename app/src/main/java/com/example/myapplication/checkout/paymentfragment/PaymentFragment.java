package com.example.myapplication.checkout.paymentfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.checkout.Checkout;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.dashbord.Product_user;
import com.example.myapplication.productdetail.AddCart;
import com.example.myapplication.thanku.ThankYou;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.verifyotp.VerifyOtp.user_type;

public class PaymentFragment extends AppCompatActivity implements View.OnClickListener {
//    Instamojo.InstamojoPaymentCallback
    TextView cashondelivery;
    LinearLayout cashondelivery_ll;
    Button confirmorderpayment_btn;
    TextView onlinepayment;
    LinearLayout onlinepayment_ll;
    TextView postpaidpayment;
    LinearLayout postpaidpayment_ll;
    ApiInterface apiInterface;
    static String user_token,Order_id,Order_id_product,Order_id_cart,token_val,status,user_typeval;
    public static final String mypreference = "mypref";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.fragment_payment);

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_payment, container, false);
        this.cashondelivery = (TextView) findViewById(R.id.cashondelivery);
        this.onlinepayment = (TextView) findViewById(R.id.onlinepayment);
        this.postpaidpayment = (TextView) findViewById(R.id.postpaidpayment);
        this.cashondelivery_ll = (LinearLayout) findViewById(R.id.cashondelivery_ll);
        this.onlinepayment_ll = (LinearLayout) findViewById(R.id.onlinepayment_ll);
        this.postpaidpayment_ll = (LinearLayout) findViewById(R.id.postpaidpayment_ll);
        this.confirmorderpayment_btn = (Button) findViewById(R.id.confirmorderpayment_btn);
        this.cashondelivery_ll.setOnClickListener(this);
        this.onlinepayment_ll.setOnClickListener(this);
        this.postpaidpayment_ll.setOnClickListener(this);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Intent intent=getIntent();
//        if (Order_id_product==null){
            Order_id=intent.getStringExtra("Order_id");
            System.out.println("Order_idsmnbdfjhfgdsjkd"+Order_id);
//        }else {
//            Order_id=Order_id_product;
//        }

//        user_typeval=user_type;


        shared();
        System.out.println(user_type);
        if (user_type.equals("PostPaid")){

        }else {
            postpaidpayment_ll.setVisibility(View.INVISIBLE);
        }
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                order_cancle();
                Intent intent=new Intent(PaymentFragment.this, Dashbord.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void order_cancle(){
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Boolean product_confirmation=false;
        Intent intent=getIntent();
//        if (Order_id==null){
//            Order_id=intent.getStringExtra("Order_id");
//            System.out.println(Order_id);
//        }else {
//            Order_id=Order_id;
//            System.out.println(Order_id);
//        }
        Call<AddCart> call=apiInterface.response_productConfirmCancle(Order_id,product_confirmation,user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<AddCart>() {

            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                System.out.println("sgdjsgdf"+response.body().getStatus());
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {

            }
        });
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cashondelivery_ll) {
            this.cashondelivery.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ticgreen, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                status="cashondelivery";
                ThankYou(status);
                this.cashondelivery.setTextColor(this.getColor(R.color.colorGreen));
            }
            this.onlinepayment.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                status="";
                this.onlinepayment.setTextColor(Color.parseColor("#414141"));
            }
            this.postpaidpayment.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                status="";
                this.postpaidpayment.setTextColor(Color.parseColor("#414141"));
            }
        } else if (id == R.id.onlinepayment_ll) {
            this.onlinepayment.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ticgreen, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                status="onlinepayment";
                ThankYou(status);
                this.onlinepayment.setTextColor(this.getColor(R.color.colorGreen));
            }
            this.cashondelivery.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                status="";
                this.cashondelivery.setTextColor(Color.parseColor("#414141"));
            }
            this.postpaidpayment.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                status="";
                this.postpaidpayment.setTextColor(Color.parseColor("#414141"));
            }
        } else if (id == R.id.postpaidpayment_ll) {
            this.postpaidpayment.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ticgreen, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                status="postpaidpayment";
                ThankYou(status);
//                PostPaid();
                this.postpaidpayment.setTextColor(this.getColor(R.color.colorGreen));
            }
            this.cashondelivery.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                status="";
                this.cashondelivery.setTextColor(Color.parseColor("#414141"));
            }
            this.onlinepayment.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= 23) {
                status="";
                this.onlinepayment.setTextColor(Color.parseColor("#414141"));
            }
        }
    }

    public void shared() {
        try {
            SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_type=sharedPref.getString("user_type","");
            user_token="Berear "+token_val;
//            user_token = token_val;
        } catch (Exception e) {

        }
    }

    public void CashOnDel(String status_type){
        System.out.println("user_tokenuser_token"+user_token);
        Call<AddCart> call=apiInterface.response_productOrderConfirm(Order_id,status_type,user_token);

        call.enqueue(new Callback<AddCart>() {

            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
if (response.body().getStatus().equals("success")){
    Intent intent=new Intent(PaymentFragment.this, ThankYou.class);
    startActivity(intent);
}
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {

            }
        });
    }

    public void OnlinePay(String status_type){

    }

    public void PostPaid(String status_type){
        Call<AddCart> call=apiInterface.response_productOrderConfirm(Order_id,status_type,user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<AddCart>() {

            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                if (response.body().getStatus().equals("success")){
                    Intent intent=new Intent(PaymentFragment.this, ThankYou.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {

            }
        });
    }

    public void ThankYou(final String status_pay){
        this.confirmorderpayment_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(status_pay.equals("cashondelivery"));
                if (status_pay.equals("cashondelivery")){
                    CashOnDel(status_pay);
                }else if (status_pay.equals("onlinepayment")){
                    OnlinePay(status_pay);
                }else if (status_pay.equals("postpaidpayment")){
                    PostPaid(status_pay);
                }

//                PaymentFragment paymentFragment = PaymentFragment.this;
//                paymentFragment.startActivity(new Intent(paymentFragment.getContext(), ThankYou.class));
            }
        });
    }
}
