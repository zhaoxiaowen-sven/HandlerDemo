package com.sven.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HandleDemo";
    private TextView tv;

    private Handler handler1;

    private Handler handler2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        handler1 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.i(TAG, "msg = "+msg.what);
                tv.setText("哈哈, 我拦截了！！！");
                return false;
            }
        }){
            @Override
            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        tv.setText("哈哈");
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        };


       new Thread(new Runnable() {
            @Override
            public void run() {
//                Looper.prepare();
//                handler2 = new Handler();
//                Message msg = Message.obtain();
//                wait3seconds();

                handler1.sendEmptyMessage(0);
//                handler1.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        wait3seconds();
//                        tv.setText("呵呵");
//                    }
//                });
//                Log.i(TAG,"1111");
//                boolean b =
//                        tv.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i(TAG,"2222");
//                        tv.setText("嘿嘿");
//                    }
//                });
//                Log.i(TAG, "b = "+b);
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        wait3seconds();
//                        tv.setText("哦哦");
//                    }
//                });
            }
        }).start();
    }

    private void wait3seconds() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onclick(View v){
//        switch (v)
    }
}
