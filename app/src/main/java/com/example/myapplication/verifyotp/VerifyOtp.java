package com.example.myapplication.verifyotp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.login.Login;
import com.example.myapplication.login.Login_sta;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtp extends AppCompatActivity {
    ApiInterface apiInterface;
    String header="application/x-www-form-urlencoded";
    ProgressDialog progressDoalog;
    String otpmessage;
    public static String Mobile,otp,time,val_otp,user_type;
    String[]spli_otp;
    EditText otp1,otp2,otp3,otp4;
    public static final String mypreference = "mypref";
    TextView user_name,user_email;
    public ArrayList<String> currencies;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_verify_otp);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
//        getSupportActionBar().hide();
        otp1=findViewById(R.id.editTextone);
        otp2=findViewById(R.id.editTexttwo);
        otp3=findViewById(R.id.editTextthree);
        otp4=findViewById(R.id.editTextfour);
        Intent intent=getIntent();
        progressDoalog = new ProgressDialog(VerifyOtp.this);
        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
//        Mobile=intent.getStringExtra("Mobile");
        otp=intent.getStringExtra("otp");
//        time=intent.getStringExtra("time");

//        spli_otp= (otp.split(" "));

//        currencies.add(String.valueOf(spli_otp));
//        Arrays.toString(new ArrayList[]{currencies});

//        for (int i=0;i<currencies.size();i++){
//            otp1.setText(""+currencies.get(0));
//            otp2.setText(""+currencies.get(1));
//            otp3.setText(""+currencies.get(2));
//            otp4.setText(""+currencies.get(3));
//        }
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
    public void verifyOtpBtn(View view) {
        SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        time = sharedPref.getString("time", "");
        Mobile = sharedPref.getString("Mobile", "");
        otpmessage=otp1.getText().toString()+""+otp2.getText().toString()+""+otp3.getText().toString()+""+otp4.getText().toString();
        System.out.println("jkfshdfsjd"+Mobile+" "+time+""+otpmessage);
        if (otpmessage.equals(otp)){
            progress_show();
            progress_message();
        Call<Login_otp> call=apiInterface.response_otpVerify(header,Mobile,otpmessage,time);
        call.enqueue(new Callback<Login_otp>() {

            @Override
            public void onResponse(Call<Login_otp> call, Response<Login_otp> response) {

if ("1".equals(response.body().getStatus())){
    progress_dismiss();
    SharedPreferences sharedPref =    getSharedPreferences(mypreference, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putString("token", response.body().getToken());
    editor.putString("u_id",response.body().get_id());
    user_type=response.body().getUser_type();
    editor.putString("user_type",user_type);
    System.out.println(response.body().get_id());
    editor.commit();
    startActivity(new Intent(getApplicationContext(), Dashbord.class));
}else {

//    startActivity(new Intent(getApplicationContext(),VerifyOtp.class));
}
            }
            
            @Override
            public void onFailure(Call<Login_otp> call, Throwable t) {

            }
        });
        }else {
            System.out.println("otp dose`t match");
        }
    }

    public void ResendOtpBtn(View view){
        otp1.setText("");
        otp2.setText("");
        otp3.setText("");
        otp4.setText("");
        SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
        time = sharedPref.getString("time", "");
        Mobile = sharedPref.getString("Mobile", "");
//        currencies=intent.getStringArrayListExtra("otp_array");
//        val_otp="'"+otp+"'";
        System.out.println("jkfshdfsjd"+Mobile+" "+time);
        progress_show();
        progress_message();
        Call<Login_sta> call=apiInterface.response_otpResend(Mobile,otpmessage);
        call.enqueue(new Callback<Login_sta>() {

            @Override
            public void onResponse(Call<Login_sta> call, Response<Login_sta> response) {

                if ("1".equals(response.body().getStatus())){
                    progress_dismiss();
                    Mobile=response.body().getLogin_val().getPhoneNumber();
                    otp=response.body().getLogin_val().getOtp();
                    currencies=response.body().getLogin_val().getUsr_otp();
//                    time=response.body().getLogin_val().getTime();
                    SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
//                    token = sharedPref.getString("token", "");
                    editor.putString("time",response.body().getLogin_val().getTime());
                    editor.putString("time",response.body().getLogin_val().getTime());
                    editor.commit();
                }else {
                    startActivity(new Intent(getApplicationContext(),VerifyOtp.class));
                }
            }

            @Override
            public void onFailure(Call<Login_sta> call, Throwable t) {

            }
        });
    }
}
