package com.example.muplayer.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class AudioService extends Service {

    private static final String TAG = "AudioService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class AudioBinder extends Binder {
        public AudioService getServiceInstance() {
            return AudioService.this;
        }
    }
}
