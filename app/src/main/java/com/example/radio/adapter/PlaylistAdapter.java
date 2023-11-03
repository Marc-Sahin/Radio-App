package com.example.radio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.PlaylistDetailsActivity;
import com.example.radio.R;
import com.example.radio.model.Playlist;

import java.util.List;

/**
 * Created by kunchok on 19/02/2021
 */
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    List<Playlist> playlistList;

    public PlaylistAdapter(List<Playlist> playlistList){
        this.playlistList = playlistList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_playlists_layout,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.playlistid.setText(playlistList.get(position).getPlaylistid());
        holder.dauer.setText(String.valueOf(playlistList.get(position).getDauer()));
        holder.genre.setText(playlistList.get(position).getGenre());

    }
    @Override
    public int getItemCount() {
        if(playlistList!=null){
            return playlistList.size();
        }else
            return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;
        TextView playlistid;
        TextView dauer;
        TextView genre;
        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            playlistid=view.findViewById(R.id.playlistid);
            dauer= view.findViewById(R.id.dauer);
            genre=view.findViewById(R.id.genre);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Playlist playlist = playlistList.get(getAbsoluteAdapterPosition());

            // Start the DetailsActivity and pass the item ID
            Intent intent = new Intent(view.getContext(), PlaylistDetailsActivity.class);
            intent.putExtra("playlistid", playlist.getPlaylistid());
            view.getContext().startActivity(intent);

        }
    }
}