package com.itunes.music.network;

import com.itunes.music.models.ItunesMusic;
import com.itunes.music.models.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search")
    Call<ItunesMusic> getMusicData(@Query("term") String term);
}