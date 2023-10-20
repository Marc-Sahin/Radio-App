package com.example.radio.repository;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.radio.model.Song;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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

        mFirestore.collectionGroup("songs").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<Song> songList = new ArrayList<>();
                for (QueryDocumentSnapshot doc : value) {
                    if (doc != null) {
                        songList.add(doc.toObject(Song.class));
                    }
                }
                songListMutableLiveData.postValue(songList);
            }
        });
        return songListMutableLiveData;
    }

}
