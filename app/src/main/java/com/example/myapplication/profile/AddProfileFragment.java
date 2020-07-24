package com.example.myapplication.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.dashbord.bulkorder.Bulk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProfileFragment extends Fragment {
    ApiInterface apiInterface;
    static String user_token,p_id;
    static String token,token_val,user_id;
    EditText name,email,phoneNumber,pinno,age,deli_location;
    Button btn_addprofile;
    String usr_name,u_id,usr_email,usr_location;
    int usr_age,usr_phoneNumber,usr_pinno;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_profile, container, false);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        name=view.findViewById(R.id.edit_name);
        email=view.findViewById(R.id.edit_email);
        phoneNumber=view.findViewById(R.id.edit_phoneno);
        pinno=view.findViewById(R.id.edit_pinno);
        age=view.findViewById(R.id.edit_age);
        btn_addprofile=view.findViewById(R.id.btnAddAddress);
        deli_location=view.findViewById(R.id.edit_location);
        shared();
        add_profile();
        return view;
//        return inflater.inflate(R.layout.fragment_add_profile, container, false);
    }
    public static final String mypreference = "mypref";

    public void shared() {
        try {
            SharedPreferences sharedPref = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_id=sharedPref.getString("u_id", "");

            user_token="Berear "+token_val;
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
                usr_phoneNumber= Integer.parseInt(phoneNumber.getText().toString());
                usr_pinno= Integer.parseInt(pinno.getText().toString());
                usr_age= Integer.parseInt(age.getText().toString());
                usr_location=deli_location.getText().toString();
                System.out.println(usr_name);

//                System.out.println("lkjgldgmdg"+usr_product+""+usr_unit);

                Call<AddProfile> call=apiInterface.response_AddProfile(usr_name,user_id,usr_email,usr_phoneNumber,usr_pinno,usr_age,usr_location,user_token);
                call.enqueue(new Callback<AddProfile>() {
                    @Override
                    public void onResponse(Call<AddProfile> call, Response<AddProfile> response) {
                        System.out.println(response.body().getStatus());
                        if (response.body().getStatus().equals("success")){
                            Profile.btnAddAddress.setVisibility(View.VISIBLE);
                            startActivity(new Intent(getActivity(), Dashbord.class));
                        }else {
                            Toast.makeText(getActivity(), ""+response.body().getStatus()+"", Toast.LENGTH_SHORT).show();
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
