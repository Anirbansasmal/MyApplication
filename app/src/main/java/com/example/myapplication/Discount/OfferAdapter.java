package com.example.myapplication.Discount;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.productdetail.ProductDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.dashbord.Dashbord.user_id;
import static com.example.myapplication.dashbord.Dashbord.user_token;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder> {
    public String[] mColors = {"#F6D85C", "#4BB1F3"};
    public String[] mColorstext = {"#000000", "#ffffff"};
    private ArrayList<TreeMap<String,String>> popularProductGetSetList;
    ApiInterface apiInterface;
//    private ArrayList<TreeMap<String,ArrayList>> popularProductSpcial;
    Context context;
    public static int p_discount_val;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public TextView discount_val;
        ImageView ppimage;
        Button applynow;
        public TextView offerStatus;
        public TextView offer_exp;
        public TextView offer_start;

        public MyViewHolder(View view) {
            super(view);
//            this.ppimage = (ImageView) view.findViewById(R.id.pPImage);
            this.offerStatus = (TextView) view.findViewById(R.id.offerStatus);
            this.offer_exp = (TextView) view.findViewById(R.id.offer_exp);
            this.offer_start = (TextView) view.findViewById(R.id.offer_start);
            this.applynow = (Button) view.findViewById(R.id.applynow);
            this.discount_val = (TextView) view.findViewById(R.id.discount_val);
        }
    }

    public OfferAdapter(Context context, ArrayList<TreeMap<String,String>> popularProductList) {
        this.context=context;
        this.popularProductGetSetList = popularProductList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        final PopularProductGetSet popularProductGetSet = this.popularProductGetSetList.get(position);
//        Glide.with(holder.ppimage.getContext()).load(popularProductGetSetList.get(position).get("p_img")).into(holder.ppimage);
//        holder.ppimage.setImageResource(popularProductGetSetList.get(position).get(""));
        holder.offerStatus.setText(popularProductGetSetList.get(position).get("offer_apply"));
        holder.offer_exp.setText(popularProductGetSetList.get(position).get("offer_start"));
        holder.offer_start.setText(popularProductGetSetList.get(position).get("offer_exp"));
        holder.discount_val.setText(popularProductGetSetList.get(position).get("discount_val"));
        System.out.println("popularProductGetSetListoffer_start"+popularProductGetSetList);
//        holder.offerStatus.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
//        holder.offer_exp.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
//        holder.offer_start.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
//        holder.ppdesc.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
//        holder.ppll.setBackgroundColor(Color.parseColor(this.mColors[position % 2]));

        holder.applynow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (popularProductGetSetList.get(position).get("discount_val").equals(null)){
                    p_discount_val=0;
                    Intent intent=new Intent(context,Dashbord.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }else {
                    p_discount_val= Integer.parseInt(popularProductGetSetList.get(position).get("discount_val"));
                    Intent intent=new Intent(context,Dashbord.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                    Discount(popularProductGetSetList.get(position).get("id"));
                }

            }
        });
    }
    public void Discount(String user_did) {
        System.out.println(user_did);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<Discount_apply> call = apiInterface.response_UserDiscountStatus(user_id,user_token,user_did);
        System.out.println(user_token);
        call.enqueue(new Callback<Discount_apply>() {

            @Override
            public void onResponse(Call<Discount_apply> call, Response<Discount_apply> response) {
                if (response.message().equals("Success")){
                    Intent intent=new Intent(context,Dashbord.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }else {

                }
            }

            @Override
            public void onFailure(Call<Discount_apply> call, Throwable t) {

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
