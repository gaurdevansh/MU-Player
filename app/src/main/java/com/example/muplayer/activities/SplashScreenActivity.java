package com.example.muplayer.activities;

import androidx.annotation.NonNull;
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
import android.util.Log;
import android.widget.TextView;

import com.example.muplayer.R;
import com.example.muplayer.model.AudioItem;
import com.example.muplayer.utils.AudioDataUtil;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreenActivity";
    private List<AudioItem> audioItemList = new ArrayList<>();
    private TextView tv_splash;
    private int SPLASH_SCREEN_TIME = 2000;
    private int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv_splash = findViewById(R.id.tv_splash);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        if(!checkPermission())
            acquirePermission();
        else
            loadAudioFiles();
    }

    private boolean checkPermission() {
        Log.d(TAG, "checkPermission: Checking External Storage Permission");
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            return false;
        else return true;
    }

    private void acquirePermission() {
        Log.d(TAG, "acquirePermission: Acquiring External Storage Permission");
        ActivityCompat.requestPermissions(SplashScreenActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_MEDIA_AUDIO}, PERMISSION_REQUEST_CODE);
    }

    private void startMainActivity() {
        Log.d(TAG, "startMainActivity: Starting Main Activity");
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
        Log.d(TAG, "loadAudioFiles: Reading Local Audio Files");
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String proj[] = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM
        };

        Cursor cursor = getApplicationContext().getContentResolver().query(uri,
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
                //Log.d(TAG, "loadAudioFiles: read audio item");
                audioItemList.add(audioItem);
            }
        }
        else {
            Log.d(TAG, "loadAudioFiles: Cursor null");
        }
        AudioDataUtil.getInstance().setAudioItemList(audioItemList);
        startMainActivity();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //loadAudioFiles();

        }
        else {
            tv_splash.setTextSize(18);
            tv_splash.setText(R.string.storage_permission_text);
        }
    }

    private void loadAudioFilesFromAssets() {

    }
}