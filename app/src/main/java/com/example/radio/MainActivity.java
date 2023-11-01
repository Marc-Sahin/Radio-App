package com.example.radio;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.arges.sepan.argmusicplayer.Models.ArgAudio;
import com.arges.sepan.argmusicplayer.Models.ArgAudioList;
import com.arges.sepan.argmusicplayer.Models.ArgNotificationOptions;
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerLargeView;
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerSmallView;
import com.example.radio.model.Song;
import com.example.radio.viewmodel.SongViewModel;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private SongViewModel songViewModel;
    private ArgPlayerSmallView argMusicPlayer;

    private ImageView moderatorImageView;
    private TextView moderatorNameTextView;

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
                    Song currentSong = song.get(i);
                    String url = currentSong.getUrl();
                    ArgAudio audio = ArgAudio.createFromURL(currentSong.getInterpret(), currentSong.getTitle(), url);
                    //Define audio2, audio3, audio4 ......
                    playlist.add(audio);

                }
                argMusicPlayer.playPlaylist(playlist);

            }


        });

        moderatorImageView = findViewById(R.id.moderatorImageView);
        moderatorNameTextView = findViewById(R.id.moderatorNameTextView);

        // Holen der aktuellen Tageszeit
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // Bestimmen des passenden Moderators basierend auf der Tageszeit
        String moderatorName;
        if (hour >= 5 && hour < 12) {
            moderatorImageView.setImageResource(R.drawable.marc);
            moderatorName = "Marc";
        } else if (hour >= 12 && hour < 18) {
            moderatorImageView.setImageResource(R.drawable.glademir);
            moderatorName = "Glademir";
        } else {
            moderatorImageView.setImageResource(R.drawable.sandra);
            moderatorName = "Sandra";
        }
        // Den Namen des Moderators in das TextView einfügen
        moderatorNameTextView.setText("\n \n on Air: \n \n" +  moderatorName);

    }

}

