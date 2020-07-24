package com.example.myapplication.verifyotp;

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
import com.example.myapplication.login.Login_sta;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtp extends AppCompatActivity {
    ApiInterface apiInterface;
    String header="application/x-www-form-urlencoded";
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
        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
        Mobile=intent.getStringExtra("Mobile");
        otp=intent.getStringExtra("otp");
        time=intent.getStringExtra("time");
        currencies=intent.getStringArrayListExtra("otp_array");
        val_otp="'"+otp+"'";
        System.out.println("jkfshdfsjd"+currencies);
        spli_otp= (otp.split(" "));

//        currencies.add(String.valueOf(spli_otp));
//        Arrays.toString(new ArrayList[]{currencies});

        for (int i=0;i<currencies.size();i++){
            otp1.setText(""+currencies.get(0));
            otp2.setText(""+currencies.get(1));
            otp3.setText(""+currencies.get(2));
            otp4.setText(""+currencies.get(3));
        }
    }

    public void verifyOtpBtn(View view) {
//        System.out.println("nsvsvhvd"+response.body().getLogin_val().getPhoneNumber());

        otpmessage=otp1.getText().toString()+""+otp2.getText().toString()+""+otp3.getText().toString()+""+otp4.getText().toString();

        if (otpmessage.equals(otp)){

        Call<Login_otp> call=apiInterface.response_otpVerify(header,Mobile,otpmessage,time);
        call.enqueue(new Callback<Login_otp>() {

            @Override
            public void onResponse(Call<Login_otp> call, Response<Login_otp> response) {

if ("1".equals(response.body().getStatus())){
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
    startActivity(new Intent(getApplicationContext(),VerifyOtp.class));
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
}
