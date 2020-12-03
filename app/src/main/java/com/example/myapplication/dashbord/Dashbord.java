package com.example.myapplication.dashbord;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.AboutUs.AboutUs;
import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.Discount.Discount;
import com.example.myapplication.Discount.Offers;
import com.example.myapplication.R;
import com.example.myapplication.RateUs.RateUs;
import com.example.myapplication.Search.Search;
import com.example.myapplication.cart.Cart;
import com.example.myapplication.cart.CartData;
import com.example.myapplication.checkout.Checkout;
import com.example.myapplication.dashbord.Emergeny.Emergency;
import com.example.myapplication.dashbord.bulkorder.BulkOrderFragment;
import com.example.myapplication.login.Login;
import com.example.myapplication.login.Login_sta;
import com.example.myapplication.orderhistory.OrderHistory;
import com.example.myapplication.productdetail.AddCart;
import com.example.myapplication.productdetail.ProductDetail;
import com.example.myapplication.profile.Profile;
import com.example.myapplication.profile.View_Profile;
//import com.example.milchmom.RateUs.RateUs;
//import com.example.milchmom.cart.Cart;
//import com.example.milchmom.dashbord.bulkorder.BulkOrderFragment;
//import com.example.milchmom.orderhistory.OrderHistory;
//import com.example.milchmom.profile.Profile;
//import com.example.milchmom.support.Support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.stream.Collectors;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.verifyotp.VerifyOtp.mypreference;

//import me.relex.circleindicator.CircleIndicator;

public class Dashbord extends AppCompatActivity {
//    private static String[] IMAGES;
    public ArrayList<String>IMAGES=new ArrayList<>();
    TreeMap<String, String> product_slider = new TreeMap<String, String>();
    TreeMap<String, String> user_single_product = new TreeMap<String, String>();
    TreeMap<String, ArrayList>Product_select_pincode=new TreeMap<String, ArrayList>();
    TreeMap<String, ArrayList>Product_popular_pincode=new TreeMap<String, ArrayList>();
    TreeMap<String, ArrayList>Product_new_pincode=new TreeMap<String, ArrayList>();
    TreeMap<String, String> product_new_arivel = new TreeMap<String, String>();
    TreeMap<String, String> product_popular = new TreeMap<String, String>();
    TreeMap<String, String> product_bulk_order = new TreeMap<String, String>();
    TreeMap<String, String> add_profile = new TreeMap<String, String>();

     ArrayList<TreeMap<String,String>> user_profile=new ArrayList<TreeMap<String, String>>();
     ArrayList<TreeMap<String,ArrayList>> user_pincode=new ArrayList<TreeMap<String, ArrayList>>();
     ArrayList<TreeMap<String,ArrayList>> user_pincode_popular=new ArrayList<TreeMap<String, ArrayList>>();
     ArrayList<TreeMap<String,ArrayList>> user_pincode_new=new ArrayList<TreeMap<String, ArrayList>>();
     ArrayList<TreeMap<String,String>>user_product=new ArrayList<TreeMap<String, String>>();
     ArrayList<TreeMap<String,String>> user_product_unit=new ArrayList<TreeMap<String, String>>();
     ArrayList<TreeMap<String,String>>new_arivel=new ArrayList<TreeMap<String, String>>();
     ArrayList<TreeMap<String,String>>popular_product =new ArrayList<TreeMap<String, String>>();
     ArrayList<TreeMap<String,String>> bulk_order=new ArrayList<TreeMap<String, String>>();
     ArrayList<String> product_arr=new ArrayList<>();
    ArrayList<String> product_arr_unique=new ArrayList<>();
    Map Product_name = new HashMap();
    public static final String mypreference = "mypref";
    public static String user_token,user_id,email="",phoneNo="",user_Name="";
    public static final String CHANNEL_ID="Milchmom customer app";
    public static String token="",address="";
    String pin;
    public static int NUM_PAGES = 0;
    /* access modifiers changed from: private */
    public static int currentPage = 0;
    /* access modifiers changed from: private */
    public static ViewPager mPager;
    RelativeLayout reltlayout;
    private ArrayList<String> ImagesArray = new ArrayList<>();
    AdapterViewFlipper avfImageFlipper;
    final Context context = this;
    DrawerLayout drawer;
    ImageView navMenu;
    private NewArrivalsAdapter newArrivalsAdapter;
    Button btn_change;
    ArrayList<NewArrivalsGetSet> newArrivalsGetSetArrayList = new ArrayList<>();
    private PopularProductAdapterDemo popularProductAdapterDemo;
    private List<PopularProductGetSet> popularProductGetSets1 = new ArrayList();
    private ProductAdapterDemo productAdapterDemo;
    private ProfileAdapter profileAdapter;
    private List<ProductGeterSeter> productGeterSeterList1 = new ArrayList();
    private RecyclerView recyclerViewPopularProduct;
    private RecyclerView recyclerViewProduct;
    private RecyclerView recyclerViewchangelocation;
    private RecyclerView.LayoutManager layoutManager;
   private TextView user_name,user_email,txt_welcome;
   private static TextView cart_count;
   AutoCompleteTextView search;
    ApiInterface apiInterface;
    ProgressDialog progressDoalog;
    static int access$008() {
        int i = currentPage;
        currentPage = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
//    @SuppressLint("WrongConstant")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navMenu = (ImageView) findViewById(R.id.nav_menu);
        btn_change=findViewById(R.id.btn_change);
        mPager = (ViewPager) findViewById(R.id.pager);
        txt_welcome=findViewById(R.id.txt_welcome);
        cart_count=(TextView) findViewById(R.id.cart_count);
        this.navMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Dashbord.this.drawer.openDrawer((int) GravityCompat.START);
            }
        });

        this.drawer.closeDrawer((int) GravityCompat.START);
//        bannerSlider();

        this.recyclerViewProduct = (RecyclerView) findViewById(R.id.recycler_view_product);
//        this.productAdapterDemo = new ProductAdapterDemo(user_product);
        this.recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        this.recyclerViewProduct.setItemAnimator(new DefaultItemAnimator());
//        this.recyclerViewProduct.setAdapter(this.productAdapterDemo);
        this.recyclerViewPopularProduct = (RecyclerView) findViewById(R.id.recycler_view_popular_product);
//        this.popularProductAdapterDemo = new PopularProductAdapterDemo(this.popularProductGetSets1);
        this.recyclerViewPopularProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        this.recyclerViewPopularProduct.setItemAnimator(new DefaultItemAnimator());
//        this.recyclerViewPopularProduct.setAdapter(this.popularProductAdapterDemo);
        this.avfImageFlipper = (AdapterViewFlipper) findViewById(R.id.avfImageFlipper);
//        this.newArrivalsAdapter = new NewArrivalsAdapter(this, this.newArrivalsGetSetArrayList);
//        this.avfImageFlipper.setAdapter(this.newArrivalsAdapter);
//        productStaticData();
//        popularProductStaticData();
//        newArrivalStaticData1();

        SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
        token = sharedPref.getString("token", "");
        user_id = sharedPref.getString("u_id", "");
        address=sharedPref.getString("address", "");
        txt_welcome.setText(""+address);
        System.out.println(token);
        user_token="Berear "+token;
        System.out.println("knsfjksgf"+user_token);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
        search=findViewById(R.id.search);
        reltlayout=findViewById(R.id.layout);
        progressDoalog = new ProgressDialog(Dashbord.this);
        search();
        IMAGES.clear();
//        Cart.user_cart.clear();
//        Cart.product_cart.clear();
        if (!isNetworkAvailable()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("You are not online!!!!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    startActivity(context,Dashbord.class);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }else {
            user_product();
            popular_product();
            change_location();
            slider_banner();
            new_arrivel();
            address_dialog();
//        address();
            Discount();
address();
        }
    }

    String header="application/json";
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void search(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.product_autocomplete_row, R.id.text_view_list_item, product_arr_unique);
        search.setAdapter(arrayAdapter);
        System.out.println("product_arr_unique"+product_arr_unique);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Set set1 = Product_name.entrySet();
                Iterator iterator1 = set1.iterator();
                while (iterator1.hasNext()) {
                    Map.Entry entry1 = (Map.Entry) iterator1.next();
                    String client_id = entry1.getValue().toString();


                    //                System.out.println(client_id);
                    if (search.getText().toString().equals(client_id)) {
                        Intent intent=new Intent(Dashbord.this, Search.class);
                        intent.putExtra("search_key",search.getText().toString());
                        startActivity(intent);
                                            System.out.println("nvkxgvhjsdgfksbfckj"+client_id);
                        //                    Map.Entry entry=(Map.Entry) iterator1.next();
                        //                    if (entry1.getKey().toString().equals(entry.getKey().toString())) {
//                        while (iterator1.hasNext()) {
//                            Map.Entry entry = (Map.Entry) iterator1.next();
//                            if (entry.getKey().toString().equals(search.getText().toString())) {
//                                String client_na = entry.getValue().toString();
//
//                                System.out.println("nvkxgvhjsdgfksbfckj" + client_na);
//                                search.setText(entry.getValue().toString());
//
////                                Intent intent=new Intent(Dashbord.this, Search.class);
////                        startActivity(intent);
//                            }
//                        }
                        //                        draft_http();
                    }

                }
//                for (int i=0;i<product_arr_unique.size();i++){
//                    if (search.getText().toString().equals(product_arr_unique.get(i))){
//                        search.setText(""+product_arr_unique.get(i));
//                        System.out.println(s);
//                        Intent intent=new Intent(Dashbord.this, Search.class);
//                        startActivity(intent);
//                    }
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

//System.out.println(s);
            }
        });
    }

    public void progress_show(){
        progressDoalog.show();
    }
    public void progress_dismiss(){
        progressDoalog.dismiss();
    }
    public void progress_message(){
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("Thank you for give some time");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }
    public void user_product(){
        progress_message();
        progress_show();
        Call<Product_user> call=apiInterface.response_ProductView(user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<Product_user>() {

            @Override
            public void onResponse(Call<Product_user> call, Response<Product_user> response) {
                progress_dismiss();
//                System.out.println("nsvsvhvdjhfgjdjf"+response.body().getProduct().getProduct_arrqty());
                for (int i=0;i<response.body().getProduct().getProduct_arrqty().size();i++) {
                    System.out.println(response.body().getProduct().getProduct_arrqty().get(i).getP_img());
//                    for (int j = 0; j < response.body().getProduct().getProduct_arrqty().size(); j++) {
//                        if (response.body().getProduct().getProduct_arrqty().get(i).get_id() == response.body().getProduct().getProduct_qty().get(i).get_id()) {
                    if (response.body().getProduct().getProduct_arrqty().get(i).getP_popularity().equals("normal")) {
                        user_single_product = Product_select(response.body().getProduct().getProduct_arrqty().get(i).get_id(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_name(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_details(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_mfg(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_exp(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_type(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_price(),
                                response.body().getProduct().getProduct_arrqty().get(i).getProductQty(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_img());
                        product_arr.add(response.body().getProduct().getProduct_arrqty().get(i).getP_name());
                        user_product.add(user_single_product);
                        Product_name.put(response.body().getProduct().getProduct_arrqty().get(i).get_id(),response.body().getProduct().getProduct_arrqty().get(i).getP_name());
                        Product_select_pincode=Product_select_pincode(response.body().getProduct().getProduct_arrqty().get(i).getPincode());
                        user_pincode.add(Product_select_pincode);

                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        product_arr_unique=(ArrayList) product_arr.stream().distinct().collect(Collectors.toList());
                    }
                    search();
                }
                System.out.println("user_product"+product_arr_unique);
                productStaticData();

            }

            @Override
            public void onFailure(Call<Product_user> call, Throwable t) {

            }
        });
    }

    public void slider_banner() {
        progress_message();
        progress_show();
        Call<Slider_product> call = apiInterface.response_ProductBanner(user_token);
        call.enqueue(new Callback<Slider_product>() {
                @Override
            public void onResponse(Call<Slider_product> call, Response<Slider_product> response) {
//                slider_product_select
                    progress_dismiss();
                for (int i=0;i<response.body().getSlider().size();i++){
                    IMAGES.add(response.body().getSlider().get(i).getP_img());
                }
                bannerSlider();
            }

            @Override
            public void onFailure(Call<Slider_product> call, Throwable t) {

            }
        });
    }

    private void change_location(){
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn_submit,btn_cancle;
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setCanceledOnTouchOutside(false);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.customdialogchangepin);
                btn_submit=dialog.findViewById(R.id.customWeekOkBtn);;
                btn_cancle=dialog.findViewById(R.id.customWeekCancelBtn);
                recyclerViewchangelocation=dialog.findViewById(R.id.rvlocation);
                recyclerViewchangelocation.setHasFixedSize(true);
                layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerViewchangelocation.setLayoutManager(layoutManager);
                change_pin();
                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Dashbord.this.profileAdapter.getWeeksSelectedArray();
//                        System.out.println(profileAdapter.getWeeksSelectedArray());
                        try {
                            if(profileAdapter.getWeeksSelectedArray().isEmpty()){
//                            Intent intent=new Intent(Dashbord.this,Dashbord.class);
//                            startActivity(intent);

                                user_profile.clear();
                                dialog.dismiss();
                            }else {
                                pin=profileAdapter.getWeeksSelectedArray().toString();
                                address=profileAdapter.getWeeksSelectedAddress().toString();
                                txt_welcome.setText(""+address);
                                sharedPin(pin);
//                            address(Integer.parseInt(pin));
                                System.out.println(pin);
                                System.out.println("jkxhckjvbjcvxkjv"+profileAdapter.getWeeksSelectedArray().toString());
                                user_profile.clear();
                                dialog.dismiss();
                            }
                        }catch (Exception e){
                            user_profile.clear();
                            dialog.dismiss();
                        }

                    }
                });
                btn_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user_profile.clear();
                        dialog.dismiss();
                    }
                });
                dialog.show();
//                dialog.getWindow().setLayout(900,700);
            }
        });
    }

    private void sharedPin(String pin){
        SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("pin", pin);
        editor.putString("address", address);
//        Intent intent=new Intent(getApplicationContext(), Dashbord.class);
//        startActivity(intent);
        editor.commit();
    }
    private void change_pin() {
        progress_message();
        progress_show();
        System.out.println("jkwdhkwgfekresponse"+user_id);
        Call<View_Profile> call = apiInterface.response_UserProfile(user_token,user_id);

        call.enqueue(new Callback<View_Profile>() {

            @Override
            public void onResponse(Call<View_Profile> call, Response<View_Profile> response) {
                progress_dismiss();
                System.out.println("jkwdhkwgfekresponse"+response.body().getMessage());
                for (int i=0;i<response.body().getUser().size();i++){
                    add_profile=profile_view_user(response.body().getUser().get(i).getU_id(),
                            response.body().getUser().get(i).getUserName(),
                            response.body().getUser().get(i).getUserAddress(),
                            response.body().getUser().get(i).getUserPin(),
                            response.body().getUser().get(i).getAge(),
                            response.body().getUser().get(i).get_id(),
                            response.body().getUser().get(i).getUserPhone(),
                            response.body().getUser().get(i).getEmail());
                    user_profile.add(add_profile);
                }
                profileAdapter=new ProfileAdapter(Dashbord.this,user_profile);
                recyclerViewchangelocation.setAdapter(profileAdapter);
            }

            @Override
            public void onFailure(Call<View_Profile> call, Throwable t) {

            }
        });
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
                txt_welcome.setText(""+response.body().getUser().get(0).getUserAddress());
                email=response.body().getUser().get(0).getEmail();
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

    public void product(){

    }

    public void popular_product() {
        progress_message();
        progress_show();
        Call<Product_user> call = apiInterface.response_ProductPopular(user_token);

        call.enqueue(new Callback<Product_user>() {

            @Override
            public void onResponse(Call<Product_user> call, Response<Product_user> response) {
                progress_dismiss();
                for (int i=0;i<response.body().getProduct().getProduct_arrqty().size();i++) {
//                    if (response.body().getProduct().getProduct_arrqty().get(i).getP_popularity().equals(null)){
//
//                    }else {


                    if (response.body().getProduct().getProduct_arrqty().get(i).getP_popularity().equals("popular")) {
                        System.out.println(response.body().getProduct().getProduct_arrqty().get(i).getP_img());
                        product_popular = popular_Product_select(response.body().getProduct().getProduct_arrqty().get(i).get_id(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_name(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_details(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_mfg(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_exp(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_type(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_price(),
                                response.body().getProduct().getProduct_arrqty().get(i).getProductQty(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_img());
                        popular_product.add(product_popular);
                        Product_popular_pincode=Product_select_pincode(response.body().getProduct().getProduct_arrqty().get(i).getPincode());
                        user_pincode_popular.add(Product_popular_pincode);
                        System.out.println("Product_popular_pincode"+user_pincode_popular);
                    }
                }
//                }
                popularProductStaticData();
            }

            @Override
            public void onFailure(Call<Product_user> call, Throwable t) {

            }
        });
    }

    public void new_arrivel() {
        progress_message();
        progress_show();
        Call<Product_user> call = apiInterface.response_ProductNewArrivel(user_token);

        call.enqueue(new Callback<Product_user>() {

            @Override
            public void onResponse(Call<Product_user> call, Response<Product_user> response) {
                progress_dismiss();
                System.out.println(response.body().getProduct().getProduct_arrqty().size());
                for (int i=0;i<response.body().getProduct().getProduct_arrqty().size();i++) {
                    if (response.body().getProduct().getProduct_arrqty().get(i).getP_popularity().equals("new arrival")) {

                        Dashbord.this.newArrivalsGetSetArrayList.add(new NewArrivalsGetSet(
                                response.body().getProduct().getProduct_arrqty().get(i).get_id(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_name(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_details(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_mfg(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_exp(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_type(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_price(),
                                response.body().getProduct().getProduct_arrqty().get(i).getProductQty(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_img()));
                        Product_new_pincode=Product_select_pincode(response.body().getProduct().getProduct_arrqty().get(i).getPincode());
                        user_pincode_new.add(Product_new_pincode);
//
                    }
                }
                newArrivalStaticData1();

            }

            @Override
            public void onFailure(Call<Product_user> call, Throwable t) {

            }
        });
                    }

    private void newArrivalStaticData1() {
        if(user_pincode_new.size()==0){
//            reltlayout.setVisibility(View.INVISIBLE);
        }else {
//            reltlayout.setVisibility(View.VISIBLE);
            this.newArrivalsAdapter = new NewArrivalsAdapter(this, this.newArrivalsGetSetArrayList,this.user_pincode_new);
            this.avfImageFlipper.setAdapter(this.newArrivalsAdapter);
            Dashbord.this.newArrivalsAdapter.notifyDataSetChanged();
        }

    }

    public void flipperHandler(View view) {
        switch (view.getId()) {
            case R.id.btnNext /*2131361873*/:
                this.avfImageFlipper.stopFlipping();
                this.avfImageFlipper.setInAnimation(getApplicationContext(), R.animator.right_in);
                this.avfImageFlipper.setOutAnimation(getApplicationContext(), R.animator.left_out);
                this.avfImageFlipper.showNext();
                return;
            case R.id.btnPrevious /*2131361874*/:
                this.avfImageFlipper.stopFlipping();
                this.avfImageFlipper.setInAnimation(getApplicationContext(), R.animator.left_in);
                this.avfImageFlipper.setOutAnimation(getApplicationContext(), R.animator.right_out);
                this.avfImageFlipper.showPrevious();
                return;
            default:
                return;
        }
    }

    private void popularProductStaticData() {
        this.popularProductAdapterDemo = new PopularProductAdapterDemo(this,popular_product,user_pincode_popular);
        this.recyclerViewPopularProduct.setAdapter(this.popularProductAdapterDemo);
    }

    private void productStaticData() {
        productAdapterDemo=new ProductAdapterDemo(this,user_product,user_pincode);
        recyclerViewProduct.setAdapter(productAdapterDemo);
    }

    private void address(){
        Call<CartData> call=apiInterface.response_fetchFromCart(user_id,user_token);
        call.enqueue(new Callback<CartData>() {
            @Override
            public void onResponse(Call<CartData> call, Response<CartData> response) {
                System.out.println(response.body().getCart_item());
                progress_dismiss();
                if (response.body().getStatus().equals("No data found")){
//cart_count.setText("0");
                    cart_count.setVisibility(View.GONE);
                }else {
                    cart_count.setVisibility(View.VISIBLE);
                    cart_count.setText(""+response.body().getCart_item().size());
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
    private void bannerSlider() {
        int i = 0;
        while (true) {
//            String[] numArr = IMAGES;
            ArrayList<String>numArr=IMAGES;
            System.out.println(numArr);
            if (i < IMAGES.size()) {
                this.ImagesArray.add(numArr.get(i));
                i++;
            } else {

                mPager.setAdapter(new ItemDisplaySlideAdapter(this, this.ImagesArray));
                CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
                indicator.setViewPager(mPager);
                float f = getResources().getDisplayMetrics().density;
                NUM_PAGES = IMAGES.size();
                final Handler handler = new Handler();
                final Runnable Update = new Runnable() {
                    public void run() {
                        if (Dashbord.currentPage == Dashbord.NUM_PAGES) {
                            int unused = Dashbord.currentPage = 0;
                        }
                        Dashbord.mPager.setCurrentItem(Dashbord.access$008(), true);
                    }
                };
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        handler.post(Update);
                    }
                }, 4000, 4000);
                indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    public void onPageSelected(int position) {
                        int unused = Dashbord.currentPage = position;
                    }

                    public void onPageScrolled(int pos, float arg1, int arg2) {
                    }

                    public void onPageScrollStateChanged(int pos) {
                    }
                });
                return;
            }
        }
    }

    public void navProfile(MenuItem item) {
        startActivity(new Intent(this, Profile.class));
        this.drawer.closeDrawer((int) GravityCompat.START);
    }

    public void navRateUs(MenuItem item) {
        startActivity(new Intent(this, RateUs.class));
        this.drawer.closeDrawer((int) GravityCompat.START);
    }

    public void navAboutUs(MenuItem item) {
        startActivity(new Intent(this, AboutUs.class));
        this.drawer.closeDrawer((int) GravityCompat.START);
    }

    public void offer(MenuItem item){
        startActivity(new Intent(this, Offers.class));
        this.drawer.closeDrawer((int) GravityCompat.START);
    }

    public void logout(MenuItem item){
        SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(this, Login.class));
    }

    public void navSupport(MenuItem item) {
//        startActivity(new Intent(this, Support.class));
        this.drawer.closeDrawer((int) GravityCompat.START);
    }

    public void navOrderhistory(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), OrderHistory.class));
        this.drawer.closeDrawer((int) GravityCompat.START);
    }

    public void carticon(View view) {
        startActivity(new Intent(getApplicationContext(), Cart.class));
    }

    public void bulkOrderBtn(View view) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        Fragment hello = new BulkOrderFragment();
//        fragmentTransaction.addToBackStack((String) null);
//        fragmentTransaction.replace(R.id.framelayout_bulkorder, hello, "HELLO");
//        fragmentTransaction.commit();
        startActivity(new Intent(getApplicationContext(), BulkOrderFragment.class));
    }
public void emergencyOrderBtn(View view){
    startActivity(new Intent(getApplicationContext(), Emergency.class));
}
    public void customDialog() {
        final Dialog dialog = new Dialog(this.context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_ad);//17170445
        dialog.setContentView(R.layout.custom_dialog_newuser);
        ((ImageView) dialog.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.applynow)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(Dashbord.this,Offers.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void customAddressDialog() {
        final Dialog dialog = new Dialog(this.context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_ad);//17170445
        dialog.setContentView(R.layout.custom_dialog_newuseraddress);

        ((Button) dialog.findViewById(R.id.applynow)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(Dashbord.this,Profile.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void Discount() {
        progress_message();
        progress_show();
        Call<Discount> call = apiInterface.response_UserDiscountData(user_id,user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<Discount>() {

            @Override
            public void onResponse(Call<Discount> call, Response<Discount> response) {
                progress_dismiss();
                String apply="";

//if (!response.body().getproduct_discount().equals(null)){
//
//}else {


                System.out.println("Applied"+response.body().getproduct_discount());
                for (int i=0;i<response.body().getproduct_discount().size();i++){
                    System.out.println(response.body().getproduct_discount().get(i).getOffer_apply());
                    if (response.body().getproduct_discount().get(i).getOffer_apply().equals("Applied")){
                        apply=response.body().getproduct_discount().get(i).getOffer_apply();
                    }
                }
                if (apply.equals("Applied")){

                }else {
                    customDialog();
                }
//}
            }


            @Override
            public void onFailure(Call<Discount> call, Throwable t) {

            }
        });
    }

    private void address_dialog(){
        progress_message();
        progress_show();
        System.out.println("address_dialog"+user_id);
        Call<View_Profile> call = apiInterface.response_UserProfile(user_token,user_id);

        call.enqueue(new Callback<View_Profile>() {

            @Override
            public void onResponse(Call<View_Profile> call, Response<View_Profile> response) {
                progress_dismiss();
                if (response.body().getMessage().equals("no data available")){
                    customAddressDialog();
                }else {

                }
            }

            @Override
            public void onFailure(Call<View_Profile> call, Throwable t) {

            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        this.finish();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 4000);
    }

            public static TreeMap<String, String> slider_product_select(String id, String p_name, String p_details, String p_img) {

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


    public static TreeMap<String, String> Product_select(String id, String p_name,String p_details, String p_mfg,
                                                         String p_exp, String p_type,String p_price,String ProductQty,
                                                         String p_img) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("p_id", String.valueOf(id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_details",p_details);
        treeMap.put("p_mfg",p_mfg);
        treeMap.put("p_exp",p_exp);
        treeMap.put("p_type",p_type);
        treeMap.put("p_price",p_price);
        treeMap.put("ProductQty",ProductQty);
        treeMap.put("p_img",p_img);
//        treeMap.put("pincode", String.valueOf(pincode));

        //
        return treeMap;
    }

    public static TreeMap<String, List> Product_select_pincode(List<String> pincode) {

        TreeMap<String, List> treeMap = new TreeMap<String, List>();

        treeMap.put("pincode",pincode);

        //
        return treeMap;
    }

    public static TreeMap<String, String> Product_select_unit(String id,String p_id, String p_name,String p_unit,
                                                              String p_price,String p_qty,String p_GST) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("_id", String.valueOf(id));
        treeMap.put("p_id", String.valueOf(p_id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_unit",p_unit);
        treeMap.put("p_price",p_price);
        treeMap.put("p_qty",p_qty);
        treeMap.put("p_GST",p_GST);

        //
        return treeMap;
    }

    public static TreeMap<String, String> popular_Product_select(String p_id, String p_name,String p_details, String p_mfg,
                                                                 String p_exp, String p_type,String p_price,String ProductQty,
                                                                 String p_img) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("p_id", String.valueOf(p_id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_details",p_details);
        treeMap.put("p_mfg",p_mfg);
        treeMap.put("p_exp",p_exp);
        treeMap.put("p_type",p_type);
        treeMap.put("p_price",p_price);
        treeMap.put("ProductQty",ProductQty);
        treeMap.put("p_img",p_img);

        //
        return treeMap;
    }

    public static TreeMap<String, String> new_Product_arrival(String p_id, String p_name,String p_details, String p_mfg,
                                                              String p_exp, String p_type,String p_price,String ProductQty,
                                                              String p_img) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("p_id", String.valueOf(p_id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_details",p_details);
        treeMap.put("p_mfg",p_mfg);
        treeMap.put("p_exp",p_exp);
        treeMap.put("p_type",p_type);
        treeMap.put("p_price",p_price);
        treeMap.put("ProductQty",ProductQty);
        treeMap.put("p_img",p_img);
//        treeMap.put("updated_at",is_last_lavel);
//        treeMap.put("is_altFormula",is_altFormula);
//        treeMap.put("altFormula",altFormula);
//        treeMap.put("is_parent",is_parent);

        //
        return treeMap;
    }

    public static TreeMap<String, String> bulk_oreder(String id, String p_name,String p_details, String p_mfg,
                                                      String p_exp,String p_type,String p_price,String ProductQty) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("id", String.valueOf(id));
        treeMap.put("p_name", p_name);
        treeMap.put("p_details",p_details);
        treeMap.put("p_mfg",p_mfg);
        treeMap.put("p_exp",p_exp);
        treeMap.put("p_type",p_type);
        treeMap.put("p_price",p_price);
        treeMap.put("ProductQty",ProductQty);

        //
        return treeMap;
    }

    public static TreeMap<String, String> profile_view_user(String u_id, String UserName,String UserAddress, String UserPin,
                                                            String age, String _id,String UserPhone,String email) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("u_id", String.valueOf(u_id));
        treeMap.put("UserName", UserName);
        treeMap.put("UserAddress",UserAddress);
        treeMap.put("UserPin",UserPin);
        treeMap.put("age",age);
        treeMap.put("_id",_id);
        treeMap.put("UserPhone",UserPhone);
        treeMap.put("email",email);
        return treeMap;
    }
}
