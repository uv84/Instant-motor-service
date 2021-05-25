package com.example.ims;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class WorkDone extends AppCompatActivity implements PaymentResultListener {
    Button paybtn;
    TextView paytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_done);
        Checkout.preload(getApplicationContext());

        paytext=(TextView)findViewById(R.id.tvmech);
        paybtn = (Button) findViewById(R.id.workdone);

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makepayment();
            }
        });

    }

    private void makepayment() {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_0MjtfUvpT5gYmb");

        checkout.setImage(R.drawable.logo);
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "IMS");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "30000");//300 X 100
            options.put("prefill.email", "urvalchikhale@gmail.com");
            options.put("prefill.contact", "8424849939");
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {

        String mecid= getIntent().getStringExtra("phone");
        Intent backhome = new Intent(this ,userRating.class);
        backhome.putExtra("phone",mecid);
        startActivity(backhome);

    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}