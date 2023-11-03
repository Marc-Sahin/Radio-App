package com.example.radio.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.radio.model.Song;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class PlaylistDetailsRepository
{
    MutableLiveData<List<Song>> PlaylistDetailsMutableLiveData;
    FirebaseFirestore mFirestore;
    MutableLiveData<Song> PlaylistMutableLiveData;

    public PlaylistDetailsRepository() {
        this.PlaylistDetailsMutableLiveData = new MutableLiveData<>();
        //define firestore
        mFirestore = FirebaseFirestore.getInstance();
        //define Playlistlist
        PlaylistMutableLiveData = new MutableLiveData<>();

    }


    //get Playlist from firebase firestore
    public MutableLiveData<List<Song>> getPlaylistDetailsMutableLiveData(String playlistid) {
        Log.i("TAG", "getPlaylistDetailsMutableLiveData: ");

        mFirestore.collection("playlist").document(playlistid).collection("songs").get()
                .addOnSuccessListener(value -> {
            // ...

            List<Song> PlaylistDetails = new ArrayList<>();
            for (QueryDocumentSnapshot doc : value) {
                if (doc != null) {
                    PlaylistDetails.add(doc.toObject(Song.class));
                }
            }
            PlaylistDetailsMutableLiveData.postValue(PlaylistDetails);
        });
        return PlaylistDetailsMutableLiveData;
    }

}
