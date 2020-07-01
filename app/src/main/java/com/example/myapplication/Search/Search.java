package com.example.myapplication.Search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Product_user;

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        shared();
    }
    public void shared() {
        try {
            SharedPreferences sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_token="Berear "+token_val;
//            user_token = token_val;
        } catch (Exception e) {

        }
    }
    public void search(){
        Call<Product_user> call=apiInterface.response_ProductView(user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<Product_user>() {

            @Override
            public void onResponse(Call<Product_user> call, Response<Product_user> response) {
                for (int i=0;i<response.body().getProduct().getProduct_arrqty().size();i++) {
                    System.out.println(response.body().getProduct().getProduct_arrqty().get(i).getP_img());
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
                productAdapter();

            }

            @Override
            public void onFailure(Call<Product_user> call, Throwable t) {

            }
        });
    }
    public void productAdapter(){

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
