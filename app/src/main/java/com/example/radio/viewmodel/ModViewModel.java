package com.example.radio.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.radio.model.Moderator;
import com.example.radio.model.Playlist;
import com.example.radio.repository.ModeratorRepository;
import com.example.radio.repository.PlaylistRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class ModViewModel extends ViewModel {
    MutableLiveData<List<Moderator>> ModListMutableLiveData;
    FirebaseFirestore mFirestore;
    ModeratorRepository moderatorRepository;



    public ModViewModel() {
        moderatorRepository= new ModeratorRepository() ;
        mFirestore = FirebaseFirestore.getInstance();
        ModListMutableLiveData = moderatorRepository.getModListMutableLiveData();



}    public MutableLiveData<List<Moderator>> getLiveModData()
{
    return ModListMutableLiveData;
}



}