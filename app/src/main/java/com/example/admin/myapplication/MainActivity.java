package com.example.admin.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    public static final int POOL_SIZE = 3;
    public static final int KEEP_ALIVE_TIME = 1;
    public static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(this);


    }


    @Override
    public boolean handleMessage(Message msg) {
        String a = msg.getData().getString("data").trim();
        String[] code = a.split("&");
        int id = Integer.valueOf(code[0]);
        switch (id) {
            case R.id.tvThread1:
            case R.id.tvThread2:
            case R.id.tvThread3:
            case R.id.tvThread4:
            case R.id.tvTime1:
            case R.id.tvTime2:
            case R.id.tvTime3:
            case R.id.tvTime4:
                ((TextView) findViewById(id)).setText(code[1]);
                break;
            case R.id.pb1:
            case R.id.pb2:
            case R.id.pb3:
            case R.id.pb4:
                switch (code[1]) {
                    case "1":
                        ((ProgressBar) findViewById(id)).setMax(Integer.valueOf(code[2]));
                        System.out.println("Max progressBar " + code[2]);
                        break;
                    case "2":
                        ((ProgressBar) findViewById(id)).setProgress(Integer.valueOf(code[2]));
                        //System.out.println("progress bar progress"+code[2]);
                        break;
                }

                break;
        }
        return false;
    }

    public void buttonClickThreadPool(View view) {
        ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, new LinkedBlockingDeque<Runnable>());
        mThreadPoolExecutor.execute(new ProgressTask((TextView) findViewById(R.id.tvThread1), (TextView) findViewById(R.id.tvTime1), (ProgressBar) findViewById(R.id.pb1), handler));
        mThreadPoolExecutor.execute(new ProgressTask((TextView) findViewById(R.id.tvThread2), (TextView) findViewById(R.id.tvTime2), (ProgressBar) findViewById(R.id.pb2), handler));
        mThreadPoolExecutor.execute(new ProgressTask((TextView) findViewById(R.id.tvThread3), (TextView) findViewById(R.id.tvTime3), (ProgressBar) findViewById(R.id.pb3), handler));
        mThreadPoolExecutor.execute(new ProgressTask((TextView) findViewById(R.id.tvThread4), (TextView) findViewById(R.id.tvTime4), (ProgressBar) findViewById(R.id.pb4), handler));
    }

    public void asyncSerial(View view) {
    }

    public void asyncParallel(View view) {
    }
}
