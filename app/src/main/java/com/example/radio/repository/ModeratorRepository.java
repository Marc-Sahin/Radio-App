package com.example.radio.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.radio.model.Moderator;
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


public class ModeratorRepository
{
    MutableLiveData<List<Moderator>> ModeratorListMutableLiveData;
    FirebaseFirestore mFirestore;
    MutableLiveData<Rating> ModeratorMutableLiveData;

    public ModeratorRepository() {
        this.ModeratorListMutableLiveData = new MutableLiveData<>();
        //define firestore
        mFirestore = FirebaseFirestore.getInstance();
        //define Modlist
        ModeratorMutableLiveData = new MutableLiveData<>();

    }


    //get Rating from firebase firestore
    public MutableLiveData<List<Moderator>> getModListMutableLiveData() {


        mFirestore.collection("moderator").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                // ...

            List<Moderator> RatingDetails = new ArrayList<>();
            for (QueryDocumentSnapshot doc : value) {
                if (doc != null) {
                    RatingDetails.add(doc.toObject(Moderator.class));
                }
            }
            ModeratorListMutableLiveData.postValue(RatingDetails);
        }});
        return ModeratorListMutableLiveData;
    }


}
