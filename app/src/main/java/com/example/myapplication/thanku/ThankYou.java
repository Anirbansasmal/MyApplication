package com.example.myapplication.thanku;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ThankYou extends AppCompatActivity {
    TextView tyDeliveryDate;
    TextView tyDeliveryMode;
    TextView tyDeliveryTimeslot;
    TextView tyPaymentMethod;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_thank_you);
//        getSupportActionBar().hide();
        this.tyDeliveryMode = (TextView) findViewById(R.id.tyDeliveryMode);
        this.tyDeliveryMode.setText(Html.fromHtml("<font color=#414141>Delivery Mode : </font> <font color=000000><b>One Time Order </B></font>"));
        this.tyDeliveryDate = (TextView) findViewById(R.id.tyDeliveryDate);
        this.tyDeliveryDate.setText(Html.fromHtml("<font color=#414141>Delivery Date : </font> <font color=000000><b>10.12.2019</B></font>"));
        this.tyDeliveryTimeslot = (TextView) findViewById(R.id.tyDeliveryTimeslot);
        this.tyDeliveryTimeslot.setText(Html.fromHtml("<font color=#414141>Delivery Time Slot : </font> <font color=000000><b>6AM - 7AM</B></font>"));
        this.tyPaymentMethod = (TextView) findViewById(R.id.tyPaymentMethod);
        this.tyPaymentMethod.setText(Html.fromHtml("<font color=#414141>Payment Method : </font> <font color=000000><b>Cash on Delivery</B></font>"));
    }
}
