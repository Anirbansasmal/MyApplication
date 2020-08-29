package com.example.myapplication.orderhistory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Discount.Offers;
import com.example.myapplication.R;
import com.example.myapplication.checkout.paymentfragment.OnlinePaymentFragment;
import com.example.myapplication.dashbord.Dashbord;
import com.google.android.material.tabs.TabLayout;

public class OrderHistory extends AppCompatActivity {
    TabLayout tabLayoutOH;
    ViewPager viewPagerOH;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_order_history);
//        getSupportActionBar().hide();
        this.tabLayoutOH = (TabLayout) findViewById(R.id.ohTabLayout);
        this.viewPagerOH = (ViewPager) findViewById(R.id.ohViewPager);
        TabLayout tabLayout = this.tabLayoutOH;
        tabLayout.addTab(tabLayout.newTab().setText((CharSequence) "Active Orders"));
//        this.tabLayoutOH.setTabTextColors(getResources().getColor(R.color.colorBlack), getResources().getColor(R.color.colorGreen));
//        this.tabLayoutOH.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorGreen));
        TabLayout tabLayout2 = this.tabLayoutOH;
        tabLayout2.addTab(tabLayout2.newTab().setText((CharSequence) "Old Orders"));
        this.tabLayoutOH.setTabGravity(0);
        this.viewPagerOH.setAdapter(new TabAdapter(this, getSupportFragmentManager(), this.tabLayoutOH.getTabCount()));
        this.viewPagerOH.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tabLayoutOH));
        this.tabLayoutOH.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {
                OrderHistory.this.viewPagerOH.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }

            public void onTabReselected(TabLayout.Tab tab) {
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
                Intent intent=new Intent(OrderHistory.this, Dashbord.class);
                startActivity(intent);
//                finish();
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

    public void orderhistoryback(View view) {
        Intent intent=new Intent(this, Dashbord.class);
        startActivity(intent);
//        finish();
    }
}
