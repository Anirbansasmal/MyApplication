package com.example.myapplication.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.cart.Cart;
import com.example.myapplication.dashbord.Dashbord;

import java.util.ArrayList;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public static Button btnAddAddress;
    ApiInterface apiInterface;
    static String user_token, p_id;
    static String Content="application/json", token_val, user_id;
    TreeMap<String, String> add_profile = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> user_profile=new ArrayList<TreeMap<String, String>>();
    public static final String mypreference = "mypref";
    RecyclerView rvprofile;
    ProfileAdapter profileAdapter;
    ImageView productdetailback;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnAddAddress = findViewById(R.id.btnAddAddress);
//        getSupportActionBar().hide();
//        btnAddAddress.setVisibility(View.VISIBLE);
        rvprofile=findViewById(R.id.rvprofile);
        rvprofile.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        rvprofile.setItemAnimator(new DefaultItemAnimator());
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        productdetailback=findViewById(R.id.productdetailback);
        shared();
        user_profile();
//        add_profile();
        productdetailback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this, Dashbord.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
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

//    public void profilelback(View view) {
//        finish();
//    }

    public void profilepagecart(View view) {
        Intent intent=new Intent(Profile.this, Cart.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void Add_New_Address(View view) {
        btnAddAddress.setVisibility(View.INVISIBLE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment hello = new AddProfileFragment();
        fragmentTransaction.addToBackStack((String) null);
        fragmentTransaction.replace(R.id.user_profile, hello, "HELLO");
        fragmentTransaction.commit();
    }

    private void user_profile() {
        System.out.println(Content);
        Call<View_Profile> call = apiInterface.response_UserProfile(user_token,user_id);
        call.enqueue(new Callback<View_Profile>() {
            @Override
            public void onResponse(Call<View_Profile> call, Response<View_Profile> response) {
                if (response.body().getMessage()=="no data available"){

                }else {

                for (int i=0;i<response.body().getUser().size();i++){
                add_profile=profile_view_user(response.body().getUser().get(i).getU_id(),
                        response.body().getUser().get(i).getUserName(),
                        response.body().getUser().get(i).getUserAddress(),
                        response.body().getUser().get(i).getUserPin(),
                        response.body().getUser().get(i).getAge(),
                        response.body().getUser().get(i).get_id(),
                        response.body().getUser().get(i).getUserPhone(),
                        response.body().getUser().get(i).getEmail());
                user_profile.add(add_profile);
                }
                }

                add_profile();
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
                Intent intent=new Intent(Profile.this, Dashbord.class);
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
    private void add_profile(){
        profileAdapter=new ProfileAdapter(this,user_profile);
        rvprofile.setAdapter(profileAdapter);
    }

    public static TreeMap<String, String> profile_view_user(String u_id, String UserName,String UserAddress, String UserPin,
                                                                 String age, String _id,String UserPhone,String email) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("u_id", String.valueOf(u_id));
        treeMap.put("UserName", UserName);
        treeMap.put("UserAddress",UserAddress);
        treeMap.put("UserPin",UserPin);
        treeMap.put("age",age);
        treeMap.put("_id",_id);
        treeMap.put("UserPhone",UserPhone);
        treeMap.put("email",email);
//        treeMap.put("p_img",p_img);

        //
        return treeMap;
    }
}
