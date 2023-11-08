package com.example.radio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.R;
import com.example.radio.model.Song;

import java.util.List;

/**
 * Created by kunchok on 19/02/2021
 */
public class PlaylistDetailsAdapter extends RecyclerView.Adapter<PlaylistDetailsAdapter.ViewHolder> {
    List<Song> playlistDetails;

    public PlaylistDetailsAdapter(List<Song> playlistDetails){
        this.playlistDetails = playlistDetails;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_details_layout,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.album.setText(String.valueOf(playlistDetails.get(position).getAlbum()));
        holder.title.setText(String.valueOf(playlistDetails.get(position).getTitle()));
        holder.veröffentlichung.setText(String.valueOf(playlistDetails.get(position).getVeröffentlichung()));
        holder.länge.setText(String.valueOf(playlistDetails.get(position).getLänge()));
        holder.interpret.setText(String.valueOf(playlistDetails.get(position).getInterpret()));

    }
    @Override
    public int getItemCount() {
        if(playlistDetails!=null){
            return playlistDetails.size();
        }else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       View view;

        TextView title;
        TextView länge;
        TextView veröffentlichung;
        TextView interpret;
        TextView album;
        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title=view.findViewById(R.id.title);
            interpret =view.findViewById(R.id.interpret);
            veröffentlichung = view.findViewById(R.id.veröffentlichung);
            länge = view.findViewById(R.id.länge);
            album=view.findViewById(R.id.album);

        }


    }
}