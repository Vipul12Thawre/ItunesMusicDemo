package com.itunes.music.interfaces;

import com.itunes.music.models.Results;

/**
 * Created by RAHUL on 11-04-2017.
 */

public interface ItunesMusicCallback {
    void onItunesMusicResponse(Results[] results);

    void onItunesMusicError(String message);
}
