package com.example.radio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.model.Rating;

import com.example.radio.R;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * Created by kunchok on 19/02/2021
 */
public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {
    List<Rating> ratingList;

    public RatingAdapter(List<Rating> ratingList){
        this.ratingList = ratingList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_details_layout,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userid.setText(String.valueOf(ratingList.get(position).getUserid()));
        holder.sterne.setText(String.valueOf(ratingList.get(position).getSterne()));
        holder.kommentar.setText(String.valueOf(ratingList.get(position).getKommentar()));


    }
    @Override
    public int getItemCount() {
        if(ratingList!=null){
            return ratingList.size();
        }else
            return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView kommentar;
        TextView userid;
        TextView sterne;
        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            kommentar=view.findViewById(R.id.kommentar);
            sterne= view.findViewById(R.id.sterne);
            userid = view.findViewById(R.id.userid);

        }

    }
}