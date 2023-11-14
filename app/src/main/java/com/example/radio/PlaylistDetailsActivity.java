package com.example.radio;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.adapter.PlaylistDetailsAdapter;
import com.example.radio.adapter.RatingAdapter;
import com.example.radio.model.Rating;
import com.example.radio.viewmodel.PlaylistDetailsViewModel;
import com.example.radio.viewmodel.RatingViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

import de.cketti.mailto.EmailIntentBuilder;

public class PlaylistDetailsActivity extends AppCompatActivity {
    private PlaylistDetailsAdapter playlistDetailsAdapter;
    private RatingAdapter ratingAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_playlist_details);
        PlaylistDetailsViewModel playlistDetailsViewModel = new ViewModelProvider(this).get(PlaylistDetailsViewModel.class);
        RecyclerView playlistDetailsRecycler = findViewById(R.id.container2);
        playlistDetailsRecycler.setLayoutManager(new LinearLayoutManager(this));
        playlistDetailsRecycler.setHasFixedSize(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        RatingViewModel ratingViewModel = new ViewModelProvider(this).get(RatingViewModel.class);
        RecyclerView ratingrecycler = findViewById(R.id.containerRating);
        ratingrecycler.setLayoutManager(new LinearLayoutManager(this));
        ratingrecycler.setHasFixedSize(true);


        Intent intent = getIntent();
        String itemId = intent.getStringExtra("playlistid");
        // get songs through viewModel
        playlistDetailsViewModel.getPlaylistDetails(itemId).observe(this, playlistDetailsList -> {
            if (playlistDetailsList != null && !playlistDetailsList.isEmpty()) {
                playlistDetailsAdapter = new PlaylistDetailsAdapter(playlistDetailsList);
                playlistDetailsRecycler.setAdapter(playlistDetailsAdapter);
                playlistDetailsAdapter.notifyDataSetChanged();
            }

        });


        ratingViewModel.getRatingListMutableLiveData(itemId).observe(this, ratingList -> {
            if (ratingList != null && !ratingList.isEmpty()) {
                ratingAdapter = new RatingAdapter(ratingList);
                ratingrecycler.setAdapter(ratingAdapter);
                ratingAdapter.notifyDataSetChanged();
            }
        });
        Slider slider = findViewById(R.id.sterneslide);
        TextInputEditText textInputEditText = findViewById(R.id.kommentarText);
        TextInputEditText nameText = findViewById(R.id.name);
        // listen für das Auslösen von Bewertung abgeben.

        MaterialButton submitRating = (MaterialButton) findViewById(R.id.submitRating);
        submitRating.setOnClickListener(new OnClickListener() {


            public void onClick(View v) {

                            String kommentarValue = String.valueOf(textInputEditText.getText());
                            int sternValue = (int) slider.getValue();
                            String nameValue = String.valueOf(nameText.getText());
                            if (!nameValue.equals("")) {
                                builder.setTitle("Moderator benachrichtigen?");
                                builder.setPositiveButton("Ja", (dialog, id) -> {
                                    send(nameValue,kommentarValue,sternValue);
                                    // User taps OK button.
                                });
                                builder.setNegativeButton("Nein", (dialog, id) -> {
                                    dialog.dismiss();
                                    // User cancels the dialog.
                                });

                                // Create the AlertDialog.
                                AlertDialog dialog = builder.create();
                                builder.show();


                                Rating rating = new Rating(nameValue, kommentarValue, sternValue);
                                ratingViewModel.saveRating(itemId, nameValue, rating);





                            }


                    }
                });


            }


    public void send(String nameValue, String kommentarValue, int sternValue) {

String kom="Kommentar";
        EmailIntentBuilder.from(this)
                .to("ccc.reitner@gmx.de")
                .subject("Neue Playlist Bewertung")
                .body(String.valueOf((HtmlCompat.fromHtml(sternValue+" Sterne" + " von " + nameValue +"<br><br+"+kom+"<br>"+ kommentarValue,HtmlCompat.FROM_HTML_MODE_LEGACY))))
                .start();
    }



}


