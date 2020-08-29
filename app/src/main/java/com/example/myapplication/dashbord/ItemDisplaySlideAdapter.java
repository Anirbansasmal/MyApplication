package com.example.myapplication.dashbord;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.TreeMap;

public class ItemDisplaySlideAdapter extends PagerAdapter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
//    private ArrayList<TreeMap<String,String>> IMAGES;
    private ArrayList<String> IMAGES;
    private Context context;
    private LayoutInflater inflater;

    public ItemDisplaySlideAdapter(Context context2, ArrayList<String> IMAGES2) {
        this.context = context2;
        this.IMAGES = IMAGES2;
        this.inflater = LayoutInflater.from(context2);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return this.IMAGES.size();
    }
@Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = this.inflater.inflate(R.layout.slider, view, false);
        System.out.println("jsdgfjksdgfksdjhf"+IMAGES);
//        for (int i=0;i<IMAGES.size();i++){
            Glide.with(((ImageView) imageLayout.findViewById(R.id.image)).getContext())
                    .load("http://app.milchmom.com:8080/"+IMAGES.get(position)).into(((ImageView) imageLayout.findViewById(R.id.image)));
////            Glide.with(context).load(IMAGES.get(position)).
//                    placeholder(R.drawable.about).error(R.drawable.about).into(imgDisplay);
//            ((ImageView) imageLayout.findViewById(R.id.image)).setImageResource(Integer.parseInt(IMAGES.get(position).get("img")));

//        }
        view.addView(imageLayout, 0);
        return imageLayout;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }
    @Override
    public Parcelable saveState() {
        return null;
    }
}
