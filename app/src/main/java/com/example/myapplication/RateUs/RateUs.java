package com.example.myapplication.RateUs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.profile.AddProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateUs extends AppCompatActivity {
    ApiInterface apiInterface;
    static String user_token,usr_id;
    RatingBar ratingbar;
    Button button;
    static String Content="application/x-www-form-urlencoded",token,token_val,user_id;
    EditText user_note,email,phoneNumber,pinno,age,deli_location;
    Button btn_addprofile;
    public static final String mypreference = "mypref";
    String UserNote,u_id,usr_email,usr_phoneNumber,usr_pinno,usr_age,usr_location;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_rate_us);
        ratingbar=(RatingBar)findViewById(R.id.ratingBarCustomer);
        button=findViewById(R.id.btn_submit);
        user_note=findViewById(R.id.UserNote);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        shared();
        rate_us();
//        getSupportActionBar().hide();
    }

    public void rateusback(View view) {
        finish();
    }
    public void shared() {
        try {
            SharedPreferences sharedPref = this.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_id = sharedPref.getString("u_id", "");
            user_token = "Berear " + token_val;
//            user_token = token_val;
        } catch (Exception e) {

        }
    }

    private void rate_us(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating=String.valueOf(ratingbar.getRating());
                UserNote=user_note.getText().toString();
                System.out.println("jkaghjdsajdn"+rating);
                Call<AddProfile> call=apiInterface.response_RateUs(user_id,rating,UserNote,user_token);
                call.enqueue(new Callback<AddProfile>() {
                    @Override
                    public void onResponse(Call<AddProfile> call, Response<AddProfile> response) {
                        System.out.println(response.body().getStatus());
                        if (response.body().getStatus().equals("success")){
                            startActivity(new Intent(getApplicationContext(), Dashbord.class));
                        }
//                for (int i=0;i<response.body().getAvl_times().size();i++){
//                    deliiveryTimeGetSetList.add(response.body().getAvl_times().get(i).getDeliTimeSlot());
//                }
                    }
                    @Override
                    public void onFailure(Call<AddProfile> call, Throwable t) {

                    }
                });
            }
        });


    }

}
