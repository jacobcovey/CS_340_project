package com.example.jacobcovey.Activities;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.Views.GameListView;

public class GameListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GameListView();
    }
}

