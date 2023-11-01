package com.example.radio.adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.radio.R;
import com.example.radio.model.Song;

import java.util.List;

/**
 * Created by kunchok on 19/02/2021
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    List<Song> songList;

    public SongAdapter(List<Song> songList){
        this.songList = songList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_song_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.album.setText(songList.get(position).getAlbum());
        holder.interpret.setText(songList.get(position).getInterpret());

    }
    @Override
    public int getItemCount() {
        if(songList!=null){
            return songList.size();
        }else
            return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView album;
        TextView interpret;

        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}