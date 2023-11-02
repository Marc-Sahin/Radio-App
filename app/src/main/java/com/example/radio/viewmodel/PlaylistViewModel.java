package com.example.radio.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.radio.model.Playlist;
import com.example.radio.repository.PlaylistRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class PlaylistViewModel extends ViewModel {
    MutableLiveData<List<Playlist>> PlaylistListMutableLiveData;
    FirebaseFirestore mFirestore;
    PlaylistRepository PlaylistRepository;



    public PlaylistViewModel() {
        PlaylistRepository = new PlaylistRepository();
        mFirestore = FirebaseFirestore.getInstance();
                PlaylistListMutableLiveData = PlaylistRepository.getPlaylistListMutableLiveData();



}    public MutableLiveData<List<Playlist>> getLivePlaylistData()
{
    return PlaylistListMutableLiveData;
}



}