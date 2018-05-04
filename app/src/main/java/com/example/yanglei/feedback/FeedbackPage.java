package com.example.yanglei.feedback;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FeedbackPage extends AppCompatActivity {
    private CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18;
    private TextView t1;
    private String SectionName;
    private Button b1;
    private String[] features = new String[18];
    private Boolean[] checkbox = new Boolean[18];
    private static int[] checksum = new int[18];
    private FileWriter fw,fw1;
    private BufferedWriter writer,writer1;
    private CountTimer countTimer;
    private int max =0;
    {
        try {
            fw = new FileWriter("/storage/sdcard0/output.txt",true);
            fw1 = new FileWriter("/storage/sdcard0/total.txt");
            writer = new BufferedWriter(fw);
            writer1 = new BufferedWriter(fw1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.transition.slide_down,R.transition.slide_top);
        setContentView(R.layout.feedback_page);

        countTimer = new CountTimer(10000,1000,FeedbackPage.this);
        SectionName = EnterPage.getName();
        t1 = (TextView) findViewById(R.id.t1);
        t1.setText("You have scanned " + SectionName +". Please select the areas\n you enjoyed the most from this department.");


        features[0] = "Interactive";
        features[1] = "Advertisements";
        features[2] = "Educational";
        features[3] = "Informative";
        features[4] = "Design";
        features[5] = "Coherent";
        features[6] = "Products";
        features[7] = "Engaging";
        features[8] = "Fun";
        features[9] = "Artistic";
        features[10] = "Universal";
        features[11] = "Memorable";
        features[12] = "Interesting";
        features[13] = "Well-paced";
        features[14] = "Engaging";
        features[15] = "Layout";
        features[16] = "User-friendly";
        features[17] = "Insightful";

        b1 = (Button) findViewById(R.id.b1);
        c1 = (CheckBox) findViewById(R.id.checkBox1);
        c2 = (CheckBox) findViewById(R.id.checkBox2);
        c3 = (CheckBox) findViewById(R.id.checkBox3);
        c4 = (CheckBox) findViewById(R.id.checkBox4);
        c5 = (CheckBox) findViewById(R.id.checkBox5);
        c6 = (CheckBox) findViewById(R.id.checkBox6);
        c7 = (CheckBox) findViewById(R.id.checkBox7);
        c8 = (CheckBox) findViewById(R.id.checkBox8);
        c9 = (CheckBox) findViewById(R.id.checkBox9);
        c10 = (CheckBox) findViewById(R.id.checkBox10);
        c11 = (CheckBox) findViewById(R.id.checkBox11);
        c12 = (CheckBox) findViewById(R.id.checkBox12);
        c13 = (CheckBox) findViewById(R.id.checkBox13);
        c14 = (CheckBox) findViewById(R.id.checkBox14);
        c15 = (CheckBox) findViewById(R.id.checkBox15);
        c16 = (CheckBox) findViewById(R.id.checkBox16);
        c17 = (CheckBox) findViewById(R.id.checkBox17);
        c18 = (CheckBox) findViewById(R.id.checkBox18);

        //TODO can change to read File if need more features in future--make it easier
        c1.setText(features[0]);
        c2.setText(features[1]);
        c3.setText(features[2]);
        c4.setText(features[3]);
        c5.setText(features[4]);
        c6.setText(features[5]);
        c7.setText(features[6]);
        c8.setText(features[7]);
        c9.setText(features[8]);
        c10.setText(features[9]);
        c11.setText(features[10]);
        c12.setText(features[11]);
        c13.setText(features[12]);
        c14.setText(features[13]);
        c15.setText(features[14]);
        c16.setText(features[15]);
        c17.setText(features[16]);
        c18.setText(features[17]);

        //TODO if have time: once tick 5 selections, cannot tick more!! c1.setOnclickListener and ischecked of each time click on it.

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max=0;
                checkbox[0]=c1.isChecked();
                checkbox[1]=c2.isChecked();
                checkbox[2]=c3.isChecked();
                checkbox[3]=c4.isChecked();
                checkbox[4]=c5.isChecked();
                checkbox[5]=c6.isChecked();
                checkbox[6]=c7.isChecked();
                checkbox[7]=c8.isChecked();
                checkbox[8]=c9.isChecked();
                checkbox[9]=c10.isChecked();
                checkbox[10]=c11.isChecked();
                checkbox[11]=c12.isChecked();
                checkbox[12]=c13.isChecked();
                checkbox[13]=c14.isChecked();
                checkbox[14]=c15.isChecked();
                checkbox[15]=c16.isChecked();
                checkbox[16]=c17.isChecked();
                checkbox[17]=c18.isChecked();
                for(int i =0; i< checkbox.length; i++){
                    if(checkbox[i]){
                        max+=1;
                    }
                }
                if(max<=3) {
                    max=0;
                    String s = "";
                    for (int i = 0; i < checkbox.length; i++) {
                        if (checkbox[i]) {
                            s += features[i] + ", ";
                            checksum[i] += 1;
                        }
                    }
                    try {
                        writer.write(s);
                        writer.newLine();
                        writer.flush();
                        writer.close();
                        for (int i = 0; i < checksum.length; i++) {
                            writer1.write(features[i] + " " + checksum[i]);
                            writer1.newLine();
                            writer1.flush();
                        }
                        writer1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(FeedbackPage.this, ResultPage.class);
                    startActivity(intent);
                } else{
                    max=0;
                    Toast.makeText(FeedbackPage.this, "Sorry, Only allow to select max 3 features. Thanks!", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(FeedbackPage.this, MainActivity.class);
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


