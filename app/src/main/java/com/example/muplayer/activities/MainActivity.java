package com.example.muplayer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.muplayer.R;
import com.example.muplayer.fragments.AudioPlayerFragment;
import com.example.muplayer.fragments.HomeFragment;
import com.example.muplayer.fragments.LibraryFragment;
import com.example.muplayer.fragments.SearchFragment;
import com.example.muplayer.model.AudioItem;
import com.example.muplayer.utils.AudioDataUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener, HomeFragment.HomeFragmentListener{

    private static final String TAG = "MainActivity";
    List<AudioItem> audioItemList = new ArrayList<>();
    private FrameLayout fragContainer, fragAudioPlayer;
    private BottomNavigationView bottomNavBar;
    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;
    private SearchFragment searchFragment;
    private AudioPlayerFragment audioPlayerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioItemList = AudioDataUtil.getInstance().getAudioItemList();
        fragContainer = findViewById(R.id.fragContainer);
        bottomNavBar = findViewById(R.id.bottomNavBar);
        fragAudioPlayer = findViewById(R.id.frag_audio_player);

        homeFragment = new HomeFragment(this::openMusicPlayerFragment);
        searchFragment = new SearchFragment();
        libraryFragment = new LibraryFragment();

        bottomNavBar.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        bottomNavBar.setSelectedItemId(R.id.home);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, homeFragment).commit();
                return true;
            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, searchFragment).commit();
                return true;
            case R.id.library:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, libraryFragment).commit();
                return true;
        }
        return false;
    }

    @Override
    public void openMusicPlayerFragment(int pos) {
        audioPlayerFragment = new AudioPlayerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_audio_player, audioPlayerFragment, "AudioPlayerFragment").addToBackStack(null).commit();
        bottomNavBar.setVisibility(View.GONE);
    }

    public void showBottomNavigationBar() {
        bottomNavBar.setVisibility(View.VISIBLE);
    }
}