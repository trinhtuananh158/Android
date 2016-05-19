package com.example.trinhtuananh.myapplication;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    Handler handler=new Handler();
    AtomicBoolean isrunning=new AtomicBoolean(false);
    TextView tvTime;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tvTime=(TextView) findViewById(R.id.tvTime);
        /*doStart();
        tvTime=(TextView) findViewById(R.id.tvTime);
        handler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tvTime.setText("Countdown "+msg.arg1);
            }
        };*/
        btnStart= (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myAT = new MyAsyncTask(MainActivity.this);
                myAT.execute();
            }
        });
    }
    public void doStart()
    {
        //isrunning.set(false);
        final int i;
        Thread th=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i >= 0; i--) {
                    SystemClock.sleep(1000);
                    Message msg=handler.obtainMessage();
                    msg.arg1=i;
                    handler.sendMessage(msg);
                }
            }
        });
        //isrunning.set(true);
        th.start();
    }
}
