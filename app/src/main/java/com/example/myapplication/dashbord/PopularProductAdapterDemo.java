package com.example.myapplication.dashbord;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.example.myapplication.dashbord.Dashbord.mypreference;

public class PopularProductAdapterDemo extends RecyclerView.Adapter<PopularProductAdapterDemo.MyViewHolder> {
    public String[] mColors = {"#F6D85C", "#4BB1F3"};
    public String[] mColorstext = {"#000000", "#ffffff"};
    private ArrayList<TreeMap<String,String>> popularProductGetSetList;
    private ArrayList<TreeMap<String,ArrayList>> check_pincode=new ArrayList<>();
    Context context;
    String pin;
int flag=0;
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

    public PopularProductAdapterDemo(Context context,ArrayList<TreeMap<String,String>> popularProductList,ArrayList<TreeMap<String,ArrayList>> productList_pincode) {
        this.context=context;
        this.popularProductGetSetList = popularProductList;
        this.check_pincode=productList_pincode;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_product_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        final PopularProductGetSet popularProductGetSet = this.popularProductGetSetList.get(position);
        Glide.with(holder.ppimage.getContext()).load("http://app.milchmom.com:8080/"+popularProductGetSetList.get(position).get("p_img")).into(holder.ppimage);
//        holder.ppimage.setImageResource(popularProductGetSetList.get(position).get(""));
        holder.ppname.setText(popularProductGetSetList.get(position).get("p_name"));
        holder.ppprice.setText("");
        holder.ppquantity.setText("");
        holder.ppdesc.setText(popularProductGetSetList.get(position).get("p_details"));
        System.out.println("popularProductGetSetList"+popularProductGetSetList);
        holder.ppname.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
        holder.ppprice.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
        holder.ppquantity.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
        holder.ppdesc.setTextColor(Color.parseColor(this.mColorstext[position % 2]));
        holder.ppll.setBackgroundColor(Color.parseColor(this.mColors[position % 2]));

        holder.ppll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPref = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                pin = sharedPref.getString("pin", "");
                if (pin.isEmpty()) {
                    Toast.makeText(context,"Select Any pin",Toast.LENGTH_SHORT).show();
//                    this.finish();
                }else {
                    Intent intent = new Intent(v.getContext(), ProductDetail.class);
                    intent.putExtra("image_url", popularProductGetSetList.get(position).get("p_img"));
                    intent.putExtra("p_id",popularProductGetSetList.get(position).get("p_id"));
                    intent.putExtra("p_details",popularProductGetSetList.get(position).get("p_details"));
                    intent.putExtra("p_name",popularProductGetSetList.get(position).get("p_name"));
                    intent.putStringArrayListExtra("pincode", check_pincode.get(position).get("pincode"));
                    intent.putExtra("p_type",popularProductGetSetList.get(position).get("p_type"));
                    for (int i=0;i<check_pincode.get(position).get("pincode").size();i++){
                        if (check_pincode.get(position).get("pincode").get(i).equals(pin)){
                            flag=1;
                            if (flag==1){
                                intent.putExtra("pincodeChek",pin);
                                System.out.println("pincodeChek"+pin);
                                break;
                            }else {

                            }
                        }else {
                            intent.putExtra("pincodeChek","0");
                            System.out.println("pincodeChek"+pin);
                        }
                    }
//                    System.out.println("pincode"+(ArrayList<String>) Arrays.asList(popularProductGetSetList.get(position).get("pincode")));
                    v.getContext().startActivity(intent);

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
