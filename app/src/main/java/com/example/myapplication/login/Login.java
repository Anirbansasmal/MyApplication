package com.example.myapplication.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
//import com.example.myapplication.ApiInterface.Post;
import com.example.myapplication.R;
import com.example.myapplication.checkout.paymentfragment.PaymentFragment;
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
    ProgressDialog progressDoalog;
    String header="application/x-www-form-urlencoded";
    public static final String mypreference = "mypref";
    public static String token,userFCMtoken,phoneNo;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_login);
//        getSupportActionBar().hide();
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        editText_phone=findViewById(R.id.txt_mobile);
        btn_otp=findViewById(R.id.btn_otp);

//        autologin();
        progressDoalog = new ProgressDialog(Login.this);
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()) {
                    userFCMtoken=task.getResult().getToken();
                }else{
                    
                }
            }
        });

        if (!isNetworkAvailable()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("You are not online!!!!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }else{


        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNo=editText_phone.getText().toString().trim();
                btnGetOtp();
            }
        });
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

    public void btnGetOtp() {
        progress_show();
        progress_message();
        Call<Login_sta> call=apiInterface.response_login(header,phoneNo,userFCMtoken);
        call.enqueue(new Callback<Login_sta>() {

            @Override
            public void onResponse(Call<Login_sta> call, Response<Login_sta> response) {
                System.out.println("nsvsvhvd"+response.body().getLogin_val());
                if (response.body().getStatus().equals("1")){
                    progress_dismiss();
                    Intent intent=new Intent(getApplicationContext(), VerifyOtp.class);
                    System.out.println(response.body().getLogin_val().getPhoneNumber());
                    intent.putExtra("Mobile",response.body().getLogin_val().getPhoneNumber());
                    intent.putExtra("otp",response.body().getLogin_val().getOtp());
                    intent.putStringArrayListExtra("otp_array",response.body().getLogin_val().getUsr_otp());
//                    intent.putExtra("time",response.body().getLogin_val().getTime());
                    SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
//                    token = sharedPref.getString("token", "");
                    editor.putString("time",response.body().getLogin_val().getTime());
                    editor.putString("Mobile",response.body().getLogin_val().getPhoneNumber());

                    editor.commit();
                    startActivity(intent);
                    Login.this.finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Your account is deactivate by admin",Toast.LENGTH_SHORT).show();
                }

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
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
