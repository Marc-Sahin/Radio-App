package com.example.radio.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.radio.model.Song;
import com.example.radio.repository.PlaylistDetailsRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class PlaylistDetailsViewModel extends ViewModel {
    MutableLiveData<List<Song>> PlaylistDetailsMutableLiveData;
    FirebaseFirestore mFirestore;
    PlaylistDetailsRepository PlaylistDetailsRepository;


    public MutableLiveData<List<Song>> getLivePlaylistDetailsData() {
        return PlaylistDetailsMutableLiveData;
    }
    public MutableLiveData<List<Song>> getPlaylistDetails(String playlistid) {
        PlaylistDetailsRepository = new PlaylistDetailsRepository();
        mFirestore = FirebaseFirestore.getInstance();
      return   PlaylistDetailsMutableLiveData=PlaylistDetailsRepository.getPlaylistDetailsMutableLiveData(playlistid);

        }
    }


