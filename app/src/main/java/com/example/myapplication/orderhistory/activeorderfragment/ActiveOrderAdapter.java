package com.example.myapplication.orderhistory.activeorderfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ActiveOrderAdapter extends RecyclerView.Adapter<ActiveOrderAdapter.MyViewHolder> {
//    private List<ActiveOrderGetSet> activeOrderGetSetList;
    ArrayList<TreeMap<String,String>> activeOrderGetSetList=new ArrayList<TreeMap<String, String>>();
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView orderID;
        public TextView orderProduct;
        public TextView orderProductDesc;
        public TextView orderStatus;

        public MyViewHolder(View view) {
            super(view);
            this.orderID = (TextView) view.findViewById(R.id.orderId);
            this.orderProduct = (TextView) view.findViewById(R.id.orderProducts);
            this.orderProductDesc = (TextView) view.findViewById(R.id.orderProductsDesc);
            this.orderStatus = (TextView) view.findViewById(R.id.orderStatus);
        }
    }

    public ActiveOrderAdapter(Context context,ArrayList<TreeMap<String,String>> activeOrderGetSetList1) {
        this.activeOrderGetSetList = activeOrderGetSetList1;
        this.context=context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.active_order_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
//        ActiveOrderGetSet activeOrderGetSet = this.activeOrderGetSetList.get(position);
        holder.orderID.setText(activeOrderGetSetList.get(position).get("Order_id"));
        holder.orderStatus.setText("Active");
        holder.orderProduct.setText(activeOrderGetSetList.get(position).get("remaning_qty"));
        holder.orderProductDesc.setText(activeOrderGetSetList.get(position).get("remaning_amt"));
    }

    public int getItemCount() {
        return this.activeOrderGetSetList.size();
    }
}
