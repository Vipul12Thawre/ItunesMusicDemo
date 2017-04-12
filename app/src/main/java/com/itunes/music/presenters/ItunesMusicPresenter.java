package com.itunes.music.presenters;

import android.util.Log;

import com.itunes.music.fragments.ItunesListFragment;
import com.itunes.music.interfaces.ItunesMusicCallback;
import com.itunes.music.models.ItunesMusic;
import com.itunes.music.models.Results;
import com.itunes.music.network.ApiClient;
import com.itunes.music.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RAHUL on 11-04-2017.
 */

public class ItunesMusicPresenter {

    private final ItunesMusicCallback itunesMusicCallback;

    public ItunesMusicPresenter(ItunesMusicCallback itunesMusicCallback) {

        this.itunesMusicCallback = itunesMusicCallback;
    }

    public void getMusic(String musicTerm) {
        callItunesAPI(musicTerm);
    }

    private void callItunesAPI(String musicTerm) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ItunesMusic> call = apiService.getMusicData(musicTerm);
        call.enqueue(new Callback<ItunesMusic>() {
            @Override
            public void onResponse(Call<ItunesMusic> call, Response<ItunesMusic> response) {
                response.body().getResults();
                itunesMusicCallback.onItunesMusicResponse(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ItunesMusic> call, Throwable t) {
                itunesMusicCallback.onItunesMusicError(t.getMessage());
                Log.d("TAG", "" + t.getMessage());
            }
        });
    }
}
