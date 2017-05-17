package com.example.jacobcovey.ticket_to_ride.Activities;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.ticket_to_ride.Views.GameListFragment;

public class GameListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GameListFragment();
    }
}

