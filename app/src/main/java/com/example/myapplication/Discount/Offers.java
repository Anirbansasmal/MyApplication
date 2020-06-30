package com.example.myapplication.Discount;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Product_user;

import java.util.ArrayList;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Offers extends AppCompatActivity {
    private OfferAdapter offerAdapter;
    TreeMap<String, String> product_offers = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> user_offers=new ArrayList<TreeMap<String, String>>();
    TreeMap<String, String> product_offers_start = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> user_offers_start=new ArrayList<TreeMap<String, String>>();
    public static final String mypreference = "mypref";
    public static String user_token,user_id,token_val;
    private RecyclerView recyclerViewOffer;
    ApiInterface apiInterface;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        this.recyclerViewOffer = (RecyclerView) findViewById(R.id.rvDiscount);
        this.recyclerViewOffer.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        this.recyclerViewOffer.setItemAnimator(new DefaultItemAnimator());
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
     shared();
    }

    public void shared() {
        try {
            SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_id = sharedPref.getString("u_id", "");
            user_token="Berear "+token_val;
            System.out.println(user_token);
            offer();
//            user_token = token_val;
        } catch (Exception e) {

        }
    }
    public void offer(){
        Call<Discount> call=apiInterface.response_UserDiscountData(user_id,user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<Discount>() {

            @Override
            public void onResponse(Call<Discount> call, Response<Discount> response) {
//                System.out.println("nsvsvhvdjhfgjdjf"+response.body().getProduct().getProduct_arrqty());
                for (int i=0;i<response.body().getproduct_discount().size();i++) {
//                    System.out.println(response.body().getProduct().getProduct_arrqty().get(i).getP_img());
//                    for (int j = 0; j < response.body().getProduct().getProduct_arrqty().size(); j++) {
//                        if (response.body().getProduct().getProduct_arrqty().get(i).get_id() == response.body().getProduct().getProduct_qty().get(i).get_id()) {
//                    if (response.body().getUser_discount().get(i).get_id().equals("Normal")) {
                        product_offers = select_offer(response.body().getproduct_discount().get(i).get_id(),
                                response.body().getproduct_discount().get(i).getOffer_apply(),
                                response.body().getproduct_discount().get(i).getU_id(),
                                response.body().getproduct_discount().get(i).getOffer_start(),
                                response.body().getproduct_discount().get(i).getOffer_exp(),
                                response.body().getproduct_discount().get(i).getDiscount_val());
                        user_offers.add(product_offers);


//                    }
                }
                System.out.println("user_product"+user_offers);
                adapter_offer();

            }

            @Override
            public void onFailure(Call<Discount> call, Throwable t) {

            }
        });
    }
    public void adapter_offer(){
        offerAdapter=new OfferAdapter(this,user_offers);
        recyclerViewOffer.setAdapter(offerAdapter);
    }
    public TreeMap<String,String> select_offer(String id,String offer_apply,String u_id,String offer_start,String offer_exp,String discount_val){
        TreeMap<String,String> treeMap=new TreeMap<>();
        treeMap.put("id",id);
        treeMap.put("offer_apply",offer_apply);
        treeMap.put("u_id",u_id);
        treeMap.put("offer_start",offer_start);
        treeMap.put("offer_exp",offer_exp);
        treeMap.put("discount_val",discount_val);
        return treeMap;
    }
}
