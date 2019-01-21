package com.spintoearncash;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView payment1, payment2, payment3;
    RelativeLayout relativeLayout;

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


}
