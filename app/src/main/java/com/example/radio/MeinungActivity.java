package com.example.radio;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.radio.model.ModeratorBewertung;
import com.example.radio.viewmodel.ModRatingViewModel;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

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

                }
            Snackbar.make(findViewById(R.id.meinung), R.string.text_label, Snackbar.LENGTH_SHORT)
                    .show();
                });


            }

}
