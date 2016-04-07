package com.projects.caim03.musicplayer.controller;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.projects.caim03.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.List;

public class RetrieveSongController {
    private Context context;
    private List<Song> list = new ArrayList<>();

    public RetrieveSongController(Context context) {
        this.context = context;
    }

    public List<Song> getList() {
        retrieveList();
        return list;
    }

    private void retrieveList() {

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
            //add songs to list
            do {
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                if (musicCursor.getString(extColumn).equals("audio/mpeg")) {
                    list.add(new Song(thisTitle, thisArtist, null));
                }
            }
            while (musicCursor.moveToNext());
        }
    }


}
