package com.example.radio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.SimpleExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.arges.sepan.argmusicplayer.Models.ArgAudio;
import com.arges.sepan.argmusicplayer.Models.ArgAudioList;
import com.arges.sepan.argmusicplayer.Models.ArgNotificationOptions;
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerLargeView;
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerSmallView;
import com.example.radio.adapter.SongAdapter;
import com.example.radio.model.Song;
import com.example.radio.viewmodel.SongViewModel;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SongViewModel songViewModel;
    private ArgPlayerSmallView argMusicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_song_item);

        ArgPlayerLargeView argMusicPlayer = (ArgPlayerLargeView) findViewById(R.id.argmusicplayer);
        argMusicPlayer.enableNotification(new ArgNotificationOptions(this).setProgressEnabled(true));
        argMusicPlayer.disableNextPrevButtons();
        argMusicPlayer.disableProgress();

// Initialize the ViewModel
        SongViewModel songViewModel = new ViewModelProvider(this).get(SongViewModel.class);

        // Initialize your ArgPlayerSmallView

        // Observe changes to the current song

        songViewModel.getLiveSongData().observe(this, song -> {
            if (song != null) {
                ArgAudioList playlist = new ArgAudioList(true);
                for (int i = 0; i < song.size(); i++) {
                    Song currentSong=song.get(i);
                    String url = currentSong.getUrl();
                    ArgAudio audio = ArgAudio.createFromURL(currentSong.getInterpret(), currentSong.getTitle(), url);
                    //Define audio2, audio3, audio4 ......
                    playlist.add(audio);

                }
                argMusicPlayer.playPlaylist(playlist);

            }


            });
    }
}