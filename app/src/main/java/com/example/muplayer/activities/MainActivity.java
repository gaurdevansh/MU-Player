package com.example.muplayer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.muplayer.R;
import com.example.muplayer.model.AudioItem;
import com.example.muplayer.utils.AudioDataUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    List<AudioItem> audioItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioItemList = AudioDataUtil.getInstance().getAudioItemList();

        if(audioItemList!=null) {
            for(AudioItem x: audioItemList) {
                Log.d(TAG, "onCreate: "+x.toString());
            }
        }
    }
}