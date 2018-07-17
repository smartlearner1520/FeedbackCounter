package com.example.yanglei.feedback;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalThankYou extends AppCompatActivity {

    Button nextBarcode;
    Button finish;

    private CountTimer countTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_thank_you);

        nextBarcode = findViewById(R.id.nextscan_button);
        nextBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalThankYou.this,EnterPage.class);
                startActivity(intent);
            }
        });

        finish = findViewById(R.id.end_button);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( FinalThankYou.this, MainActivity.class);
                startActivity(intent);

            }
        });

        countTimer = new CountTimer(300000,60000,FinalThankYou.this);
    }

    private void timeStart(){
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                countTimer.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        countTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeStart();
    }
}
