package com.example.myapplication.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit extends AppCompatActivity {
    ApiInterface apiInterface;
    static String user_token,usr_id;
    static String Content="application/x-www-form-urlencoded",token,token_val,user_id;
    EditText name,email,phoneNumber,pinno,age,deli_location;
    Button btn_addprofile;
    public static final String mypreference = "mypref";
    String usr_name,u_id,usr_email,usr_phoneNumber,usr_pinno,usr_age,usr_location;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.fragment_add_profile);

        name=findViewById(R.id.edit_name);
        email=findViewById(R.id.edit_email);
        phoneNumber=findViewById(R.id.edit_phoneno);
        pinno=findViewById(R.id.edit_pinno);
        age=findViewById(R.id.edit_age);
        btn_addprofile=findViewById(R.id.btnAddAddress);
        deli_location=findViewById(R.id.edit_location);
        Intent intent=getIntent();
        usr_id=intent.getStringExtra("_id");
        shared();
        add_profile();
        setEdit();
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
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

    private void add_profile(){
        btn_addprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("jkgsfdvhfhsvfhvsjdf"+user_id);
                usr_name=name.getText().toString();
                usr_email=email.getText().toString();
                usr_phoneNumber=phoneNumber.getText().toString();
                usr_pinno=pinno.getText().toString();
                usr_age=age.getText().toString();
                usr_location=deli_location.getText().toString();

                System.out.println(usr_id);

//                System.out.println("lkjgldgmdg"+usr_product+""+usr_unit);

                Call<AddProfile> call=apiInterface.response_EditAddress(usr_name,user_id,usr_email,usr_phoneNumber,usr_pinno,usr_age,usr_location,usr_id,user_token);
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

    private void setEdit(){
        Intent intent=getIntent();
        name.setText(""+intent.getStringExtra("UserName"));
        email.setText(""+intent.getStringExtra("email"));
        phoneNumber.setText(""+intent.getStringExtra("UserPhone"));
        pinno.setText(""+intent.getStringExtra("UserPin"));
        age.setText(""+intent.getStringExtra("age"));
        deli_location.setText(""+intent.getStringExtra("UserAddress"));
    }
}
