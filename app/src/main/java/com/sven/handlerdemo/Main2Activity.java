package com.sven.handlerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main2Activity extends AppCompatActivity {

    private String TAG = "Main2Activity";
    private Runnable command;
    private ExecutorService executorService;
    private ScheduledExecutorService scheduledExecutorService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        command = new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG,"threadname = " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                    Log.i(TAG, "this command");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.start:
//                Executors.newScheduledThreadPool(3).schedule(command, 1, TimeUnit.SECONDS);
                scheduledExecutorService = Executors.newScheduledThreadPool(3);
                scheduledExecutorService.scheduleAtFixedRate(command, 1, 2, TimeUnit.SECONDS);
//                executorService = Executors.newFixedThreadPool(3);
//                for(int i=0;i<10;i++){
//                    final int index = i;
//                    executorService.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.i(TAG, "thread name = "+Thread.currentThread().getName() + "taskid"+index);
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                }

                break;
            case R.id.stop:
                if(executorService != null){
                    executorService.shutdown();
                }
                if(scheduledExecutorService != null){
                    scheduledExecutorService.shutdown();
                }
                break;
        }
    }
}
