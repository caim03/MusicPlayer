package com.projects.caim03.musicplayer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class ObservableSong extends Observable {
    private List<Observer> observers = new ArrayList<>();
    private Song song;
    private static ObservableSong instance = null;

    private ObservableSong() {}

    public static ObservableSong getInstance() {
        if (instance == null) {
            instance = new ObservableSong();
        }
        return instance;
    }

    public void attach(Observer o) {
        this.observers.add(o);
    }

    public void detach(Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this, null);
        }
    }

    public Song getState() {
        return song;
    }

    public void setState(Song song) {
        this.song = song;
        notifyObservers();
    }
}
