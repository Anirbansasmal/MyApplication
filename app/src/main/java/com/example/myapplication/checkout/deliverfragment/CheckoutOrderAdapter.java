package com.example.myapplication.checkout.deliverfragment;

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

public class CheckoutOrderAdapter extends RecyclerView.Adapter<CheckoutOrderAdapter.MyViewHolder> {
//    private List<CheckoutOrderGetSet> checkoutOrderGetSetList;
    ArrayList<TreeMap<String,String>> checkoutOrderGetSetList=new ArrayList<TreeMap<String, String>>();
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productPrice;
        public TextView productQty;

        public MyViewHolder(View view) {
            super(view);
            this.productName = (TextView) view.findViewById(R.id.product_name);
            this.productQty = (TextView) view.findViewById(R.id.product_qty);
            this.productPrice = (TextView) view.findViewById(R.id.product_price);
        }
    }

    public CheckoutOrderAdapter(Context context,ArrayList<TreeMap<String,String>> checkoutOrderGetSetList1) {
        this.context=context;
        this.checkoutOrderGetSetList = checkoutOrderGetSetList1;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_order_detail_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        for (int i=0;i<checkoutOrderGetSetList.size();i++){
            System.out.println("hgdfjhbdjsvbj"+checkoutOrderGetSetList);
            holder.productName.setText(""+checkoutOrderGetSetList.get(position).get("ProductName"));
            holder.productQty.setText(""+checkoutOrderGetSetList.get(position).get("ProductQty"));
            holder.productPrice.setText(""+checkoutOrderGetSetList.get(position).get("p_price"));
        }
    }
    public int getItemCount() {
        return this.checkoutOrderGetSetList.size();
    }
}
