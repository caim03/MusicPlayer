package com.projects.caim03.musicplayer.view;


import android.support.design.widget.FloatingActionButton;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;

public class Mediator {
    private static FloatingActionButton fab;
    private static FABToolbarLayout toolbar;
    private static RecyclerAdapter recyclerAdapter;
    private static boolean fabState = false;

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
}
