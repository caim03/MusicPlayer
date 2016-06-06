package com.projects.caim03.musicplayer.controller;


import android.os.Handler;
import android.widget.SeekBar;

public class UpdateSeekBar implements Runnable {
    private MusicController musicController;
    private SeekBar seekBar;

    public UpdateSeekBar(SeekBar seekBar) {
        this.musicController = MusicController.getInstance();
        this.seekBar = seekBar;
    }

    @Override
    public void run() {
        int seek = musicController.getSeek();
        int duration = musicController.getDuration();
        seekBar.setProgress(0);
        seekBar.setMax(duration);
        while (seek <= duration) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seek = musicController.getSeek();
            seekBar.setProgress(seek);
        }
    }
}
