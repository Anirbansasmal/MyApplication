package com.example.myapplication.cart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.checkout.Checkout;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.productdetail.AddCart;
import com.example.myapplication.profile.AddProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.dashbord.Dashbord.user_id;

public class Cart extends AppCompatActivity {
    ApiInterface apiInterface;
    private CartAdapter cartAdapter;
    static String user_token,usr_id,cart_id,Order_id,product_confirmation;
    int p_price,p_gst,p_discount,p_count,p_discount_total,product_amt_gst,payment_amount;
    public static TextView cartTotal;
    ProgressDialog progressDoalog;
    public static TreeMap<String, String> product_cart = new TreeMap<String, String>();
//    TreeMap<String, String> user_address = new TreeMap<String, String>();
//    TreeMap<String, String>Product_timeslot=new TreeMap<String, String>();
    TreeMap<String, String> product_payment = new TreeMap<String, String>();
    TreeMap<String, String> product_cart_update = new TreeMap<String, String>();

   public static ArrayList<TreeMap<String,String>> user_cart=new ArrayList<TreeMap<String, String>>();
//    public static ArrayList<TreeMap<String,String>> user_del_address=new ArrayList<TreeMap<String, String>>();
//    public static ArrayList<TreeMap<String,String>>user_product_timeslot=new ArrayList<TreeMap<String, String>>();
    ArrayList<TreeMap<String,String>> user_payment=new ArrayList<TreeMap<String, String>>();
    ArrayList<TreeMap<String,String>> user_cart_update=new ArrayList<TreeMap<String, String>>();

    public static final String mypreference = "mypref";
    static String Content="application/x-www-form-urlencoded",token,token_val,user_id;
    String product_count;
    private List<CartGetSet> cartGetSetList = new ArrayList();
    public static RecyclerView rvCart,rvAddress,rvtimeslot,rvpaymenttype;
private CartAdapterAddress cartAdapterAddress;
private CartAdaptertimeslot cartAdaptertimeslot;
private CartAdapterPayment cartAdapterPayment;
    RecyclerView.SmoothScroller smoothScroller;
    RelativeLayout cart_not,offer_screen;
    Button shopping;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_cart);
//        getSupportActionBar().hide();
        this.rvCart = (RecyclerView) findViewById(R.id.rvCart);
//        this.rvAddress = (RecyclerView) findViewById(R.id.rvaddress);
//        this.rvtimeslot = (RecyclerView) findViewById(R.id.rvdeltime);
        cartTotal=findViewById(R.id.cartTotal);
//        this.rvpaymenttype = (RecyclerView) findViewById(R.id.rvpayment);
        offer_screen=findViewById(R.id.offer_notseen);
        cart_not=findViewById(R.id.cart_not);
        shopping=findViewById(R.id.shopping);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cart.this,Dashbord.class));
            }
        });
//        this.cartAdapter = new CartAdapter(this.cartGetSetList);
        this.rvCart.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        this.rvCart.setItemAnimator(new DefaultItemAnimator());

//        this.rvAddress.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        this.rvAddress.setItemAnimator(new DefaultItemAnimator());
//
//        this.rvtimeslot.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        this.rvtimeslot.setItemAnimator(new DefaultItemAnimator());
        progressDoalog = new ProgressDialog(Cart.this);
//        this.rvpaymenttype.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        this.rvpaymenttype.setItemAnimator(new DefaultItemAnimator());

        this.rvCart.setAdapter(this.cartAdapter);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
//        cartStaticData();

//        smoothScroller = new
//                LinearSmoothScroller(getApplicationContext()) {
//                    @Override
//                    protected int getVerticalSnapPreference() {
//                        return LinearSmoothScroller.SNAP_TO_START;
//                    }
//                };

        shared();
        address();
        user_cart.clear();
        product_cart.clear();
//        user_del_address.clear();
//        user_product_timeslot.clear();
//        scroll();
//        smooth();
        scroll_recycler();
//        cart_update();
//        cart();
    }

    public void shared() {
        try {
            SharedPreferences sharedPref = this.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_id = sharedPref.getString("u_id", "");
            user_token = "Berear " + token_val;
//            user_token = token_val;
        } catch (Exception e) {

        }
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
    private void address(){
        progress_show();
        progress_message();
        Call<CartData> call=apiInterface.response_fetchFromCart(user_id,user_token);
        call.enqueue(new Callback<CartData>() {
            @Override
            public void onResponse(Call<CartData> call, Response<CartData> response) {
                System.out.println(response.body().getCart_item());
                progress_dismiss();
                if (response.body().getStatus().equals("No data found")){

//                    startActivity(new Intent(Cart.this,Shopping.class));

                    offer_screen.setVisibility(View.VISIBLE);
                    cart_not.setVisibility(View.GONE);
                }else {
                    for (int i=0;i<response.body().getCart_item().size();i++){
                        product_cart=product_select_cart(response.body().getCart_item().get(i).get_id(),
                                response.body().getCart_item().get(i).getProductName(),
                                response.body().getCart_item().get(i).getP_details(),
                                response.body().getCart_item().get(i).getP_img(),
                                response.body().getCart_item().get(i).getProduct_qty(),
                                response.body().getCart_item().get(i).getPayment_amount(),
                                response.body().getCart_item().get(i).getTime_slot(),
                                response.body().getCart_item().get(i).getUser_id(),
                                response.body().getCart_item().get(i).getProduct_id(),
                                response.body().getCart_item().get(i).getP_unit(),
                                response.body().getCart_item().get(i).getP_Gst(),
                                response.body().getCart_item().get(i).getP_price(),
                                response.body().getCart_item().get(i).getUser_address(),
                                response.body().getCart_item().get(i).getP_discount_total(),
                                response.body().getCart_item().get(i).getP_discount(),
                                response.body().getCart_item().get(i).getProduct_amt_gst(),
                                response.body().getCart_item().get(i).getOrder_frequency_count());
                        user_cart.add(product_cart);
//                        user_address=product_select_address(response.body().getCart_item().get(i).get_id(),
//                                response.body().getCart_item().get(i).getProductName(),
//                                response.body().getCart_item().get(i).getP_details(),
//                                response.body().getCart_item().get(i).getUser_address(),
//                                response.body().getCart_item().get(i).getProduct_id(),
//                                response.body().getCart_item().get(i).getUser_id());
//                        user_del_address.add(user_address);
//                        Product_timeslot=product_select_timeslot(response.body().getCart_item().get(i).get_id(),
//                                response.body().getCart_item().get(i).getProductName(),
//                                response.body().getCart_item().get(i).getP_details(),
//                                response.body().getCart_item().get(i).getTime_slot(),
//                                response.body().getCart_item().get(i).getProduct_id(),
//                                response.body().getCart_item().get(i).getUser_id());
//                        user_product_timeslot.add(Product_timeslot);
//                    product_payment=product_select_payment();
//                    user_payment.add(product_payment);

                    }
//                    setRvtimeslot();
//                    setRvpaymenttype();
                    cartStaticData();
                }


//                if (response.body().getStatus().equals("success")){
//                    startActivity(new Intent(getApplicationContext(), Dashbord.class));
//                }
//                for (int i=0;i<response.body().getAvl_times().size();i++){
//                    deliiveryTimeGetSetList.add(response.body().getAvl_times().get(i).getDeliTimeSlot());
//                }
            }
            @Override
            public void onFailure(Call<CartData> call, Throwable t) {

            }
        });
//    }
//});


    }

    public void smooth(){
        final int[] overallXScroll = {0};
        rvCart.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                overallXScroll[0] = overallXScroll[0] + dy;

                Log.i("check","overallXScroll->" + overallXScroll[0]);

            }
        });
    }
    private static int displayedposition = 0;
    public void scroll_recycler(){
        rvCart.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager llm = (LinearLayoutManager) rvCart .getLayoutManager();
                displayedposition = llm.findFirstVisibleItemPosition();
            }
        });
    }

    MyCustomObject.MyCustomObjectListener listener;



    public void incr_decr(){
        MyCustomObject myCustomObject=new MyCustomObject(listener);
        myCustomObject.setCustomObjectListener(new MyCustomObject.MyCustomObjectListener() {
            @Override
            public void onadd(int index, int val) {
                increment(index,val);
System.out.println(val);
            }

            @Override
            public void onsub(int index, int val) {
                decrement(index,val);
                System.out.println(val);
            }

            @Override
            public void onDelete(int index) {
cart_delete(index);
            }
        });
    }

    public void increment(int pos,int p_count){
        System.out.println(user_cart);
        p_price= Integer.parseInt(user_cart.get(pos).get("p_price"));
        p_gst=Integer.parseInt(user_cart.get(pos).get("p_Gst"));
//        p_discount=Integer.parseInt(user_cart.get(pos).get("p_discount"));

//        p_count=Integer.parseInt(user_cart.get(pos).get("p_discount"));
        cart_id=(user_cart.get(pos).get("id"));

//        cart_update(pos,p_count);
        cart_update_val(p_count,cart_id, String.valueOf(p_price));
        System.out.println("mnbfdjsbdfjghk"+p_price);
    }
    public void decrement(int pos,int p_count){
        p_price=Integer.parseInt(user_cart.get(pos).get("p_price"));
        System.out.println(p_count);
//        cart_update(pos,p_count);
        cart_update_val(p_count,cart_id, String.valueOf(p_price));
    }

    public void cart_delete(final int pos){

        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<AddCart> call = apiInterface.response_DeleteCart(user_cart.get(pos).get("id"),user_token);
        call.enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                user_cart.remove(pos);
//                user_del_address.remove(pos);
//                user_product_timeslot.remove(pos);
                if (user_cart.size()<0){
                    Intent intent=new Intent(Cart.this, Dashbord.class);
                    startActivity(intent);
                }
                cart_update();
//                cart_addressupdate();
//                cart_timeupdate();
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {

            }
        });
    }
    public void cart_update() {

        this.cartAdapter = new CartAdapter(this,user_cart);
        this.rvCart.setAdapter(this.cartAdapter);
//        cart_update_val(4,"5e9b5cd9f62d203f84ee01a9");
    }
//
//    public void cart_addressupdate() {
//
//        this.cartAdapterAddress = new CartAdapterAddress(this,user_del_address);
//        this.rvAddress.setAdapter(this.cartAdapterAddress);
////        cart_update_val(4,"5e9b5cd9f62d203f84ee01a9");
//    }
//
//    public void cart_timeupdate() {
//
//        this.cartAdaptertimeslot = new CartAdaptertimeslot(this,user_product_timeslot);
//        this.rvtimeslot.setAdapter(this.cartAdaptertimeslot);
////        cart_update_val(4,"5e9b5cd9f62d203f84ee01a9");
//    }

    public void cart_update_val(int p_count,String cart_id,String p_price){
//        apiInterface= ApiClient.getClient().create(ApiInterface.class);
//        Call<AddCart> call = apiInterface.response_EditFromCart(String.valueOf(p_count),user_id,cart_id,p_price,user_token);
//        call.enqueue(new Callback<AddCart>() {
//            @Override
//            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<AddCart> call, Throwable t) {
//
//            }
//        });
    }

    public void cart(){
        product_count=cartAdapter.item().toString();
        System.out.println(product_count);
    }

//    private void setRvtimeslot(){
//        this.cartAdaptertimeslot = new CartAdaptertimeslot(this,user_product_timeslot);
//        this.rvtimeslot.setAdapter(this.cartAdaptertimeslot);
//    }
//
//    private void setRvpaymenttype(){
//        this.cartAdapterAddress = new CartAdapterAddress(this,user_del_address);
//        this.rvAddress.setAdapter(this.cartAdapterAddress);
//    }

    private void cartStaticData() {
        float total_amt=0;
        for (int i=0;i<user_cart.size();i++){
//            total_amt= Float.parseFloat((total_amt+ (user_cart.get(i).get("payment_amount"))+(user_cart.get(i).get("product_amt_gst"))));
        }
//        cartTotal.setText(""+total_amt);
        this.cartAdapter = new CartAdapter(this,user_cart);
        this.rvCart.setAdapter(this.cartAdapter);
        cart();
    }

    public void cartback(View view) {
        Intent intent=new Intent(Cart.this, Dashbord.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
//        finish();
    }
    public void cart_order() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AddCartOrder> call = apiInterface.response_productConfirmCart(user_id,"false", user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<AddCartOrder>() {
            @Override
            public void onResponse(Call<AddCartOrder> call, Response<AddCartOrder> response) {
                Order_id=response.body().getOrder_id();
                product_confirmation=response.body().getProduct_confirmation();
            }

            @Override
            public void onFailure(Call<AddCartOrder> call, Throwable t) {

            }
        });
    }

    public void continuecheckout(View view) {
        user_cart.clear();
        product_cart.clear();
//        cart_order();
        progress_show();
        progress_message();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AddCartOrder> call = apiInterface.response_productConfirmCart(user_id,"false", user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<AddCartOrder>() {
            @Override
            public void onResponse(Call<AddCartOrder> call, Response<AddCartOrder> response) {
                Order_id=response.body().getOrder_id();
                if (response.body().getStatus().equals("success")){
                    progress_dismiss();
                    System.out.println("kjhfdsgjsghdfjhsgdfgjdfgdsf"+Order_id);
                    product_confirmation=response.body().getProduct_confirmation();
                    Intent intent=new Intent(getApplicationContext(), Checkout.class);
                    intent.putExtra("Order_id",Order_id);
                    startActivity(intent);
                }else{

                }
            }


            @Override
            public void onFailure(Call<AddCartOrder> call, Throwable t) {

            }
        });


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
//                order_cancle();
                Intent intent=new Intent(Cart.this, Dashbord.class);
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
    public void scroll(){
        rvCart.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int scrollDy = 0;
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollDy += dy;
                System.out.println("jhgfhvshf"+scrollDy);
            }
        });
    }
    public static TreeMap<String, String> product_select_cart(String id, String p_name, String p_details, String p_img,
                                                              String product_qty,String payment_amount,String time_slot,String user_id,
                                                              String product_id,String p_unit,String p_Gst,String p_price,String user_address,
                                                              String p_discount_total,String p_discount,String product_amt_gst,String order_frequency_count) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("id", String.valueOf(id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_details",p_details);
        treeMap.put("p_img",p_img);
        treeMap.put("product_qty",product_qty);
        treeMap.put("payment_amount",payment_amount);
        treeMap.put("time_slot",time_slot);
        treeMap.put("user_id",user_id);
        treeMap.put("product_id",product_id);
        treeMap.put("p_unit",p_unit);
        treeMap.put("p_Gst",p_Gst);
        treeMap.put("p_price",p_price);
        treeMap.put("user_address",user_address);
        treeMap.put("p_discount_total",p_discount_total);
        treeMap.put("p_discount",p_discount);
        treeMap.put("product_amt_gst",product_amt_gst);
        treeMap.put("order_frequency_count",order_frequency_count);
        //
        return treeMap;
    }

    public static TreeMap<String, String> product_select_cart_update(String id, String p_name, String p_img,
                                                              String product_qty,String payment_amount,String user_id,
                                                              String product_id,String p_unit,String p_Gst,String p_price) {

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
        //
        return treeMap;
    }
    public static TreeMap<String, String> product_select_address(String id, String p_name, String p_details, String user_address,
                                                                 String product_id,String user_id) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("id", String.valueOf(id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_details",p_details);
        treeMap.put("user_address",user_address);
        treeMap.put("product_id",product_id);
        treeMap.put("user_id",user_id);

        //
        return treeMap;
    }

    public static TreeMap<String, String> product_select_timeslot(String id, String p_name, String p_details, String time_slot,
                                                                  String product_id,String user_id) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("id", String.valueOf(id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_details",p_details);
        treeMap.put("time_slot",time_slot);
        treeMap.put("product_id",product_id);
        treeMap.put("user_id",user_id);

        //
        return treeMap;
    }

    public static TreeMap<String, String> product_select_payment(String id, String p_name, String p_details, String p_img) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("id", String.valueOf(id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_details",p_details);
        treeMap.put("p_img",p_img);
//        treeMap.put("updated_at",is_last_lavel);
//        treeMap.put("is_altFormula",is_altFormula);
//        treeMap.put("altFormula",altFormula);
//        treeMap.put("is_parent",is_parent);

        //
        return treeMap;
    }
}
