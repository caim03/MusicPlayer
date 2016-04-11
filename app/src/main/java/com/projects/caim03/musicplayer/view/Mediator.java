package com.projects.caim03.musicplayer.view;


import android.support.design.widget.FloatingActionButton;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;

public class Mediator {
    private static FloatingActionButton fab;
    private static FABToolbarLayout toolbar;

    public static FloatingActionButton getFab() {
        return fab;
    }

    public static FABToolbarLayout getToolbar() {
        return toolbar;
    }

    public static void setFab(FloatingActionButton button) {
        Mediator.fab = button;
    }

    public static void setToolbar(FABToolbarLayout bar) {
        Mediator.toolbar = bar;
    }

}
