package com.projects.caim03.musicplayer.view;


import android.widget.SeekBar;

import com.projects.caim03.musicplayer.model.Song;

import java.util.List;

public class Mediator {

    private static RecyclerAdapter recyclerAdapter;
    private static boolean fabState = false;
    private static List<Song> list;
    private static SeekBar seekBar;


    public static RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    public static boolean getFabState() { return fabState; }

    public static List<Song> getList() { return list; }

    public static SeekBar getSeekBar() { return seekBar; }

    public static void setRecyclerAdapter(RecyclerAdapter recyclerAdapter) {
        Mediator.recyclerAdapter = recyclerAdapter;
    }

    public static void setFabState(boolean fabState) {
        Mediator.fabState = fabState;
    }

    public static void setList(List<Song> list) { Mediator.list = list; }

    public static void setSeekBar(SeekBar seekBar) { Mediator.seekBar = seekBar; }

}
