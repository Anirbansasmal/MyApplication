package com.example.myapplication.checkout.paymentfragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.checkout.Checkout;
import com.example.myapplication.dashbord.Dashbord;
import com.example.myapplication.thanku.ThankYou;

import java.util.HashSet;
import java.util.Set;

public class MyWebViewClient extends WebViewClient {
    static String user_token,Order_id;
    private Activity onlinePaymentFragment = null;
    String server;
    String path;
    String protocol;
    Set<String> args=new HashSet<String>();
    String chapter;
    public MyWebViewClient(Activity activity) {
        this.onlinePaymentFragment = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Intent intent0=onlinePaymentFragment.getIntent();
        Order_id=intent0.getStringExtra("Order_id");
        if (url.contains("?Order_id=")) {
            Uri uri = Uri.parse(url);
            server = uri.getAuthority();
            path = uri.getPath();
            protocol = uri.getScheme();
             args = uri.getQueryParameterNames();
            chapter = uri.getQueryParameter("Order_id");
            System.out.println("khasdkjfgjkdfgd"+chapter);
            Intent intent = new Intent(onlinePaymentFragment, ThankYou.class);
            intent.putExtra("Order_id",chapter);
            onlinePaymentFragment.startActivity(intent);
//            finish();
        }  // close activity
        else {
            webView.loadUrl(url);
        }

        return true;
    }
}
