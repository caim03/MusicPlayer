package com.projects.caim03.musicplayer.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.projects.caim03.musicplayer.R;
import com.projects.caim03.musicplayer.controller.MiniFabController;
import com.projects.caim03.musicplayer.controller.MusicController;
import com.projects.caim03.musicplayer.controller.TypeFaceService;
import com.projects.caim03.musicplayer.controller.UpdateSeekBar;
import com.projects.caim03.musicplayer.model.ObservableSong;

import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Observer, SeekBar.OnSeekBarChangeListener, Runnable {

    private DrawerLayout drawer;
    private Toolbar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private FloatingActionButton fab;
    private FloatingActionButton miniFabPrev;
    private FloatingActionButton miniFabPlay;
    private FloatingActionButton miniFabNext;
    private RelativeLayout toolbar;
    private TextView title, artist;
    private ObservableSong observableSong;
    private CircularImageView album;
    private Animation fab_open, fab_close;
    private int seek;
    private MiniFabController miniFabController;
    private MusicController musicController;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Songs"));
        tabLayout.addTab(tabLayout.newTab().setText("Playlist"));
        tabLayout.addTab(tabLayout.newTab().setText("Favourites"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar = (RelativeLayout) findViewById(R.id.fabtoolbar_toolbar);
        title = (TextView) findViewById(R.id.title_footer);
        artist = (TextView) findViewById(R.id.artist_footer);

        toolbar.setClickable(true);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!miniFabController.getIsFabOpen()) {
                    miniFabController.showMiniFab();
                    miniFabController.setIsFabOpen(true);
                }
                else {
                    miniFabController.hideMiniFab();
                    miniFabController.setIsFabOpen(false);
                }
            }
        });

        miniFabPrev = (FloatingActionButton) findViewById(R.id.prevFab);
        miniFabPlay = (FloatingActionButton) findViewById(R.id.playFab);
        miniFabNext = (FloatingActionButton) findViewById(R.id.nextFab);
        album = (CircularImageView) findViewById(R.id.view);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        Mediator.setSeekBar(seekBar);

        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);

        miniFabController = MiniFabController.getInstance();
        miniFabController.initialize(miniFabPrev, miniFabPlay, miniFabNext, fab_close, fab_open);

        miniFabPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicController musicController = MusicController.getInstance();
                int pos = musicController.getPos();
                if (pos != 0) {
                    pos = pos - 1;
                    ObservableSong observableSong = ObservableSong.getInstance();
                    observableSong.setState(Mediator.getList().get(pos));
                    musicController.setPos(pos);
                    if (musicController.isStarted()) {
                        musicController.pause();
                    }
                    musicController.start(pos);
                    new Thread(new UpdateSeekBar(seekBar)).start();
                }
            }
        });

        miniFabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicController musicController = MusicController.getInstance();
                int pos = musicController.getPos();
                if (pos != Mediator.getList().size() - 1) {
                    pos = pos + 1;
                    ObservableSong observableSong = ObservableSong.getInstance();
                    observableSong.setState(Mediator.getList().get(pos));
                    musicController.setPos(pos);
                    if (musicController.isStarted()) {
                        musicController.pause();
                    }
                    musicController.start(pos);
                    new Thread(new UpdateSeekBar(seekBar)).start();
                }
            }
        });

        miniFabPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicController musicController = MusicController.getInstance();
                if (musicController.isStarted()) {
                    seek = musicController.getSeek();
                    musicController.pause();
                    miniFabPlay.setImageResource(R.drawable.ic_play_arrow_white_36dp);
                }
                else {
                    musicController.setSeek(seek);
                    musicController.startSeek(musicController.getPos());
                    miniFabPlay.setImageResource(R.drawable.ic_pause_white_36dp);
                }
            }
        });

        title.setTypeface(TypeFaceService.getRobotoMedium(this));
        artist.setTypeface(TypeFaceService.getRobotoRegular(this));

        observableSong = ObservableSong.getInstance();
        observableSong.attach(this);

        musicController = MusicController.getInstance();
    }


    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            return true;
        }

        if (id == android.R.id.home) {
            if (!drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.openDrawer(GravityCompat.START);
            }
            else {
                drawer.closeDrawer(GravityCompat.START);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void update(Observable observable, Object data) {

        title.setText(observableSong.getState().getTitle());
        artist.setText(observableSong.getState().getArtist());

        miniFabPlay.setImageResource(R.drawable.ic_pause_white_36dp);
        if (!miniFabController.getIsFabOpen()) {
            miniFabController.showMiniFab();
            miniFabController.setIsFabOpen(true);
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (musicController.isStarted()) {
            if (fromUser) {
                musicController.setSeek(progress);
                seekBar.setProgress(progress);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void run() {
        int seek = 0;
        int duration = musicController.getDuration();
        seekBar.setProgress(100);
        while (seek <= duration) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seek = musicController.getSeek();
            int progress = (seek / duration) * 100;
            seekBar.setProgress(progress);
            System.out.println("RUNNABLE");
        }
    }
}
