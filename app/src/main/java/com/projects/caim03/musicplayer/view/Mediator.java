package com.projects.caim03.musicplayer.view;


import android.support.design.widget.FloatingActionButton;
import android.view.animation.Animation;
import android.widget.SeekBar;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.projects.caim03.musicplayer.model.Song;

import java.util.List;

public class Mediator {
    private static FloatingActionButton fab;

    private static FABToolbarLayout toolbar;
    private static RecyclerAdapter recyclerAdapter;
    private static boolean fabState = false;
    private static List<Song> list;
    private static SeekBar seekBar;

    public static FloatingActionButton getFab() {
        return fab;
    }

    public static FABToolbarLayout getToolbar() {
        return toolbar;
    }

    public static RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    public static boolean getFabState() { return fabState; }

    public static List<Song> getList() { return list; }

    public static SeekBar getSeekBar() { return seekBar; }

    public static void setFab(FloatingActionButton button) {
        Mediator.fab = button;
    }

    public static void setToolbar(FABToolbarLayout bar) {
        Mediator.toolbar = bar;
    }

    public static void setRecyclerAdapter(RecyclerAdapter recyclerAdapter) {
        Mediator.recyclerAdapter = recyclerAdapter;
    }

    public static void setFabState(boolean fabState) {
        Mediator.fabState = fabState;
    }

    public static void setList(List<Song> list) { Mediator.list = list; }

    public static void setSeekBar(SeekBar seekBar) { Mediator.seekBar = seekBar; }

}
