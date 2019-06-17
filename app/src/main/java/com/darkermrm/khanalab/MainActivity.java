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
import android.view.View;
import android.widget.Toast;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    private static int splash_time_out=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hidnNav();

        if(!isConnected(MainActivity.this)) {
            buildDialog(MainActivity.this).show();
            Toast.makeText(MainActivity.this,"Be Online", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            //Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent homeIntent = new Intent(MainActivity.this,homeActivity.class);
                    //Intent homeIntent = new Intent(MainActivity.this,homeActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            },splash_time_out);
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

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
}
