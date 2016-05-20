package com.tuananh.music;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        Toast.makeText(this, Environment.getExternalStorageDirectory()+"/Download/cogai.mp3",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isMyServiceRunning(MyService.class))
        {
            btnStop.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "Destroy",Toast.LENGTH_LONG).show();
        stopService(new Intent(getBaseContext(),MyService.class));
        super.onDestroy();

    }

    public void playMusic(View view){
        startService(new Intent(getBaseContext(),MyService.class));
        btnStop.setVisibility(View.VISIBLE);
        btnPlay.setVisibility(View.GONE);
        /*MediaPlayer player=new MediaPlayer();
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/cogai.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        try {
            player.prepare();
            player.start();
            Toast.makeText(this, "Service started",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*String path = Environment.getExternalStorageDirectory()+"/Download/cogai.mp3";
        MediaPlayer player = new MediaPlayer();
        File file = new File(path);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            player.setDataSource(inputStream.getFD());
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();*/
    }

    public void stopMusic(View view){
        stopService(new Intent(getBaseContext(),MyService.class));
        btnStop.setVisibility(View.GONE);
        btnPlay.setVisibility(View.VISIBLE);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
