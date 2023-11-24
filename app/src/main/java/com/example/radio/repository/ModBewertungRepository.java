package com.example.radio.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.radio.model.ModeratorBewertung;
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


public class ModBewertungRepository
{
    MutableLiveData<List<ModeratorBewertung>> RatingListMutableLiveData;
    FirebaseFirestore mFirestore;
    MutableLiveData<Rating> RatingMutableLiveData;

    public ModBewertungRepository() {
        this.RatingListMutableLiveData = new MutableLiveData<>();
        //define firestore
        mFirestore = FirebaseFirestore.getInstance();
        //define Ratinglist
        RatingMutableLiveData = new MutableLiveData<>();

    }



    public void addRating(String moderatorName, String userid, ModeratorBewertung moderatorBewertung) {
        Log.i("TAG", "addRating: ");
        mFirestore.collection("moderator").document(moderatorName).collection("bewertung").document(userid).set(moderatorBewertung)
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
