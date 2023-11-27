package com.example.radio;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.radio.model.ModeratorBewertung;
import com.example.radio.viewmodel.ModRatingViewModel;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

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

public class MeinungActivity extends AppCompatActivity {
    ModeratorBewertung moderatorBewertung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.deine_meinung);

        ModRatingViewModel modRatingViewModel=new ViewModelProvider(this).get(ModRatingViewModel.class);

        TextInputEditText nameText = findViewById(R.id.name);
        RatingBar slider = findViewById(R.id.sterneslide);
        TextInputEditText textInputEditText = findViewById(R.id.kommentarText);
        Button button = findViewById(R.id.submitRating);
        Intent intent=getIntent();
        String mod=intent.getStringExtra("mod");
        button.setOnClickListener(view->{
                String kommval=String.valueOf(textInputEditText.getText());
                String nameValue = String.valueOf(nameText.getText());
                int slideval = (int) slider.getRating();
                if (!nameValue.equals("")) {

                    moderatorBewertung = new ModeratorBewertung(nameValue, kommval, slideval);
                    modRatingViewModel.saveRating(mod,nameValue,moderatorBewertung);
                    send(nameValue,kommval,slideval);

                }

                });


            }
    public void send(String name, String kom, int star) {
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
                message.setSubject("Neuer Moderator Bewertung");
                String msg = "<b>Bewertung</b><br>"+star+ " Sterne<br><br>von "+name+"<br><b>Kommentar:</b>"+kom;

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
        });      Snackbar.make(findViewById(R.id.meinung), R.string.text_label, Snackbar.LENGTH_SHORT)
                .show();
    }
}
