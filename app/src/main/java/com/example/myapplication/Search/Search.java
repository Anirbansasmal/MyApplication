package com.example.myapplication.Search;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.dashbord.ProductAdapterDemo;
import com.example.myapplication.productdetail.ProductDetail;
//import com.example.myapplication.dashbord.Product_user;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Discount.Offers.token_val;

public class Search extends AppCompatActivity {
    public static final String mypreference = "mypref";
    public static String user_token;
    TreeMap<String, String> user_single_product = new TreeMap<String, String>();
    TreeMap<String, ArrayList>Product_select_pincode=new TreeMap<String, ArrayList>();
    ArrayList<TreeMap<String,String>> user_product=new ArrayList<TreeMap<String, String>>();
    ArrayList<TreeMap<String,ArrayList>> user_pincode=new ArrayList<TreeMap<String, ArrayList>>();
    ApiInterface apiInterface;
    ProgressDialog progressDoalog;
    public static String search_key;
    private RecyclerView recyclerViewSearchProduct;
    private RecyclerView.LayoutManager layoutManager;
    ProductAdapterSearch productAdapterSearch;
    ImageView img_back;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Intent intent=getIntent();
        img_back=findViewById(R.id.img_back);
        search_key=intent.getStringExtra("search_key");
        System.out.println("search_key"+search_key);
        this.recyclerViewSearchProduct = (RecyclerView) findViewById(R.id.rvSearch);
        this.recyclerViewSearchProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        this.recyclerViewSearchProduct.setItemAnimator(new DefaultItemAnimator());
        progressDoalog = new ProgressDialog(Search.this);
        shared();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Search.this, Dashbord.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void shared() {
        try {
            SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_token="Berear "+token_val;
//            user_token = token_val;
            search();
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
    public void search(){
        progress_message();
        progress_show();
        Call<Product_user_search> call=apiInterface.response_productSearch(user_token,search_key);
        System.out.println(user_token);
        call.enqueue(new Callback<Product_user_search>() {

            @Override
            public void onResponse(Call<Product_user_search> call, Response<Product_user_search> response) {
                progress_dismiss();
                for (int i=0;i<response.body().getProduct().getProduct_arrqty().size();i++) {
                    System.out.println(response.body().getProduct().getProduct_arrqty().get(i).getP_img());
                    if (response.body().getProduct().getProduct_arrqty().get(i).getP_popularity().equals("normal") || response.body().getProduct().getProduct_arrqty().get(i).getP_popularity().equals("popular") || response.body().getProduct().getProduct_arrqty().get(i).getP_popularity().equals("new arrival")) {
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
                productAdapter();

            }

            @Override
            public void onFailure(Call<Product_user_search> call, Throwable t) {

            }
        });
    }

    public void productAdapter(){
        productAdapterSearch=new ProductAdapterSearch(this,user_product,user_pincode);
        recyclerViewSearchProduct.setAdapter(productAdapterSearch);
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
                Intent intent=new Intent(Search.this, Dashbord.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
        return treeMap;
    }

    public static TreeMap<String, List> Product_select_pincode(List<String> pincode) {

        TreeMap<String, List> treeMap = new TreeMap<String, List>();
        treeMap.put("pincode",pincode);
        return treeMap;
    }
}
