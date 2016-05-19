package com.tuananh.music;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btnPlay)
    Button btnPlay;
    @Bind(R.id.btnStop)
    Button btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnStop.setVisibility(View.GONE);
        Toast.makeText(this, Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/cogai.mp3",Toast.LENGTH_LONG).show();
    }

    public void playMusic(View view){
        startService(new Intent(getBaseContext(),MyService.class));
        btnStop.setVisibility(View.VISIBLE);
        btnPlay.setVisibility(View.GONE);
    }

    public void stopMusic(View view){
        stopService(new Intent(getBaseContext(),MyService.class));
        btnStop.setVisibility(View.GONE);
        btnPlay.setVisibility(View.VISIBLE);
    }
}
