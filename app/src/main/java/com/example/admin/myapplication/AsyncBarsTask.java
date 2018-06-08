package com.example.admin.myapplication;

import android.os.AsyncTask;

public class AsyncBarsTask extends AsyncTask <Object, Integer, String>{

    ProgressTask progressTask;
    int time;
    String threadName;

    public AsyncBarsTask(ProgressTask progressTask) {
        this.progressTask = progressTask;
    }

    @Override
    protected void onPreExecute() {

        time = (int) (Math.round(Math.random() * 5));
        progressTask.bar.setMax(time);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... objects) {
        threadName = Thread.currentThread().getName();
        for (int i = 0; i <= time ; i++) {
            try {
                Thread.currentThread().sleep(500);
                publishProgress(i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressTask.tvTime.setText("Time : 0 / " + time);
        progressTask.tvThread.setText("Thread : " + threadName);
        progressTask.bar.setProgress(values[0]);
        progressTask.tvTime.setText("Time : " +values[0]+" / " + time);
    }
}
