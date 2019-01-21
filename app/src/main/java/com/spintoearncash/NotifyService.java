package com.spintoearncash;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class NotifyService extends Service {

    Timer timer;
    SharedPreferences pref;
    SharedPreferences.Editor edit;


    private void doSomethingRepeatedly() {
        timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {



                try{

                     pref = getApplicationContext().getSharedPreferences("myPref", 0);
                    edit = pref.edit();
                    edit.clear();
                    edit.apply();





                }
                catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
                }

            }
        }, 0, 1000 );



    }





    @Override
    public void onCreate() {



        pref = getApplicationContext().getSharedPreferences("myPref" , Activity.MODE_PRIVATE);
        edit = pref.edit();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        doSomethingRepeatedly();

        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        receive r = new receive();



        if (r!=null) {
            try {

                unregisterReceiver(r);
                r=null;

            }catch (Exception e)
            {

            }
        }







    }

}
