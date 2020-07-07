package com.example.myapplication.dashbord;

import android.content.Context;
import android.content.Intent;
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

public class NewArrivalsAdapter extends ArrayAdapter<NewArrivalsGetSet> {
    TextView desc;
    ImageView image;
    TextView name;
    LinearLayout newArriavleProductLL;
    ArrayList<NewArrivalsGetSet> newArrivalsGetSets;
    private ArrayList<TreeMap<String,ArrayList>> check_pincode=new ArrayList<>();
Context context;
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
        Glide.with(this.image.getContext()).load(newArrivalsGetSet1.getP_img()).into(this.image);
//        this.image.setImageResource(newArrivalsGetSet1.getPic());
        this.newArriavleProductLL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Dashbord.pin!=null) {
                    Intent intent = new Intent(v.getContext(), ProductDetail.class);
                    intent.putExtra("image_url", newArrivalsGetSet1.getP_img());
                    intent.putExtra("p_id", newArrivalsGetSet1.getP_id());
                    intent.putExtra("p_details", newArrivalsGetSet1.getP_details());
                    intent.putExtra("p_name", newArrivalsGetSet1.getP_name());
                    intent.putExtra("p_type",newArrivalsGetSet1.getP_type());
                    intent.putStringArrayListExtra("pincode", check_pincode.get(position).get("pincode"));
                    v.getContext().startActivity(intent);
                }else {
                    Toast.makeText(context,"Select Any pin",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }
}
