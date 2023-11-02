package com.example.radio.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.radio.model.Song;


import com.google.firebase.firestore.FirebaseFirestore;


import com.google.firebase.firestore.QueryDocumentSnapshot;


import java.util.ArrayList;
import java.util.List;


public class SongRepository
{
    MutableLiveData<List<Song>> songListMutableLiveData;
    FirebaseFirestore mFirestore;
    MutableLiveData<Song> songMutableLiveData;

    public SongRepository() {
        this.songListMutableLiveData = new MutableLiveData<>();
        //define firestore
        mFirestore = FirebaseFirestore.getInstance();
        //define songlist
        songMutableLiveData = new MutableLiveData<>();

    }


    //get song from firebase firestore
    public MutableLiveData<List<Song>> getSongListMutableLiveData() {
        Log.i("TAG", "getSongListMutableLiveData: ");

        mFirestore.collectionGroup("songs").get().addOnSuccessListener(value -> {
            // ...

            List<Song> songList = new ArrayList<>();
            for (QueryDocumentSnapshot doc : value) {
                if (doc != null) {
                    songList.add(doc.toObject(Song.class));
                }
            }
            songListMutableLiveData.postValue(songList);
        });
        return songListMutableLiveData;
    }

}
