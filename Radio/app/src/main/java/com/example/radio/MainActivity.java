package com.example.radio;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.radio.adapter.SongAdapter;
import com.example.radio.viewmodel.SongViewModel;
import com.google.firebase.FirebaseApp;

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