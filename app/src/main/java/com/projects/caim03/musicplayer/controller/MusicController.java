package com.projects.caim03.musicplayer.controller;


import android.content.ContentUris;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import com.projects.caim03.musicplayer.model.ObservableSong;
import com.projects.caim03.musicplayer.model.Song;
import java.util.List;

public class MusicController {

    private List<Song> songList;
    private Context context;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int pos = 0;
    private static MusicController instance = null;
    private ObservableSong observableSong;

    protected MusicController() {
        observableSong = ObservableSong.getInstance();
    }

    // SINGLETON PATTERN
    public static MusicController getInstance() {
        if (instance == null) {
            instance = new MusicController();
        }
        return instance;
    }

    public int getPos() {
        return pos;
    }


    public void setPos(int pos) {
        this.pos = pos;
    }

    public void initialize(List<Song> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    public void start(int pos) {
        this.pos = pos;
        observableSong.setState(songList.get(pos));
        Uri uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songList.get(pos).getId());
        mediaPlayer = MediaPlayer.create(context, uri);
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public Boolean isStarted() {
        return mediaPlayer.isPlaying();
    }
}
