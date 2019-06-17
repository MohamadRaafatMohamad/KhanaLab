package com.darkermrm.khanalab;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class homeActivity extends AppCompatActivity {

    private WebView webView;
    public int b=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //hidnNav();
        webView = (WebView) findViewById(R.id.WebView);

        final Handler ha=new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call function
                //if(!isConnected(homeActivity.this)&& b==0) {
                if(!isNetworkAvailable(homeActivity.this)&& b==0) {
                    Intent homeIntent = new Intent(homeActivity.this,OfflineActivity.class);
                    startActivity(homeIntent);
                    finish();
                    //Toast.makeText(homeActivity.this,"Offline", Toast.LENGTH_SHORT).show();
                    b=1;
                }
                else {
                    //Toast.makeText(homeActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
                }

                ha.postDelayed(this, 1000);
            }
        }, 1000);




        webView.setWebViewClient(new MyBrouser());
        String url="https://khana-lab.000webhostapp.com/labratoriris/eMZ7BEXUvgUA25h/login.php";
        webView.getSettings().getLoadsImagesAutomatically();
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.getScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }

    private class MyBrouser extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

//    private void hidnNav(){
//        this.getWindow().getDecorView()
//                .setSystemUiVisibility(
//                        View.SYSTEM_UI_FLAG_FULLSCREEN |
//                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
//                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
//                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
//                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
//                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                );
//    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }



    public static Boolean isNetworkAvailable(Context context) {
        Process p1 = null;
        try {
            p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            if (reachable) {

                //System.out.println("Internet access");
                return reachable;
            } else {

                //System.out.println("No Internet access");
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            p1.destroy();
        }
        return false;
    }

}
