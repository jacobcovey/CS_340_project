package com.example.jacobcovey.Activities;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.Views.GameLobbyView;

/**
 * Created by jacobcovey on 5/17/17.
 */

public class GameLobbyActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GameLobbyView();
    }
}
