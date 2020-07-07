package com.example.myapplication.dashbord;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

//import com.example.milchmom.productdetail.ProductDetail;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.productdetail.ProductDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;


public class ProductAdapterDemo extends RecyclerView.Adapter<ProductAdapterDemo.MyViewHolder> {
    Context mContext;
    private ArrayList<TreeMap<String,String>> productListFetch;
    private ArrayList<TreeMap<String,ArrayList>> check_pincode=new ArrayList<>();
    String p_details;
//    String check_pincode;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView discount;
        public TextView discountPrice;
        ImageView image;
        public TextView name;
        public TextView price;
        RelativeLayout productRL;

        public MyViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.product_image);
            this.name = (TextView) view.findViewById(R.id.product_name);
            this.discountPrice = (TextView) view.findViewById(R.id.product_discount_price);
            this.price = (TextView) view.findViewById(R.id.product_price);
            this.productRL = (RelativeLayout) view.findViewById(R.id.productRL);
        }
    }

    public ProductAdapterDemo(Context context,ArrayList<TreeMap<String,String>> productList,ArrayList<TreeMap<String,ArrayList>> productList_pincode) {
        this.mContext=context;
        this.productListFetch = productList;
        this.check_pincode=productList_pincode;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {

//for (int i=0;i<productListFetch.size();i++) {

//    final Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), Integer.parseInt(productListFetch.get(position).get("p_img")));
    Glide.with(holder.image.getContext()).load(productListFetch.get(position).get("p_img")).into(holder.image);
//        holder.image.setImageResource(Integer.parseInt(String.valueOf(bitmap)));
    holder.name.setText(productListFetch.get(position).get("p_name"));
    holder.price.setText(productListFetch.get(position).get("p_price"));
    holder.discountPrice.setText(productListFetch.get(position).get("p_price"));
//}
        System.out.println(Dashbord.pin);
//        check_pincode.clear();
            holder.productRL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Dashbord.pin!=null){

//                    for (int i=0;i<productListFetch.get(position).get("pincode").length();i++){
//                        check_pincode=(productListFetch.get(position).get("pincode"));
//
//                        for (int j=0;j<productListFetch.get(i).get("pincode").length();j++){
//                            if (productListFetch.get(j).get("pincode").equals(Dashbord.pin)){
//                                System.out.println("productListFetch"+productListFetch.get(position).get("pincode").length());
//                            }
//                        }

                    System.out.println("check_pincode"+check_pincode);
//                    check_pincode.add(productListFetch.get(position).get("pincode"));
                Intent intent = new Intent(mContext, ProductDetail.class);
                intent.putExtra("image_url", productListFetch.get(position).get("p_img"));
                intent.putExtra("p_id",productListFetch.get(position).get("p_id"));
                intent.putExtra("p_details",productListFetch.get(position).get("p_details"));
                intent.putExtra("p_name",productListFetch.get(position).get("p_name"));
                intent.putExtra("pincode",check_pincode.get(position).get("pincode"));
                    intent.putExtra("p_type",check_pincode.get(position).get("p_type"));
                mContext.startActivity(intent);
                }else {
                    Toast.makeText(mContext,"Select Any pin",Toast.LENGTH_SHORT).show();
                }
            }
        });

//}
    }

    public int getItemCount() {
        return this.productListFetch.size();
    }
}
