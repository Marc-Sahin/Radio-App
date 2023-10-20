package com.example.radio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.SimpleExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.example.radio.adapter.SongAdapter;
import com.example.radio.viewmodel.SongViewModel;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SongViewModel songViewModel;
    private RecyclerView songRecycler;
    private SongAdapter songAdapter;
    ProgressDialog progressDialog;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //define viewmodel
        songViewModel = new ViewModelProvider(this).get(SongViewModel.class);
        progressDialog =new ProgressDialog(this);
        progressDialog.setTitle("Loading ...");
        progressDialog.show();
        ExoPlayer player = new ExoPlayer.Builder(this).build();
        ArrayList<String> videoUris=new ArrayList<>();
        videoUris.add("C:/Users/cccre/Downloads");
        List<MediaItem> mediaItems = new ArrayList<>();
        for (int i = 0; i < videoUris.size(); i++) {
            mediaItems.add(MediaItem.fromUri(videoUris.get(i)));
        }

// Set the playlist, prepare and play.
        player.setMediaItems(mediaItems);

        player.prepare();
        player.play();

        songRecycler = findViewById(R.id.container);
        songRecycler.setLayoutManager(new LinearLayoutManager(this));
        songRecycler.setHasFixedSize(true);
        // get song through viewModel
        songViewModel.getLiveSongData().observe(this, songList -> {
            progressDialog.dismiss();
            songAdapter = new SongAdapter(songList);
            songRecycler.setAdapter(songAdapter);
            songAdapter.notifyDataSetChanged();
        });
    }

}