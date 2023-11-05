package com.example.radio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import com.arges.sepan.argmusicplayer.Models.ArgAudio;
import com.arges.sepan.argmusicplayer.Models.ArgAudioList;
import com.arges.sepan.argmusicplayer.Models.ArgNotificationOptions;
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerLargeView;

import com.example.radio.model.Song;
import com.example.radio.viewmodel.SongViewModel;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private ArgPlayerLargeView argMusicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        setMod(getDayTime());


        Button switchToSecondActivity = findViewById(R.id.playlists_btn);
        switchToSecondActivity.setOnClickListener(view -> switchActivity());
      // erstelle Exoplayer Instanz
        ExoPlayer player = new ExoPlayer.Builder(this).build();
      // playlist wiederholen
        player.setRepeatMode(Player.REPEAT_MODE_ALL);     
      
      // Bind the player to the view.
        PlayerView playerView = findViewById(R.id.player);
    // Textview für Titel deklarieren
        TextView textView=findViewById(R.id.title);
        playerView.setPlayer(player);

TextView textView1=findViewById(R.id.interpret);
// Initialize the ViewModel
        SongViewModel songViewModel = new ViewModelProvider(this).get(SongViewModel.class);

        // Initialize your ArgPlayerSmallView

        // Observe changes to the current song

        songViewModel.getSongListMutableLiveData().observe(this, song -> {
            if (song != null) {
                for (int i = 0; i < song.size(); i++) {
                    Song currentSong = song.get(i);
                    String url = currentSong.getUrl();
                    MediaMetadata mediaMetadata = new MediaMetadata.Builder()
                            .setTitle(currentSong.getTitle())
                            .setArtist("\n"+currentSong.getInterpret())
                            .build();
                    MediaItem mediaItem = new MediaItem.Builder()
                            .setUri(url)
                            .setMediaMetadata(mediaMetadata)
                            .build();
                    textView.setText(mediaMetadata.title);
                    textView1.setText(mediaMetadata.artist);
                    player.setMediaItem(mediaItem);
                }

                player.prepare();
// Start the playback.
                player.play();

            }


        });

    }

    
    private void setMod(int hour){
        ImageView moderatorImageView = findViewById(R.id.moderatorImageView);
        TextView moderatorNameTextView = findViewById(R.id.moderatorNameTextView);
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
        moderatorNameTextView.setText(String.format("\n \n on Air: \n \n%s", moderatorName));

    }
      
      //Wechsel Activity
    private void switchActivity() {
        Intent switchActivityIntent = new Intent(this, PlaylistsActivity.class);
        startActivity(switchActivityIntent);
    }
    private int getDayTime() {
        // Hol aktuelle Tageszeit
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}

