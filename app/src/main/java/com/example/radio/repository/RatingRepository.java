package com.example.radio.repository;

import static androidx.media3.common.MediaLibraryInfo.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.radio.model.Rating;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class RatingRepository
{
    MutableLiveData<List<Rating>> RatingListMutableLiveData;
    FirebaseFirestore mFirestore;
    MutableLiveData<Rating> RatingMutableLiveData;

    public RatingRepository() {
        this.RatingListMutableLiveData = new MutableLiveData<>();
        //define firestore
        mFirestore = FirebaseFirestore.getInstance();
        //define Ratinglist
        RatingMutableLiveData = new MutableLiveData<>();

    }


    //get Rating from firebase firestore
    public MutableLiveData<List<Rating>> getRatingListMutableLiveData(String playlistid) {
        Log.i("TAG", "getRatingListMutableLiveData: ");

        mFirestore.collection("playlist").document(playlistid).collection("bewertung").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                // ...

            List<Rating> RatingDetails = new ArrayList<>();
            for (QueryDocumentSnapshot doc : value) {
                if (doc != null) {
                    RatingDetails.add(doc.toObject(Rating.class));
                }
            }
            RatingListMutableLiveData.postValue(RatingDetails);
        }});
        return RatingListMutableLiveData;
    }
    public void addRating(String playlistid, String userid, Rating rating) {
        Log.i("TAG", "addRating: ");
        mFirestore.collection("playlist").document(playlistid).collection("bewertung").document(userid).set(rating)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });

    }

}
