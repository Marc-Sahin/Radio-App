package com.example.radio.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.radio.model.Rating;
import com.example.radio.repository.RatingRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class RatingViewModel extends ViewModel {
    MutableLiveData<List<Rating>> ratingListMutableLiveData;
    FirebaseFirestore mFirestore;
    RatingRepository ratingRepository;


    public MutableLiveData<List<Rating>> getRatingListMutableLiveData(String playlistid) {
        ratingRepository = new RatingRepository();
        mFirestore = FirebaseFirestore.getInstance();
        return ratingListMutableLiveData = ratingRepository.getRatingListMutableLiveData(playlistid);


    }
    public void saveRating(String playlistid, String userid, Rating rating) {
        ratingRepository = new RatingRepository();
        mFirestore = FirebaseFirestore.getInstance();
        ratingRepository.addRating(playlistid, userid, rating);

    }
}