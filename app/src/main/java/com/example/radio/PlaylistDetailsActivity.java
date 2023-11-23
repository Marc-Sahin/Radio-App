package com.example.radio;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.radio.adapter.PlaylistDetailsAdapter;
import com.example.radio.adapter.RatingAdapter;
import com.example.radio.model.Rating;
import com.example.radio.model.Song;
import com.example.radio.viewmodel.PlaylistDetailsViewModel;
import com.example.radio.viewmodel.RatingViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import de.cketti.mailto.EmailIntentBuilder;

public class PlaylistDetailsActivity extends AppCompatActivity {
    private PlaylistDetailsAdapter playlistDetailsAdapter;
    private RatingAdapter ratingAdapter;

private     ViewPager2 viewPager2;
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
        viewPager2 = findViewById(R.id.viewPager);

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        ratingAdapter=new RatingAdapter();
viewPager2.setAdapter(ratingAdapter);


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

        ratingViewModel.getRatingListMutableLiveData(itemId).observe(this, new Observer<List<Rating>>() {

            @Override
            public void onChanged(List<Rating> ratings) {
                ratingAdapter.setItems(ratings);



            } });

         RatingBar slider = findViewById(R.id.sterneslide);
        TextInputEditText textInputEditText = findViewById(R.id.kommentarText);
        TextInputEditText nameText = findViewById(R.id.name);
        // listen für das Auslösen von Bewertung abgeben.

        MaterialButton submitRating = (MaterialButton) findViewById(R.id.submitRating);
        submitRating.setOnClickListener(new OnClickListener() {


            public void onClick(View v) {

                            String kommentarValue = String.valueOf(textInputEditText.getText());
                            int sternValue = (int) slider.getRating();
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
                                Snackbar.make(findViewById(R.id.rootPlaylistdetails), R.string.text_label, Snackbar.LENGTH_SHORT)
                                        .show();





                            }


                    }
                });


            }



    public void send(String nameValue, String kommentarValue, int sternValue) {
        Executors.newSingleThreadExecutor().execute(() -> {
            // todo: background tasks


            Log.w("Tag","Done");

            String sender = "a5c34057-14db-45ca-897e-018bdeb4b584";
            String password = "f690d8ea-137f-4963-af4f-c5761ed48f9b";
            String receiver = " cccreitner@v0ddpdsuykwjfggl3rs1ha.imitate.email";

            Properties properties = new Properties();

            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.host", "smtp.imitate.email");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.debug.auth", "true");
            properties.put("mail.smtp.user", sender);
            properties.put("mail.smtp.password", password);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.debug", "true");

            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(sender, password);
                        }
                    });
            session.setDebug(true);


            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(receiver));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(receiver));
                message.setSubject("Neue Playlist Bewertung");
                String msg = sternValue+ " Sterne" + " von " + nameValue +
                        "<br><br><b>Kommentar</b><br>"+ kommentarValue;

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);

                message.setContent(multipart);

                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.imitate.email",
                        sender,
                        "f690d8ea-137f-4963-af4f-c5761ed48f9b");
                transport.sendMessage(message, message.getAllRecipients());

                Log.i("Tag","Done");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });


                Snackbar.make(findViewById(R.id.rootPlaylistdetails), R.string.text_label, Snackbar.LENGTH_SHORT)
                        .show();

    }



}


