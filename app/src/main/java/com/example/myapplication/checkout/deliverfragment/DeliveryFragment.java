package com.example.myapplication.checkout.deliverfragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.checkout.paymentfragment.PaymentFragment;
import com.example.myapplication.confirmorderdetail.ConfirmOrderDetail;
import com.example.myapplication.dashbord.Product_user;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryFragment extends Fragment implements OnBackPressed{
    private CheckoutOrderAdapter checkoutOrderAdapter;
    TreeMap<String, String> user_order = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> user_order_detail=new ArrayList<TreeMap<String, String>>();
    ApiInterface apiInterface;
    static String user_token;
    public static final String mypreference = "mypref";
    static String token,token_val,prname;
    String content="application/json";
//    private List<CheckoutOrderGetSet> checkoutOrderGetSetList = new ArrayList();
    RecyclerView checkoutOrderRV;
    Button confirmpayment;
    TextView txt_items,txt_price,txt_gst,txt_total;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_delivery, container, false);
        this.checkoutOrderRV = (RecyclerView) v.findViewById(R.id.checkoutOrderRV);

        this.checkoutOrderRV.setLayoutManager(new LinearLayoutManager(getContext()));
        this.checkoutOrderRV.setItemAnimator(new DefaultItemAnimator());
        txt_items=v.findViewById(R.id.txt_items);
        txt_price=v.findViewById(R.id.txt_price);
        txt_gst=v.findViewById(R.id.txt_gst);
        txt_total=v.findViewById(R.id.txt_total);
        checkoutOrderStaticData();
        onBackPressed();
        this.confirmpayment = (Button) v.findViewById(R.id.confirmpayment);
//        this.confirmpayment.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Fragment fragment = new PaymentFragment();
//                FragmentTransaction fragmentTransaction = DeliveryFragment.this.getActivity().getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.checkoutFramelayout, fragment);
//                fragmentTransaction.addToBackStack((String) null);
//                fragmentTransaction.commit();
//            }
//        });
        return v;
    }

    private void checkoutOrderStaticData() {
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        String Order_id;
        Order_id=ConfirmOrderDetail.Order_id;
        System.out.println(Order_id);
        Call<OrderConfirm> call=apiInterface.response_productFetchOrder(Order_id,user_token);
        System.out.println(user_token);
        call.enqueue(new Callback<OrderConfirm>() {

            @Override
            public void onResponse(Call<OrderConfirm> call, Response<OrderConfirm> response) {
                    for (int i = 0; i < response.body().getOrder_product().size(); i++) {
                        System.out.println("jkbdsfhsdjfjsbfd"+response.body().getOrder_product().get(i).getProduct_name());
                    user_order=Product_select(response.body().getOrder_product().get(i).get_id(),
                            response.body().getOrder_product().get(i).getProduct_name(),
                            response.body().getOrder_product().get(i).getPayment_details(),
                            response.body().getOrder_product().get(i).getProduct_confirmation(),
                            response.body().getOrder_product().get(i).getP_price(),
                            response.body().getOrder_product().get(i).getProduct_qty(),
                            response.body().getOrder_product().get(i).getP_img(),
                            response.body().getOrder_product().get(i).getProduct_id(),
                            response.body().getOrder_product().get(i).getUsername(),
                            response.body().getOrder_product().get(i).getDelivery_date(),
                            response.body().getOrder_product().get(i).getPincode(),
                            response.body().getOrder_product().get(i).getAddress(),
                            response.body().getOrder_product().get(i).getProduct_amt_total(),
                            response.body().getOrder_product().get(i).getProduct_amt_gst(),
                            response.body().getOrder_product().get(i).getPayment_amount(),
                            response.body().getOrder_product().get(i).getDelivery_time());
                    user_order_detail.add(user_order);
                    checkoutOrderAdapter = new CheckoutOrderAdapter(DeliveryFragment.this.getActivity(),user_order_detail);
                        checkoutOrderRV.setAdapter(checkoutOrderAdapter);
//
                    }
                placed_order();
//                }
//                productStaticData();


            }

            @Override
            public void onFailure(Call<OrderConfirm> call, Throwable t) {

            }
        });
    }

    public void placed_order(){
        for (int i=0;i<user_order_detail.size();i++){
            txt_items.setText(""+user_order_detail.get(i).get("ProductQty")+" items");
            txt_price.setText(""+user_order_detail.get(i).get("p_price"));
            txt_gst.setText(""+user_order_detail.get(i).get("product_amt_gst"));
            txt_total.setText("INR "+user_order_detail.get(i).get("product_amt_total"));
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getActivity(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
}

    public static TreeMap<String, String> Product_select(String id,String p_name, String payment_details,String product_confirmation, String p_price,
                                                         String ProductQty,String p_img,String product_id,String Username,String delivery_date,String pincode,
                                                         String address,String product_amt_total,String product_amt_gst,String payment_amount,String delivery_time) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("p_id", String.valueOf(id));
        treeMap.put("ProductName", p_name);
        treeMap.put("payment_details",payment_details);
        treeMap.put("product_confirmation",product_confirmation);
//        treeMap.put("p_exp",p_exp);
//        treeMap.put("p_type",p_type);
        treeMap.put("p_price",p_price);
        treeMap.put("ProductQty",ProductQty);
        treeMap.put("p_img",p_img);
        treeMap.put("product_id", String.valueOf(product_id));
        treeMap.put("Username", Username);
        treeMap.put("delivery_date",delivery_date);
        treeMap.put("pincode",pincode);
        treeMap.put("address",address);
        treeMap.put("product_amt_total",product_amt_total);
        treeMap.put("product_amt_gst",product_amt_gst);
        treeMap.put("payment_amount",payment_amount);
        treeMap.put("delivery_time",delivery_time);
        //
        return treeMap;
    }
}
