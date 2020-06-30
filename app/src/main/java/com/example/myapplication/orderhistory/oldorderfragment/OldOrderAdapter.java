package com.example.myapplication.orderhistory.oldorderfragment;

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

public class OldOrderAdapter extends RecyclerView.Adapter<OldOrderAdapter.MyViewHolder> {
    private ArrayList<TreeMap<String,String>> oldOrderGetSetList=new ArrayList<TreeMap<String, String>>();
Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView orderID;
        public TextView orderPlacedDate;
        public TextView orderProduct;
        public TextView orderProductDesc;
        public TextView orderStatus;

        public MyViewHolder(View view) {
            super(view);
            this.orderID = (TextView) view.findViewById(R.id.orderId);
            this.orderProduct = (TextView) view.findViewById(R.id.orderProducts);
            this.orderProductDesc = (TextView) view.findViewById(R.id.orderProductsDesc);
            this.orderStatus = (TextView) view.findViewById(R.id.orderStatus);
            this.orderPlacedDate = (TextView) view.findViewById(R.id.orderPlacedDate);
        }
    }

    public OldOrderAdapter(Context context,ArrayList<TreeMap<String,String>> oldOrderGetSetList1) {
        this.oldOrderGetSetList = oldOrderGetSetList1;
        this.context=context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.old_order_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
//        OldOrderGetSet oldOrderGetSet = this.oldOrderGetSetList.get(position);
        holder.orderID.setText(oldOrderGetSetList.get(position).get("Order_id"));
        holder.orderStatus.setText("Delivered");
        holder.orderProduct.setText(oldOrderGetSetList.get(position).get("product_qty"));
        holder.orderProductDesc.setText(oldOrderGetSetList.get(position).get("delivery_time"));
        holder.orderPlacedDate.setText(oldOrderGetSetList.get(position).get("delivery_date"));
    }

    public int getItemCount() {
        return this.oldOrderGetSetList.size();
    }
}
