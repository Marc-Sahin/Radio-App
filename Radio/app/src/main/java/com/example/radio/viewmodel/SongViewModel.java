package com.example.radio.viewmodel;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.media3.exoplayer.ExoPlayer;

import com.example.radio.model.Song;
import com.example.radio.repository.SongRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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
        mFirestore = FirebaseFirestore.getInstance();
                songListMutableLiveData = songRepository.getSongListMutableLiveData();



    }
    public MutableLiveData<List<Song>> getLiveSongData() {
        return songListMutableLiveData;
    }
}