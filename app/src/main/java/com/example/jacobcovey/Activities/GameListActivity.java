package com.example.jacobcovey.Activities;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.Views.GameListFragment;

public class GameListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GameListFragment();
    }
}

