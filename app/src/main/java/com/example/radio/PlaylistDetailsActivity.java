package com.example.radio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.adapter.PlaylistDetailsAdapter;
import com.example.radio.viewmodel.PlaylistDetailsViewModel;
public class PlaylistDetailsActivity extends AppCompatActivity {
private PlaylistDetailsAdapter playlistDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_playlist_details);
        PlaylistDetailsViewModel playlistDetailsViewModel = new ViewModelProvider(this).get(PlaylistDetailsViewModel.class);

        RecyclerView playlistDetailsRecycler = findViewById(R.id.container2);
        playlistDetailsRecycler.setLayoutManager(new LinearLayoutManager(this));
        playlistDetailsRecycler.setHasFixedSize(true);
        Intent intent = getIntent();
        String itemId = intent.getStringExtra("playlistid");

        // get blog through viewModel
        playlistDetailsViewModel.getPlaylistDetails(itemId).observe(this, playlistDetailsList -> {
            playlistDetailsAdapter= new PlaylistDetailsAdapter(playlistDetailsList);
            playlistDetailsRecycler.setAdapter(playlistDetailsAdapter);
            playlistDetailsAdapter.notifyDataSetChanged();
        });

    }
}