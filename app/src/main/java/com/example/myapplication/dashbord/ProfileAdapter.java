package com.example.myapplication.dashbord;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.productdetail.WeekAdapter;
import com.example.myapplication.profile.Edit;

import java.util.ArrayList;
import java.util.TreeMap;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {
    private ArrayList<String> Date;
    ArrayList<TreeMap<String,String>> user_profile=new ArrayList<TreeMap<String, String>>();
    String WeeksSelectedArray,WeeksSelectedAddress;
    Context context;
    public ProfileAdapter(Context context, ArrayList<TreeMap<String,String>> user_profile) {
        this.context=context;
        this.user_profile=user_profile;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.changepin_layout_row, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        for (int i=0;i<user_profile.size();i++){
            holder.tv_edit_address.setText(user_profile.get(position).get("UserAddress"));
            holder.tv_address_pin.setText(user_profile.get(position).get("UserPin"));
            holder.tv_address_pin.setSelected(true);
            holder.tv_address_pin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (holder.tv_address_pin.isSelected()) {
                        holder.tv_address_pin.setTextColor(Color.GREEN); //16711936
                        ProfileAdapter.this.WeeksSelectedArray=(holder.tv_address_pin.getText().toString());
                        ProfileAdapter.this.WeeksSelectedAddress=(holder.tv_edit_address.getText().toString());
                        holder.tv_address_pin.setSelected(false);
                    } else {
                        holder.tv_address_pin.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        ProfileAdapter.this.WeeksSelectedArray="";
                        ProfileAdapter.this.WeeksSelectedAddress="";
                        holder.tv_address_pin.setSelected(true);
                    }
                    Log.d("tag", "onClick: " + ProfileAdapter.this.WeeksSelectedArray.toString());
                }
            });
        }
    }
    public String getWeeksSelectedArray() {
        return this.WeeksSelectedArray;
    }
    public String getWeeksSelectedAddress() {
        return this.WeeksSelectedAddress;
    }
    public int getItemCount() {
        return this.user_profile.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_edit_address,tv_address_pin,tv_address_heading;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_edit_address = (TextView) itemView.findViewById(R.id.user_address);
            this.tv_address_pin = (TextView) itemView.findViewById(R.id.user_pin);
//            this.tv_address_heading=itemView.findViewById(R.id.address_heading);
        }
    }
}
