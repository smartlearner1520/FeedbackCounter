package com.example.yanglei.feedback;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

class CountTimer extends CountDownTimer {
    private Context context;

    public CountTimer(long millisInFuture, long countDownInterval,Context context) {
        super(millisInFuture, countDownInterval);
        this.context=context;
    }
    @Override
    public void onFinish() {
        context.startActivity(new Intent(context, MainActivity.class));

    }
    @Override
    public void onTick(long millisUntilFinished) {
        Log.i("time", "time running");
    }
}