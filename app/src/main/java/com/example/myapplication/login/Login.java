package com.example.myapplication.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
//import com.example.myapplication.ApiInterface.Post;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.verifyotp.VerifyOtp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    ArrayList<HashMap<String,String>> login=new ArrayList<HashMap<String, String>>();
    ApiInterface apiInterface;
    EditText editText_phone;
    Button btn_otp;
    String header="application/x-www-form-urlencoded";
    public static final String mypreference = "mypref";
    public static String token,userFCMtoken;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_login);
//        getSupportActionBar().hide();
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        editText_phone=findViewById(R.id.txt_mobile);
        btn_otp=findViewById(R.id.btn_otp);
//        autologin();
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()) {
                    userFCMtoken=task.getResult().getToken();
                }else{
                    
                }
            }
        });
    }


    public void btnGetOtp(View view) {
        Call<Login_sta> call=apiInterface.response_login(header,(editText_phone.getText().toString()),userFCMtoken);
        call.enqueue(new Callback<Login_sta>() {

            @Override
            public void onResponse(Call<Login_sta> call, Response<Login_sta> response) {
                System.out.println("nsvsvhvd"+response.body().toString());
                Intent intent=new Intent(getApplicationContext(), VerifyOtp.class);
                intent.putExtra("Mobile",response.body().getLogin_val().getPhoneNumber());
                intent.putExtra("otp",response.body().getLogin_val().getOtp());
                intent.putStringArrayListExtra("otp_array",response.body().getLogin_val().getUsr_otp());
                intent.putExtra("time",response.body().getLogin_val().getTime());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Login_sta> call, Throwable t) {

            }
        });

    }
    public void autologin(){
        SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
        token = sharedPref.getString("token", "");
        if (token.length()==0){
            startActivity(new Intent(this, Login.class));
        }else{
            startActivity(new Intent(this, Dashbord.class));
        }
    }
}
