package com.tuananh.music;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by trinhtuananh on 5/19/2016.
 */
public class MyService extends Service {
    MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player=new MediaPlayer();
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/cogai.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            player.prepare();
            player.start();
            Toast.makeText(this, "Service started",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
//        super.onDestroy();
        player.stop();
        player.release();
        Toast.makeText(this, "Service destroyed",Toast.LENGTH_LONG).show();
    }

    /*public void playSound(String soundPath){
        MediaPlayer m = new MediaPlayer();

        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }

        });

        try {

            AssetFileDescriptor descriptor = getBaseContext().getAssets().openFd(soundPath);
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(),
                    descriptor.getLength());

            descriptor.close();

            m.prepare();
            m.setVolume(100f, 100f);
            m.setLooping(false);
            m.start();

        } catch (Exception e) {
        }
    }*/
}
