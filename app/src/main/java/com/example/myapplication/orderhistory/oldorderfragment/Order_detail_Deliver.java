package com.example.myapplication.orderhistory.oldorderfragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.orderhistory.OrderHistory;
import com.example.myapplication.orderhistory.activeorderfragment.ActiveOrderAdapterDetail;
import com.example.myapplication.orderhistory.activeorderfragment.Order_detail;
import com.example.myapplication.orderhistory.activeorderfragment.User_Order;

import java.util.ArrayList;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.dashbord.Dashbord.user_id;
import static com.example.myapplication.verifyotp.VerifyOtp.user_type;

public class Order_detail_Deliver extends AppCompatActivity {
    public static final String mypreference = "mypref";
    static String user_token,Order_id,user_email,phoneno,user_name,Order_id_cart,token_val,status,user_typeval;
    TreeMap<String, String> activeOrderGetSetList = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> activeOrder=new ArrayList<TreeMap<String, String>>();
    ApiInterface apiInterface;
    RecyclerView rvActiveOrder;
    private DeliverOrderAdapter deliverOrderAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_order_detail_old);
        this.rvActiveOrder = (RecyclerView) findViewById(R.id.rvOldOrder);
        this.rvActiveOrder.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.rvActiveOrder.setItemAnimator(new DefaultItemAnimator());
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Intent intent=getIntent();
//        if (Order_id_product==null){
        Order_id=intent.getStringExtra("Order_id");
        shared();
    }

    public void shared() {
        try {
            SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_type=sharedPref.getString("user_type","");
            user_token="Berear "+token_val;
//            address(Integer.parseInt(pin));
            order_deliver();
//            user_token = token_val;
        } catch (Exception e) {

        }
    }

    public void order_deliver(){
        Call<Order_delivered> call = apiInterface.response_Deliver_order_old_detail(user_id,Order_id,user_token);

        call.enqueue(new Callback<Order_delivered>() {

            @Override
            public void onResponse(Call<Order_delivered> call, Response<Order_delivered> response) {
                for (int i=0;i<response.body().getDelivered().size();i++){
                    System.out.println("ljksdfkljkdfj"+response.body().getDelivered().get(i).getRemaning_qty());
                    if (response.body().getDelivered().get(i).getRemaning_qty().equals("0")){

                    }else {
                        activeOrderGetSetList=Product_select_ordered(
                                response.body().getDelivered().get(i).get_id(),
                                response.body().getDelivered().get(i).getProduct_name(),
                                response.body().getDelivered().get(i).getOrder_id(),
                                response.body().getDelivered().get(i).getRemaning_qty(),
                                response.body().getDelivered().get(i).getRecive_amt(),
                                response.body().getDelivered().get(i).getProduct_amt(),
                                response.body().getDelivered().get(i).getRecive_date(),
                                response.body().getDelivered().get(i).getPayment_type());
//                                response.body().getOrder_product_active().get(i).getRemaning_amt());
                        activeOrder.add(activeOrderGetSetList);
                    }
                    order_adapter();

                }
            }

            @Override
            public void onFailure(Call<Order_delivered> call, Throwable t) {

            }
        });
    }

    public void order_detail(){
        for (int i=0;i<activeOrder.size();i++){

        }
    }
    public void order_adapter(){
        this.deliverOrderAdapter = new DeliverOrderAdapter(getApplicationContext(),activeOrder);
        rvActiveOrder.setAdapter(deliverOrderAdapter);
    }
    public void Oldback(View view){
        Intent intent=new Intent(getApplicationContext(), OrderHistory.class);
        startActivity(intent);
    }

    public static TreeMap<String, String> Product_select_ordered(String id, String p_name, String Order_id,String remaning_qty,
                                                                 String recvamt, String product_amt_total,String recive_date,
                                                                 String payment_type) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("_id", String.valueOf(id));
//        treeMap.put("p_id", String.valueOf(p_id));
        treeMap.put("p_name", p_name);
        treeMap.put("Order_id",Order_id);
//        treeMap.put("p_price",p_price);
//        treeMap.put("p_qty",p_qty);
//        treeMap.put("p_GST",p_GST);
//        treeMap.put("pincode",pincode);
        treeMap.put("recvamt",recvamt);
        treeMap.put("product_amt_total",product_amt_total);
//        treeMap.put("product_amt_gst",product_amt_gst);
//        treeMap.put("product_qty",product_qty);
//        treeMap.put("delivery_time",delivery_time);
//        treeMap.put("p_img",p_img);
        treeMap.put("recive_date",recive_date);
//        treeMap.put("p_discount_total",p_discount_total);
        treeMap.put("remaning_qty",remaning_qty);
//        treeMap.put("remaning_amt",remaning_amt);
        treeMap.put("payment_type",payment_type);
//        treeMap.put("UserPhone",UserPhone);
//        treeMap.put("post",post);

        //
        return treeMap;
    }
}
