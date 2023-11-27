package com.example.radio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radio.MeinungActivity;
import com.example.radio.PlaylistDetailsActivity;
import com.example.radio.R;
import com.example.radio.model.Moderator;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by kunchok on 19/02/2021
 */
public class ModAdapter extends RecyclerView.Adapter<ModAdapter.ViewHolder> {
    List<Moderator> moderatorList;

    public ModAdapter() {
    }

    public ModAdapter(List<Moderator> moderatorList){
        this.moderatorList = moderatorList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moderator_auswahl_items,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(moderatorList.get(position).getName());



    }
    @Override
    public int getItemCount() {
        if(moderatorList!=null){
            return moderatorList.size();
        }else
            return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;

        TextView name;
        private Context context;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.moderatorNameTextView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Moderator moderator = moderatorList.get(getAbsoluteAdapterPosition());

            // Start the MeinungActivity and pass the moderator name
            Intent intent = new Intent(view.getContext(), MeinungActivity.class);
            intent.putExtra("mod", moderator.getName());
            view.getContext().startActivity(intent);

        }
    }
}