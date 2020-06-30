package com.example.myapplication.orderhistory.activeorderfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

public class ActiveOrderFragment extends Fragment {
    private ActiveOrderAdapter activeOrderAdapter;
//    private List<ActiveOrderGetSet> activeOrderGetSetList = new ArrayList();
    TreeMap<String, String> activeOrderGetSetList = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> activeOrder=new ArrayList<TreeMap<String, String>>();
    public static final String mypreference = "mypref";
    public static String user_token,user_id,token;
    RecyclerView rvActiveOrder;
    ApiInterface apiInterface;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_active_order, container, false);
        this.rvActiveOrder = (RecyclerView) v.findViewById(R.id.rvActiveOrder);

        this.rvActiveOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rvActiveOrder.setItemAnimator(new DefaultItemAnimator());
//        this.rvActiveOrder.setAdapter(this.activeOrderAdapter);

        shared();

        return v;
    }
        public void shared(){
            SharedPreferences sharedPref = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
            token = sharedPref.getString("token", "");
            user_id = sharedPref.getString("u_id", "");
            user_token="Berear "+token;
            activeOrderStaticData();
        }
    private void activeOrderStaticData() {
        System.out.println(user_token);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        System.out.println("ljksdfkljkdfj"+user_token+" "+user_id);
        Call<User_Order> call=apiInterface.response_productFetchOrderDeliveryActive(user_id,user_token);
        call.enqueue(new Callback<User_Order>() {

            @Override
            public void onResponse(Call<User_Order> call, Response<User_Order> response) {
                System.out.println("ljksdfkljkdfj"+response.body().getOrder_product_active());
                for (int i=0;i<response.body().getOrder_product_active().size();i++){
                    if (response.body().getOrder_product_active().get(i).getRemaning_qty()!="0"){
                        activeOrderGetSetList=Product_select_ordered(response.body().getOrder_product_active().get(i).get_id(),
                                response.body().getOrder_product_active().get(i).getProduct_name(),
                                response.body().getOrder_product_active().get(i).getOrder_id(),
                                response.body().getOrder_product_active().get(i).getProduct_qty(),
                                response.body().getOrder_product_active().get(i).getRemaning_qty());
//                                response.body().getOrder_product_active().get(i).getRemaning_amt());
                        activeOrder.add(activeOrderGetSetList);
                    }


                }
                order_adapter();
            }

            @Override
            public void onFailure(Call<User_Order> call, Throwable t) {

            }
        });
    }

    public void order_adapter(){
        this.activeOrderAdapter = new ActiveOrderAdapter(getActivity().getApplicationContext(),activeOrder);
        rvActiveOrder.setAdapter(activeOrderAdapter);
    }

    public static TreeMap<String, String> Product_select_ordered(String id,String p_name,String Order_id,
                                                                 String p_qty,String remaning_qty) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("_id", String.valueOf(id));
//        treeMap.put("p_id", String.valueOf(p_id));
        treeMap.put("p_name", p_name);
        treeMap.put("Order_id",Order_id);
//        treeMap.put("p_price",p_price);
        treeMap.put("p_qty",p_qty);
//        treeMap.put("p_GST",p_GST);
//        treeMap.put("pincode",pincode);
//        treeMap.put("address",address);
//        treeMap.put("product_amt_total",product_amt_total);
//        treeMap.put("product_amt_gst",product_amt_gst);
//        treeMap.put("product_qty",product_qty);
//        treeMap.put("delivery_time",delivery_time);
//        treeMap.put("p_img",p_img);
//        treeMap.put("product_qty_total",product_qty_total);
//        treeMap.put("p_discount_total",p_discount_total);
        treeMap.put("remaning_qty",remaning_qty);
//        treeMap.put("remaning_amt",remaning_amt);
//        treeMap.put("UserPhone",UserPhone);
//        treeMap.put("post",post);

        //
        return treeMap;
    }
}
