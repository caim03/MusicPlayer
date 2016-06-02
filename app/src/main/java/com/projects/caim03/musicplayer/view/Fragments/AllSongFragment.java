package com.projects.caim03.musicplayer.view.Fragments;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.projects.caim03.musicplayer.R;
import com.projects.caim03.musicplayer.controller.RetrieveSongController;
import com.projects.caim03.musicplayer.view.Mediator;
import com.projects.caim03.musicplayer.view.RecyclerAdapter;


public class AllSongFragment extends Fragment {
    private FloatingActionButton fab;
    private FABToolbarLayout toolbar;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_song_frag, container, false);

        fab = Mediator.getFab();
        toolbar = Mediator.getToolbar();
        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        RetrieveSongController songCtrl = RetrieveSongController.getInstance();
        songCtrl.setContext(view.getContext());

        recyclerAdapter = new RecyclerAdapter(songCtrl.getList(), view.getContext());
        Mediator.setRecyclerAdapter(recyclerAdapter);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Mediator.setFabState(false);
                toolbar.hide();
            }
        });
    }
}
