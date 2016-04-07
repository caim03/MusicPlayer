package com.projects.caim03.musicplayer.model;


import android.media.Image;

public class Song {
    private String title;
    private String artist;
    private Image albumImage;

    public Song(String title, String author, Image albumImage) {
        this.title = title;
        this.artist = author;
        this.albumImage = albumImage;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public Image getAlbumImage() {
        return albumImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbumImage(Image albumImage) {
        this.albumImage = albumImage;
    }
}
