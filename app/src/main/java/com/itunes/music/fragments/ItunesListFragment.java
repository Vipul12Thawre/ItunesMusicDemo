package com.itunes.music.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itunes.music.R;
import com.itunes.music.adapters.ItunesMusicAdapter;
import com.itunes.music.interfaces.ItunesMusicCallback;
import com.itunes.music.interfaces.MusicItemCallBack;
import com.itunes.music.models.Results;
import com.itunes.music.presenters.ItunesMusicPresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by RAHUL on 11-04-2017.
 */

public class ItunesListFragment extends Fragment implements ItunesMusicCallback, MusicItemCallBack {

    private RecyclerView rcMusicList;
    private ItunesMusicAdapter itunesMusicAdapter;
    private ArrayList<Results> resultses = new ArrayList<>();
    private CardView cardLoading;
    private View rootView;
    private ItunesMusicPresenter itunesMusicPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_itunes_music_list, container, false);
        initViews(rootView);
        return rootView;

    }

    private void initViews(View rootView) {

        cardLoading = (CardView) rootView.findViewById(R.id.cardLoading);
        rcMusicList = (RecyclerView) rootView.findViewById(R.id.rcMusicList);
        itunesMusicAdapter = new ItunesMusicAdapter(getActivity(), resultses, this);
        rcMusicList.setAdapter(itunesMusicAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        itunesMusicPresenter = new ItunesMusicPresenter(this);
        itunesMusicPresenter.getMusic("Michael Jackson");
    }

    @Override
    public void onItunesMusicResponse(final Results[] results) {
        bindUI(results);
    }

    private void bindUI(Results[] results) {
        resultses.clear();
        resultses = new ArrayList<Results>(Arrays.asList(results));
        itunesMusicAdapter = new ItunesMusicAdapter(getActivity(), resultses, this);
        rcMusicList.setAdapter(itunesMusicAdapter);
        itunesMusicAdapter.notifyDataSetChanged();

        if (cardLoading.getVisibility() == View.VISIBLE && rcMusicList.getVisibility() == View.GONE) {
            cardLoading.setVisibility(View.GONE);
            rcMusicList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItunesMusicError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMusicClick(Results results) {
        ShowDetailsDialogFragment showDetailsDialogFragment = new ShowDetailsDialogFragment().newInstance(results);
        showDetailsDialogFragment.show(getFragmentManager(), "show details");
    }
}
