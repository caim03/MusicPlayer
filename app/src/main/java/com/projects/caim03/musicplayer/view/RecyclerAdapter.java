package com.projects.caim03.musicplayer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.projects.caim03.musicplayer.R;
import com.projects.caim03.musicplayer.controller.MusicController;
import com.projects.caim03.musicplayer.controller.TypeFaceService;
import com.projects.caim03.musicplayer.controller.UpdateSeekBar;
import com.projects.caim03.musicplayer.model.ObservableSong;
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
        private TextView titleText, authorText, durationText;

        public SongViewHolder(View v, final List<Song> list) {
            super(v);
            titleText = (TextView) v.findViewById(R.id.title_text);
            authorText = (TextView) v.findViewById(R.id.author_text);
            durationText = (TextView) v.findViewById(R.id.duration_text);

            titleText.setTypeface(TypeFaceService.getRobotoMedium(v.getContext()));
            authorText.setTypeface(TypeFaceService.getRobotoRegular(v.getContext()));
            durationText.setTypeface(TypeFaceService.getRobotoRegular(v.getContext()));

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Mediator.setFabState(true);
                    int pos = getAdapterPosition();
                    MusicController musicController = MusicController.getInstance();
                    ObservableSong observableSong = ObservableSong.getInstance();
                    observableSong.setState(list.get(pos));

                    if (!musicController.isStarted()) {
                        musicController.start(pos);
                        musicController.setPos(pos);
                        new Thread(new UpdateSeekBar(Mediator.getSeekBar())).start();
                    }

                    else {
                        if (pos == musicController.getPos()) {

                        }
                        else{
                            musicController.pause();
                            musicController.start(pos);
                            musicController.setPos(pos);
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

        return new SongViewHolder(itemView, list);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.SongViewHolder holder, int position) {
        Song song = list.get(position);

        String title = song.getTitle();
        String author = song.getArtist();
        String duration = song.getDuration();

        int seconds = (Integer.parseInt(duration) / 1000) % 60;
        int minutes = (Integer.parseInt(duration) / (1000*60)) % 60;

        String sec = String.valueOf(seconds);
        if (seconds < 10) {
            sec = "0" + sec;
        }

        holder.titleText.setText(title);
        holder.authorText.setText(author);
        holder.durationText.setText(String.valueOf(minutes) + ":" + sec);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
