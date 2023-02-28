package com.example.muplayer.adapter;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muplayer.R;
import com.example.muplayer.model.AudioItem;

import java.util.List;

public class AudiosAdapter extends RecyclerView.Adapter<AudiosAdapter.ViewHolder> {

    private List<AudioItem> audioItemList;

    public AudiosAdapter(List<AudioItem> audioItemList) {
        this.audioItemList = audioItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(audioItemList.get(position).getTitle());
        holder.tv_artist.setText(audioItemList.get(position).getArtist());
        holder.iv_poster.setImageResource(R.drawable.ic_audio);

    }

    @Override
    public int getItemCount() {
        return audioItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView  iv_poster;
        private TextView tv_title;
        private TextView tv_artist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster = itemView.findViewById(R.id.iv_poster);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_artist = itemView.findViewById(R.id.tv_artist);
        }
    }
}
