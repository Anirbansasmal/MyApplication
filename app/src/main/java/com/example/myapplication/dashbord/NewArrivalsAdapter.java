package com.example.myapplication.dashbord;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.productdetail.ProductDetail;

import java.util.ArrayList;
import java.util.TreeMap;

import static com.example.myapplication.dashbord.Dashbord.mypreference;

public class NewArrivalsAdapter extends ArrayAdapter<NewArrivalsGetSet> {
    TextView desc;
    ImageView image;
    TextView name;
    LinearLayout newArriavleProductLL;
    ArrayList<NewArrivalsGetSet> newArrivalsGetSets;
    private ArrayList<TreeMap<String,ArrayList>> check_pincode=new ArrayList<>();
    String pin;
Context context;
int flag=0;
    public NewArrivalsAdapter(Context context, ArrayList<NewArrivalsGetSet> objects,ArrayList<TreeMap<String,ArrayList>> productList_pincode) {
        super(context, R.layout.new_arrivals_product_row, objects);
        this.newArrivalsGetSets = objects;
        this.check_pincode=productList_pincode;
        this.context=context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(final int position, View convertView, ViewGroup parent) {
        final NewArrivalsGetSet newArrivalsGetSet1 = (NewArrivalsGetSet) getItem(position);
        if (convertView == null) {
            if (parent == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.new_arrivals_product_row, (ViewGroup) null);
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.new_arrivals_product_row, parent, false);
            }
        }
        this.name = (TextView) convertView.findViewById(R.id.new_product_name);
        this.desc = (TextView) convertView.findViewById(R.id.new_product_desc);
        this.image = (ImageView) convertView.findViewById(R.id.ivMyImageView);
        this.newArriavleProductLL = (LinearLayout) convertView.findViewById(R.id.newArriavleProductLL);
        this.name.setText(newArrivalsGetSet1.getP_name());
        this.desc.setText(newArrivalsGetSet1.getP_details());
        Glide.with(this.image.getContext()).load("http://app.milchmom.com:8080/"+newArrivalsGetSet1.getP_img()).into(this.image);
//        this.image.setImageResource(newArrivalsGetSet1.getPic());

        this.newArriavleProductLL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPref = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                pin = sharedPref.getString("pin", "");
                if (pin.isEmpty()) {
                    Toast.makeText(context,"Select Any pin",Toast.LENGTH_SHORT).show();
//                    this.finish();
                }else {
                    Intent intent = new Intent(v.getContext(), ProductDetail.class);
                    intent.putExtra("image_url", newArrivalsGetSet1.getP_img());
                    intent.putExtra("p_id", newArrivalsGetSet1.getP_id());
                    intent.putExtra("p_details", newArrivalsGetSet1.getP_details());
                    intent.putExtra("p_name", newArrivalsGetSet1.getP_name());
                    intent.putExtra("p_type",newArrivalsGetSet1.getP_type());
                    intent.putStringArrayListExtra("pincode", check_pincode.get(position).get("pincode"));
                    for (int i=0;i<check_pincode.get(position).get("pincode").size();i++){
                        if (check_pincode.get(position).get("pincode").get(i).equals(pin)){
                            flag=1;
                            if (flag==1){
                                intent.putExtra("pincodeChek",pin);
                                System.out.println("pincodeChek"+pin);
                                break;
                            }else {

                            }
                        }else {
                            intent.putExtra("pincodeChek","0");
                            System.out.println("pincodeChek"+pin);
                        }
                    }
                    v.getContext().startActivity(intent);

                }
            }
        });
        return convertView;
    }
}
