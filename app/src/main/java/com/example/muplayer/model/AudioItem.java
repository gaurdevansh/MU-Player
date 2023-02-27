package com.example.muplayer.model;

import java.io.Serializable;

public class AudioItem implements Serializable {
    private String title;
    private String duration;
    private String path;
    private String artist;
    private String album;

    public AudioItem(String title, String duration, String path, String artist, String album) {
        this.title = title;
        this.duration = duration;
        this.path = path;
        this.artist =artist;
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "AudioItem{" +
                "title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", path='" + path + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}
