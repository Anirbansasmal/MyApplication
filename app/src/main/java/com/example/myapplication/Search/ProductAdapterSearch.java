package com.example.myapplication.Search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.productdetail.ProductDetail;

import java.util.ArrayList;
import java.util.TreeMap;

import static com.example.myapplication.dashbord.Dashbord.mypreference;

//import com.example.milchmom.productdetail.ProductDetail;


public class ProductAdapterSearch extends RecyclerView.Adapter<ProductAdapterSearch.MyViewHolder> {
    Context mContext;
    private ArrayList<TreeMap<String,String>> productListFetch;
    private ArrayList<TreeMap<String,ArrayList>> check_pincode=new ArrayList<>();
    String p_details;
    String pin;
    int flag=0;
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

    public ProductAdapterSearch(Context context, ArrayList<TreeMap<String,String>> productList, ArrayList<TreeMap<String,ArrayList>> productList_pincode) {
        this.mContext=context;
        this.productListFetch = productList;
        this.check_pincode=productList_pincode;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {

//for (int i=0;i<productListFetch.size();i++) {

//    final Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), Integer.parseInt(productListFetch.get(position).get("p_img")));
    Glide.with(holder.image.getContext()).load("http://app.milchmom.com:8080/"+productListFetch.get(position).get("p_img")).into(holder.image);
//        holder.image.setImageResource(Integer.parseInt(String.valueOf(bitmap)));
    holder.name.setText(productListFetch.get(position).get("p_name"));
    holder.price.setText(productListFetch.get(position).get("p_price"));
    holder.discountPrice.setText(productListFetch.get(position).get("p_price"));

//}
        System.out.println(pin);
//        check_pincode.clear();
            holder.productRL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPref = mContext.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                pin = sharedPref.getString("pin", "");
                if (pin.isEmpty()){

//                          for (int i=0;i<productListFetch.get(position).get("pincode").length();i++){
//                        check_pincode=(productListFetch.get(position).get("pincode"));
//
//                        for (int j=0;j<productListFetch.get(i).get("pincode").length();j++){
//                            if (productListFetch.get(j).get("pincode").equals(Dashbord.pin)){
//                                System.out.println("productListFetch"+productListFetch.get(position).get("pincode").length());
//                            }
//                        }

                    Toast.makeText(mContext,"Select Any pin",Toast.LENGTH_SHORT).show();

                }else {
                    System.out.println("check_pincode"+check_pincode);
//                    check_pincode.add(productListFetch.get(position).get("pincode"));
                    Intent intent = new Intent(mContext, ProductDetail.class);
                    intent.putExtra("image_url", productListFetch.get(position).get("p_img"));
                    intent.putExtra("p_id",productListFetch.get(position).get("p_id"));
                    intent.putExtra("p_details",productListFetch.get(position).get("p_details"));
                    intent.putExtra("p_name",productListFetch.get(position).get("p_name"));
                    intent.putExtra("pincode",check_pincode.get(position).get("pincode"));
                    mContext.startActivity(intent);

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
                    mContext.startActivity(intent);


                }
            }
        });

//}
    }

    public int getItemCount() {
        return this.productListFetch.size();
    }
}
