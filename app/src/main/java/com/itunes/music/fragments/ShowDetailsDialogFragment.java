package com.itunes.music.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.itunes.music.R;
import com.itunes.music.models.Results;
import com.squareup.picasso.Picasso;

public class ShowDetailsDialogFragment extends DialogFragment implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {


    private static final String ARG_ITEM = "MUSIC_DATA";
    private Results item;
    private ImageView imgDetails;
    private TextView txtTrackName;
    private TextView txtAlbumName;
    private TextView txtArtist;
    private TextView txtPrice;
    private MediaPlayer mp;
    private TextView txtAlbumLink;
    private TextView txtTrackLink;

    public ShowDetailsDialogFragment newInstance(Results item) {
        ShowDetailsDialogFragment f = new ShowDetailsDialogFragment();
        Bundle b = new Bundle();
        b.putSerializable(ARG_ITEM, item);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = (Results) getArguments().getSerializable(ARG_ITEM);
    }

    @Override
    public void onStart() {
        super.onStart();
        //set transparent background
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragement_music_details, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        imgDetails = (ImageView) view.findViewById(R.id.imgDetails);
        txtTrackName = (TextView) view.findViewById(R.id.txtTrackName);
        txtAlbumName = (TextView) view.findViewById(R.id.txtAlbumName);
        txtArtist = (TextView) view.findViewById(R.id.txtArtist);
        txtPrice = (TextView) view.findViewById(R.id.txtPrice);
        txtAlbumLink = (TextView) view.findViewById(R.id.txtAlbumLink);
        txtTrackLink = (TextView) view.findViewById(R.id.txtTrackLink);
        txtAlbumLink.setMovementMethod(LinkMovementMethod.getInstance());
        txtTrackLink.setMovementMethod(LinkMovementMethod.getInstance());
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUIDate();
        serupMediaPlayer();
    }

    private void serupMediaPlayer() {
        try {
            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setOnPreparedListener(this);
            mp.setOnErrorListener(this);
            mp.setDataSource(item.getPreviewUrl());
            mp.prepareAsync();
            mp.setOnCompletionListener(this);
        } catch (Exception e) {
            Log.e("StreamAudioDemo", e.getMessage());
        }
    }

    private void setUIDate() {
        Picasso.with(getActivity())
                .load(item.getArtworkUrl100())
                .placeholder(R.drawable.music)
                .error(R.drawable.music)
                .into(imgDetails);
        txtTrackName.setText(item.getTrackName());
        txtAlbumName.setText(item.getCollectionName());
        txtArtist.setText(item.getArtistName());
        txtAlbumLink.setText(item.getCollectionViewUrl());
        txtTrackLink.setText(item.getTrackViewUrl());
        txtPrice.setText(getActivity().getResources().getString(R.string.dollar) + item.getTrackPrice());
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mp = mediaPlayer;
        Log.d("media status", "completed");
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        Log.d("media status", "error");
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d("media status", "Prepared");
        mp = mediaPlayer;
        mediaPlayer.start();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mp != null )
            mp.stop();
    }
}