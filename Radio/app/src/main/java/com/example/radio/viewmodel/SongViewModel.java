package com.example.radio.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.radio.model.Song;
import com.example.radio.repository.SongRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class SongViewModel extends ViewModel {
    MutableLiveData<List<Song>> songListMutableLiveData;
    FirebaseFirestore mFirestore;
    SongRepository songRepository;

    public SongViewModel() {
        songRepository = new SongRepository();
        songListMutableLiveData = songRepository.getSongListMutableLiveData();
        mFirestore = FirebaseFirestore.getInstance();
    }

    public MutableLiveData<List<Song>> getLiveSongData() {
        return songListMutableLiveData;
    }
}