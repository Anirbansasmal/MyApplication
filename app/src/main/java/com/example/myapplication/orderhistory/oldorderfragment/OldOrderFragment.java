package com.example.myapplication.orderhistory.oldorderfragment;

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
import com.example.myapplication.orderhistory.activeorderfragment.User_Order;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OldOrderFragment extends Fragment {
    private OldOrderAdapter oldOrderAdapter;
//    private List<OldOrderGetSet> oldOrderGetSetList = new ArrayList();
    TreeMap<String, String> oldOrderGetSetList = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> oldOrder=new ArrayList<TreeMap<String, String>>();
    RecyclerView rvOldOrder;
    public static final String mypreference = "mypref";
    public static String user_token,user_id,token;
    RecyclerView rvActiveOrder;
    ApiInterface apiInterface;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_old_order, container, false);
        this.rvOldOrder = (RecyclerView) v.findViewById(R.id.rvOldOrder);

        this.rvOldOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rvOldOrder.setItemAnimator(new DefaultItemAnimator());
shared();
//        oldOrderStaticData();
        return v;
    }
    public void shared(){
        SharedPreferences sharedPref = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
        token = sharedPref.getString("token", "");
        user_id = sharedPref.getString("u_id", "");
        user_token="Berear "+token;
        oldOrderStaticData();
    }

    private void oldOrderStaticData() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Order_delivered> call = apiInterface.response_productFetchDelivered(user_id, user_token);
        call.enqueue(new Callback<Order_delivered>() {

            @Override
            public void onResponse(Call<Order_delivered> call, Response<Order_delivered> response) {
                System.out.println(response.body().getDelivered());
                for (int i=0;i<response.body().getDelivered().size();i++){
                    oldOrderGetSetList=Product_select_ordered(response.body().getDelivered().get(i).get_id(),
                            response.body().getDelivered().get(i).getProduct_qty(),
                            response.body().getDelivered().get(i).getOrder_id(),
                            response.body().getDelivered().get(i).getDelivery_time(),
                            response.body().getDelivered().get(i).getDelivery_date());
                    oldOrder.add(oldOrderGetSetList);
                }
                oldorder();
            }

            @Override
            public void onFailure(Call<Order_delivered> call, Throwable t) {

            }
        });
    }

            public void oldorder(){
        this.oldOrderAdapter = new OldOrderAdapter(getActivity().getApplicationContext(),oldOrder);
        this.rvOldOrder.setAdapter(this.oldOrderAdapter);
    }

    public static TreeMap<String, String> Product_select_ordered(String id,String product_qty,String Order_id,
                                                                 String delivery_time,String delivery_date) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("_id", String.valueOf(id));
//        treeMap.put("p_id", String.valueOf(p_id));
        treeMap.put("product_qty", product_qty);
        treeMap.put("Order_id",Order_id);
//        treeMap.put("p_price",p_price);
        treeMap.put("delivery_time",delivery_time);
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
        treeMap.put("delivery_date",delivery_date);
//        treeMap.put("remaning_amt",remaning_amt);
//        treeMap.put("UserPhone",UserPhone);
//        treeMap.put("post",post);

        //
        return treeMap;
    }
}
