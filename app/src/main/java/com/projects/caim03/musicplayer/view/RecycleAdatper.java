package com.projects.caim03.musicplayer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.caim03.musicplayer.R;
import com.projects.caim03.musicplayer.model.Song;

import java.util.List;


public class RecycleAdatper extends RecyclerView.Adapter<RecycleAdatper.SongViewHolder> {
    private Context context;
    private List<Song> list;


    public static class SongViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText, authorText;
        private ImageView thumb;

        public SongViewHolder(View v) {
            super(v);

            titleText = (TextView) v.findViewById(R.id.title_text);
            authorText = (TextView) v.findViewById(R.id.author_text);
            thumb = (ImageView) v.findViewById(R.id.thumb);
        }
    }

    public RecycleAdatper(List<Song> songList) {
        list = songList;
    }

    @Override
    public RecycleAdatper.SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);

        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleAdatper.SongViewHolder holder, int position) {
        Song song = list.get(position);

        String title = song.getTitle();
        System.out.println(title);
        if (title == null) {
            title = "unknown";
        }
        holder.titleText.setText(title);

        String author = song.getArtist();
        if (author == null) {
            author = "unknown";
        }
        holder.authorText.setText(author);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
