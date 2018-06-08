package com.example.admin.myapplication;

import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.admin.myapplication.utils.HandlerUtils;


public class ProgressTask implements Runnable {

    public TextView tvThread;
    public TextView tvTime;
    public ProgressBar bar;
    int time;
    android.os.Handler handler;


    public ProgressTask(TextView tvThread, TextView tvTime, ProgressBar bar, android.os.Handler handler) {
        this.tvThread = tvThread;
        this.tvTime = tvTime;
        this.bar = bar;
        this.handler = handler;


    }

    public void run() {
        time = (int) (Math.round(Math.random() * 30));
        HandlerUtils.with(handler).sendMessage(""+tvThread.getId()+"&Thread : "+Thread.currentThread().getName());
        HandlerUtils.with(handler).sendMessage(""+bar.getId()+"&1&"+time);
        for (int i = 0; i <= time ; i++) {
            try {
                //Todo actualizar tv tiempo y progreso
                HandlerUtils.with(handler).sendMessage(""+tvTime.getId()+"&Time : "+i+"/"+time);
                HandlerUtils.with(handler).sendMessage(""+bar.getId()+"&2&"+i);
                Thread.currentThread().sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        HandlerUtils.with(handler).sendMessage(""+tvTime.getId()+"&Time : "+time+"/"+time);
        HandlerUtils.with(handler).sendMessage(""+bar.getId()+"&2&"+time);
    }



}
