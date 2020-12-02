package com.example.myapplication.orderhistory.oldorderfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.orderhistory.activeorderfragment.ActiveOrderFragment;

import java.util.ArrayList;
import java.util.TreeMap;

public class DeliverOrderAdapter extends RecyclerView.Adapter<DeliverOrderAdapter.MyViewHolder> {
//    private List<ActiveOrderGetSet> activeOrderGetSetList;
    ArrayList<TreeMap<String,String>> activeOrderGetSetList=new ArrayList<TreeMap<String, String>>();
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView orderID;
        public TextView orderProduct;
        public TextView orderProductDesc;
        public TextView orderStatus;
        public TextView orderProductRemQty;
        public TextView orderProductsRemAmt;
        public TextView orderProductsFreq;
        public TextView orderProductsAddress;
        public MyViewHolder(View view) {
            super(view);
            this.orderID = (TextView) view.findViewById(R.id.orderId);
            this.orderProduct = (TextView) view.findViewById(R.id.orderProducts);
            this.orderProductDesc = (TextView) view.findViewById(R.id.orderProductsDesc);
            this.orderStatus = (TextView) view.findViewById(R.id.orderStatus);
            this.orderProductRemQty=(TextView) view.findViewById(R.id.orderProductRemQty);
            this.orderProductsRemAmt=(TextView) view.findViewById(R.id.orderProductsRemAmt);
            this.orderProductsFreq=(TextView) view.findViewById(R.id.orderProductsFreq);
            this.orderProductsAddress=(TextView) view.findViewById(R.id.orderProductsAddress);
        }
    }

    public DeliverOrderAdapter(Context context, ArrayList<TreeMap<String,String>> activeOrderGetSetList1) {
        this.activeOrderGetSetList = activeOrderGetSetList1;
        this.context=context;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delivered_order_row, parent, false);

//        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.active_order_row, parent, false));
        MyViewHolder vh = new MyViewHolder(view);
        view.setOnClickListener(OldOrderFragment.myOnClickListener);
        return vh;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
//        ActiveOrderGetSet activeOrderGetSet = this.activeOrderGetSetList.get(position);
        holder.orderID.setText(activeOrderGetSetList.get(position).get("Order_id"));
        holder.orderStatus.setText("delivered");
        holder.orderProduct.setText(activeOrderGetSetList.get(position).get("p_name"));
        holder.orderProductDesc.setText(activeOrderGetSetList.get(position).get("recvamt"));
        holder.orderProductDesc.setText(activeOrderGetSetList.get(position).get("product_amt_total"));
        holder.orderProductDesc.setText(activeOrderGetSetList.get(position).get("recive_date"));
        holder.orderProductDesc.setText(activeOrderGetSetList.get(position).get("remaning_qty"));
        holder.orderProductDesc.setText(activeOrderGetSetList.get(position).get("payment_type"));
    }

    public int getItemCount() {
        return this.activeOrderGetSetList.size();
    }
}
