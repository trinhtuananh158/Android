package com.tuananh.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }
}
