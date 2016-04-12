package com.projects.caim03.musicplayer.controller;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.projects.caim03.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RetrieveSongController {

    private Context context;
    private List<Song> list;
    private static RetrieveSongController instance = null;

    // PATTERN SINGLETON
    protected RetrieveSongController() {

    }

    public static RetrieveSongController getInstance() {
        if (instance == null) {
            instance = new RetrieveSongController();
        }
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Song> getList() {
        retrieveList();
        return list;
    }

    private void retrieveList() {

        list = new ArrayList<>();

        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int extColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.MIME_TYPE);
            int idColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media._ID);
            int durColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);
            int albumColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.ALBUM_ID);
            //add songs to list
            do {
                String thisTitle = musicCursor.getString(titleColumn);
                if (thisTitle.contains("AUD-")) break;

                String thisArtist = musicCursor.getString(artistColumn);
                if ("<unknown>".equals(thisArtist)) thisArtist = "Unknown";

                String thisMime = musicCursor.getString(extColumn);
                int thisId = musicCursor.getInt(idColumn);
                String thisDuration = musicCursor.getString(durColumn);
                long thisAlbum = musicCursor.getLong(albumColumn);

                if (musicCursor.getString(extColumn).equals("audio/mpeg")) {
                    list.add(new Song(thisId, thisTitle, thisArtist, thisMime, thisAlbum, thisDuration));
                }
            }
            while (musicCursor.moveToNext());
        }
        Collections.sort(list, new Comparator<Song>() {
            @Override
            public int compare(Song lhs, Song rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());
            }
        });
    }
}
