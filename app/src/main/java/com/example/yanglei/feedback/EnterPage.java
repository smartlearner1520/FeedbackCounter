package com.example.yanglei.feedback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EnterPage extends AppCompatActivity {
    private Button button;
    private static String inpString ="<SECTION>";
    private CountTimer countTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_page);

        countTimer = new CountTimer(10000,1000,EnterPage.this);


        button = (Button) findViewById(R.id.b3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterPage.this,FeedbackPage.class);
                startActivity(intent);
            }
        });

    }


    public static String getName(){
        return inpString;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }
        if(keyCode == KeyEvent.KEYCODE_HOME){
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("yl", "onBackPressed Called");
        Intent intent = new Intent(EnterPage.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){

            case MotionEvent.ACTION_UP:
                countTimer.start();
                break;

            default:countTimer.cancel();
                break;
        }
        return super.dispatchTouchEvent(ev);
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
