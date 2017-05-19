package com.example.jacobcovey.Activities;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.Views.GameBoardFragment;

/**
 * Created by jacobcovey on 5/19/17.
 */

public class GameBoardActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GameBoardFragment();
    }
}
