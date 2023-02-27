package com.example.muplayer.utils;

import com.example.muplayer.model.AudioItem;

import java.util.ArrayList;
import java.util.List;

public class AudioDataUtil {

    private List<AudioItem> audioItemList = new ArrayList<>();
    private static AudioDataUtil instance=null;

    public static AudioDataUtil getInstance() {
        if(instance == null) {
            instance = new AudioDataUtil();
        }
        return instance;
    }
    private AudioDataUtil(){};

    public void setAudioItemList(List<AudioItem> audioItemList) {
        this.audioItemList = audioItemList;
    }

    public boolean isAudioItemListSet() {
        if(instance != null && audioItemList.size()!=0)
            return true;
        else
            return false;
    }

    public List<AudioItem> getAudioItemList() {
        if(instance != null && audioItemList.size()!=0)
            return audioItemList;
        else
            return null;
    }
}
