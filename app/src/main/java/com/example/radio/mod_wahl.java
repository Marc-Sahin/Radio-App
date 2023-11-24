package com.example.radio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.adapter.ModAdapter;
import com.example.radio.viewmodel.ModViewModel;

public class mod_wahl extends AppCompatActivity {
    private ModAdapter modAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mod_auswahl_recycler);

        ModViewModel modViewModel = new ViewModelProvider(this).get(com.example.radio.viewmodel.ModViewModel.class);
        RecyclerView modRecycler = findViewById(R.id.container);

        modRecycler.setLayoutManager(new LinearLayoutManager(this));
        modRecycler.setHasFixedSize(true);
        // get blog through viewModel
        modViewModel.getLiveModData().observe(this, modlist -> {
            if (modlist != null){

                modAdapter = new ModAdapter(modlist);
                modRecycler.setAdapter(modAdapter);
                modAdapter.notifyDataSetChanged();
            }
        });

    }
}