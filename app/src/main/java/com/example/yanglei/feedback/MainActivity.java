package com.example.yanglei.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    //private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        TODO: using thread to detect action
//        video = (VideoView) findViewById(R.id.video);
//        File file = new File("/storage/sdcard0/1.mp4");
//        Log.i("Video path", Environment.getExternalStorageDirectory().getPath());
//        MediaController mc = new MediaController(MainActivity.this);
//        if(file.exists()){
//            Log.i("Video path", "Found it");
//            video.setVideoPath(file.getAbsolutePath());
//            video.setMediaController(mc);
//            video.requestFocus();
//            try{
//                video.start();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mediaPlayer) {
//                    mediaPlayer.setLooping(true);
//                    if (!mediaPlayer.isPlaying()) {
//                        if (video.getCurrentPosition()!=0){
//                            mediaPlayer.seekTo(video.getCurrentPosition());
//                            mediaPlayer.start();
//                            mediaPlayer.pause();
//                            return;
//                        }
//                        mediaPlayer.start();
//                    }else{
//                        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
//                            @Override
//                            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
//                                if(i == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START){
//                                    if(video.getCurrentPosition()!=0){
//                                        mediaPlayer.seekTo(video.getCurrentPosition());
//                                        mediaPlayer.start();
//                                        mediaPlayer.pause();
//                                        return true;
//                                    }
//                                    return true;
//                                }
//                                return false;
//                            }
//                        });
//                    }
//                }
//            });
//
//            video.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                    return true;
//                }
//            });
//        } else{
//            Log.i("Video path", "No such file");
//            Toast.makeText(MainActivity.this, "no such file", Toast.LENGTH_SHORT).show();
//        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        Intent intent = new Intent(MainActivity.this,EnterPage.class);
        startActivity(intent);
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("yl", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("yl", "onBackPressed Called");
        new AlertDialog.Builder(this,R.style.MyDialogTheme)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }



}

