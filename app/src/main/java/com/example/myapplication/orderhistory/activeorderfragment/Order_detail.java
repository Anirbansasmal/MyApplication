package com.example.myapplication.orderhistory.activeorderfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.orderhistory.OrderHistory;
import com.instamojo.android.models.Order;

import java.util.ArrayList;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.dashbord.Dashbord.user_id;
import static com.example.myapplication.verifyotp.VerifyOtp.user_type;

public class Order_detail extends AppCompatActivity {
    public static final String mypreference = "mypref";
    static String user_token,Order_id,user_email,phoneno,user_name,Order_id_cart,token_val,status,user_typeval;
    TreeMap<String, String> activeOrderGetSetList = new TreeMap<String, String>();
    TreeMap<String, String> bottleReturn = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> activeOrder=new ArrayList<TreeMap<String, String>>();
    ArrayList<TreeMap<String,String>> activeBottle=new ArrayList<TreeMap<String, String>>();
    ApiInterface apiInterface;
    RecyclerView rvActiveOrder;
    private static LinearLayout rlbottle;
    private ActiveOrderAdapterDetail activeOrderAdapterDetail;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_order_detail);
        this.rvActiveOrder = (RecyclerView) findViewById(R.id.rvactive);
//        rlbottle=findViewById(R.id.rlbottle);
//        rlbottle.setVisibility(View.GONE);
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
order_active();
//            user_token = token_val;
        } catch (Exception e) {

        }
    }

    public void order_active(){
        Call<User_Order> call = apiInterface.response_Deliver_order_active_detail(user_id,Order_id,user_token);

        call.enqueue(new Callback<User_Order>() {

            @Override
            public void onResponse(Call<User_Order> call, Response<User_Order> response) {
                for (int i=0;i<response.body().getOrder_product_active().size();i++){
                    System.out.println("ljksdfkljkdfj"+response.body().getOrder_product_active().get(i).getRemaning_qty());
                    if (response.body().getOrder_product_active().get(i).getRemaning_qty().equals("0")){

                    }else {
                        activeOrderGetSetList=Product_select_ordered(response.body().getOrder_product_active().get(i).get_id(),
                                response.body().getOrder_product_active().get(i).getProduct_name(),
                                response.body().getOrder_product_active().get(i).getOrder_id(),
                                response.body().getOrder_product_active().get(i).getProduct_qty(),
                                response.body().getOrder_product_active().get(i).getRemaning_qty(),
                                response.body().getOrder_product_active().get(i).getAddress(),
                                response.body().getOrder_product_active().get(i).getProduct_amt_total(),
                                response.body().getOrder_product_active().get(i).getProduct_qty_total(),
//
                                response.body().getOrder_product_active().get(i).getOrder_frequency().toString(),
                                response.body().getOrder_product_active().get(i).getRemaning_amt());
//                                response.body().getOrder_product_active().get(i).getRemaning_amt());
                        activeOrder.add(activeOrderGetSetList);
                        System.out.println("activeOrder"+activeOrder);
                    }

                    order_adapter();
                }
                if(response.body().getBottle().size()<0){
//                    bottleReturn
                }
            }

            @Override
            public void onFailure(Call<User_Order> call, Throwable t) {

            }
        });
    }

    public void order_adapter(){
        this.activeOrderAdapterDetail = new ActiveOrderAdapterDetail(getApplicationContext(),activeOrder);
        rvActiveOrder.setAdapter(activeOrderAdapterDetail);
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                Intent intent=new Intent(Order_detail.this, OrderHistory.class);
                startActivity(intent);
//                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void activeback(View view){
        Intent intent=new Intent(getApplicationContext(), OrderHistory.class);
        startActivity(intent);
    }

    public void order_detail(){
        for (int i=0;i<activeOrder.size();i++){

        }
    }

    public static TreeMap<String, String> Product_select_ordered(String id, String p_name, String Order_id,String p_qty,
                                                                 String remaning_qty,String address, String product_amt_total, String product_qty_total,
                                                                 String freq,String remaning_amt) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("_id", String.valueOf(id));
//        treeMap.put("p_id", String.valueOf(p_id));
        treeMap.put("p_name", p_name);
        treeMap.put("Order_id",Order_id);
//        treeMap.put("p_price",p_price);
        treeMap.put("p_qty",p_qty);
//        treeMap.put("p_GST",p_GST);
//        treeMap.put("pincode",pincode);
        treeMap.put("remaning_qty",remaning_qty);
        treeMap.put("address",address);
        treeMap.put("product_amt_total",product_amt_total);
//        treeMap.put("product_amt_gst",product_amt_gst);
//        treeMap.put("product_qty",product_qty);
//        treeMap.put("delivery_time",delivery_time);
//        treeMap.put("p_img",p_img);
        treeMap.put("product_qty_total",product_qty_total);
//        treeMap.put("p_discount_total",p_discount_total);

        treeMap.put("remaning_amt",remaning_amt);
        treeMap.put("freq",freq);
//        treeMap.put("UserPhone",UserPhone);
//        treeMap.put("post",post);

        //
        return treeMap;
    }
}
