package com.example.myapplication.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CartAdaptertimeslot extends RecyclerView.Adapter<CartAdaptertimeslot.MyViewHolder> {
    private ArrayList<TreeMap<String,String>> cartGetSetList;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public TextView timeslot;
//        public TextView priceperunit;
//        public TextView productName;
//        public TextView totalproductprice;

        public MyViewHolder(View view) {
            super(view);
            this.timeslot = (TextView) view.findViewById(R.id.timeslot);
//            this.orderQuantity = (TextView) view.findViewById(R.id.orderQuantity);
//            this.priceperunit = (TextView) view.findViewById(R.id.priceperunit);
//            this.totalproductprice = (TextView) view.findViewById(R.id.totalproductprice);
//            this.image = (ImageView) view.findViewById(R.id.productImage);
        }
    }

    public CartAdaptertimeslot(Context context,ArrayList<TreeMap<String,String>> cartGetSetList1) {
        this.cartGetSetList = cartGetSetList1;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row_delivery, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
//        CartGetSet cartGetSet = this.cartGetSetList.get(position);
        holder.timeslot.setText(cartGetSetList.get(position).get("time_slot"));
//        holder.productName.setText(cartGetSet.getProductName());
//        holder.orderQuantity.setText(cartGetSet.getOrderQuantity());
//        holder.priceperunit.setText(cartGetSet.getPriceperunit());
//        holder.totalproductprice.setText(cartGetSet.getTotalproductprice());
    }

    public int getItemCount() {
        return this.cartGetSetList.size();
    }
}
