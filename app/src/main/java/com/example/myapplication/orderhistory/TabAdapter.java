package com.example.myapplication.orderhistory;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.orderhistory.activeorderfragment.ActiveOrderFragment;
import com.example.myapplication.orderhistory.oldorderfragment.OldOrderFragment;


public class TabAdapter extends FragmentPagerAdapter {
    int totalTabs;

    public TabAdapter(Context context, FragmentManager fm, int totalTabs2) {
        super(fm);
        this.totalTabs = totalTabs2;
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new ActiveOrderFragment();
        }
        if (position != 1) {
            return null;
        }
        return new OldOrderFragment();
    }

    public int getCount() {
        return this.totalTabs;
    }
}
