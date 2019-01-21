package com.spintoearncash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SplashActivity extends AppCompatActivity {
    ProgressBar pBar;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pBar = findViewById(R.id.pBar);
        ////geting mobile time
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd");
        String formattedDate = df.format(c);
        int day = Integer.valueOf(formattedDate);
       // Toast.makeText(this, ""+formattedDate, Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPref", 0);

        String prevDay = sharedPreferences.getString("date", "");
       // Toast.makeText(this, "prev Date" + prevDay, Toast.LENGTH_SHORT).show();
        if (!formattedDate.equals(prevDay)) {
            pref = getApplicationContext().getSharedPreferences("myPref", 0);
            edit = pref.edit();
            edit.clear();
            edit.apply();
        }
/////////

        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            @Override
            public void run() {
                pBar.setVisibility(View.GONE);

                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();

                // handler.postDelayed(this,1000);

            }
        };

        handler.postDelayed(r, 2000);
    }
}
