package com.example.myapplication.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.checkout.Checkout;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.productdetail.AddCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.dashbord.Dashbord.user_id;
import static com.example.myapplication.dashbord.Dashbord.user_token;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private ArrayList<TreeMap<String,String>> cartGetSetList;
    Map<String,String> itemCount=new HashMap<String, String>();
    public static TreeMap<String, String> product_cart = new TreeMap<String, String>();
    ArrayList<Float> total_value=new ArrayList<Float>();
    ArrayList<Float> total_valuegst=new ArrayList<Float>();
    ArrayList<Float> total_valuedisc=new ArrayList<Float>();
    ArrayList<Float> total_value_unique=new ArrayList<Float>();
    ArrayList<Float> total_value_gstunique=new ArrayList<Float>();
    ArrayList<Float> total_value_discunique=new ArrayList<Float>();
    int count=0,dcr=0;
    float total_amt=0;
    float p_discount_total=0,product_amt_gst=0;
    float payment_amount;
    float p_discount_total_upd=0;
    float product_amt_gst_upd=0;
    float payment_amount_upd=0;
    float payment_amount_total_upd=0;
    float dayorderfreq=0;
    float p_price;
    float p_GST;
    float p_discount;
    float p_count;
int positem=0;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public TextView orderQuantity;
        public TextView priceperunit;
        public TextView productName,deltimeSlot;
        public TextView totalproductprice,btn_delete;
        public Button btn_incr,btn_dcr;
        public MyViewHolder(View view) {
            super(view);
            this.productName = view.findViewById(R.id.productName);
            this.orderQuantity = view.findViewById(R.id.orderQuantity);
            this.priceperunit = view.findViewById(R.id.priceperunit);
            this.totalproductprice = view.findViewById(R.id.totalproductprice);
            this.deltimeSlot = view.findViewById(R.id.deltimeSlot);
            this.image = view.findViewById(R.id.productImage);
            this.btn_incr=view.findViewById(R.id.btn_incr);
            this.btn_dcr=view.findViewById(R.id.btn_dcr);
            this.btn_delete=view.findViewById(R.id.btn_delete);
        }
    }

    public CartAdapter(Context context,ArrayList<TreeMap<String,String>> cartGetSetList1) {
        this.context=context;
        this.cartGetSetList = cartGetSetList1;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        CartGetSet cartGetSet = this.cartGetSetList.get(position);
        Glide.with(holder.image.getContext())
                .load("http://app.milchmom.com:8080/"+cartGetSetList.get(position).get("p_img")).into(holder.image);
//        holder.image.setImageResource(cartGetSetList.get(position).get(""));
        holder.productName.setText(cartGetSetList.get(position).get("p_name"));
        holder.orderQuantity.setText(cartGetSetList.get(position).get("product_qty"));
        holder.priceperunit.setText(cartGetSetList.get(position).get("p_unit"));
        holder.totalproductprice.setText("INR: "+cartGetSetList.get(position).get("payment_amount"));
        holder.deltimeSlot.setText(cartGetSetList.get(position).get("time_slot"));

//        System.out.println("payment_amountasdsadsad"+cartGetSetList.size());
        for (int i=0;i<cartGetSetList.size();i++) {
            payment_amount = Float.parseFloat((((cartGetSetList.get(position).get("payment_amount").toString()))));
//            p_discount_total = Float.parseFloat((((cartGetSetList.get(position).get("p_discount_total").toString()))));
            product_amt_gst = Float.parseFloat((((cartGetSetList.get(position).get("product_amt_gst").toString()))));

            total_value.add(payment_amount);
            total_valuegst.add(product_amt_gst);
//            total_valuedisc.add(p_discount_total);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                total_value_unique = (ArrayList) total_value.stream().distinct().collect(Collectors.toList());
                total_value_gstunique = (ArrayList) total_valuegst.stream().distinct().collect(Collectors.toList());
//                total_value_discunique = (ArrayList) total_valuedisc.stream().distinct().collect(Collectors.toList());
//                System.out.println("total_value_unique"+total_value_unique);
            }
        }
//            if ()
        for (int i=0;i<total_value_unique.size();i++){
            total_amt= (total_amt+ (total_value_unique.get(i))+(total_value_gstunique.get(i)));

        }
//        Cart.cartTotal.setText(""+total_amt);
        System.out.println("payment_amountasdsadsad"+total_amt+" "+total_value_unique+" "+p_discount_total+" "+p_discount_total);
        final MyCustomObject myCustomObject=new MyCustomObject(new MyCustomObject.MyCustomObjectListener() {
            @Override
            public void onadd(int index, int val) {

            }

            @Override
            public void onsub(int index, int val) {

            }

            @Override
            public void onDelete(int index) {

            }
        });

        final Cart parent=new Cart();

        holder.btn_incr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                count++;
                total_value_unique.clear();
                total_value_gstunique.clear();
                total_value.clear();
                total_valuegst.clear();
                positem=Integer.parseInt(holder.orderQuantity.getText().toString())+1;
                dayorderfreq= Float.parseFloat(cartGetSetList.get(position).get("order_frequency_count").toString());
                p_price=Float.parseFloat(cartGetSetList.get(position).get("p_price").toString());
                p_GST=Float.parseFloat(cartGetSetList.get(position).get("p_Gst").toString());
                p_discount=Float.parseFloat(cartGetSetList.get(position).get("p_discount").toString());

                product_amt_gst_upd=((dayorderfreq*positem)*((p_price*(p_GST))/100));
                System.out.println("iojfdsfjsdjfo"+p_GST);

                payment_amount_upd=product_amt_gst_upd+(dayorderfreq*positem)*((p_price));
                p_discount_total_upd=((payment_amount_upd*(p_discount))/100);
                payment_amount_total_upd=payment_amount_upd-p_discount_total_upd;
                p_count=dayorderfreq*positem;
//cartGetSetList.set(position,product_cart);
                holder.orderQuantity.setText(""+positem);
                holder.totalproductprice.setText(""+payment_amount_total_upd);
//                parent.incr_decr();
//                myCustomObject.add(position, Integer.parseInt(cartGetSetList.get(position).get("product_qty"))+1);
                pos(positem,
                        cartGetSetList.get(position).get("id"),
                        Integer.parseInt(cartGetSetList.get(position).get("p_price")),payment_amount_total_upd,product_amt_gst_upd,p_count,p_discount_total_upd);
//                count=0;
//                Cart.cartTotal.setText(""+total_amt);
                product_cart=product_select_cart_update(cartGetSetList.get(position).get("id"),
                        cartGetSetList.get(position).get("p_name"),
                        cartGetSetList.get(position).get("p_img"),
                        String.valueOf(p_count),
                        String.valueOf(payment_amount_total_upd),
                        cartGetSetList.get(position).get("user_id"),
                        cartGetSetList.get(position).get("product_id"),
                        cartGetSetList.get(position).get("p_unit"),
                        cartGetSetList.get(position).get("p_Gst"),
                        cartGetSetList.get(position).get("p_price"),
                        cartGetSetList.get(position).get("order_frequency_count"),
                        cartGetSetList.get(position).get("p_discount"),
                        String.valueOf(product_amt_gst_upd));

                cartGetSetList.set(position,product_cart);

System.out.println("cartGetSetList"+cartGetSetList);
                for (int i=0;i<cartGetSetList.size();i++) {
                    payment_amount = Float.parseFloat((((cartGetSetList.get(i).get("payment_amount").toString()))));
//            p_discount_total = Float.parseFloat((((cartGetSetList.get(position).get("p_discount_total").toString()))));
                    product_amt_gst = Float.parseFloat((((cartGetSetList.get(i).get("product_amt_gst").toString()))));

                    total_value.add(payment_amount);
                    total_valuegst.add(product_amt_gst);
//            total_valuedisc.add(p_discount_total);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        total_value_unique = (ArrayList) total_value.stream().distinct().collect(Collectors.toList());
                        total_value_gstunique = (ArrayList) total_valuegst.stream().distinct().collect(Collectors.toList());
//                total_value_discunique = (ArrayList) total_valuedisc.stream().distinct().collect(Collectors.toList());
                        System.out.println("total_value_unique"+total_value_unique+" "+total_value_gstunique);
                    }
                }
//            if ()
                for (int i=0;i<total_value_unique.size();i++){
                    total_amt= ((total_value_unique.get(i))+(total_value_gstunique.get(i)));

                }
//                Cart.cartTotal.setText(""+total_amt);
//            }
            }
        });
        holder.btn_dcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_value_unique.clear();
                total_value_gstunique.clear();
                total_value.clear();
                total_valuegst.clear();
                dcr=Integer.parseInt(holder.orderQuantity.getText().toString())-1;
                if (dcr < 1) {
//                    myCustomObject.subtract(position, 0);
                } else {
                    dcr=Integer.parseInt(holder.orderQuantity.getText().toString())-1;

                    dayorderfreq= Float.parseFloat(cartGetSetList.get(position).get("order_frequency_count").toString());
                    p_price=Float.parseFloat(cartGetSetList.get(position).get("p_price").toString());
                    p_GST=Float.parseFloat(cartGetSetList.get(position).get("p_Gst").toString());
                    p_discount=Float.parseFloat(cartGetSetList.get(position).get("p_discount").toString());

                    product_amt_gst_upd=((dayorderfreq*dcr)*((p_price*(p_GST))/100));
                    System.out.println("iojfdsfjsdjfo"+p_GST);

                    payment_amount_upd=product_amt_gst_upd+(dayorderfreq*dcr)*((p_price));
                    p_discount_total_upd=((payment_amount_upd*(p_discount))/100);
                    payment_amount_total_upd=payment_amount_upd-p_discount_total_upd;
                    p_count=dayorderfreq*dcr;

                    holder.orderQuantity.setText(""+dcr);
                    holder.totalproductprice.setText(""+payment_amount_total_upd);
                    pos(dcr,cartGetSetList.get(position).get("id"),
                    Integer.parseInt(cartGetSetList.get(position).get("p_price")),payment_amount_total_upd,product_amt_gst_upd,p_count,p_discount_total_upd);
                    product_cart=product_select_cart_update(cartGetSetList.get(position).get("id"),
                            cartGetSetList.get(position).get("p_name"),
                            cartGetSetList.get(position).get("p_img"),
                            String.valueOf(p_count),
                            String.valueOf(payment_amount_total_upd),
                            cartGetSetList.get(position).get("user_id"),
                            cartGetSetList.get(position).get("product_id"),
                            cartGetSetList.get(position).get("p_unit"),
                            cartGetSetList.get(position).get("p_Gst"),
                            cartGetSetList.get(position).get("p_price"),
                            cartGetSetList.get(position).get("order_frequency_count"),
                            cartGetSetList.get(position).get("p_discount"),
                            String.valueOf(product_amt_gst_upd));

                    cartGetSetList.set(position,product_cart);


                    for (int i=0;i<cartGetSetList.size();i++) {
                        payment_amount = Float.parseFloat((((cartGetSetList.get(i).get("payment_amount").toString()))));
//            p_discount_total = Float.parseFloat((((cartGetSetList.get(position).get("p_discount_total").toString()))));
                        product_amt_gst = Float.parseFloat((((cartGetSetList.get(i).get("product_amt_gst").toString()))));

                        total_value.add(payment_amount);
                        total_valuegst.add(product_amt_gst);
//            total_valuedisc.add(p_discount_total);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            total_value_unique = (ArrayList) total_value.stream().distinct().collect(Collectors.toList());
                            total_value_gstunique = (ArrayList) total_valuegst.stream().distinct().collect(Collectors.toList());
//                total_value_discunique = (ArrayList) total_valuedisc.stream().distinct().collect(Collectors.toList());
                            System.out.println("total_value_unique"+total_value_unique);
                        }
                    }
//            if ()
                    for (int i=0;i<total_value_unique.size();i++){
                        total_amt= ((total_value_unique.get(i))+(total_value_gstunique.get(i)));

                    }
//                    Cart.cartTotal.setText(""+total_amt);
                }
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parent.incr_decr();
//                cartGetSetList.remove(position);
                myCustomObject.detele(position);

            }
        });

    }
    ApiInterface apiInterface;
    public void pos(int p_count,String cart_id,int p_price,float payment_amount_upd,float product_amt_gst_upd,float p_count_total,float p_discount_total_upd){
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<AddCart> call = apiInterface.response_EditFromCart(String.valueOf(p_count),user_id,cart_id, String.valueOf(p_price)
                ,String.valueOf(payment_amount_upd),String.valueOf(product_amt_gst_upd),String.valueOf(p_count_total),String.valueOf(p_discount_total_upd),user_token);
        call.enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {

            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {

            }
        });
    }
    public static float sum(List<Float> list)
    {
        // iterator for accessing the elements
        Iterator<Float> it = list.iterator();

        float res = 0;
        while (it.hasNext()) {
            float num = it.next();

            // adding the elements greater than 5
            if (num >= 0) {
                res += num;
            }
        }

        return res;
    }
    public static TreeMap<String, String> product_select_cart_update(String id, String p_name, String p_img,
                                                                     String product_qty,String payment_amount,String user_id,
                                                                     String product_id,String p_unit,String p_Gst,String p_price,String order_frequency_count,String p_discount,String product_amt_gst) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("id", String.valueOf(id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_img",p_img);
        treeMap.put("product_qty",product_qty);
        treeMap.put("payment_amount",payment_amount);
        treeMap.put("user_id",user_id);
        treeMap.put("product_id",product_id);
        treeMap.put("p_unit",p_unit);
        treeMap.put("p_Gst",p_Gst);
        treeMap.put("p_price",p_price);
        treeMap.put("order_frequency_count",order_frequency_count);
        treeMap.put("p_discount",p_discount);
        treeMap.put("product_amt_gst",product_amt_gst);
        //
        return treeMap;
    }

public Map<String,String> item(){
        return this.itemCount;
}
    public int getItemCount() {
//        if (cartGetSetList.size()<0){
//            Intent intent=new Intent(context.getApplicationContext(), Dashbord.class);
//            context.startActivity(intent);
//        }
        return this.cartGetSetList.size();
    }
}
