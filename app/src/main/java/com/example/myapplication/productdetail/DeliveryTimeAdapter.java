package com.example.myapplication.productdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DeliveryTimeAdapter extends RecyclerView.Adapter<DeliveryTimeAdapter.MyViewHolder> {
    private ArrayList<String> deliiveryTimeGetSetList;
    String slottime="";
    ArrayList<TreeMap<String,String>> user_product_unit=new ArrayList<TreeMap<String, String>>();
    int delivery_index = -1;
    public int[] mColors = {R.drawable.custom_deliverytime_1, R.drawable.custom_deliverytime_2, R.drawable.custom_deliverytime_3};

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView deliveryTime;
        CardView delivery_time_cv;

        public MyViewHolder(View view) {
            super(view);
            this.deliveryTime = (TextView) view.findViewById(R.id.delivery_time);
            this.delivery_time_cv = (CardView) view.findViewById(R.id.delivery_time_cv);
        }
    }

    public DeliveryTimeAdapter(ArrayList<TreeMap<String,String>> user_product_unit) {
        this.user_product_unit =user_product_unit;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_time_row, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        for (int i=0;i<user_product_unit.size();i++){
System.out.println(user_product_unit.size());
            holder.deliveryTime.setText(this.user_product_unit.get(position).get("DeliTimeSlot"));

            holder.delivery_time_cv.setBackgroundResource(this.mColors[position % 3]);
            holder.deliveryTime.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DeliveryTimeAdapter deliveryTimeAdapter = DeliveryTimeAdapter.this;
                    slottime=holder.deliveryTime.getText().toString();
//                    slottime=holder.deliveryTime.getText().toString();
                    deliveryTimeAdapter.delivery_index = position;
                    deliveryTimeAdapter.notifyDataSetChanged();
                }
            });
            if (this.delivery_index == position) {
                holder.deliveryTime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ticgreen, 0, 0, 0);
            } else {
                holder.deliveryTime.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
    }
    public String delitimeslot(){
     return this.slottime;
    }
    public int getItemCount() {
        return this.user_product_unit.size();
    }
}
