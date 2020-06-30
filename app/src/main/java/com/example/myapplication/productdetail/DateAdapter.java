package com.example.myapplication.productdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.MyViewHolder> {
    private ArrayList<String> Date;

    public DateAdapter(ArrayList<String> DATE) {
        this.Date = DATE;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_date_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_customdate.setText(this.Date.get(position));
    }

    public int getItemCount() {
        return this.Date.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_customdate;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_customdate = (TextView) itemView.findViewById(R.id.tv_customdate);
        }
    }
}
