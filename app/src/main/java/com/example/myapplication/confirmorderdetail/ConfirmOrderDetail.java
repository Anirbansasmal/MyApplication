package com.example.myapplication.confirmorderdetail;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.cart.Cart;
import com.example.myapplication.checkout.Checkout;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.productdetail.AddCart;
import com.example.myapplication.productdetail.ProductDetail;
import com.example.myapplication.profile.View_Profile;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Discount.OfferAdapter.p_discount_val;
//import static com.example.myapplication.dashbord.Dashbord.address;
//import static com.example.myapplication.dashbord.Dashbord.phoneNo;
//import static com.example.myapplication.dashbord.Dashbord.pin;
//import static com.example.myapplication.dashbord.Dashbord.address;
//import static com.example.myapplication.dashbord.Dashbord.user_Name;
import static com.example.myapplication.dashbord.Dashbord.user_id;

public class ConfirmOrderDetail extends AppCompatActivity {
    ArrayList<String> myorderfrequency = new ArrayList<>();
    ArrayList<String> DateArray = new ArrayList<>();
    int count=0;
   public static float p_gst;
    public int p_discount;
    public static float p_count;
    public static float p_temp;
    TextView orderfrequency,codProductName,codAddress,codQuantity,deliverytimeslot;
    ImageView codProductImage;
    String proname,Address,deltimeslot,frequency="",unit,p_type;
    int Quantity;
    ApiInterface apiInterface;
    ProgressDialog progressDoalog;
    static String user_token;
    public static final String mypreference = "mypref";
    static String token,token_val,prname,pin,useraddress,userphoneNo,user_Name,email;
    String content="application/json";
    String img;
    public static String p_details,Order_type,product_qty,p_unit,time_slot,order_frequency,Username,Order_id,p_GST,product_id,p_img;
    public static float p_price,payment_amount,onetimeorder,everyorder,payment_amount_total;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_confirm_order_detail);
//        getSupportActionBar().hide();
        this.orderfrequency = (TextView) findViewById(R.id.orderfrequency);
        codProductName=findViewById(R.id.codProductName);
        codAddress=findViewById(R.id.codAddress);
        codQuantity=findViewById(R.id.codQuantity);
        deliverytimeslot=findViewById(R.id.deliverytimeslot);

        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        codProductImage=findViewById(R.id.codProductImage);
//        String img;
//        img= String.valueOf(getIntent().getStringExtra("image_url"));
        progressDoalog = new ProgressDialog(ConfirmOrderDetail.this);
//        Glide.with(this.codProductImage.getContext()).load(img).into(this.codProductImage);
        if (p_discount_val>0){
            p_discount=p_discount_val;
        }else {
            p_discount=0;
            System.out.println("kjsfhdjsdfbjsbf"+p_discount);
        }
        DateArray.clear();
        myorderfrequency.clear();
        shared();
        confirm();
    }
    public void shared() {
        try {
            SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            pin = sharedPref.getString("pin", "");
            address(Integer.parseInt(pin));
            user_token="Berear "+token_val;
            System.out.println("Berearpin"+pin);
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

    public void progress_message_cart(){
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its product place to cart....");
        progressDoalog.setTitle("Thank you for give some time");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    public void progress_message_buynow(){
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its product order place to....");
        progressDoalog.setTitle("Thank you for give some time");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }


    public void address(int pincode){
        progress_message();
        progress_show();
        Call<View_Profile> call = apiInterface.response_UserProfile(user_token,user_id);

        call.enqueue(new Callback<View_Profile>() {

            @Override
            public void onResponse(Call<View_Profile> call, Response<View_Profile> response) {
                progress_dismiss();
                System.out.println(response.body().getUser());
                email=response.body().getUser().get(0).getEmail();
                userphoneNo=response.body().getUser().get(0).getUserPhone();
                useraddress=response.body().getUser().get(0).getUserAddress();
                user_Name=response.body().getUser().get(0).getUserName();
                codAddress.setText(""+useraddress);
//                for (int i=0;i<response.body().getUser().size();i++){
//                    add_profile=profile_view_user(response.body().getUser().get(i).getU_id(),
//                            response.body().getUser().get(i).getUserName(),
//                            response.body().getUser().get(i).getUserAddress(),
//                            response.body().getUser().get(i).getUserPin(),
//                            response.body().getUser().get(i).getAge(),
//                            response.body().getUser().get(i).get_id(),
//                            response.body().getUser().get(i).getUserPhone(),
//                            response.body().getUser().get(i).getEmail());
//                    address=response.body().getUser().get(i).getUserAddress();
//
////                    user_profile.add(add_profile);
//                }
//                user_name.setText(""+response.body().getUser().get(0).getUserName());
//                user_email.setText(""+response.body().getUser().get(0).getEmail());
//                profileAdapter=new ProfileAdapter(Dashbord.this,user_profile);
//                recyclerViewchangelocation.setAdapter(profileAdapter);
            }

            @Override
            public void onFailure(Call<View_Profile> call, Throwable t) {

            }
        });
    }

    public void confirm(){
     Intent intent=getIntent();
        proname=intent.getStringExtra("proname");
        Quantity=intent.getIntExtra("count",0);
        deltimeslot=intent.getStringExtra("deltimeslot");
        DateArray=intent.getStringArrayListExtra("customDate");
        time_slot=intent.getStringExtra("time_slot");
        onetimeorder=intent.getIntExtra("onetimeorder",0);
        everyorder=intent.getIntExtra("everyorder",0);
        Order_type=intent.getStringExtra("Order_type");
        myorderfrequency=intent.getStringArrayListExtra("weekorderfreq");
p_type=intent.getStringExtra("p_type");
        System.out.println("myorderfrequency"+DateArray);
        if (onetimeorder!=0){
            orderfrequency.setText(""+"onetimeorder");
//        System.out.println("iojfdsfjsdjfo"+DateArray);
//        dayorderfreq.add(String.valueOf(onetimeorder));
        }else if (everyorder!=0) {
            orderfrequency.setText(""+"everyorder");
//        dayorderfreq.add(String.valueOf(everyorder));
        }else if (DateArray.size()>0){
            for (int i=0;i<DateArray.size();i++){
                frequency= (frequency+","+DateArray.get(i));
                orderfrequency.setText(""+frequency);
//                dayorderfreq.add(DateArray.get(i));
            }
        }else if (myorderfrequency.size()>0){
            for (int i=0;i<myorderfrequency.size();i++){
                frequency= (frequency+","+myorderfrequency.get(i));
                orderfrequency.setText(""+frequency);
//                dayorderfreq.add(weekAdapter.getWeeksSelectedArray().get(i));
            }
        }

        System.out.println("jkgdksd"+myorderfrequency);
        for (int i=0;i<myorderfrequency.size();i++){

            System.out.println(myorderfrequency.get(i).toString());
        }
        codProductName.setText(""+proname);
        codQuantity.setText(""+Quantity);
        deliverytimeslot.setText(""+deltimeslot);


        img= String.valueOf(getIntent().getStringExtra("image_url"));
        System.out.println("kjsfhdjsdfbjsbf"+img);
        Glide.with(this.codProductImage.getContext()).load("http://app.milchmom.com:8080/"+img).into(this.codProductImage);
    }

    private void add_cart(){
//        DateArray.clear();
        Intent intent=getIntent();
        proname=intent.getStringExtra("proname");
        Quantity=intent.getIntExtra("count",0);
        deltimeslot=intent.getStringExtra("deltimeslot");
        onetimeorder=intent.getIntExtra("onetimeorder",0);
        everyorder=intent.getIntExtra("everyorder",0);
        myorderfrequency=intent.getStringArrayListExtra("weekorderfreq");
        DateArray=intent.getStringArrayListExtra("customDate");
        p_details=intent.getStringExtra("p_details");
        product_id=intent.getStringExtra("product_id");
        unit=intent.getStringExtra("unit");
        p_price= Integer.parseInt(intent.getStringExtra("p_price"));
        p_GST=intent.getStringExtra("p_GST");
        p_type=intent.getStringExtra("p_type");
        Order_type=intent.getStringExtra("Order_type");
        System.out.println("DateArray"+DateArray);
        System.out.println("myorderfrequency"+myorderfrequency);
        if (onetimeorder!=0){
            System.out.println("iojfdsfjsdjfo"+p_price);
//            dayorderfreq.add(String.valueOf(onetimeorder));
            jsonElementscart.add(onetimeorder);
            p_gst=((onetimeorder*Quantity)*((p_price*Integer.parseInt(p_GST))/100));


            payment_amount=p_gst+(onetimeorder*Quantity)*((p_price));
            p_temp=((payment_amount*(p_discount))/100);
            payment_amount_total=payment_amount-p_temp;
            p_count=onetimeorder*Quantity;
            order_frequency_count=onetimeorder;
            System.out.println("iojfdsfjsdjfop_discount"+p_temp);
        }else if (everyorder!=0){
//            dayorderfreq.add(String.valueOf(everyorder));
            jsonElementscart.add(everyorder);
            p_gst=((everyorder*Quantity)*((p_price*Integer.parseInt(p_GST))/100));
            System.out.println("iojfdsfjsdjfo"+p_gst);

            payment_amount=p_gst+(everyorder*Quantity)*((p_price));
            p_temp=((payment_amount*(p_discount))/100);
            payment_amount_total=payment_amount-p_temp;
            p_count=everyorder*Quantity;
            System.out.println(payment_amount+ +p_price+ +Integer.parseInt(p_GST));
            order_frequency_count=everyorder;
        }else if (DateArray.size()>0){
            count=DateArray.size();

            p_gst=((count*Quantity)*((p_price*Integer.parseInt(p_GST))/100));
            System.out.println("iojfdsfjsdjfo"+p_gst+" "+(p_price*Integer.parseInt(p_GST))/100+""+count);
            System.out.println("iojfdsfjsdjfo"+count);
            payment_amount=p_gst+(count*Quantity)*((p_price));
            p_temp=((payment_amount*(p_discount))/100);
            payment_amount_total=payment_amount-p_temp;
            p_count=count*Quantity;
            for (int i=0;i<DateArray.size();i++){
                jsonElementscart.add(DateArray.get(i));
//                dayorderfreq.add(DateArray.get(i));
            }
            order_frequency_count=count;
        }else if (myorderfrequency.size()<=4){
            count=myorderfrequency.size()*4;

            p_gst=((count*Quantity)*((p_price*Integer.parseInt(p_GST))/100));
            System.out.println("iojfdsfjsdjfo"+count);
            System.out.println("iojfdsfjsdjfop_gst"+count);
            payment_amount=p_gst+(count*Quantity)*((p_price));
            p_temp=((payment_amount*(p_discount))/100);
            payment_amount_total=payment_amount-p_temp;
            p_count=count*Quantity;
            System.out.println("iojfdsfjsdjfoppayment_amount_total"+payment_amount_total);
            System.out.println("iojfdsfjsdjfoppaymentp_count"+p_count);
            for (int i=0;i<myorderfrequency.size();i++){
                jsonElementscart.add(myorderfrequency.get(i));
            }
            order_frequency_count=myorderfrequency.size()*4;
            }
//        }
    }

    public void confirmorderback(View view) {
//        finish();
        Intent intentfetc=getIntent();
        product_id=intentfetc.getStringExtra("product_id");
//        Intent intent=new Intent(getApplicationContext(), ProductDetail.class);

        Intent intent=new Intent(getApplicationContext(), ProductDetail.class);
        intent.putExtra("pincodeChek",pin);
        intent.putExtra("p_id",product_id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    JsonObject cart=new JsonObject();

    JsonArray jsonElements=new JsonArray();
    JsonArray jsonElementscart=new JsonArray();

    public void buynow(View view) {
        Intent intent=getIntent();
        product_id=intent.getStringExtra("product_id");
        unit=intent.getStringExtra("unit");
        Quantity=intent.getIntExtra("count",0);
        proname=intent.getStringExtra("proname");
        onetimeorder=intent.getIntExtra("onetimeorder",0);
        everyorder=intent.getIntExtra("everyorder",0);
        DateArray=intent.getStringArrayListExtra("customDate");
        myorderfrequency=intent.getStringArrayListExtra("weekorderfreq");
        p_price= Integer.parseInt(intent.getStringExtra("p_price"));
        p_GST=intent.getStringExtra("p_GST");
        p_details=intent.getStringExtra("p_details");
        p_type=intent.getStringExtra("p_type");
        System.out.println("iojfdsfjsdjfojfgdjhsfdghfgjhsfgjs"+p_gst);
        if (onetimeorder!=0){

//            dayorderfreq.add(String.valueOf(onetimeorder));
            jsonElements.add(onetimeorder);
            p_gst=((onetimeorder*Quantity)*((p_price*Integer.parseInt(p_GST))/100));
            payment_amount=p_gst+(onetimeorder*Quantity)*((p_price));
            System.out.println("iojfdsfjsdjfo"+p_gst);
            p_temp=((payment_amount)*(p_discount)/100);
            payment_amount_total=payment_amount-p_temp;
            p_count=onetimeorder*Quantity;
            System.out.println("iojfdsfjsdjfop_discount"+p_temp);
            System.out.println("payment_amount_total"+payment_amount_total);
//            Order_type="Day";

        }else if (everyorder!=0){
//            dayorderfreq.add(String.valueOf(everyorder));
            jsonElements.add(everyorder);
            p_gst=((everyorder*Quantity)*((p_price*Integer.parseInt(p_GST))/100));
            System.out.println("iojfdsfjsdjfo"+p_gst);
            payment_amount=p_gst+(everyorder*Quantity)*((p_price));
            p_temp=((payment_amount*(p_discount))/100);
            payment_amount_total=payment_amount-p_temp;
            p_count=everyorder*Quantity;
            System.out.println(payment_amount+ +p_price+ +Integer.parseInt(p_GST));
//            Order_type="EveryDay";
        }else if (DateArray.size()>0){
            count=DateArray.size();

            p_gst=((count*Quantity)*((p_price*Integer.parseInt(p_GST))/100));
            System.out.println("iojfdsfjsdjfo"+p_gst+" "+(p_price*Integer.parseInt(p_GST))/100);
            payment_amount=p_gst+(count*Quantity)*((p_price));
            p_temp=((payment_amount*(p_discount))/100);
            payment_amount_total=payment_amount-p_temp;
            p_count=count*Quantity;
            for (int i=0;i<DateArray.size();i++){
                jsonElements.add(DateArray.get(i));
//                dayorderfreq.add(DateArray.get(i));
            }
//            Order_type="weekly";
        }else if (myorderfrequency.size()>=4){
            count=myorderfrequency.size()*4;

            p_gst=((count*Quantity)*((p_price*Integer.parseInt(p_GST))/100));
            System.out.println("iojfdsfjsdjfojfgdjhsfdghfgjhsfgjs"+p_gst);
            payment_amount=p_gst+(count*Quantity)*((p_price));
            p_temp=((payment_amount*(p_discount))/100);
            payment_amount_total=payment_amount-p_temp;
            p_count=count*Quantity;
            for (int i=0;i<myorderfrequency.size();i++){
                jsonElements.add(myorderfrequency.get(i));
            }
//            Order_type="CustomDate";
//                dayorderfreq.add(weekAdapter.getWeeksSelectedArray().get(i));
        }

//        for (int i=0;i<myorderfrequency.size();i++){
//            jsonElements.add(myorderfrequency.get(i));
//            System.out.println(jsonElements);
//        }
//        cart.addProperty("Username","1234567890");
        cart.addProperty("user_id",user_id);
        cart.addProperty("product_id",product_id);
        cart.addProperty("p_details",p_details);
        cart.addProperty("payment_amount",payment_amount_total);
        cart.addProperty("time_slot",deltimeslot);
        cart.addProperty("product_qty",Quantity);
        cart.addProperty("p_unit",unit);
        cart.add("order_frequency",jsonElements);
        cart.addProperty("p_img",img);
        cart.addProperty("p_price",p_price);
        cart.addProperty("p_Gst",p_GST);
        cart.addProperty("product_name",proname);
        cart.addProperty("payment_type",false);
        cart.addProperty("pincode", pin);
        cart.addProperty("product_confirmation",false);
        cart.addProperty("product_qty_total",p_count);
        cart.addProperty("p_discount",p_discount);
        cart.addProperty("product_amt_gst",p_gst);
        cart.addProperty("p_discount_total",p_temp);
        cart.addProperty("remaning_qty",p_count);
        cart.addProperty("remaning_amt",payment_amount_total);
        cart.addProperty("Order_type",Order_type);
        cart.addProperty("p_type",p_type);
        cart.addProperty("UserName",user_Name);
        cart.addProperty("UserPhone",userphoneNo);
//        cart.addProperty("pincode",Dashbord.pin);
        cart.addProperty("UserAddress",useraddress);
        progress_message_buynow();
        progress_show();
        Call<OrderStatus> call=apiInterface.response_productConfirm(cart,user_token);
        call.enqueue(new Callback<OrderStatus>() {
            @Override
            public void onResponse(Call<OrderStatus> call, Response<OrderStatus> response) {

                System.out.println("ljksdshdfjgsj"+response.body().getStatus());
                System.out.println("ljksdshdfjgsj"+response.body().getStatus());

                if (response.body().getStatus().equals("success")){
                    progress_dismiss();
                    Order_id=response.body().getOrder_id();
                    System.out.println(Order_id);
                    Intent intent=new Intent(ConfirmOrderDetail.this, Checkout.class);
                    intent.putExtra("Order_id",Order_id);
                    startActivity(intent);
                }else {

                }
            }

            @Override
            public void onFailure(Call<OrderStatus> call, Throwable t) {

            }
        });
//        startActivity(new Intent(getApplicationContext(), Checkout.class));
    }

    public void buy_item(){

    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Back and edit your item?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
//                order_cancle();
                DateArray.clear();
                myorderfrequency.clear();
                Intent intentfetc=getIntent();
                product_id=intentfetc.getStringExtra("product_id");
                p_img=intentfetc.getStringExtra("p_img");
                p_details=String.valueOf(getIntent().getStringExtra("p_details"));
                p_type=String.valueOf(getIntent().getStringExtra("p_type"));
                Intent intent=new Intent(getApplicationContext(), ProductDetail.class);
                intent.putExtra("pincodeChek",pin);
                intent.putExtra("p_id",product_id);
                intent.putExtra("image_url",img);
                intent.putExtra("p_details",p_details);
                intent.putExtra("p_type",p_type);
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
    public static float order_frequency_count=0;
    public void addtocart(View view) {
        add_cart();
        JsonObject cart=new JsonObject();
        cart.addProperty("Username","1234567890");
        cart.addProperty("user_id",user_id);
        cart.addProperty("product_id",product_id);

        cart.addProperty("p_details",p_details);
        cart.addProperty("payment_amount",payment_amount_total);
        cart.addProperty("time_slot",deltimeslot);
        cart.addProperty("product_qty",Quantity);
        cart.addProperty("p_unit",unit);
        cart.add("order_frequency",jsonElementscart);
        cart.addProperty("p_img",img);
        cart.addProperty("p_price",p_price);
        cart.addProperty("p_Gst",p_GST);
        cart.addProperty("ProductName",proname);
        cart.addProperty("payment_type",false);
        cart.addProperty("product_confirmation",false);
        cart.addProperty("pincode", pin);
        cart.addProperty("product_qty_total",p_count);
        cart.addProperty("p_discount",p_discount);
        cart.addProperty("product_amt_gst",p_gst);
        cart.addProperty("p_discount_total",p_temp);
        cart.addProperty("order_frequency_count",order_frequency_count);
        cart.addProperty("remaning_qty",p_count);
        cart.addProperty("remaning_amt",payment_amount);
        cart.addProperty("Order_type",Order_type);
        cart.addProperty("p_type",p_type);
//            cart.put("Username",Username);
        //        p_details,this.count,unit,time_slot,myorderfrequency,"123456789",user_id,String.valueOf(payment_amount),product_id,p_img,(p_price),p_GST,
//        time_slot=deliveryAdapter.delitimeslot();
        System.out.println("dgfdgfhfgdhdf"+cart);
        progress_message_cart();
        progress_show();
        Call<AddCart> call=apiInterface.response_Cart(cart,content,user_token);
        call.enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {

                System.out.println(response.body().getStatus());
                if (response.body().getStatus().equals("success")){
                    progress_dismiss();
                    Toast toast = new Toast(getApplicationContext());
                    LayoutInflater li = getLayoutInflater();
                    View layout = li.inflate(R.layout.activity_cart_toast,(ViewGroup) findViewById(R.id.custom_toast_layout));
                    TextView tv = (TextView) layout.findViewById(R.id.txtvw);
                    tv.setText("success item add to cart");
                    tv.setTextColor(Color.BLUE);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 20, 40);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
                    Intent intent=new Intent(ConfirmOrderDetail.this, Cart.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {

                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {

            }
        });
//        Toast.makeText(getApplicationContext(), "Item Added To Cart Sussesfully", Toast.LENGTH_LONG).show();
//        finish();
    }
}
