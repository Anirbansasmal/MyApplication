package com.example.myapplication.dashbord;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.productdetail.ProductDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class PopularProductAdapterDemo extends RecyclerView.Adapter<PopularProductAdapterDemo.MyViewHolder> {
    public String[] mColors = {"#F6D85C", "#4BB1F3"};
    public String[] mColorstext = {"#000000", "#ffffff"};
    private ArrayList<TreeMap<String,String>> popularProductGetSetList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public TextView ppdesc;
        ImageView ppimage;
        LinearLayout ppll;
        public TextView ppname;
        public TextView ppprice;
        public TextView ppquantity;

        public MyViewHolder(View view) {
            super(view);
            this.ppimage = (ImageView) view.findViewById(R.id.pPImage);
            this.ppname = (TextView) view.findViewById(R.id.pPName);
            this.ppprice = (TextView) view.findViewById(R.id.pPPrice);
            this.ppdesc = (TextView) view.findViewById(R.id.pPDescription);
            this.ppquantity = (TextView) view.findViewById(R.id.pPQuantity);
            this.ppll = (LinearLayout) view.findViewById(R.id.ppll);
        }
    }

    public PopularProductAdapterDemo(Context context,ArrayList<TreeMap<String,String>> popularProductList) {
        this.context=context;
        this.popularProductGetSetList = popularProductList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_product_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        final PopularProductGetSet popularProductGetSet = this.popularProductGetSetList.get(position);
        Glide.with(holder.ppimage.getContext()).load(popularProductGetSetList.get(position).get("p_img")).into(holder.ppimage);
//        holder.ppimage.setImageResource(popularProductGetSetList.get(position).get(""));
        holder.ppname.setText(popularProductGetSetList.get(position).get("p_name"));
        holder.ppprice.setText(popularProductGetSetList.get(position).get("p_price"));
        holder.ppquantity.setText(popularProductGetSetList.get(position).get("ProductQty"));
        holder.ppdesc.setText(popularProductGetSetList.get(position).get("p_details"));
        System.out.println("popularProductGetSetList"+popularProductGetSetList);
        holder.ppname.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
        holder.ppprice.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
        holder.ppquantity.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
        holder.ppdesc.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
        holder.ppll.setBackgroundColor(Color.parseColor(this.mColors[position % 2]));
        holder.ppll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Dashbord.pin!=null) {
                    Intent intent = new Intent(v.getContext(), ProductDetail.class);
                    intent.putExtra("image_url", popularProductGetSetList.get(position).get("p_img"));
                    intent.putExtra("p_id",popularProductGetSetList.get(position).get("p_id"));
                    intent.putExtra("p_details",popularProductGetSetList.get(position).get("p_details"));
                    intent.putExtra("p_name",popularProductGetSetList.get(position).get("p_name"));
                    intent.putStringArrayListExtra("pincode", (ArrayList<String>) Arrays.asList(popularProductGetSetList.get(position).get("pincode")));
                    System.out.println("pincode"+(ArrayList<String>) Arrays.asList(popularProductGetSetList.get(position).get("pincode")));
                    v.getContext().startActivity(intent);
                }else {
                    Toast.makeText(context,"Select Any pin",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int getRandomColorCode() {
        Random random = new Random();
        return Color.argb(20, random.nextInt(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION), random.nextInt(180), random.nextInt(276));
    }

    public int getItemCount() {
        return popularProductGetSetList.size();
    }
}
