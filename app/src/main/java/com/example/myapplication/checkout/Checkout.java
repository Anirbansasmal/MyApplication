package com.example.myapplication.checkout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.checkout.deliverfragment.CheckoutOrderAdapter;
import com.example.myapplication.checkout.deliverfragment.DeliveryFragment;
import com.example.myapplication.checkout.deliverfragment.OrderConfirm;
import com.example.myapplication.checkout.paymentfragment.PaymentFragment;
import com.example.myapplication.confirmorderdetail.ConfirmOrderDetail;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.productdetail.AddCart;

import java.util.ArrayList;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.confirmorderdetail.ConfirmOrderDetail.product_id;
import static com.example.myapplication.dashbord.Dashbord.address;
import static com.example.myapplication.dashbord.Dashbord.user_id;

public class Checkout extends AppCompatActivity {
    /* access modifiers changed from: protected */
    private CheckoutOrderAdapter checkoutOrderAdapter;
    TreeMap<String, String> user_order = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> user_order_detail=new ArrayList<TreeMap<String, String>>();
    ApiInterface apiInterface;
    static String user_token,Order_id,Order_id_product,Order_id_cart;
    public static final String mypreference = "mypref";
    ProgressDialog progressDoalog;
    static String token,token_val,prname;
    float total,shipping,gst,item,price;
    String content="application/json";
    //    private List<CheckoutOrderGetSet> checkoutOrderGetSetList = new ArrayList();
    RecyclerView checkoutOrderRV;
    Button confirmpayment;
    TextView txt_items,txt_price,txt_gst,txt_total,txt_shipping,user_address;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.fragment_delivery);
//        getSupportActionBar().hide();
        this.checkoutOrderRV = (RecyclerView) findViewById(R.id.checkoutOrderRV);

        this.checkoutOrderRV.setLayoutManager(new LinearLayoutManager(this));
        this.checkoutOrderRV.setItemAnimator(new DefaultItemAnimator());
        txt_items=findViewById(R.id.txt_items);
        txt_price=findViewById(R.id.txt_price);
        txt_gst=findViewById(R.id.txt_gst);
        txt_total=findViewById(R.id.txt_total);
        txt_shipping=findViewById(R.id.txt_shipping);
        user_address=findViewById(R.id.user_address);
        user_address.setText(""+address);
//        cart_order();
//        onBackPressed();
        Order_id_product=ConfirmOrderDetail.Order_id;
        this.confirmpayment = (Button) findViewById(R.id.confirmpayment);
//        this.confirmpayment.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Fragment fragment = new PaymentFragment();
//                FragmentTransaction fragmentTransaction = Checkout.this.getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.checkoutFramelayout, fragment);
//                fragmentTransaction.addToBackStack((String) null);
//                fragmentTransaction.commit();
//            }
//        });
        Intent intent=getIntent();
        if (Order_id_product==null){
            Order_id=intent.getStringExtra("Order_id");
            System.out.println("khjdhsajdsjdsa"+Order_id);
        }else {
            Order_id=Order_id_product;
        }


        progressDoalog = new ProgressDialog(Checkout.this);
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.checkoutFramelayout, new DeliveryFragment(), "HELLO");
//        fragmentTransaction.commit();
        checkoutOrderStaticData();
    }

    public void progress_show(){
        progressDoalog.show();
    }
    public void progress_dismiss(){
        progressDoalog.dismiss();
    }
    public void progress_message(){
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its product detail loading....");
        progressDoalog.setTitle("Thank you for give some time");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void checkoutOrderStaticData() {
        progress_message();
        progress_show();
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
//        String Order_id;
//        Order_id= ConfirmOrderDetail.Order_id;
        System.out.println(Order_id);
        Call<OrderConfirm> call=apiInterface.response_productFetchOrder(Order_id,user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<OrderConfirm>() {

            @Override
            public void onResponse(Call<OrderConfirm> call, Response<OrderConfirm> response) {
                progress_dismiss();
                for (int i = 0; i < response.body().getOrder_product().size(); i++) {
                    System.out.println("jkbdsfhsdjfjsbfd"+response.body().getOrder_product().size());
                    user_order=Product_select(response.body().getOrder_product().get(i).get_id(),
                            response.body().getOrder_product().get(i).getProduct_name(),
                            response.body().getOrder_product().get(i).getPayment_details(),
                            response.body().getOrder_product().get(i).getProduct_confirmation(),
                            response.body().getOrder_product().get(i).getP_price(),
                            response.body().getOrder_product().get(i).getProduct_qty(),
                            response.body().getOrder_product().get(i).getP_img(),
                            response.body().getOrder_product().get(i).getProduct_id(),
                            response.body().getOrder_product().get(i).getUsername(),
                            response.body().getOrder_product().get(i).getDelivery_date(),
                            response.body().getOrder_product().get(i).getPincode(),
                            response.body().getOrder_product().get(i).getAddress(),
                            response.body().getOrder_product().get(i).getProduct_amt_total(),
                            response.body().getOrder_product().get(i).getProduct_amt_gst(),
                            response.body().getOrder_product().get(i).getPayment_amount(),
                            response.body().getOrder_product().get(i).getDelivery_time());
                    user_order_detail.add(user_order);
                    checkoutOrderAdapter = new CheckoutOrderAdapter(Checkout.this,user_order_detail);
                    checkoutOrderRV.setAdapter(checkoutOrderAdapter);
//
                }
                placed_order();
//                }
//                productStaticData();


            }

            @Override
            public void onFailure(Call<OrderConfirm> call, Throwable t) {

            }
        });
    }

    public void placed_order(){
        for (int i=0;i<user_order_detail.size();i++){
            total=total+Float.parseFloat(user_order_detail.get(i).get("payment_amount"));
            gst=gst+Float.parseFloat(user_order_detail.get(i).get("product_amt_gst"));
            item=item+Float.parseFloat(user_order_detail.get(i).get("ProductQty"));
            price=price+((Float.parseFloat(user_order_detail.get(i).get("payment_amount"))-Float.parseFloat(user_order_detail.get(i).get("product_amt_gst"))));
            txt_items.setText(""+item+" items");
            txt_price.setText(String.format("%.2f",price));
            txt_gst.setText(String.format("%.2f",gst));
            txt_total.setText("INR:"+String.format("%.2f",total));
            txt_shipping.setText("INR "+0);
            System.out.println("jhgdjgsdjgjsd"+user_order_detail);
        }
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
                order_cancle();
                user_order.clear();
                user_order_detail.clear();
                Intent intent=new Intent(Checkout.this, Dashbord.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
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

    public void order_cancle(){
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Boolean product_confirmation=false;
        Intent intent=getIntent();
//        if (Order_id==null){
//            Order_id=intent.getStringExtra("Order_id");
//            System.out.println(Order_id);
//        }else {
//            Order_id=Order_id;
//            System.out.println(Order_id);
//        }
        Call<AddCart> call=apiInterface.response_productConfirmCancle(Order_id,product_confirmation,user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<AddCart>() {

            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                System.out.println("sgdjsgdf"+response.body().getStatus());
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {

            }
        });
    }

    public void ConfirmPayment(View view){
//        Fragment fragment = new PaymentFragment();
        Intent intent=new Intent(this,PaymentFragment.class);
        System.out.println(total);
        intent.putExtra("Order_id",Order_id);
        intent.putExtra("product_amt_total",total);
//        intent.putExtra("Order_id",Order_id);
//        intent.putExtra("Order_id",Order_id);
        startActivity(intent);
//
    }
    public static TreeMap<String, String> Product_select(String id,String p_name, String payment_details,String product_confirmation, String p_price,
                                                         String ProductQty,String p_img,String product_id,String Username,String delivery_date,String pincode,
                                                         String address,String product_amt_total,String product_amt_gst,String payment_amount,String delivery_time) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("p_id", String.valueOf(id));
        treeMap.put("ProductName", p_name);
        treeMap.put("payment_details",payment_details);
        treeMap.put("product_confirmation",product_confirmation);
//        treeMap.put("p_exp",p_exp);
//        treeMap.put("p_type",p_type);
        treeMap.put("p_price",p_price);
        treeMap.put("ProductQty",ProductQty);
        treeMap.put("p_img",p_img);
        treeMap.put("product_id", String.valueOf(product_id));
        treeMap.put("Username", Username);
        treeMap.put("delivery_date",delivery_date);
        treeMap.put("pincode",pincode);
        treeMap.put("address",address);
        treeMap.put("product_amt_total",product_amt_total);
        treeMap.put("product_amt_gst",product_amt_gst);
        treeMap.put("payment_amount",payment_amount);
        treeMap.put("delivery_time",delivery_time);
        //
        return treeMap;
    }
//    public void checkoutback(View view) {
//        finish();
//    }
}
