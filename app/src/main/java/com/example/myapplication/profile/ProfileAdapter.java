package com.example.myapplication.profile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.productdetail.ProductDetail;

import java.util.ArrayList;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.profile.Profile.user_token;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {
    private ArrayList<String> Date;
    ArrayList<TreeMap<String,String>> user_profile=new ArrayList<TreeMap<String, String>>();
    Context context;
    ApiInterface apiInterface;

    public ProfileAdapter(Context context,ArrayList<TreeMap<String,String>> user_profile) {
        this.context=context;
        this.user_profile=user_profile;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_address_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        for (int i=0;i<user_profile.size();i++){
            holder.tv_address_view.setText(user_profile.get(position).get("UserAddress"));
            holder.tv_address_heading.setText("Address "+""+(position+1));
            holder.tv_edit_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Edit.class);
                    intent.putExtra("u_id",user_profile.get(position).get("u_id"));
                    intent.putExtra("UserName",user_profile.get(position).get("UserName"));
                    intent.putExtra("email",user_profile.get(position).get("email"));
                    intent.putExtra("UserAddress",user_profile.get(position).get("UserAddress"));
                    intent.putExtra("UserPin",user_profile.get(position).get("UserPin"));
                    intent.putExtra("age",user_profile.get(position).get("age"));
                    intent.putExtra("_id",user_profile.get(position).get("_id"));
                    intent.putExtra("UserPhone",user_profile.get(position).get("UserPhone"));
                    v.getContext().startActivity(intent);
                }
            });
            holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    profile_delete(user_profile.get(position).get("_id"),position);
                }
            });
        }
    }

    public void profile_delete(String user_id, final int position){
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<View_Profile> call = apiInterface.response_ProfileDelete(user_token,user_id);
        call.enqueue(new Callback<View_Profile>() {
            @Override
            public void onResponse(Call<View_Profile> call, Response<View_Profile> response) {
                user_profile.remove(position);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<View_Profile> call, Throwable t) {

            }
        });

    }

    public int getItemCount() {
        return this.user_profile.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_edit_address,tv_address_view,tv_address_heading,btn_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_edit_address = (TextView) itemView.findViewById(R.id.edit_address);
            this.tv_address_view = (TextView) itemView.findViewById(R.id.address_view);
            this.tv_address_heading=itemView.findViewById(R.id.address_heading);
            this.btn_delete=itemView.findViewById(R.id.btn_delete);
        }
    }
}
