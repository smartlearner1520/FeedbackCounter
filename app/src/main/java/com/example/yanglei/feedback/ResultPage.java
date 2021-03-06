package com.example.yanglei.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ResultPage extends AppCompatActivity {
    private GraphView graphView;
    private DataPoint[] dataPoints;
    private FileReader fr;
    private BufferedReader bfr;
    private int[] intlist = new int[8];
    private String[] stlist = new String[8];
    private int total=0;
    int state=0;
    private CountTimer countTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultpage);

        countTimer = new CountTimer(300000,60000,ResultPage.this);

        try {
            fr = new FileReader(MainActivity.getDirPath()+"Section.txt");
            bfr = new BufferedReader(fr);
            String line=null;
            int i=0;
            while((line = bfr.readLine())!=null){
                stlist[i] = line.split(" ")[0];
                Log.i("yl",stlist[i]);
                intlist[i] = Integer.parseInt(line.split(" ")[1]);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataPoints = new DataPoint[8];
        for(int i=0; i<stlist.length;i++){
            total+=intlist[i];
        }
        //double[] test = new double[] {23.45,6.73,34.2,6.5,23.5,67.8,12.3,8.08};
        for(int i=0; i<stlist.length;i++){
            double d = intlist[i]/(double)total;
            DecimalFormat df = new DecimalFormat("#.####");
            d = Double.valueOf(df.format(d)) *100;
            dataPoints[i] = new DataPoint(i+1,d);
            //dataPoints[i] = new DataPoint(i+1,test[i]);
            Log.i("Percentage " , "" + dataPoints[i]);
        }


        graphView = (GraphView) findViewById(R.id.graph);
        GridLabelRenderer gridLabelRenderer = graphView.getGridLabelRenderer();
        gridLabelRenderer.setVerticalAxisTitle("Percentage (%)");
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(dataPoints);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        String[] newstr = new String[10];
        System.arraycopy(stlist,0,newstr,1,stlist.length);
//        newstr[1] = "AUTOMOBILE";
//        newstr[2] = "ENTERTAINMENT";
//        newstr[3] = "FASHION";
//        newstr[4] = "FOOD";
//        newstr[5] = "HOSPITALITY";
//        newstr[6] = "HOUSEHOLD";
//        newstr[7] = "MEDICAL";
//        newstr[8] = "TRAVEL";
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(1);
        format.setMinimumIntegerDigits(1);
        staticLabelsFormatter.setHorizontalLabels(stlist);
        staticLabelsFormatter.setDynamicLabelFormatter(new DefaultLabelFormatter(null,format));
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        graphView.addSeries(series);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(100);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(9);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getGridLabelRenderer().setHighlightZeroLines(false);
        //TODO all x and y lines visible
        graphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        //TODO vertical line visible
        graphView.getGridLabelRenderer().setVerticalLabelsVisible(false);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(80);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.rgb(0,0,250));

        graphView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                state++;
                if(state==1) {
                    getDialog();
                }
                return true;
            }
        });

    }

    public void getDialog(){
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ResultPage.this, MainActivity.class);
                        startActivity(intent);
                        state=0;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        state=0;
                    }
                })
                .show();

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
    public boolean onTouchEvent(MotionEvent event){
        state++;
        if(state==1) {
            getDialog();
        }
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            Intent intent = new Intent(ResultPage.this,MainActivity.class);
            startActivity(intent);
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            return true;
        }
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("yl", "onBackPressed Called");
        Intent intent = new Intent(ResultPage.this, MainActivity.class);
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
