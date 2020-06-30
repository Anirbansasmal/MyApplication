package com.example.myapplication.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dashbord.Dashbord;

public class Shopping extends AppCompatActivity {
Button btn_shopping;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_cart_shopping);
        btn_shopping=findViewById(R.id.shopping);
        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shopping.this, Dashbord.class));
            }
        });
    }
}
