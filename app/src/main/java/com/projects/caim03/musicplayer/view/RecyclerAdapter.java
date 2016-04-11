package com.projects.caim03.musicplayer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.projects.caim03.musicplayer.R;
import com.projects.caim03.musicplayer.controller.MusicController;
import com.projects.caim03.musicplayer.controller.TypeFaceService;
import com.projects.caim03.musicplayer.model.Song;

import java.util.List;

import es.claucookie.miniequalizerlibrary.EqualizerView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.SongViewHolder> {
    private Context context;
    private List<Song> list;
    private MusicController musicController;

    public List<Song> getList() {
        return list;
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText, authorText;
        private EqualizerView equalizer;

        public SongViewHolder(View v) {
            super(v);

            titleText = (TextView) v.findViewById(R.id.title_text);
            authorText = (TextView) v.findViewById(R.id.author_text);
            equalizer = (EqualizerView) v.findViewById(R.id.equalizer_view);

            titleText.setTypeface(TypeFaceService.getRobotoMedium(v.getContext()));
            authorText.setTypeface(TypeFaceService.getRobotoRegular(v.getContext()));

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    MusicController musicController = MusicController.getInstance();

                    if (!musicController.isStarted()) {
                        musicController.setOldView(v);
                        equalizer.animateBars();
                        equalizer.setVisibility(View.VISIBLE);
                        musicController.start(pos);
                    }

                    else {
                        if (pos == musicController.getPos()) {
                            equalizer.stopBars();
                            equalizer.setVisibility(View.INVISIBLE);
                            musicController.pause();
                        }
                        else {
                            View old = musicController.getOldViewV();
                            EqualizerView oldEq = (EqualizerView) old.findViewById(R.id.equalizer_view);

                            oldEq.stopBars();
                            oldEq.setVisibility(View.INVISIBLE);
                            musicController.pause();

                            musicController.setOldView(v);
                            equalizer.animateBars();
                            equalizer.setVisibility(View.VISIBLE);
                            musicController.start(pos);
                        }
                    }
                }
            });
        }
    }

    public RecyclerAdapter(List<Song> songList, Context context) {
        this.list = songList;
        this.context = context;
        musicController = MusicController.getInstance();
        musicController.initialize(list, context);
    }

    @Override
    public RecyclerAdapter.SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);

        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.SongViewHolder holder, int position) {
        Song song = list.get(position);

        String title = song.getTitle();
        System.out.println(title);
        if (title == null) {
            title = "Unknown";
        }
        holder.titleText.setText(title);

        String author = song.getArtist();
        if (author == null) {
            author = "Unknown";
        }
        holder.authorText.setText(author);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
