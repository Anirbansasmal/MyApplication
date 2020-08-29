package com.example.myapplication.dashbord.bulkorder;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.ApiClient.ApiClient;
import com.example.myapplication.ApiInterface.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.confirmorderdetail.ConfirmOrderDetail;
import com.example.myapplication.dashbord.Avl_times_deli;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.dashbord.Product_unit;
import com.example.myapplication.dashbord.Product_user;
import com.example.myapplication.productdetail.ProductDetail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BulkOrderFragment extends AppCompatActivity {
    ApiInterface apiInterface;
    static String user_token,p_id;
    static String token,token_val;
    TreeMap<String, String> product_bulk_pid = new TreeMap<String, String>();
    ArrayList<TreeMap<String,String>> bulk_order_pid=new ArrayList<TreeMap<String, String>>();
    ArrayList<String> productslist = new ArrayList<>();
    ArrayList<String> unitslist = new ArrayList<>();
    EditText name,email,phoneNumber,p_qty,deli_location,note;
    TextView delivery_date,deli_time;
    Spinner product,spinnerunits;
    Button btn_bulk;
    String usr_name,usr_email,usr_product,usr_phoneNumber,usr_p_qty,usr_unit,usr_delivery_date,usr_deli_location,usr_deli_time,usr_note;
    int t1Minute,t1Hour;

    ProgressDialog progressDoalog;
//    int =0;
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_bulk_order, container, false);
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_bulk_order);
//        View view = inflater.inflate(R.layout.fragment_bulk_order, container, false);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        name=findViewById(R.id.edit_name);
        email=findViewById(R.id.bulk_email);
        phoneNumber=findViewById(R.id.bulk_phoneno);
        product=findViewById(R.id.bulk_product);
        spinnerunits=findViewById(R.id.bulk_product_unit);
        p_qty=findViewById(R.id.bulk_qty);
        delivery_date=findViewById(R.id.bulk_date);
        deli_location=findViewById(R.id.bulk_location);
        deli_time=findViewById(R.id.bulk_prefferd_time);
        note=findViewById(R.id.bulk_addnote);
        btn_bulk=findViewById(R.id.btn_enquiry);


    progressDoalog = new ProgressDialog(BulkOrderFragment.this);

    deli_time.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TimePickerDialog timePickerDialog=new TimePickerDialog(BulkOrderFragment.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            t1Minute=minute;
                            t1Hour=hourOfDay;
                            Calendar calendar=Calendar.getInstance();
                            calendar.set(0,0,0,t1Hour,t1Minute);
                            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
//                            deli_time.setText(t1Hour+":"+t1Minute);
                            String time=t1Hour+":"+t1Minute;
                            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                            SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
                            Date dt;
                            try {
                                dt = sdf.parse(time);
                                System.out.println("Time Display: " + sdfs.format(dt)); // <-- I got result here
                                deli_time.setText(""+sdfs.format(dt));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }
//                            deli_time.setText(dateFormat.format(calendar));
                        }
                    },12,0,false
            );
            timePickerDialog.updateTime(t1Hour,t1Minute);
            timePickerDialog.show();
        }
    });

//        delivery_date.setClickable(true);
//        ArrayAdapter aa = new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item, unitslist);
//        aa.setDropDownViewResource(R.layout.select_dialog_singlechoice_material);
//        product.setAdapter(aa);

        shared();
        product();
    deliver_time();
    delivery_date.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calender();
        }
    });
    bulk_order();
//        return view;
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
    private void bulk_order(){
//        apiInterface= ApiClient.getClient().create(ApiInterface.class);
//        product();
        select_pro();
        spinner();
//        calender();
        btn_bulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr_name=name.getText().toString().trim();
                usr_email=email.getText().toString().trim();
                usr_phoneNumber=(phoneNumber.getText().toString().trim());
                usr_p_qty= p_qty.getText().toString();
                usr_delivery_date=delivery_date.getText().toString().trim();
                usr_deli_location=deli_location.getText().toString().trim();
                usr_deli_time=deli_time.getText().toString().trim();
                usr_note=note.getText().toString().trim();
                System.out.println(usr_name);

                if (usr_name.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"usr_name Fill Required data",Toast.LENGTH_SHORT).show();
                }else if (usr_email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"usr_email Fill Required data",Toast.LENGTH_SHORT).show();
                }else if (usr_phoneNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"usr_phoneNumber Fill Required data",Toast.LENGTH_SHORT).show();
                }else if (usr_p_qty.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"usr_p_qty Fill Required data",Toast.LENGTH_SHORT).show();
                }else if (usr_delivery_date.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"usr_delivery_date Fill Required data",Toast.LENGTH_SHORT).show();
                }else if (usr_deli_location.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"usr_deli_location Fill Required data",Toast.LENGTH_SHORT).show();
                }else if (usr_deli_time.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"usr_deli_time Fill Required data",Toast.LENGTH_SHORT).show();
                }else if (usr_note.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"usr_note Fill Required data",Toast.LENGTH_SHORT).show();
                }else {
                    bulk();
                }


System.out.println("lkjgldgmdg"+usr_product+""+usr_unit);


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
                Intent intent=new Intent(getApplicationContext(), Dashbord.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

    public void bulk(){
        progress_show();
        progress_message();
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<Bulk> call=apiInterface.response_BulkOrder(usr_name,usr_email,usr_phoneNumber,usr_product, usr_unit,String.valueOf(usr_p_qty),usr_delivery_date,usr_deli_location,usr_deli_time,usr_note,user_token);
        call.enqueue(new Callback<Bulk>() {
            @Override
            public void onResponse(Call<Bulk> call, Response<Bulk> response) {
                System.out.println("response"+response.body().getStatus());
                if (response.body().getStatus().equals("success")){
                    progress_dismiss();
                    Toast toast = new Toast(getApplicationContext());
                    LayoutInflater li = getLayoutInflater();
                    View layout = li.inflate(R.layout.activity_bulk_toast,(ViewGroup) findViewById(R.id.custom_toast_layout));
                    TextView tv = (TextView) layout.findViewById(R.id.txtcustom);
                    tv.setText("success placed order data");
                    tv.setTextColor(Color.BLUE);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
//                    Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Dashbord.class));
                }else {
                    Toast.makeText(getApplicationContext(),"failed placed order data",Toast.LENGTH_SHORT).show();
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

    public void deliver_time(){

    }
    private void select_pro(){
    ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,productslist);
//    aa.setDropDownViewResource(R.layout.select_dialog_singlechoice_material);
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
        progress_show();
        progress_message();
        Call<Product_user> call=apiInterface.response_ProductView(user_token);
        call.enqueue(new Callback<Product_user>() {
            @Override
            public void onResponse(Call<Product_user> call, Response<Product_user> response) {
                progress_dismiss();
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
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, unitslist);
//        aa.setDropDownViewResource(R.layout.select_dialog_singlechoice_material);
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

    String inputDateStr;

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public void calender_date(View v){
//        calender();
//    }
//    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calender() {

         Calendar calendar = Calendar.getInstance();
//        String formate = "yyyy-MM-dd";
//         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formate, Locale.US);
        @SuppressLint("WrongConstant") DatePickerDialog dateSetListener = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                inputDateStr = dayOfMonth + "-" + month + "-" + year;

                delivery_date.setText(inputDateStr);
//            }
////        };
//
////        delivery_date.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                new DatePickerDialog(getActivity(),dateSetListener,
////                        calendar.get(Calendar.DAY_OF_MONTH),
////                        calendar.get(Calendar.MONTH),
////                        calendar.get(Calendar.YEAR)).show();
////
////            }
////        });

    }
    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        dateSetListener.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dateSetListener.show();
    }

    public static final String mypreference = "mypref";

    public void shared() {
        try {
            SharedPreferences sharedPref =getApplicationContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
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
