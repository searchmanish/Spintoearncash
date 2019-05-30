package com.spintoearncash;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;



import com.startapp.android.publish.adsCommon.StartAppAd;

import org.json.JSONException;
import org.json.JSONObject;

import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;

public class PaymentActivity extends AppCompatActivity {
    private String TAG ="Payment";
    Toolbar toolbar;
    ImageView payment1, payment2, payment3;
    RelativeLayout relativeLayout;

    //instamojo
    InstamojoPay instamojoPay = new InstamojoPay();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment1 = findViewById(R.id.payment1);
        payment2 = findViewById(R.id.payment2);
        payment3 = findViewById(R.id.payment3);

        relativeLayout = findViewById(R.id.relative);

        //back button
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Payment Method");
        toolbar.setNavigationIcon(R.drawable.ic_back_button4);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // b.mylist.remove(b.mylist.size() - 1);

                finish();

            }
        });


        payment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar snackbar = Snackbar.make(relativeLayout, R.string.paymentMessage, Snackbar.LENGTH_LONG);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(getResources().getColor(R.color.green));
                snackbar.show();

                callInstamojoPay("search.manish23@gmail.com", "9811429903",
                        "11", "Payment spintoearn app ", "manish");




            }
        });


        payment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar snackbar = Snackbar.make(relativeLayout, R.string.paymentMessage, Snackbar.LENGTH_LONG);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(getResources().getColor(R.color.green));
                snackbar.show();

                // Toast.makeText(PaymentActivity.this, "Insufficient Balance to reedem", Toast.LENGTH_SHORT).show();

            }
        });

        payment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(relativeLayout, R.string.paymentMessage, Snackbar.LENGTH_LONG);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(getResources().getColor(R.color.green));
                snackbar.show();

                // Toast.makeText(PaymentActivity.this, "Insufficient Balance to reedem", Toast.LENGTH_SHORT).show();

            }
        });

    }

    //ads by startapp
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }



    //............instamojo
    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        final Activity activity = this;

        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initListener();
        instamojoPay.start(activity, pay, listener);
    }

    InstapayListener listener;

    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                Log.e(TAG, " payment done succesfully "+ response);

                String resArray[] =   response.split(":");
                Log.e(TAG,  " array index 0 "+ resArray[0] + "--orderid -"+ resArray[1] +"---paymentid--"+ resArray[3] );
                String orderid =  resArray[1].substring(resArray[1].indexOf("=")+1);
                String paymentid =  resArray[3].substring(resArray[3].indexOf("=")+1);

                Log.e(TAG, " order id "+ orderid +" paymentid "+ paymentid);

                //CallPlaceOrderAPI( orderid, paymentid, totalamount, addressid);

                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG) .show();
                unregisterReceiver(instamojoPay);

                //navigate to next page
                Intent intent =new Intent(PaymentActivity.this,PaymentSucess.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int code, String reason) {
                Log.e(TAG, " payment fail "+ code+ "  reason -- " +reason);
                Toast.makeText(getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG) .show();
            }
        };
    }



}