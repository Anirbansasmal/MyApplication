package com.example.myapplication.orderhistory.activeorderfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.TreeMap;

public class ActiveOrderAdapterDetail extends RecyclerView.Adapter<ActiveOrderAdapterDetail.MyViewHolder> {
//    private List<ActiveOrderGetSet> activeOrderGetSetList;
    ArrayList<TreeMap<String,String>> activeOrderGetSetList=new ArrayList<TreeMap<String, String>>();
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView orderID;
        public TextView orderProduct;
//        public TextView orderProductDesc;
        public TextView orderStatus;
        public TextView orderProductRemQty;
        public TextView orderProductsRemAmt;
        public TextView orderProductsFreq;
        public TextView orderProductsAddress;
        public TextView orderProductTotal;
        public TextView orderProductsAmtTotal;
//        public TextView orderStatus;
        public MyViewHolder(View view) {
            super(view);
            this.orderID = (TextView) view.findViewById(R.id.orderId);
            this.orderProduct = (TextView) view.findViewById(R.id.orderProducts);
//            this.orderProductDesc = (TextView) view.findViewById(R.id.orderProductsDesc);
            this.orderStatus = (TextView) view.findViewById(R.id.orderStatus);
            this.orderProductRemQty=(TextView) view.findViewById(R.id.orderProductRemQty);
            this.orderProductsRemAmt=(TextView) view.findViewById(R.id.orderProductsRemAmt);
//            this.orderProductsFreq=(TextView) view.findViewById(R.id.orderProductsFreq);
            this.orderProductsAddress=(TextView) view.findViewById(R.id.orderProductsAddress);
            this.orderProductTotal=(TextView) view.findViewById(R.id.orderProductTotal);
            this.orderProductsAmtTotal=(TextView) view.findViewById(R.id.orderProductsAmtTotal);
        }
    }

    public ActiveOrderAdapterDetail(Context context, ArrayList<TreeMap<String,String>> activeOrderGetSetList1) {
        this.activeOrderGetSetList = activeOrderGetSetList1;
        this.context=context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.active_order_row_details, parent, false);

//        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.active_order_row, parent, false));
        MyViewHolder vh = new MyViewHolder(view);
        view.setOnClickListener(ActiveOrderFragment.myOnClickListener);
        return vh;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
//        ActiveOrderGetSet activeOrderGetSet = this.activeOrderGetSetList.get(position);
        holder.orderID.setText(activeOrderGetSetList.get(position).get("Order_id"));
        holder.orderStatus.setText("Active");
        holder.orderProduct.setText(activeOrderGetSetList.get(position).get("p_name"));
        holder.orderProductRemQty.setText(activeOrderGetSetList.get(position).get("remaning_qty"));
        holder.orderProductsRemAmt.setText(activeOrderGetSetList.get(position).get("remaning_amt"));
//        holder.orderProductsFreq.setText(activeOrderGetSetList.get(position).get("freq"));
        holder.orderProductsAddress.setText(activeOrderGetSetList.get(position).get("address"));
        holder.orderProductTotal.setText(activeOrderGetSetList.get(position).get("p_qty"));
        holder.orderProductsAmtTotal.setText(activeOrderGetSetList.get(position).get("product_amt_total"));
    }

    public int getItemCount() {
        return this.activeOrderGetSetList.size();
    }
}
