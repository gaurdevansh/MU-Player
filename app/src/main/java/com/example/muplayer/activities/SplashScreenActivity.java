package com.example.muplayer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;

import com.example.muplayer.R;
import com.example.muplayer.model.AudioItem;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private int SPLASH_SCREEN_TIME = 2000;
    private List<AudioItem> audioItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(!checkPermission())
            acquirePermission();
        else
            startMainActivity();
    }

    private boolean checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            return false;
        else return true;
    }

    private void acquirePermission() {
        ActivityCompat.requestPermissions(SplashScreenActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
    }

    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIME);
    }

    private void loadAudioFiles() {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String proj[] = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM
        };

        Cursor cursor = getContentResolver().query(uri,
                proj,
                null,
                null, null
        );
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(0);
                String duration = cursor.getString(1);
                String data = cursor.getString(2);
                String artist = cursor.getString(3);
                String album = cursor.getString(4);
                AudioItem audioItem = new AudioItem(title, duration, data, artist, album);
                audioItemList.add(audioItem);
            }
        }
    }
}