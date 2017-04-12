package com.itunes.music.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.itunes.music.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolBar();
    }

    private void setupToolBar() {
        mainToolBar = (Toolbar) findViewById(R.id.mainToolBar);
        setSupportActionBar(mainToolBar);
    }
}
