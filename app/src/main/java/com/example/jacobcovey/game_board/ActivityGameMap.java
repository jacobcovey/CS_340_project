package com.example.jacobcovey.game_board;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.Activities.SingleFragmentActivity;

/**
 * Created by Riley on 5/31/2017.
 */

public class ActivityGameMap extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return FragmentGameMap.newInstance();
    }
}
