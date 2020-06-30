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

public class CartAdapterAddress extends RecyclerView.Adapter<CartAdapterAddress.MyViewHolder> {
    private ArrayList<TreeMap<String,String>> cartGetSetList;
Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public TextView address;
//        public TextView priceperunit;
//        public TextView productName;
//        public TextView totalproductprice;

        public MyViewHolder(View view) {
            super(view);
            this.address = (TextView) view.findViewById(R.id.address);
//            this.orderQuantity = (TextView) view.findViewById(R.id.orderQuantity);
//            this.priceperunit = (TextView) view.findViewById(R.id.priceperunit);
//            this.totalproductprice = (TextView) view.findViewById(R.id.totalproductprice);
//            this.image = (ImageView) view.findViewById(R.id.productImage);
        }
    }

    public CartAdapterAddress(Context context,ArrayList<TreeMap<String,String>> cartGetSetList1) {
        this.context=context;
        this.cartGetSetList = cartGetSetList1;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row_address, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
//        CartGetSet cartGetSet = this.cartGetSetList.get(position);
        holder.address.setText(cartGetSetList.get(position).get("user_address"));
//        holder.productName.setText(cartGetSet.getProductName());
//        holder.orderQuantity.setText(cartGetSet.getOrderQuantity());
//        holder.priceperunit.setText(cartGetSet.getPriceperunit());
//        holder.totalproductprice.setText(cartGetSet.getTotalproductprice());
    }

    public int getItemCount() {
        return this.cartGetSetList.size();
    }
}
