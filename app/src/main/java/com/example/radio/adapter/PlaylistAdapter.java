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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
            LocalDate today = LocalDate.now();
            int now=today.getDayOfWeek().getValue();
            int playlistday=playlistList.get(position).getTag();
            if (playlistday==now){
            holder.tag.setText(R.string.heute);
            }
            else if (playlistday<now){
                holder.tag.setText(R.string.gestern);
            }
            else {
                holder.tag.setText(R.string.morgen);
            }



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
        TextView tag;
        private Context context;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;
            tag = view.findViewById(R.id.Tag);
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