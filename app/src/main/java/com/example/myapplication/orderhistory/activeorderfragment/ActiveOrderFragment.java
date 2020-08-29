package com.example.myapplication.orderhistory.activeorderfragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.myapplication.checkout.paymentfragment.OnlinePaymentFragment;
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
    ProgressDialog progressDoalog;
    ApiInterface apiInterface;
    static View.OnClickListener myOnClickListener;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_active_order, container, false);
        this.rvActiveOrder = (RecyclerView) v.findViewById(R.id.rvActiveOrder);
        myOnClickListener = new MyOnClickListener(getActivity());
        this.rvActiveOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rvActiveOrder.setItemAnimator(new DefaultItemAnimator());
//        this.rvActiveOrder.setAdapter(this.activeOrderAdapter);
        progressDoalog = new ProgressDialog(getActivity());
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

    public void progress_show(){
        progressDoalog.show();
    }
    public void progress_dismiss(){
        progressDoalog.dismiss();
    }
    public void progress_message(){
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its Active product detail loading....");
        progressDoalog.setTitle("Thank you for give some time");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void activeOrderStaticData() {
        progress_show();
        progress_message();
        System.out.println(user_token);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        System.out.println("ljksdfkljkdfj"+user_token+" "+user_id);
        Call<User_Order_detail> call=apiInterface.response_productFetchOrderDeliveryActive(user_id,user_token);
        call.enqueue(new Callback<User_Order_detail>() {

            @Override
            public void onResponse(Call<User_Order_detail> call, Response<User_Order_detail> response) {
                progress_dismiss();
                for (int i=0;i<response.body().getOrder_product_active_id().size();i++){
                    System.out.println("ljksdfkljkdfj"+response.body().getOrder_product_active_id());

                        activeOrderGetSetList=Product_select_ordered(response.body().getOrder_product_active_id().get(i));
//                                response.body().getOrder_product_active().get(i).getRemaning_amt());
                        activeOrder.add(activeOrderGetSetList);



                }
                order_adapter();
            }

            @Override
            public void onFailure(Call<User_Order_detail> call, Throwable t) {

            }
        });
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int selectedItemPosition=rvActiveOrder.getChildAdapterPosition(v);
            RecyclerView.ViewHolder viewHolder=rvActiveOrder.findViewHolderForAdapterPosition(selectedItemPosition);
            details(viewHolder.getAdapterPosition());
        }
    }
    public void details(int position){
        Intent intent=new Intent(getActivity(),Order_detail.class);
        intent.putExtra("Order_id",activeOrder.get(position).get("Order_id"));
        startActivity(intent);
    }
    public void order_adapter(){
        this.activeOrderAdapter = new ActiveOrderAdapter(getActivity().getApplicationContext(),activeOrder);
        rvActiveOrder.setAdapter(activeOrderAdapter);
    }

    public static TreeMap<String, String> Product_select_ordered(String id) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("Order_id", String.valueOf(id));
//
        return treeMap;
    }
}
