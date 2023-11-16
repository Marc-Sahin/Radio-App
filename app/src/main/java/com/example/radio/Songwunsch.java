package com.example.radio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.os.Bundle;
import android.view.View;

import com.example.radio.R;
import com.example.radio.model.Rating;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import de.cketti.mailto.EmailIntentBuilder;

public class Songwunsch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songwunsch);

        TextInputEditText textInputEditText = findViewById(R.id.SongwunschText);
        TextInputEditText nameText = findViewById(R.id.name);
        // listen für das Auslösen von Bewertung abgeben.

        MaterialButton submit = (MaterialButton) findViewById(R.id.submitWunsch);
        submit.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                String SongwunschValue = String.valueOf(textInputEditText.getText());
                String nameValue = String.valueOf(nameText.getText());
                if (!nameValue.equals("") && !SongwunschValue.equals("")) {
                        send(nameValue,SongwunschValue);
                        // User taps OK button.

                }


            }
        });


    }


    public void send(String nameValue, String songwunsch) {

        String kom="Songwunsch";
        EmailIntentBuilder.from(this)
                .to("ccc.reitner@gmx.de")
                .subject("Neuer Songwunsch")
                .body(String.valueOf((HtmlCompat.fromHtml(nameValue +"<br><br+"+kom+"<br>"+ songwunsch,HtmlCompat.FROM_HTML_MODE_LEGACY))))
                .start();
    }


}
