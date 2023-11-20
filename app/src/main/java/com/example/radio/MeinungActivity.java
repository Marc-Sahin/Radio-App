package com.example.radio;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

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

        setContentView(R.layout.activity_songwunsch);
        TextInputEditText wunsch=findViewById(R.id.SongwunschText);
        TextInputEditText nameText = findViewById(R.id.name);
        Button button = findViewById(R.id.submitWunsch);

        button.setOnClickListener(view->{

                String SongwunschValue = String.valueOf(wunsch.getText());
                String nameValue = String.valueOf(nameText.getText());
                if (!nameValue.equals("") && !SongwunschValue.equals("")) {
                    send(nameValue, SongwunschValue);
                    // User taps OK button.
                }

                });


            }
public void send(String name, String kommentar) {

        String kom="Kommentar";
        EmailIntentBuilder.from(this)
                .to("ccc.reitner@gmx.de")
                .subject("Neue Playlist Bewertung")
                .body(String.valueOf((HtmlCompat.fromHtml(kom+"<br>"+ kommentar+"von"+"name",HtmlCompat.FROM_HTML_MODE_LEGACY))))
                .start();
    }
}
