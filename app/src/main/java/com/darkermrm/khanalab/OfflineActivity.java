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
import android.widget.Toast;

public class OfflineActivity extends AppCompatActivity {

    private static int splash_time_out=10000;
    public int off=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);


        final Handler ha=new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call function
                if(!isConnected(OfflineActivity.this)) {
                    //Toast.makeText(OfflineActivity.this,"Offline", Toast.LENGTH_SHORT).show();
                }
                else if(isConnected(OfflineActivity.this)&& off==0){
                    Intent homeIntent = new Intent(OfflineActivity.this,homeActivity.class);
                    startActivity(homeIntent);
                    finish();
                    //Toast.makeText(OfflineActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
                    off=1;
                }

                ha.postDelayed(this, splash_time_out);
            }
        }, splash_time_out);
    }

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

}
