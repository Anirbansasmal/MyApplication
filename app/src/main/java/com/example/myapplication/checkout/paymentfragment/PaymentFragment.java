package com.example.myapplication.checkout.paymentfragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import com.example.myapplication.productdetail.ProductDetail;
import com.example.myapplication.profile.View_Profile;
import com.example.myapplication.thanku.ThankYou;

import im.delight.android.webview.AdvancedWebView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.dashbord.Dashbord.user_id;
import static com.example.myapplication.verifyotp.VerifyOtp.user_type;

public class PaymentFragment extends AppCompatActivity implements View.OnClickListener{
//    Instamojo.InstamojoPaymentCallback
    TextView cashondelivery;
    LinearLayout cashondelivery_ll;
    Button confirmorderpayment_btn;
    TextView onlinepayment;
    LinearLayout onlinepayment_ll;
    TextView postpaidpayment;
    LinearLayout postpaidpayment_ll;
    ProgressDialog progressDoalog;
    ApiInterface apiInterface;
    static String user_token,Order_id,user_email,phoneno,user_name,Order_id_cart,token_val,status,user_typeval;
    private AdvancedWebView mWebView;
    float total;
    static String token,prname,pin,useraddress,userphoneNo,user_Name,email;
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
//        mWebView = (AdvancedWebView) findViewById(R.id.webview);
//        mWebView.setVisibility(View.INVISIBLE);
//        mWebView = (AdvancedWebView) findViewById(R.id.webview);
//        mWebView.setListener(this, this);
        this.cashondelivery_ll.setOnClickListener(this);
        this.onlinepayment_ll.setOnClickListener(this);
        this.postpaidpayment_ll.setOnClickListener(this);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Intent intent=getIntent();
//        if (Order_id_product==null){
            Order_id=intent.getStringExtra("Order_id");
        total=intent.getFloatExtra("product_amt_total",0);

            System.out.println("Order_idsmnbdfjhfgdsjkd"+Order_id);
//        }else {
//            Order_id=Order_id_product;
//        }

//        user_typeval=user_type;

        progressDoalog = new ProgressDialog(PaymentFragment.this);
        shared();
        System.out.println(user_type);
        if (user_type.equals("PostPaid")){
            postpaidpayment_ll.setVisibility(View.VISIBLE);
        }else {
            postpaidpayment_ll.setVisibility(View.INVISIBLE);
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
        progressDoalog.setMessage("Its product detail loading....");
        progressDoalog.setTitle("Thank you for give some time");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }
    public void address(int pincode){
        progress_show();
        progress_message();
        Call<View_Profile> call = apiInterface.response_UserProfile(user_token,user_id);

        call.enqueue(new Callback<View_Profile>() {

            @Override
            public void onResponse(Call<View_Profile> call, Response<View_Profile> response) {
                progress_dismiss();
                System.out.println(response.body().getUser());
                email=response.body().getUser().get(0).getEmail();
                userphoneNo=response.body().getUser().get(0).getUserPhone();
                useraddress=response.body().getUser().get(0).getUserAddress();
                user_Name=response.body().getUser().get(0).getUserName();
                phoneno=userphoneNo;
                user_email=email;
                user_name=user_Name;
//                for (int i=0;i<response.body().getUser().size();i++){
//                    add_profile=profile_view_user(response.body().getUser().get(i).getU_id(),
//                            response.body().getUser().get(i).getUserName(),
//                            response.body().getUser().get(i).getUserAddress(),
//                            response.body().getUser().get(i).getUserPin(),
//                            response.body().getUser().get(i).getAge(),
//                            response.body().getUser().get(i).get_id(),
//                            response.body().getUser().get(i).getUserPhone(),
//                            response.body().getUser().get(i).getEmail());
//                    address=response.body().getUser().get(i).getUserAddress();
//
////                    user_profile.add(add_profile);
//                }
//                user_name.setText(""+response.body().getUser().get(0).getUserName());
//                user_email.setText(""+response.body().getUser().get(0).getEmail());
//                profileAdapter=new ProfileAdapter(Dashbord.this,user_profile);
//                recyclerViewchangelocation.setAdapter(profileAdapter);
            }

            @Override
            public void onFailure(Call<View_Profile> call, Throwable t) {

            }
        });
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
            pin=sharedPref.getString("pin","");
            user_token="Berear "+token_val;
            address(Integer.parseInt(pin));
//            user_token = token_val;
        } catch (Exception e) {

        }
    }

    public void CashOnDel(String status_type){
        progress_message();
        progress_show();
        System.out.println("user_tokenuser_token"+user_token);
        Call<AddCart> call=apiInterface.response_productOrderConfirm(Order_id,status_type,user_token);

        call.enqueue(new Callback<AddCart>() {

            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
if (response.body().getStatus().equals("success")){
    progress_dismiss();
    Intent intent=new Intent(PaymentFragment.this, ThankYou.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.putExtra("Order_id",Order_id);
    startActivity(intent);
}
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {

            }
        });
    }

    public void OnlinePay(String status_type){
        Intent intent=new Intent(PaymentFragment.this, OnlinePaymentFragment.class);
        intent.putExtra("OnlinePay",status_type);
        intent.putExtra("Order_id",Order_id);
        intent.putExtra("product_amt_total",total);
        startActivity(intent);
    }

//    public void OnlinePay(String status_type){
//        System.out.println(total);
////        String redirect_url="http://app.milchmom.com:8080/api/callback?user_id="+user_id+" "+"Order_id="+Order_id+"";
////        System.out.println("jkgwdjgjgsj"+redirect_url);
//        Call<Payment> call=apiInterface.response_productOrderOnlinePayment(Order_id,total,phoneno,email,user_name,status_type,user_id,user_token);
//        System.out.println(user_token);
//        call.enqueue(new Callback<Payment>() {
//
//            @Override
//            public void onResponse(Call<Payment> call, Response<Payment> response) {
//                System.out.println("response"+response.body());
//                payment(response.body().getResponseData().getPayment_request().getLongurl());
////                if (response.body().getStatus().equals("success")){
////                    Intent intent=new Intent(PaymentFragment.this, ThankYou.class);
////                    startActivity(intent);
////                }
//            }
//
//            @Override
//            public void onFailure(Call<Payment> call, Throwable t) {
//                System.out.println("response");
//            }
//        });
//    }
//
//    public void payment(String url){
//    mWebView.setVisibility(View.VISIBLE);
//        mWebView.loadUrl(url);
//    }
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
//
//    @Override
//    public void onPageStarted(String url, Bitmap favicon) {
//
//    }
//
//    @Override
//    public void onPageFinished(String url) {
//
//    }
//
//    @Override
//    public void onPageError(int errorCode, String description, String failingUrl) {
//
//    }
//
//    @Override
//    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
//
//    }
//
//    @Override
//    public void onExternalPageRequest(String url) {
//
//    }
}
