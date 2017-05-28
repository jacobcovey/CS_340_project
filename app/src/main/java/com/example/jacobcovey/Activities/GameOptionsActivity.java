package com.example.jacobcovey.Activities;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.Views.GameOptionsView;

/**
 * Created by jacobcovey on 5/16/17.
 */

public class GameOptionsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new GameOptionsView();
    }
}
