package com.projects.caim03.musicplayer.controller;

import android.support.design.widget.FloatingActionButton;
import android.view.animation.Animation;

public class MiniFabController {
    private FloatingActionButton fabPrev, fabPlay, fabNext;
    private Animation fabClose, fabOpen;
    private boolean isFabOpen = false;
    private static MiniFabController instance = null;

    public static MiniFabController getInstance() {
        if (instance == null) {
            instance = new MiniFabController();
        }
        return instance;
    }

    public void initialize(FloatingActionButton prev, FloatingActionButton play, FloatingActionButton next, Animation close, Animation open) {
        fabPrev = prev;
        fabPlay = play;
        fabNext = next;

        fabClose = close;
        fabOpen = open;
    }

    public void showMiniFab() {
        fabPrev.startAnimation(fabOpen);
        fabPlay.startAnimation(fabOpen);
        fabNext.startAnimation(fabOpen);
    }

    public void hideMiniFab() {
        fabPrev.startAnimation(fabClose);
        fabPlay.startAnimation(fabClose);
        fabNext.startAnimation(fabClose);
    }

    public boolean getIsFabOpen() {
        return isFabOpen;
    }

    public void setIsFabOpen(boolean isFabOpen) {
        this.isFabOpen = isFabOpen;
    }
}
