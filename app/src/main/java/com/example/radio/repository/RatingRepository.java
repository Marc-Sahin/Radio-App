package com.example.radio.repository;

import static androidx.media3.common.MediaLibraryInfo.TAG;

import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.radio.PlaylistDetailsActivity;
import com.example.radio.R;
import com.example.radio.model.Rating;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class RatingRepository
{PlaylistDetailsActivity playlistDetailsActivity;
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
    public void addRating(String playlistid, Rating rating, Context context) {
        Log.i("TAG", "addRating: ");
        mFirestore.collection("playlist").document(playlistid).collection("bewertung").document(rating.getUserid()).set(rating)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(context, R.string.text_label, Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Fehlgeschlagen! Bewertung konnte nicht gespeichert werden", Toast.LENGTH_SHORT).show();

                    }
                });

    }

}
