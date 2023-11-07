package com.example.radio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import com.example.radio.adapter.PlaylistAdapter;
import com.example.radio.viewmodel.PlaylistViewModel;


public class PlaylistsActivity extends AppCompatActivity {
    private PlaylistAdapter playlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_playlist);

        PlaylistViewModel playlistViewModel = new ViewModelProvider(this).get(com.example.radio.viewmodel.PlaylistViewModel.class);
        RecyclerView playlistRecycler = findViewById(R.id.container);

        playlistRecycler.setLayoutManager(new LinearLayoutManager(this));
        playlistRecycler.setHasFixedSize(true);
        // get blog through viewModel
        playlistViewModel.getLivePlaylistData().observe(this, playlistist -> {
            playlistAdapter = new PlaylistAdapter(playlistist);
            playlistRecycler.setAdapter(playlistAdapter);
            playlistAdapter.notifyDataSetChanged();
        });

    }

}