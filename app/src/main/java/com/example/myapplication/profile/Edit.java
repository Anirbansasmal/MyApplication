package com.example.myapplication.profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.checkout.paymentfragment.OnlinePaymentFragment;
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
    ProgressDialog progressDoalog;
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
                            SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("pin",usr_pinno);
                            editor.putString("address", usr_location);
                            System.out.println("nbsdfjnvsdhf"+usr_pinno);
                            editor.commit();
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
                Intent intent=new Intent(Edit.this, Dashbord.class);
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
