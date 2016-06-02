package com.projects.caim03.musicplayer.controller;


import com.projects.caim03.musicplayer.model.ObservableSong;
import com.projects.caim03.musicplayer.view.Mediator;

import java.util.Random;

public class FabController {
    public static void startRandom(ObservableSong observableSong) {
        Mediator.setFabState(true);

        Random random = new Random();
        int pos = random.nextInt(Mediator.getRecyclerAdapter().getItemCount());

        observableSong.setState(Mediator.getRecyclerAdapter().getList().get(pos));

        MusicController musicController = MusicController.getInstance();

        if (musicController.isStarted()) MusicController.getInstance().pause();
        MusicController.getInstance().start(pos);
        Mediator.getToolbar().show();
    }

}
