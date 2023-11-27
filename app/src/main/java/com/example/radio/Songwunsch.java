package com.example.radio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.radio.R;
import com.example.radio.model.Rating;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import de.cketti.mailto.EmailIntentBuilder;

public class Songwunsch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_songwunsch);
        TextInputEditText wunsch = findViewById(R.id.SongwunschText);
        TextInputEditText nameText = findViewById(R.id.name);
        Button button = findViewById(R.id.submitWunsch);

        button.setOnClickListener(view -> {

            String SongwunschValue = String.valueOf(wunsch.getText());
            String nameValue = String.valueOf(nameText.getText());
            if (!nameValue.equals("") && !SongwunschValue.equals("")) {

                send(nameValue, SongwunschValue);
                // User taps OK button.
            }

        });


    }

    public void send(String name, String wunsch) {
        Executors.newSingleThreadExecutor().execute(() -> {
            // todo: background tasks

            //Email Anmeldedaten initialisieren

            String sender = "a5c34057-14db-45ca-897e-018bdeb4b584";
            String password = "f690d8ea-137f-4963-af4f-c5761ed48f9b";
            String receiver = " cccreitner@v0ddpdsuykwjfggl3rs1ha.imitate.email";

            Properties properties = new Properties();
            //SMTP Properties festlegen
            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.host", "smtp.imitate.email");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.debug.auth", "true");
            properties.put("mail.smtp.user", sender);
            properties.put("mail.smtp.password", password);
            properties.put("mail.smtp.starttls.enable", "true");

            // mit Server Session authentifizieren
            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(sender, password);
                        }
                    });


            try {
                //Nachricht zusammenbauen
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(receiver));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(receiver));
                message.setSubject("Neuer Songwunsch");
                String msg = "<b>Songwunsch</b><br>" + wunsch + "<br><br>von " + name;

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                message.setContent(multipart);

                //Email senden
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.imitate.email",
                        sender,
                        "f690d8ea-137f-4963-af4f-c5761ed48f9b");
                transport.sendMessage(message, message.getAllRecipients());

                Log.i("Tag", "Done");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        // Nachricht gesendet Benachrichtigung
        Snackbar.make(findViewById(R.id.meinung), R.string.text_label, Snackbar.LENGTH_SHORT)
                .show();
    }
}
