package com.example.myapplication.productdetail;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.milchmom.confirmorderdetail.ConfirmOrderDetail;

import com.bumptech.glide.Glide;
import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.cart.Cart;
import com.example.myapplication.checkout.Checkout;
import com.example.myapplication.confirmorderdetail.ConfirmOrderDetail;
import com.example.myapplication.dashbord.Avl_times_deli;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.dashbord.Product;
import com.example.myapplication.dashbord.Product_unit;
import com.example.myapplication.dashbord.Product_user;
import com.example.myapplication.dashbord.bulkorder.BulkOrderFragment;
import com.example.myapplication.profile.Profile;
import com.example.myapplication.profile.View_Profile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.dashbord.Dashbord.user_id;

public class ProductDetail extends AppCompatActivity {
    TreeMap<String, String>Product_select_unit=new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> user_product_unit=new ArrayList<TreeMap<String, String>>();
    TreeMap<String, String>Product_select_timeslot=new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> user_product_timeslot=new ArrayList<TreeMap<String, String>>();
    ArrayList<String> dayorderfreq=new ArrayList<>();
    ArrayList<String> weekorderfreq=new ArrayList<>();
    public static ArrayList<String> Pincode=new ArrayList<>();
    public static final String mypreference = "mypref";
    static String user_token;
    static String token,token_val,prname,unit,pincode;
    String pin;
    ApiInterface apiInterface;
    public static final String[] MONTHS = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private static final String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    /* access modifiers changed from: private */
    public static ArrayList<String> DateArray = new ArrayList<>();
    private ArrayList<String> WeeksArray = new ArrayList<>();
    int onetimeorder=0,everyorder=0;
    static int product_alert;
    final Context context = this;
    int count = 1;
    RecyclerView customDateRV;
    TextView customedate,tv_alert,user_address,tv_price;
    RelativeLayout customedateLL;
    DateAdapter dateAdapter;
    ProgressDialog progressDoalog;
//    private List<DeliiveryTimeGetSet> deliiveryTimeGetSetList = new ArrayList();
    private ArrayList<String> deliiveryTimeGetSetList = new ArrayList();
    DeliveryTimeAdapter deliveryAdapter;
    RecyclerView deliveryTimeRecyclerView;
    ProgressBar progressBar;
    RelativeLayout progress_layout;
    ImageView downquantity;
    ImageView productdetailimage;
    TextView quantity;
    RecyclerView rvWeek;
    Spinner spinnerunits;
    Button confirmorderdetail;
    String inputDateStr;
   static String p_details,product_id,Order_type;
    String deltimeslot="";
    String p_price,p_GST,payment_amount,p_type,address="",email="",phoneNo="",user_Name="";
//    {"Units", "Pcs", "Dozen", "Gms", "Kgs", "Tonnes"};
    ArrayList<String> unitslist = new ArrayList<>();
    WeekAdapter weekAdapter;
    String img;
    String weekOrderString;

    /* access modifiers changed from: protected */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
//        getSupportActionBar().hide();
        this.productdetailimage = (ImageView) findViewById(R.id.productImage);
        spinnerunits = (Spinner) findViewById(R.id.spinnerunits);
        this.downquantity = (ImageView) findViewById(R.id.downquantity);
        this.quantity = (TextView) findViewById(R.id.quantity);
        this.quantity.setText(String.valueOf(this.count));
        this.deliveryTimeRecyclerView = (RecyclerView) findViewById(R.id.deliveryTimeRecyclerView);
        this.customedate = (TextView) findViewById(R.id.customedate);
        this.customedateLL = (RelativeLayout) findViewById(R.id.customedateLL);
        this.customDateRV = (RecyclerView) findViewById(R.id.customDateRV);
        tv_alert=findViewById(R.id.tv_alert);
        tv_price=findViewById(R.id.tv_price);
        tv_alert.setVisibility(View.INVISIBLE);
//        progressBar.setVisibility(View.GONE);
        progressBar=findViewById(R.id.progress_cyclic);
        progress_layout=findViewById(R.id.progress_layout);
        confirmorderdetail=findViewById(R.id.confirmorderdetail);
        user_address=findViewById(R.id.address);
//        this.spinnerunits.setOnItemSelectedListener(this);
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, this.unitslist);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        this.spinnerunits.setAdapter(aa);
//        String img;
        img= String.valueOf(getIntent().getStringExtra("image_url"));
        p_details=String.valueOf(getIntent().getStringExtra("p_details"));
        p_type=String.valueOf(getIntent().getStringExtra("p_type"));
        System.out.println("kjsfhdjsdfbjsbf"+img);
        Glide.with(this.productdetailimage.getContext()).load("http://app.milchmom.com:8080/"+img).into(this.productdetailimage);
//        this.productdetailimage.setImageResource(getIntent().getIntExtra("image_url", 0));
//        this.deliveryAdapter = new DeliveryTimeAdapter(this.deliiveryTimeGetSetList);
        this.deliveryTimeRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        this.deliveryTimeRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        this.deliveryTimeRecyclerView.setAdapter(this.deliveryAdapter);
//        deliveryTimeStaticData();
        this.WeeksArray.add("Monday");
        this.WeeksArray.add("Tuesday");
        this.WeeksArray.add("Wednesday");
        this.WeeksArray.add("Thursday");
        this.WeeksArray.add("Friday");
        this.WeeksArray.add("Saturday");
        this.WeeksArray.add("Sunday");
        DateArray.clear();
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Intent intent=getIntent();
        Pincode=intent.getStringArrayListExtra("pincode");
        pincode=intent.getStringExtra("pincodeChek");
        System.out.println("pincodeUser"+pincode);
//        confirmorderdetail.setClickable(false);
        progressDoalog = new ProgressDialog(ProductDetail.this);
        shared();
        int flag=0;
//        for (int i=0;i<Pincode.size();i++){
//            System.out.println("pincodeUser"+Pincode.get(i).equals(Dashbord.pin));
            if (pincode.equals(pin)){
//                flag=1;
//                if ()

                user_product();
                deliveryTimeStaticData();
////                break;
            }else {
                available();
            }
            pincode="";
//        }
//        available();
//        shared();

//        spinner();
    }

    public void shared() {
        try {
            SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_token="Berear "+token_val;
            pin=sharedPref.getString("pin", "");
            address(Integer.parseInt(pin));
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

    public void user_product(){
//        String pin=Dashbord.pin.toString();
//        String address=Dashbord.address.toString();
        progress_message();
        progress_show();
        System.out.println(pin);
//        System.out.println(address);
        Intent intent=getIntent();
        String id=intent.getStringExtra("p_id");
        System.out.println(id);
        System.out.println(user_token);
        Call<Product_unit> call=apiInterface.response_ProductView_unit(user_token,id);
        call.enqueue(new Callback<Product_unit>() {
            @Override
            public void onResponse(Call<Product_unit> call, Response<Product_unit> response) {
                progress_dismiss();
//                System.out.println("nsvsvhvdjhfgjdjf"+response.body().getUnit());
if (response.body().getUnit().size()<0){
    progress_layout.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.VISIBLE);
}else {
    progress_layout.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
}
                for (int j = 0; j < response.body().getUnit().size(); j++) {
                    prname=response.body().getUnit().get(j).getP_name();
                    product_id=response.body().getUnit().get(j).getP_id();
                    p_price= response.body().getUnit().get(j).getP_price();
                    p_GST=response.body().getUnit().get(j).getP_GST();

                    Product_select_unit=Product_select_unit(response.body().getUnit().get(j).get_id(),
                            response.body().getUnit().get(j).getP_id(),
                            response.body().getUnit().get(j).getP_name(),
                            response.body().getUnit().get(j).getP_unit(),
                            response.body().getUnit().get(j).getP_price(),
                            response.body().getUnit().get(j).getP_qty(),
                            response.body().getUnit().get(j).getP_GST(),
                            response.body().getUnit().get(j).getP_details());
                    user_product_unit.add(Product_select_unit);
                    unitslist.add(response.body().getUnit().get(j).getP_unit());
                }
                spinner();
            }
//            }
//        }

            @Override
            public void onFailure(Call<Product_unit> call, Throwable t) {

            }
        });
    }

    public void available(){
        Button btn_submit,btn_cancle;
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customdialogavailable);
        btn_submit=dialog.findViewById(R.id.customWeekOkBtn);;
        btn_cancle=dialog.findViewById(R.id.customWeekCancelBtn);
        btnVisablity();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Dashbord.class);
                startActivity(intent);
               ProductDetail.this.finish();
            }
        });
        dialog.show();
//        dialog.getWindow().setLayout(900,700);
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
                phoneNo=response.body().getUser().get(0).getUserPhone();
                address=response.body().getUser().get(0).getUserAddress();
                user_Name=response.body().getUser().get(0).getUserName();
                user_address.setText(""+address);
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

    private void deliveryTimeStaticData() {
        progress_message();
        progress_show();
        Intent intent=getIntent();
        String id=intent.getStringExtra("p_id");
//        String pin=Dashbord.pin.toString();
//        String address=Dashbord.address.toString();
        Call<Deltimeslotuser> call=apiInterface.response_FetchDelTimeSlot(pin,user_token);
        call.enqueue(new Callback<Deltimeslotuser>() {
            @Override
            public void onResponse(Call<Deltimeslotuser> call, Response<Deltimeslotuser> response) {
                progress_dismiss();
                for (int i=0;i<response.body().getDeltimeslot().size();i++){
                    System.out.println("khsfjksjkdf"+response.body().getDeltimeslot().size());
                    Product_select_timeslot=Product_select_timeslot(response.body().getDeltimeslot().get(i).get_id(),
                            response.body().getDeltimeslot().get(i).getPin_id(),
                            response.body().getDeltimeslot().get(i).getDeliTimeSlot(),
                            response.body().getDeltimeslot().get(i).get__v());
                    user_product_timeslot.add(Product_select_timeslot);
                    timeslotadapter();
                }
            }

            @Override
            public void onFailure(Call<Deltimeslotuser> call, Throwable t) {

            }
        });
//        this.deliveryAdapter.notifyDataSetChanged();
    }

    public void btnVisablity(){
        confirmorderdetail.setClickable(false);
//        confirmorderdetail.setBackgroundColor(13617076);
    }

    private void timeslotadapter(){
        deliveryAdapter = new DeliveryTimeAdapter(user_product_timeslot);
        deliveryTimeRecyclerView.setAdapter(deliveryAdapter);
    }

    public void productdetailback(View view) {
        Intent intent=new Intent(getApplicationContext(), Dashbord.class);
        startActivity(intent);
//        finish();
    }

    public void setQuantity(View view) {
        int id = view.getId();
        if (id == R.id.downquantity) {
            int i = this.count;
            if (i == 1) {
                this.downquantity.setEnabled(false);
                return;
            }
            this.count = i - 1;
            this.quantity.setText(String.valueOf(this.count));
        } else if (id == R.id.upquantity) {
            this.count++;
            this.quantity.setText(String.valueOf(this.count));
            if (this.count >= 1) {
                this.downquantity.setEnabled(true);
            }
            if (this.count>5){
                confirmorderdetail.setVisibility(View.INVISIBLE);
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                Fragment hello = new BulkOrderFragment();
//                fragmentTransaction.addToBackStack((String) null);
//                fragmentTransaction.replace(R.id.rel_bulk, hello, "HELLO");
//                fragmentTransaction.commit();
                Intent intent=new Intent(getApplicationContext(), BulkOrderFragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                count=1;
                this.quantity.setText(String.valueOf(this.count));
            }
        }
    }

//    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//    }
//
//    public void onNothingSelected(AdapterView<?> adapterView) {
//    }

    public void orderFrequency(View view) {
        confirmorderdetail.setVisibility(View.VISIBLE);
        TextView onetime = (TextView) findViewById(R.id.onetime);
        TextView everyday = (TextView) findViewById(R.id.everyday);
        TextView weekly = (TextView) findViewById(R.id.weekly);
        switch (view.getId()) {
            case R.id.customedate /*2131361935*/:
                this.customedate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ticgreen, 0, 0, 0);
                onetime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                everyday.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                weekly.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                Log.d("tag", "onClickproduct: " + this.DateArray);
                customeDateforDelivery();
                onetimeorder=0;
                everyorder=0;
                return;
            case R.id.everyday /*2131361969*/:
                everyday.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ticgreen, 0, 0, 0);
                onetime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                weekly.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                this.customedate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                this.customedate.setVisibility(View.VISIBLE);
                this.customedateLL.setVisibility(View.GONE);
                this.DateArray.clear();
                everyorder=15;
                onetimeorder=0;
                if ((product_alert)<18){
                    tv_alert.setVisibility(View.VISIBLE);
                    btnVisablity();
                }else {
                    tv_alert.setVisibility(View.VISIBLE);
                    tv_alert.setText("Your product is now ready to buy");
                    confirmorderdetail.setClickable(true);
                }
//                product_alert=0;
                everyDay();
                return;
            case R.id.onetime /*2131362077*/:
                onetime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ticgreen, 0, 0, 0);
                everyday.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                weekly.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                this.customedate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                this.customedate.setVisibility(View.VISIBLE);
                this.customedateLL.setVisibility(View.GONE);
                this.DateArray.clear();
                onetimeorder=1;
                everyorder=0;
                if (((product_alert))<7){
                    tv_alert.setVisibility(View.VISIBLE);
                    btnVisablity();
                }else {
                    tv_alert.setVisibility(View.VISIBLE);
                    tv_alert.setText("Your product is now ready to buy");
                    confirmorderdetail.setClickable(true);
                }
//                product_alert=0;
                OneTime();
                return;
            case R.id.weekly /*2131362259*/:
                weekly.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ticgreen, 0, 0, 0);
                onetime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                everyday.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                this.customedate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tickgrey, 0, 0, 0);
                this.customedate.setVisibility(View.VISIBLE);
                this.customedateLL.setVisibility(View.GONE);
                this.DateArray.clear();
                onetimeorder=0;
                everyorder=0;
                weekdays();
                return;
            default:
                return;
        }
    }

 private void OneTime(){

 }

 private void everyDay(){

 }

private void spinner(){
        product_alert=0;
    ArrayAdapter<String> aa = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, unitslist);
//    aa.setDropDownViewResource(R.layout.select_dialog_singlechoice_material);
    spinnerunits.setAdapter(aa);
    spinnerunits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            ((TextView)parent.getChildAt(0)).setTextColor(Color.BLACK);
            unit=spinnerunits.getSelectedItem().toString();
            System.out.println(spinnerunits.getSelectedItem());
            prname=user_product_unit.get(position).get("p_name");
            product_id=user_product_unit.get(position).get("p_id");
            p_price=user_product_unit.get(position).get("p_price");
            p_GST=user_product_unit.get(position).get("p_GST");
            product_alert= Integer.parseInt(user_product_unit.get(position).get("p_qty"));
            tv_price.setText("MRP :"+p_price);
//            tv_price.setTextColor(15222288);
//            p_details=user_product_unit.get(position).get("p_details");
//            ((TextView) parent.getChildAt(0)).setTextColor(0x00000000);
System.out.println("product_alert"+product_alert);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
}
int count_custom;
private void cal(){
        System.out.println("iojfdsfjsdjfo"+DateArray);
    System.out.println(everyorder);
    System.out.println(onetimeorder);
    if (onetimeorder!=0){
//        System.out.println("iojfdsfjsdjfo"+DateArray);
//        dayorderfreq.add(String.valueOf(onetimeorder));
        Order_type="onetimeorder";

    }else if (everyorder!=0) {
//        dayorderfreq.add(String.valueOf(everyorder));

        Order_type="everyorder";
    }



    Intent intent=new Intent(getApplicationContext(), ConfirmOrderDetail.class);
    intent.putExtra("image_url",img);
    intent.putExtra("count",this.count);
    System.out.println("countqty"+dayorderfreq);
    intent.putExtra("deltimeslot",deltimeslot);
    intent.putStringArrayListExtra("customDate",dayorderfreq);
    intent.putExtra("onetimeorder",onetimeorder);
    intent.putExtra("everyorder",everyorder);
    intent.putStringArrayListExtra("weekorderfreq",weekorderfreq);
//    intent.putExtra("weekAdapter",dayorderfreq);
    intent.putExtra("proname",prname);
    intent.putExtra("p_details",p_details);
    intent.putExtra("product_id",product_id);
    intent.putExtra("time_slot",deliveryAdapter.delitimeslot());
    intent.putExtra("unit",unit);
    intent.putExtra("p_price",p_price);
    intent.putExtra("p_GST",p_GST);
    intent.putExtra("Order_type",Order_type);
    intent.putExtra("p_type",p_type);

    System.out.println("deltimeslot"+(dayorderfreq.isEmpty() || weekorderfreq.isEmpty() || onetimeorder==0 || everyorder==0));

        startActivity(intent);
//    }
//    if (weekAdapter.getWeeksSelectedArray().size()<0){
//        intent.putExtra("weekAdapter","");
//    }else {
//        intent.putExtra("weekAdapter",weekAdapter.getWeeksSelectedArray());
//    }

}


    public void Edit_Address(View view){
        startActivity(new Intent(this, Profile.class));
    }

    private void weekdays() {
        if ((product_alert)<18){
            tv_alert.setVisibility(View.VISIBLE);
            btnVisablity();
        }else {
            tv_alert.setVisibility(View.VISIBLE);
            tv_alert.setText("Your product is now ready to buy");
            confirmorderdetail.setClickable(true);
        }
//        product_alert=0;
        final Dialog dialog = new Dialog(this.context);
        dialog.setContentView(R.layout.customdialogweeks);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        this.rvWeek = (RecyclerView) dialog.findViewById(R.id.rvWeek);
        this.weekAdapter = new WeekAdapter(this.WeeksArray);
        this.rvWeek.setLayoutManager(new GridLayoutManager(this, 3));
        this.rvWeek.setItemAnimator(new DefaultItemAnimator());
        this.rvWeek.setAdapter(this.weekAdapter);
        ((Button) dialog.findViewById(R.id.customWeekOkBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ProductDetail.this.weekAdapter.getWeeksSelectedArray().isEmpty()) {
                    Toast.makeText(ProductDetail.this.getApplicationContext(), "select a day", Toast.LENGTH_LONG).show();
                    return;
                }
//                if ()

//                if (Build.VERSION.SDK_INT >= 26) {
//                    ProductDetail productDetail = ProductDetail.this;
//                    productDetail.weekOrderString = String.join(" ", new CharSequence[]{productDetail.weekAdapter.getWeeksSelectedArray().toString()});
                if(weekAdapter.getWeeksSelectedArray().size()>=5){
                    Toast.makeText(ProductDetail.this.getApplicationContext(),"weekOrder only four possible",Toast.LENGTH_LONG).show();
                    System.out.println("weekOrderString"+weekAdapter.getWeeksSelectedArray().toString());
                }else{
                    System.out.println("weekOrderString"+weekAdapter.getWeeksSelectedArray().toString());
                    dialog.dismiss();
                }
//                }
//
            }
        });

        ((Button) dialog.findViewById(R.id.customWeekCancelBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout(800,850);
        dialog.show();
    }

    private void customeDateforDelivery()   {
        if ((product_alert)<18){
            tv_alert.setVisibility(View.VISIBLE);
            btnVisablity();
        }else {
            tv_alert.setVisibility(View.VISIBLE);
            tv_alert.setText("Your product is now ready to buy");
            confirmorderdetail.setClickable(true);
        }
//        product_alert=0;
        Calendar calender = Calendar.getInstance();
        @SuppressLint("WrongConstant") DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                inputDateStr = dayOfMonth + "-" + ProductDetail.MONTHS[monthOfYear] + "-" + year;
                Log.d("sufifn", inputDateStr);
//                System.out.println(ProductDetail.this.DateArray.size());
                if (ProductDetail.this.DateArray.size()==0){
                        ProductDetail.this.DateArray.add(inputDateStr);
                    ProductDetail.this.customedate.setVisibility(View.GONE);
                    ProductDetail.this.customedateLL.setVisibility(View.VISIBLE);
                    ProductDetail productDetail = ProductDetail.this;
                    productDetail.dateAdapter = new DateAdapter(productDetail.DateArray);
                    ProductDetail.this.customDateRV.setLayoutManager(new LinearLayoutManager(ProductDetail.this.getApplicationContext(), 0, false));
                    ProductDetail.this.customDateRV.setItemAnimator(new DefaultItemAnimator());
                    ProductDetail.this.customDateRV.setAdapter(ProductDetail.this.dateAdapter);
//                    }
//                }
                }else {
                    int flag=0;
                    for (int i=0;i<DateArray.size();i++) {
                        System.out.println(DateArray.get(i).startsWith(inputDateStr));
                        if (DateArray.get(i).startsWith(inputDateStr)) {
                            flag = 1;
                            break;
                        }
                    }

//                        else {
                    if (flag==1){
                        Toast.makeText(getApplicationContext(), "Selected date duplicate", Toast.LENGTH_LONG).show();
                    }else {
                        DateArray.add(inputDateStr);
                    }
                            System.out.println(DateArray);
                ProductDetail.this.customedate.setVisibility(View.GONE);
                    ProductDetail.this.customedateLL.setVisibility(View.VISIBLE);
                    ProductDetail productDetail = ProductDetail.this;
                    productDetail.dateAdapter = new DateAdapter(productDetail.DateArray);
                    ProductDetail.this.customDateRV.setLayoutManager(new LinearLayoutManager(ProductDetail.this.getApplicationContext(), 0, false));
                    ProductDetail.this.customDateRV.setItemAnimator(new DefaultItemAnimator());
                    ProductDetail.this.customDateRV.setAdapter(ProductDetail.this.dateAdapter);
//                        }
//                    }
                }
            }
        }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DATE));
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dpd.show();
    }

    public void calanderIV(View view) {
        customeDateforDelivery();
    }

    public void productincart(View view) {
//    add_cart();
//    time_slot=deliveryAdapter.delitimeslot();
//    System.out.println("dgfdgfhfgdhdf"+time_slot);
//        Call<AddCart> call=apiInterface.response_Cart(p_details,this.count,time_slot,dayorderfreq,user_id,String.valueOf(payment_amount),product_id,img,String.valueOf(p_price),String.valueOf(p_GST),user_token);
//        call.enqueue(new Callback<AddCart>() {
//            @Override
//            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
//                if (response.body().getStatus()=="success"){
                    startActivity(new Intent(ProductDetail.this, Cart.class));
//                }else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AddCart> call, Throwable t) {
//
//            }
//        });
    }

    public void confirmorderdetail(View view) {
        deltimeslot= deliveryAdapter.delitimeslot();
        System.out.println("deltimeslotdayo"+(dayorderfreq.isEmpty() && weekorderfreq.isEmpty() && onetimeorder==0 && everyorder==0));
        System.out.println("deltimeslotdayo"+(dayorderfreq));
        System.out.println("deltimeslotdayo"+( weekorderfreq));
        try{
        if (DateArray.size()>0) {
            weekorderfreq.clear();
            for (int i = 0; i < DateArray.size(); i++) {
                dayorderfreq.add(DateArray.get(i));
            }
            System.out.println("dayorderfreq"+dayorderfreq);
            Order_type="CustomDate";
        }else if (weekAdapter.getWeeksSelectedArray().size()>0){
            dayorderfreq.clear();
            DateArray.clear();
            for (int i=0;i<weekAdapter.getWeeksSelectedArray().size();i++){
                weekorderfreq.add(weekAdapter.getWeeksSelectedArray().get(i));
                System.out.println("weekorderfreq"+weekorderfreq);
            }

            Order_type="weekly";
        }
        }catch (Exception e){

        }
        if (deltimeslot.isEmpty()) {

            Toast.makeText(ProductDetail.this.getApplicationContext(),"Please select delivery time",Toast.LENGTH_LONG).show();

        }else if (dayorderfreq.isEmpty() && weekorderfreq.isEmpty() && onetimeorder==0 && everyorder==0){
            Toast.makeText(ProductDetail.this.getApplicationContext(),"Please select Frequency field",Toast.LENGTH_LONG).show();
        }else {
        cal();
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
//                order_cancle();
                Intent intent=new Intent(getApplicationContext(), Dashbord.class);
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
    public static TreeMap<String, String> Product_select_unit(String id, String p_id, String p_name, String p_unit,
                                                              String p_price, String p_qty, String p_GST,String p_details) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("_id", String.valueOf(id));
        treeMap.put("p_id", String.valueOf(p_id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_unit",p_unit);
        treeMap.put("p_price",p_price);
        treeMap.put("p_qty",p_qty);
        treeMap.put("p_GST",p_GST);
        treeMap.put("p_details",p_details);

        //
        return treeMap;
    }
    public static TreeMap<String, String> Product_select_timeslot(String id, String pin_id, String DeliTimeSlot, String __v) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("_id", String.valueOf(id));
        treeMap.put("pin_id", String.valueOf(pin_id));
        treeMap.put("DeliTimeSlot", DeliTimeSlot);
        treeMap.put("__v",__v);
//        treeMap.put("p_price",p_price);
//        treeMap.put("p_qty",p_qty);
//        treeMap.put("p_GST",p_GST);

        //
        return treeMap;
    }
}
