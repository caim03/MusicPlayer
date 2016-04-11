package com.projects.caim03.musicplayer.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.projects.caim03.musicplayer.view.Fragments.AllSongFragment;
import com.projects.caim03.musicplayer.view.Fragments.FavouriteFragments;
import com.projects.caim03.musicplayer.view.Fragments.PlaylistFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AllSongFragment tab1 = new AllSongFragment();
                return tab1;
            case 1:
                PlaylistFragment tab2 = new PlaylistFragment();
                return tab2;
            case 2:
                FavouriteFragments tab3 = new FavouriteFragments();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}