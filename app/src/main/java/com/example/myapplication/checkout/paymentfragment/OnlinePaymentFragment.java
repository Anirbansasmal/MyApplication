package com.example.myapplication.checkout.paymentfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.productdetail.AddCart;
import com.example.myapplication.profile.View_Profile;
import com.example.myapplication.thanku.ThankYou;

import java.util.HashSet;
import java.util.Set;

import im.delight.android.webview.AdvancedWebView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.dashbord.Dashbord.user_id;
import static com.example.myapplication.verifyotp.VerifyOtp.user_type;

public class OnlinePaymentFragment extends AppCompatActivity implements AdvancedWebView.Listener{
//    Instamojo.InstamojoPaymentCallback
    ApiInterface apiInterface;
    static String user_token,Order_id,user_email,phoneno,user_name,Order_id_cart,token_val,status,user_typeval;
    private AdvancedWebView mWebView;
    float total;
    ProgressDialog progressDoalog;
    static String token,OnlinePay,pin,useraddress,userphoneNo,user_Name,email;
    public static final String mypreference = "mypref";
    private Activity onlinePaymentFragment = null;
    String server;
    String path;
    String protocol;
    Set<String> args=new HashSet<String>();
    String chapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_paymentonline);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_payment, container, false);
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
//        mWebView.getSettings().setJavaScriptEnabled(true);

        Intent intent=getIntent();
//        if (Order_id_product==null){
            Order_id=intent.getStringExtra("Order_id");
        total=intent.getFloatExtra("product_amt_total",0);
        OnlinePay=intent.getStringExtra("OnlinePay");
            System.out.println("Order_idsmnbdfjhfgdsjkd"+Order_id);
//        }else {
//            Order_id=Order_id_product;
//        }

//        user_typeval=user_type;

        progressDoalog = new ProgressDialog(OnlinePaymentFragment.this);
        shared();

    }

    public void progress_show(){
        progressDoalog.show();
    }
    public void progress_dismiss(){
        progressDoalog.dismiss();
    }
    public void progress_message(){
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its payment detail loading....");
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
                System.out.println("user_nameuser_name"+user_name);
                OnlinePay(OnlinePay);
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
                Intent intent=new Intent(OnlinePaymentFragment.this, Dashbord.class);
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


    String redirect_url;
    public void OnlinePay(String status_type){
        System.out.println(total);
        progress_show();
        progress_message();
        redirect_url="http://app.milchmom.com:8080/api/callback?Order_idUser="+Order_id+"&"+"user_id="+user_id+"&"+"total="+total;
        System.out.println("jkgwdjgjgsj"+redirect_url);
        Call<Payment> call=apiInterface.response_productOrderOnlinePayment(Order_id,total,phoneno,email,user_name,status_type,user_id,redirect_url,user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<Payment>() {

            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                progress_dismiss();
                System.out.println("response"+response.body());
                payment(response.body().getResponseData().getPayment_request().getLongurl());
//                if (response.body().getStatus().equals("success")){
//                    Intent intent=new Intent(PaymentFragment.this, ThankYou.class);
//                    startActivity(intent);
//                }
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                System.out.println("response");
            }
        });
    }

public void payment(String url){
//    mWebView.setVisibility(View.VISIBLE);

    mWebView.loadUrl(url);
    MyWebViewClient webViewClient = new MyWebViewClient(this);
//    mWebView.setWebViewClient(webViewClient);
}



    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

//    @Override
//    public void onBackPressed() {
//        if (!mWebView.onBackPressed()) { return; }
//        // ...
//        super.onBackPressed();
//    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        System.out.println("onPageStarted"+url);
    }

    @Override
    public void onPageFinished(String url) {
        System.out.println("sldjfkldghjfdgl"+url);
        if (url.contains("?Order_idUser=")) {
            Uri uri = Uri.parse(url);
            server = uri.getAuthority();
            path = uri.getPath();
            protocol = uri.getScheme();
            args = uri.getQueryParameterNames();
            chapter = uri.getQueryParameter("Order_idUser");
//            try {
                if (chapter.equals(Order_id)) {
                    Intent intent = new Intent(getApplicationContext(), ThankYou.class);
                    intent.putExtra("Order_id", chapter);
                    startActivity(intent);
//            }
                    System.out.println("khasdkjfgjkdfgd" + chapter);

//            finish();
                }  // close activity
                else {
//            mWebView.loadUrl(url);
                }
//            } catch (Exception e) {
//
//            }
        }
//        Intent intent=new Intent(PaymentFragment.this, Dashbord.class);
//        startActivity(intent);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        System.out.println("khasdkjfgjkdfgdonPageError"+failingUrl);
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
        System.out.println("khasdkjfgjkdfgdonDownloadRequested"+url);
    }

//    @Override
//    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) { }

    @Override
    public void onExternalPageRequest(String url) {
        System.out.println("khasdkjfgjkdfgdonExternalPageRequest"+url);
    }
}
