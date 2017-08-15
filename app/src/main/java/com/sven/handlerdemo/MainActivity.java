package com.sven.handlerdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HandlerDemo";

    private TextView tv;

    private ProgressBar progressBar;

    private Handler handler1;

    private Handler handler2;

    private MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(5);

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
    }

    private void wait3seconds() {
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onclick(View v){
        switch (v.getId()){
            case R.id.update:
                threadstart();
                break;

            case R.id.update2:
                myTask = new MyTask();
                myTask.execute("begin");
                break;

            case R.id.stop:
                myTask.cancel(true);
                break;
            default:
                break;
        }
    }

    class MyTask extends AsyncTask<String, Integer, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(String... params) {

            try {
                int i = 0;
                while (true){
                    publishProgress(i);
                    if (i == 5){
                        break;
                    }
                    i++;
                    Thread.sleep(1000);
                }
                return i;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            tv.setText("进度为: "+ values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            tv.setText("完成了");
        }
    }

    private void threadstart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                Looper.prepare();
//                handler2 = new Handler();
//                Message msg = Message.obtain();
//                wait3seconds();

//                handler1.sendEmptyMessage(0);
//                handler1.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        wait3seconds();
//                        tv.setText("呵呵");
//                    }
//                });
//                Log.i(TAG,"1111");
                boolean b =
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG,"2222");
                                wait3seconds();
                                tv.setText("嘿嘿");
                            }
                        });
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
}
