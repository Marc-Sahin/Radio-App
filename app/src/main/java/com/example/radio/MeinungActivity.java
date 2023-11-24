package com.example.radio;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import com.example.radio.model.Moderator;
import com.example.radio.model.ModeratorBewertung;
import com.example.radio.model.Rating;
import com.example.radio.repository.ModBewertungRepository;
import com.example.radio.repository.ModeratorRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Properties;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
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

public class MeinungActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.deine_meinung);
        TextInputEditText nameText = findViewById(R.id.name);
        RatingBar slider = findViewById(R.id.sterneslide);
        TextInputEditText textInputEditText = findViewById(R.id.kommentarText);
        Button button = findViewById(R.id.submitRating);
        Intent intent=new Intent();
        String mod=intent.getStringExtra("mod");
        button.setOnClickListener(view->{
                String kommval=String.valueOf(textInputEditText.getText());
                String nameValue = String.valueOf(nameText.getText());
                int slideval = (int) slider.getRating();
                if (!nameValue.equals("")) {
                    ModeratorBewertung moderatorBewertung = new ModeratorBewertung(nameValue, kommval, slideval);
                    ModBewertungRepository modBewertungRepository = new ModBewertungRepository();
                    modBewertungRepository.addRating(mod,nameValue,moderatorBewertung);

                }

                });


            }

}
