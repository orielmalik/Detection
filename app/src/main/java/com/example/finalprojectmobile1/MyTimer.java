package com.example.finalprojectmobile1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class MyTimer  {
    final int DELAY = 1000;
    String timer_LBL_time=null;
    long startTime = 0;
    final Handler handler=new Handler();
   public   Runnable runnable;
public  MyTimer()
{

}

    public void updatePlayTime() {
        Log.d("TimerCount", "" + System.currentTimeMillis());
        long millis = System.currentTimeMillis() - startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        int hours = minutes / 60;
        minutes = minutes % 60;
        hours %= 24;

        timer_LBL_time=(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
    public void stopTimer() {
        handler.removeCallbacks(runnable);
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
public int getMinutes()
{

    Log.d("TimePlay", "" + System.currentTimeMillis());
    long millis = System.currentTimeMillis() -startTime;
    int seconds = (int) (millis / 1000);
    return seconds / 60;
}
public int getSeconds()
{
    Log.d("TimePlay", "" + System.currentTimeMillis());
    long millis = System.currentTimeMillis() -startTime;
    int seconds = (int) (millis / 1000);
    return  seconds;
}
    public void startTimer() {
       startTime = System.currentTimeMillis();
        handler.postDelayed(runnable,DELAY);
    }
}
