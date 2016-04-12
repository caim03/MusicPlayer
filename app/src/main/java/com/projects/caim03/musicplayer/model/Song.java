package com.projects.caim03.musicplayer.model;


public class Song {
    private int id;
    private String title;
    private String artist;
    private String mime;
    private long albumId;
    private String duration;

    public Song(int id, String title, String author, String mime, long albumImage, String duration) {
        this.id = id;
        this.title = title;
        this.artist = author;
        this.mime = mime;
        this.albumId = albumImage;
        this.duration = duration;
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

    public long getAlbumImage() {
        return albumId;
    }

    public String getDuration() {
        return duration;
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

    public void setAlbumImage(long albumId) {
        this.albumId = albumId;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
