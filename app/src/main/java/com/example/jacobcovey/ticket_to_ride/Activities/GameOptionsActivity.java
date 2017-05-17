package com.example.jacobcovey.ticket_to_ride.Activities;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.ticket_to_ride.Views.GameOptionsFragment;

/**
 * Created by jacobcovey on 5/16/17.
 */

public class GameOptionsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new GameOptionsFragment();
    }
}
