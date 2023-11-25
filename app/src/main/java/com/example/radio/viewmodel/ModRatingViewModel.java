package com.example.radio.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.radio.model.ModeratorBewertung;
import com.example.radio.model.Rating;
import com.example.radio.model.Song;
import com.example.radio.repository.ModBewertungRepository;
import com.example.radio.repository.RatingRepository;
import com.example.radio.repository.SongRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class ModRatingViewModel extends ViewModel {
    MutableLiveData<List<ModeratorBewertung>> ratingListMutableLiveData;
    FirebaseFirestore mFirestore;
    ModBewertungRepository modBewertungRepository;


    public MutableLiveData<List<ModeratorBewertung>> getRatingListMutableLiveData(String playlistid) {
        modBewertungRepository = new ModBewertungRepository();
        mFirestore = FirebaseFirestore.getInstance();
        return ratingListMutableLiveData;


    }
    public void saveRating(String mod, String userid, ModeratorBewertung moderatorBewertung) {
        modBewertungRepository=new ModBewertungRepository();
        mFirestore = FirebaseFirestore.getInstance();
        modBewertungRepository.addRating(mod, userid, moderatorBewertung);

    }



}