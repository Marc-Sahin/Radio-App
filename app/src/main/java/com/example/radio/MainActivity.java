package com.example.radio;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import com.arges.sepan.argmusicplayer.Models.ArgAudio;
import com.arges.sepan.argmusicplayer.Models.ArgAudioList;
import com.arges.sepan.argmusicplayer.Models.ArgNotificationOptions;
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerLargeView;

import com.example.radio.model.Song;
import com.example.radio.viewmodel.SongViewModel;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


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

    // Textview für Titel u. Interp. deklarieren
        playerView.setPlayer(player);
        TextView texttitle=findViewById(R.id.title);
        TextView textInterpret =findViewById(R.id.interpret);
        // Initialize the ViewModel
        SongViewModel songViewModel = new ViewModelProvider(this).get(SongViewModel.class);

        //setzt aktuellen Song
        songViewModel.getSongListMutableLiveData().observe(this, song -> {
            if (song != null) {
                for (int i = 0; i < song.size(); i++) {
                    //geht durch Songliste
                    Song currentSong = song.get(i);
                    String url = currentSong.getUrl();
                    //setzt Metadaten für jeden Song
                    MediaMetadata mediaMetadata = new MediaMetadata.Builder()
                            .setTitle(currentSong.getTitle())
                            .setArtist("\n"+currentSong.getInterpret())
                            .build();
                    MediaItem mediaItem = new MediaItem.Builder()
                            .setUri(url)
                            .setTag(mediaMetadata)
                            .setMediaMetadata(mediaMetadata)
                            .build();
                    // fülle Playlist
                    player.addMediaItem(mediaItem);
                }
              // Start the playback.
                player.prepare();
                player.play();
                // pausiert anfangs
                player.pause();
            }
        });

        final int[] counterValue = {0};
        player.addListener(
                new Player.Listener() {
                    CountDownTimer counterTimer;
                    @Override
                    public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {

                        if (!playWhenReady) {
                            //Wenn player pausiert, starte counter
                            counterTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
                                @Override @OptIn(markerClass = UnstableApi.class)
                                public void onTick(long millisUntilFinished) {
                                    counterValue[0] += 1000;
                                    Log.i("TAG", "h"+counterValue[0]);
                                  if (counterValue[0]>=player.getDuration()-player.getCurrentPosition()){
                                      //counter erreicht Restzeit des aktuellen Songs
                                      Log.i("TAG", "end");
                                      //spielt nächsten Song
                                      player.seekToNext();
                                      //counter wird auf null gesetzt
                                      counterValue[0]=0;
                                  }
                                }
                                @Override
                                public void onFinish() {
                                    // Counter finished (not applicable in this case)
                                }
                            }.start();
                        }
                        else {//player resumes
                            //geht zur Stelle des counters im Song
                            player.seekTo(counterValue[0]);
                            if (counterTimer!=null){
                                counterTimer.cancel();
                            }

                        }
                    }
                    // ändert Anzeige aktuellen Song
                    @Override
                    public void onMediaItemTransition(
                            @Nullable MediaItem mediaItem, @Player.MediaItemTransitionReason int reason) {
                        @Nullable MediaMetadata metadata = null;
                        if (mediaItem != null && mediaItem.localConfiguration != null) {
                            metadata = (MediaMetadata) mediaItem.localConfiguration.tag;
                        }        TextView textView=findViewById(R.id.title);
                        assert metadata != null;
                        texttitle.setText(metadata.title);
                        textInterpret.setText(metadata.artist);
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

