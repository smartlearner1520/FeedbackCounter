package com.example.yanglei.feedback;

import android.content.Intent;
import android.os.Environment;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FeedbackPage extends AppCompatActivity {
    private CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18;
    private TextView t1,n1;
    private String SectionName;
    private Button b1;
    private String[] features = new String[18];
    private Boolean[] checkbox = new Boolean[18];
    private int[][] checksum = new int[8][18];
    private int index;
    private FileWriter fw,fw1,fw2;
    private FileReader fr,fr1;
    private BufferedWriter writer,writer1,writer2;
    private BufferedReader reader,reader1;
    private CountTimer countTimer;
    private String[] sectionNameList = EnterPage.getSectionNameList();
    private int[] sectionLikesList = new int[8];
    private HashMap<String,Integer> sectionList = EnterPage.getSectionList();
    private int max =0;
    File f = new File(MainActivity.getDirPath()+EnterPage.getBarcodeStr()+".txt");
    File file = new File(MainActivity.getDirPath()+"Section.txt");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.transition.slide_down,R.transition.slide_top);
        setContentView(R.layout.feedback_page);

        try{
            Log.i("yl","section " + file.exists());
            if(!file.exists()){
                Log.i("yl","write a new file");
                fw = new FileWriter(MainActivity.getDirPath()+"Section.txt");
                writer = new BufferedWriter(fw);
                for(int i=0; i<8;i++){
                    writer.write(sectionNameList[i] +" 0");
                    writer.newLine();
                    writer.flush();
                }
                writer.close();
            } else{
                fr = new FileReader(file);
                reader = new BufferedReader(fr);
                String line;
                while((line = reader.readLine())!=null){
                    String name = line.split(" ")[0];
                    int index = sectionList.get(name);
                    sectionLikesList[index] = Integer.parseInt(line.split(" ")[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        countTimer = new CountTimer(300000,60000,FeedbackPage.this);
        SectionName = EnterPage.getBarcodeStr();
        //SectionName = EnterPage.getName();

        index = sectionList.get(SectionName);
        sectionLikesList[index] +=1;
        try {
            fw2 = new FileWriter(MainActivity.getDirPath()+"Section.txt");
            writer2 = new BufferedWriter(fw2);
            for(int i=0; i<8; i++){
                writer2.write(sectionNameList[i] + " " + sectionLikesList[i]);
                writer2.newLine();
                writer2.flush();
            }
            writer2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Log.i("yl","features " + f.exists());
            if(f.exists()){
                fr1 = new FileReader(f);
                reader1 = new BufferedReader(fr1);
                String line;
                int i=0;
                while((line = reader1.readLine())!=null){
                    checksum[index][i] = Integer.parseInt(line.split(" ")[1]);
                    Log.i("yl",""+checksum[index][i]);
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        t1 = (TextView) findViewById(R.id.t1);
        t1.setText("You have scanned " + SectionName +". \nPlease select the areas you enjoyed the most from this department.");


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
        n1 = (TextView) findViewById(R.id.notice1);

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

        //b1.setOnClickListener(new View.OnClickListener() {
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
                if(max<=5) {
                    max=0;
                    for (int i = 0; i < checkbox.length; i++) {
                        if (checkbox[i]) {
                            checksum[index][i] += 1;
                        }
                    }
                    //TODO here is the way to write each section features infor
                    try {
                        fw1 = new FileWriter(MainActivity.getDirPath()+EnterPage.getBarcodeStr()+".txt");
                        writer1 = new BufferedWriter(fw1);
                        for (int i = 0; i <18; i++) {
                            writer1.write(features[i] + " " + checksum[index][i]);
                            writer1.newLine();
                            writer1.flush();
                        }
                        writer1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Intent intent = new Intent(FeedbackPage.this, ResultPage.class);
                    //startActivity(intent);

                    Intent intent = new Intent(FeedbackPage.this,FinalThankYou.class);
                    startActivity(intent);
                } else{
                    max=0;
                    n1.setText("Sorry, only allow to select max 5 features. Thanks!");
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


