package com.itunes.music.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itunes.music.R;
import com.itunes.music.interfaces.MusicItemCallBack;
import com.itunes.music.models.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by RAHUL on 05-03-2017.
 */

public class ItunesMusicAdapter extends RecyclerView.Adapter<ItunesMusicAdapter.MusicHolder> {


    private final Context context;
    ArrayList<Results> resultses;
    private final MusicItemCallBack musicItemCallBack;

    public ItunesMusicAdapter(Context context, ArrayList<Results> resultses, MusicItemCallBack musicItemCallBack) {
        this.context = context;
        this.resultses = resultses;
        this.musicItemCallBack = musicItemCallBack;
    }

    @Override
    public MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_music_card, parent, false);
        return new MusicHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MusicHolder holder, final int position) {
        bindViews(holder, position);
    }

    private void bindViews(MusicHolder holder, final int position) {
        holder.txtMusicName.setText(resultses.get(position).getTrackName());
        holder.txtArtist.setText(resultses.get(position).getArtistName());
        Picasso.with(context)
                .load(resultses.get(position).getArtworkUrl100())
                .placeholder(R.drawable.music)
                .error(R.drawable.music)
                .into(holder.imgMusic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicItemCallBack.onMusicClick(resultses.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultses.size();
    }

     class MusicHolder extends RecyclerView.ViewHolder {

        private final ImageView imgMusic;
        private final TextView txtMusicName;
        private final TextView txtArtist;

         MusicHolder(View itemView) {
            super(itemView);
            imgMusic = (ImageView) itemView.findViewById(R.id.imgMusic);
            txtMusicName = (TextView) itemView.findViewById(R.id.txtMusicName);
            txtArtist = (TextView) itemView.findViewById(R.id.txtArtist);
        }
    }
}
