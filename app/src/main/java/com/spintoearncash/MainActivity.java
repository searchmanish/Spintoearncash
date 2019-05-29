package com.spintoearncash;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import rubikstudio.library.LuckyWheelView;
import rubikstudio.library.model.LuckyItem;

public class MainActivity extends AppCompatActivity {
    List<LuckyItem> data = new ArrayList<>();
    TextView balance, chance, totalChance;
    Button share, redeem, play;
    Handler handler;
    int total1;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    RelativeLayout relativeLayout;
    SharedPreferences pref;
    int counter;
    //sachin
    //startapp inter...
    private StartAppAd startAppAd;


    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         //startapp ads
        StartAppSDK.init(this, "200833362", true);
         startAppAd = new StartAppAd(this);

        StartAppSDK.setUserConsent (this,
                "pas",
                System.currentTimeMillis(),
                false);

        setContentView(R.layout.activity_main);

        relativeLayout = findViewById(R.id.activity_main);
        chance = findViewById(R.id.chance);
        totalChance = findViewById(R.id.totalChance);
        play = findViewById(R.id.play);

       /* //Advertisment
        MobileAds.initialize(this, "ca-app-pub-3642751437734166~2812470254");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

*/
     /*   mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3642751437734166/7077102601");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //reload add
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
*/

        //


        final LuckyWheelView luckyWheelView = (LuckyWheelView) findViewById(R.id.luckyWheel);
        balance = findViewById(R.id.balance);
        share = findViewById(R.id.share);
        redeem = findViewById(R.id.redeem);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("WHEEL", 0);
        int selected = pref.getInt("amount", 0);
        // String selected =pref.getString("key","xyz");
        // Toast.makeText(this, ""+selected, Toast.LENGTH_SHORT).show();

        balance.setText(String.valueOf("Bal ₹" + selected));


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "दोस्तों online पैसे कमाने का एक नया तरीका आया है। मैंने अभी 6035 रुपए कमाऐ हैं। \uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47 इसमें आपको कुछ देर के लिए एक गेम खेलना है। \uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47 आपको एक पहिया दिखाई देगा, जिस पर कुछ रकम लिखी हुई होगी, जिसे आपको प्ले बटन पर क्लिक करके घुमाना है। और जिस रकम पर पहिया रुकेगा वह राशि आपके बैलेंस में ऐड कर दी जाएगी । और फिर आप उन पैसो को ट्रांसफर कर सकते है। \uD83D\uDCF1\uD83D\uDCF1\uD83D\uDCF1\uD83D\uDCF1\uD83D\uDCF1\uD83D\uDCF1\uD83D\uDCF1\uD83D\uDCF1\uD83D\uDCF1\uD83D\uDCF1 आप भी ये गेम खेल कर कमा सकते है हजारों रुपए हर रोज । http://youthlive.in/spintoearn/spintoearn.apk");//
                //
                final SharedPreferences pref = getApplicationContext().getSharedPreferences("WHEEL", 0);
                int shareamount = pref.getInt("amount", 0);
                total1 = shareamount + 10;

                editor = pref.edit();
                editor.putInt("amount", total1);
                editor.apply();

                handler = new Handler();

                final Runnable r = new Runnable() {
                    public void run() {
                        balance.setText(String.valueOf("Bal ₹ " + pref.getInt("amount", 0)));
                        handler.postDelayed(this, 10000);
                    }
                };
//
                handler.postDelayed(r, 10000);


                try {
                    getApplicationContext().startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    // ToastHelper.MakeShortText("Whatsapp have not been installed.");
                    Toast.makeText(MainActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }

                //  loadInterAd();

                // Toast.makeText(MainActivity.this, "rotate", Toast.LENGTH_SHORT).show();
            }
        });
        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent paymentIntent = new Intent(MainActivity.this, PaymentActivity.class);
                startActivity(paymentIntent);
                //satrt app ad
                StartAppAd.showAd(MainActivity.this);
               // loadInterAd();


            }
        });

        LuckyItem luckyItem1 = new LuckyItem();
        luckyItem1.text = "10";
        luckyItem1.icon = R.drawable.test1;
        luckyItem1.color = 0xffFFF3E0;
        data.add(luckyItem1);

        LuckyItem luckyItem2 = new LuckyItem();
        luckyItem2.text = "20";
        luckyItem2.icon = R.drawable.test2;
        luckyItem2.color = 0xffFFE0B2;
        data.add(luckyItem2);

        LuckyItem luckyItem3 = new LuckyItem();
        luckyItem3.text = "30";
        luckyItem3.icon = R.drawable.test3;
        luckyItem3.color = 0xffFFCC80;
        data.add(luckyItem3);

        //////////////////
        LuckyItem luckyItem4 = new LuckyItem();
        luckyItem4.text = "bankrupt";
        //  luckyItem4.icon = R.drawable.test4;
        luckyItem4.icon = R.drawable.skull;
        luckyItem4.color = 0xffFFF3E0;
        data.add(luckyItem4);

        LuckyItem luckyItem5 = new LuckyItem();
        luckyItem5.text = "50";
        luckyItem5.icon = R.drawable.test5;
        luckyItem5.color = 0xffFFE0B2;
        data.add(luckyItem5);

        LuckyItem luckyItem6 = new LuckyItem();
        luckyItem6.text = "60";
        luckyItem6.icon = R.drawable.test6;
        luckyItem6.color = 0xffFFCC80;
        data.add(luckyItem6);
        //////////////////

        //////////////////////
        LuckyItem luckyItem7 = new LuckyItem();
        luckyItem7.text = "70";
        luckyItem7.icon = R.drawable.test7;
        luckyItem7.color = 0xffFFF3E0;
        data.add(luckyItem7);

        LuckyItem luckyItem8 = new LuckyItem();
        luckyItem8.text = "80";
        luckyItem8.icon = R.drawable.test8;
        luckyItem8.color = 0xffFFE0B2;
        data.add(luckyItem8);


        LuckyItem luckyItem9 = new LuckyItem();
        luckyItem9.text = "90";
        luckyItem9.icon = R.drawable.test9;
        luckyItem9.color = 0xffFFCC80;
        data.add(luckyItem9);
        ////////////////////////

        LuckyItem luckyItem10 = new LuckyItem();
        luckyItem10.text = "100";
        luckyItem10.icon = R.drawable.test10;
        luckyItem10.color = 0xffFFE0B2;
        data.add(luckyItem10);

        LuckyItem luckyItem11 = new LuckyItem();
        luckyItem11.text = "110";
        luckyItem11.icon = R.drawable.test10;
        luckyItem11.color = 0xffFFE0B2;
        data.add(luckyItem11);

        LuckyItem luckyItem12 = new LuckyItem();
        luckyItem12.text = "120";
        luckyItem12.icon = R.drawable.test10;
        luckyItem12.color = 0xffFFE0B2;
        data.add(luckyItem12);

        /////////////////////

        luckyWheelView.setData(data);
        //luckyWheelView.setRound(getRandomRound());
        luckyWheelView.setRound(4);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play.setEnabled(false);
                //
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPref", 0);

                counter = sharedPreferences.getInt("count", 0);
                if (counter < 15) {


                    int index = getRandomIndex();
                    // int index=4;

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("WHEEL", 0);
                    int amount = pref.getInt("amount", 0);
                    if (amount > 7100) {
                        int index1 = 4;
                        luckyWheelView.startLuckyWheelWithTargetIndex(index1);
                    } else {
                        // to avoid bankrupt
                        if (index == 4) {
                            index = index + 1;
                        }
                        luckyWheelView.startLuckyWheelWithTargetIndex(index);
                    }

                    SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("myPref", 0);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    counter = counter + 1;
                    editor.putInt("count", counter);
                    //
                    Date day = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd");
                    String formattedDate = df.format(day);

                    editor.putString("date", formattedDate);

                    editor.apply();
                    chance.setText("Left=" + String.valueOf(10 - counter));
                    //
                    // luckyWheelView.startLuckyWheelWithTargetIndex(index);
                } else {
                    Snackbar snackbar = Snackbar.make(relativeLayout,"Todays limit over Keep playing next day",Snackbar.LENGTH_LONG);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.green));
                    snackbar.show();

                   // Toast.makeText(getApplicationContext(), "Todays limit over Keep playing next day", Toast.LENGTH_SHORT).show();
                    play.setEnabled(true);
                }
            }
        });


        luckyWheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                // Toast.makeText(getApplicationContext(), String.valueOf(index), Toast.LENGTH_SHORT).show();
                play.setEnabled(true);
                if (index == 4) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("WHEEL", 0);
                    editor = pref.edit();
                    editor.clear();
                    //editor.commit();
                    editor.apply();

                    int total = 0;

                    balance.setText(String.valueOf("Bal ₹" + total));

                    Snackbar snackbar = Snackbar.make(relativeLayout, R.string.bankruptMessage, Snackbar.LENGTH_LONG);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.green));
                    snackbar.show();


                    //loadInterAd();


                } else {
                    if (index == 0) {
                        index = index + 1;
                    }
                    int amount1 = (index * 10);

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("WHEEL", 0);
                    int amount = pref.getInt("amount", 0);
                    int total = amount1 + amount;

                    editor = pref.edit();
                    editor.putInt("amount", total);
                    editor.putString("key", "value");
                    editor.commit();
                    editor.apply();

                    balance.setText(String.valueOf("Bal ₹" + total));

                    //startApp
                    startAppAd.showAd(); // show the ad
                   // loadInterAd();
                }


            }


        });
    }

   /* private void loadInterAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
*/
    private int getRandomIndex() {
        Random rand = new Random();
        return rand.nextInt(data.size() - 1) + 0;
    }

    private int getRandomRound() {
        Random rand = new Random();
        return rand.nextInt(10) + 15;
    }


    //ads by startapp
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }
}
