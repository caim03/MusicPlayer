package com.projects.caim03.musicplayer.model;


import android.media.Image;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String mime;
    private Image albumImage;

    public Song(int id, String title, String author, String mime,Image albumImage) {
        this.id = id;
        this.title = title;
        this.artist = author;
        this.mime = mime;
        this.albumImage = albumImage;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getMime() {
        return mime;
    }

    public Image getAlbumImage() {
        return albumImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setMime(String mime) { this.mime = mime; }

    public void setAlbumImage(Image albumImage) {
        this.albumImage = albumImage;
    }
}
