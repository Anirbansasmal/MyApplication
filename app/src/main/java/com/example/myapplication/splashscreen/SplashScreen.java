package com.example.myapplication.splashscreen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.login.Login;

public class SplashScreen extends AppCompatActivity {
    ImageView iv1;
    ImageView iv2;
    public static final String mypreference = "mypref";
    public static String token;
    SplashScreen splashScreen = SplashScreen.this;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.splash_screen);
//        getSupportActionBar().hide();
        this.iv1 = (ImageView) findViewById(R.id.imageView_flow_up);
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
            autologin();
        }

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        }
    }
    public void autologin(){
        SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
        token = sharedPref.getString("token", "");
        System.out.println("token   "+token.length());
//        startActivity(new Intent(splashScreen.getApplicationContext(), Login.class));
        if (token.length()==0){
//            startActivity(new Intent(this, Login.class));
            new Handler().postDelayed(new Runnable() {
                public void run() {
//                    SplashScreen splashScreen = SplashScreen.this;
                    splashScreen.startActivity(new Intent(splashScreen.getApplicationContext(), Login.class));
                    SplashScreen.this.finish();
                }
            }, 10000);
        }else{
            startActivity(new Intent(this, Dashbord.class));
            SplashScreen.this.finish();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
