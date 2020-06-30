package com.example.myapplication.dashbord.bulkorder;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.dashbord.Avl_times_deli;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.dashbord.Product_unit;
import com.example.myapplication.dashbord.Product_user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BulkOrderFragment extends Fragment {
    ApiInterface apiInterface;
    static String user_token,p_id;
    static String token,token_val;
    TreeMap<String, String> product_bulk_pid = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> bulk_order_pid=new ArrayList<TreeMap<String, String>>();
    ArrayList<String> productslist = new ArrayList<>();
    ArrayList<String> unitslist = new ArrayList<>();
    EditText name,email,phoneNumber,p_qty,delivery_date,deli_location,deli_time,note;
    Spinner product,spinnerunits;
    Button btn_bulk;
    String usr_name,usr_email,usr_phoneNumber,usr_product,usr_unit,usr_p_qty,usr_delivery_date,usr_deli_location,usr_deli_time,usr_note;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_bulk_order, container, false);
        View view = inflater.inflate(R.layout.fragment_bulk_order, container, false);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        name=view.findViewById(R.id.edit_name);
        email=view.findViewById(R.id.bulk_email);
        phoneNumber=view.findViewById(R.id.bulk_phoneno);
        product=view.findViewById(R.id.bulk_product);
        spinnerunits=view.findViewById(R.id.bulk_product_unit);
        p_qty=view.findViewById(R.id.bulk_qty);
        delivery_date=view.findViewById(R.id.bulk_date);
        deli_location=view.findViewById(R.id.bulk_location);
        deli_time=view.findViewById(R.id.bulk_prefferd_time);
        note=view.findViewById(R.id.bulk_addnote);
        btn_bulk=view.findViewById(R.id.btn_enquiry);
//        ArrayAdapter aa = new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item, unitslist);
//        aa.setDropDownViewResource(R.layout.select_dialog_singlechoice_material);
//        product.setAdapter(aa);
        product();
        bulk_order();
        shared();
        return view;
    }

    private void bulk_order(){
//        product();
        select_pro();
        spinner();
        calender();
        btn_bulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usr_name=name.getText().toString();
                usr_email=email.getText().toString();
                usr_phoneNumber=phoneNumber.getText().toString();
                usr_p_qty=p_qty.getText().toString();
                usr_delivery_date=delivery_date.getText().toString();
                usr_deli_location=deli_location.getText().toString();
                usr_deli_time=deli_time.getText().toString();
                usr_note=note.getText().toString();
                System.out.println(usr_name);

System.out.println("lkjgldgmdg"+usr_product+""+usr_unit);

        Call<Bulk> call=apiInterface.response_BulkOrder(usr_name,usr_email,usr_phoneNumber,usr_product,usr_p_qty,usr_delivery_date,usr_deli_location,usr_deli_time,usr_note,user_token);
        call.enqueue(new Callback<Bulk>() {
            @Override
            public void onResponse(Call<Bulk> call, Response<Bulk> response) {
                System.out.println(response.body().getStatus());
                if (response.body().getStatus().equals("success")){

                    startActivity(new Intent(getActivity(), Dashbord.class));
                }
//                for (int i=0;i<response.body().getAvl_times().size();i++){
//                    deliiveryTimeGetSetList.add(response.body().getAvl_times().get(i).getDeliTimeSlot());
//                }
            }

            @Override
            public void onFailure(Call<Bulk> call, Throwable t) {


            }
        });
            }
        });
    }

    private void select_pro(){
    ArrayAdapter aa = new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item, productslist);
    aa.setDropDownViewResource(R.layout.select_dialog_singlechoice_material);
    product.setAdapter(aa);
    product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            usr_product=parent.getSelectedItem().toString();
            System.out.println("vbcnsvcsj"+usr_product);
            unitslist.clear();
            user_product();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
}

    private void product(){
        Call<Product_user> call=apiInterface.response_ProductView(user_token);
        call.enqueue(new Callback<Product_user>() {
            @Override
            public void onResponse(Call<Product_user> call, Response<Product_user> response) {
                System.out.println(response.body().getProduct().getProduct_arrqty());
                for (int i=0;i<response.body().getProduct().getProduct_arrqty().size();i++){
                    product_bulk_pid=bulk_oreder(response.body().getProduct().getProduct_arrqty().get(i).get_id(),
                                                response.body().getProduct().getProduct_arrqty().get(i).getP_name());
                    bulk_order_pid.add(product_bulk_pid);
                    productslist.add(response.body().getProduct().getProduct_arrqty().get(i).getP_name());
//                    deliiveryTimeGetSetList.add(response.body().getAvl_times().get(i).getDeliTimeSlot());
                }
                select_pro();
//                user_product();
            }

            @Override
            public void onFailure(Call<Product_user> call, Throwable t) {


            }
        });

    }

    private void spinner(){
        ArrayAdapter aa = new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item, unitslist);
        aa.setDropDownViewResource(R.layout.select_dialog_singlechoice_material);
        spinnerunits.setAdapter(aa);
        spinnerunits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            ((TextView)parent.getChildAt(0)).setTextColor(Color.BLACK);
                System.out.println(spinnerunits.getSelectedItem());
                usr_unit=spinnerunits.getSelectedItem().toString();

//            ((TextView) parent.getChildAt(0)).setTextColor(0x00000000);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void user_product(){
        for (int i=0;i<bulk_order_pid.size();i++){
         if (bulk_order_pid.get(i).get("p_name").equals(usr_product)){
             p_id=bulk_order_pid.get(i).get("id");
             System.out.println(p_id);
         }
        }
        Call<Product_unit> call=apiInterface.response_ProductView_unit(user_token,p_id);
        call.enqueue(new Callback<Product_unit>() {
            @Override
            public void onResponse(Call<Product_unit> call, Response<Product_unit> response) {
//                System.out.println("nsvsvhvdjhfgjdjf"+response.body().getUnit());

                for (int j = 0; j < response.body().getUnit().size(); j++) {

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

    private void calender() {
        final Calendar calendar = Calendar.getInstance();
        String formate = "yyyy-MM-dd";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formate, Locale.US);
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.YEAR,year);
                delivery_date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        delivery_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),dateSetListener,
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR)).show();

            }
        });
    }

    public static final String mypreference = "mypref";

    public void shared() {
        try {
            SharedPreferences sharedPref = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            token_val = sharedPref.getString("token", "");
            user_token="Berear "+token_val;
//            user_token = token_val;
        } catch (Exception e) {

        }
    }

    public static TreeMap<String, String> bulk_oreder(String id, String p_name) {
//, String p_details, String p_mfg,
//                String p_exp, String p_type, String p_price, String ProductQty
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("id", String.valueOf(id));
        treeMap.put("p_name", p_name);
//        treeMap.put("p_details",p_details);
//        treeMap.put("p_mfg",p_mfg);
//        treeMap.put("p_exp",p_exp);
//        treeMap.put("p_type",p_type);
//        treeMap.put("p_price",p_price);
//        treeMap.put("ProductQty",ProductQty);

        //
        return treeMap;
    }
}
