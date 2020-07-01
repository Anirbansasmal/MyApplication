package com.example.myapplication.dashbord;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.example.myapplication.cart.Cart;
import com.example.myapplication.checkout.Checkout;
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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.verifyotp.VerifyOtp.mypreference;

//import me.relex.circleindicator.CircleIndicator;

public class Dashbord extends AppCompatActivity {
//    private static String[] IMAGES;
    public static ArrayList<String>IMAGES=new ArrayList<>();
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
    public static final String mypreference = "mypref";
    public static String user_token,user_id;
    public static String token,pin,address;
    public static int NUM_PAGES = 0;
    /* access modifiers changed from: private */
    public static int currentPage = 0;
    /* access modifiers changed from: private */
    public static ViewPager mPager;
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
   private TextView user_name,user_email;
    ApiInterface apiInterface;
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
        customDialog();
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navMenu = (ImageView) findViewById(R.id.nav_menu);
        btn_change=findViewById(R.id.btn_change);
        mPager = (ViewPager) findViewById(R.id.pager);
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
        System.out.println(token);
        user_token="Berear "+token;
        System.out.println("knsfjksgf"+user_token);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
        user_product();
        popular_product();
        change_location();
        slider_banner();
        new_arrivel();
        address();
//        Cart.user_cart.clear();
//        Cart.product_cart.clear();
    }

    String header="application/json";

    public void user_product(){

        Call<Product_user> call=apiInterface.response_ProductView(user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<Product_user>() {

            @Override
            public void onResponse(Call<Product_user> call, Response<Product_user> response) {
//                System.out.println("nsvsvhvdjhfgjdjf"+response.body().getProduct().getProduct_arrqty());
                for (int i=0;i<response.body().getProduct().getProduct_arrqty().size();i++) {
                    System.out.println(response.body().getProduct().getProduct_arrqty().get(i).getP_img());
//                    for (int j = 0; j < response.body().getProduct().getProduct_arrqty().size(); j++) {
//                        if (response.body().getProduct().getProduct_arrqty().get(i).get_id() == response.body().getProduct().getProduct_qty().get(i).get_id()) {
                    if (response.body().getProduct().getProduct_arrqty().get(i).getP_popularity().equals("Normal")) {
                        user_single_product = Product_select(response.body().getProduct().getProduct_arrqty().get(i).get_id(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_name(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_details(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_mfg(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_exp(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_type(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_price(),
                                response.body().getProduct().getProduct_arrqty().get(i).getProductQty(),
                                response.body().getProduct().getProduct_arrqty().get(i).getP_img());
                        user_product.add(user_single_product);
                        Product_select_pincode=Product_select_pincode(response.body().getProduct().getProduct_arrqty().get(i).getPincode());
                        user_pincode.add(Product_select_pincode);

                    }
                }
                System.out.println("user_product"+user_product);
                productStaticData();

            }

            @Override
            public void onFailure(Call<Product_user> call, Throwable t) {

            }
        });
    }

    public void slider_banner() {
        Call<Slider_product> call = apiInterface.response_ProductBanner(user_token);
        call.enqueue(new Callback<Slider_product>() {
                @Override
            public void onResponse(Call<Slider_product> call, Response<Slider_product> response) {
//                slider_product_select
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
                        Dashbord.this.profileAdapter.getWeeksSelectedArray();
                        if(profileAdapter.getWeeksSelectedArray()==null){
                            user_profile.clear();
                            dialog.dismiss();
                        }else {
                            pin=profileAdapter.getWeeksSelectedArray().toString();
//                            address=profileAdapter.getWeeksSelectedAddress().toString();
//                            address();
                            System.out.println(pin);
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
                dialog.getWindow().setLayout(900,700);
            }
        });
    }

    private void change_pin() {
        Call<View_Profile> call = apiInterface.response_UserProfile(user_token,user_id);

        call.enqueue(new Callback<View_Profile>() {

            @Override
            public void onResponse(Call<View_Profile> call, Response<View_Profile> response) {
                System.out.println(response.body().getUser());
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

    public void address(){
        Call<View_Profile> call = apiInterface.response_UserProfile(user_token,user_id);

        call.enqueue(new Callback<View_Profile>() {

            @Override
            public void onResponse(Call<View_Profile> call, Response<View_Profile> response) {
                System.out.println(response.body().getUser());
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
        Call<Product_user> call = apiInterface.response_ProductPopular(user_token);

        call.enqueue(new Callback<Product_user>() {

            @Override
            public void onResponse(Call<Product_user> call, Response<Product_user> response) {
                for (int i=0;i<response.body().getProduct().getProduct_arrqty().size();i++) {
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
                    }
                }
                popularProductStaticData();
            }

            @Override
            public void onFailure(Call<Product_user> call, Throwable t) {

            }
        });
    }

    public void new_arrivel() {
        Call<Product_user> call = apiInterface.response_ProductNewArrivel(user_token);

        call.enqueue(new Callback<Product_user>() {

            @Override
            public void onResponse(Call<Product_user> call, Response<Product_user> response) {
                System.out.println(response.body().getProduct().getProduct_arrqty().size());
                for (int i=0;i<response.body().getProduct().getProduct_arrqty().size();i++) {
                    if (response.body().getProduct().getProduct_arrqty().get(i).getP_popularity().equals("NewArrivel")) {

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
        this.newArrivalsAdapter = new NewArrivalsAdapter(this, this.newArrivalsGetSetArrayList,this.user_pincode_new);
        this.avfImageFlipper.setAdapter(this.newArrivalsAdapter);
        Dashbord.this.newArrivalsAdapter.notifyDataSetChanged();
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
                }, 2000, 2000);
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment hello = new BulkOrderFragment();
        fragmentTransaction.addToBackStack((String) null);
        fragmentTransaction.replace(R.id.framelayout_bulkorder, hello, "HELLO");
        fragmentTransaction.commit();
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
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void Discount() {
        Call<Discount> call = apiInterface.response_UserDiscountData(user_id,user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<Discount>() {

            @Override
            public void onResponse(Call<Discount> call, Response<Discount> response) {
            }

            @Override
            public void onFailure(Call<Discount> call, Throwable t) {

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
//                Intent intent=new Intent(Dashbord.this, Dashbord.class);
//                startActivity(intent);
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
//        treeMap.put("p_img",p_img);

        //
        return treeMap;
    }
}
