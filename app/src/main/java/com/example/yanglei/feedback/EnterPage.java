package com.example.yanglei.feedback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EnterPage extends AppCompatActivity {
    private Button button;
    //private static String inpString ="HOSPITALITY";
    private CountTimer countTimer;
    private EditText txt;
    private static volatile String barcodestr;
    private static HashMap<String,Integer> sectionList = new HashMap<>();
    private static String[] sectionNameList = new String[8];
    {setSectionList();}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_page);


        txt = (EditText) findViewById(R.id.editText);
        // using state =0; if state>1 --> pop up to ask do u want change to another tap? yes or no... // set only when press button and go next.
        txt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int inType = txt.getInputType();
                txt.setInputType(InputType.TYPE_NULL);
                txt.onTouchEvent(motionEvent);
                txt.setInputType(inType);
                return true;
            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(txt.getText().toString().equals("")){

                }
                barcodestr = txt.getText().toString();
                if(!sectionList.containsKey(barcodestr)){
                    txt.setText("");
                    txt.setHint("Invalid Input");
                }
            }
        };
        final Thread thread = new Thread(runnable);
        thread.start();

        countTimer = new CountTimer(10000,1000,EnterPage.this);

        button = (Button) findViewById(R.id.b3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo need to change to barcodestr!!!
                if(sectionList.containsKey(barcodestr)){
                    Intent intent = new Intent(EnterPage.this,FeedbackPage.class);
                    startActivity(intent);
                }
            }
        });

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

    private void setSectionList(){
        sectionList.put("AUTOMOBILE",0);
        sectionList.put("ENTERTAINMENT",1);
        sectionList.put("FASHION",2);
        sectionList.put("FOOD",3);
        sectionList.put("HOSPITALITY",4);
        sectionList.put("HOUSEHOLD",5);
        sectionList.put("MEDICAL",6);
        sectionList.put("TRAVEL",7);
        sectionNameList[0] = "AUTOMOBILE";
        sectionNameList[1] = "ENTERTAINMENT";
        sectionNameList[2] = "FASHION";
        sectionNameList[3] = "FOOD";
        sectionNameList[4] = "HOSPITALITY";
        sectionNameList[5] = "HOUSEHOLD";
        sectionNameList[6] = "MEDICAL";
        sectionNameList[7] = "TRAVEL";
    }

    public static HashMap<String,Integer> getSectionList(){
        return sectionList;
    }

    public static String[] getSectionNameList(){
        return sectionNameList;
    }

    public static String getBarcodeStr(){
        return barcodestr;
    }
//    public static String getName(){
//        return inpString;
//    }

}
