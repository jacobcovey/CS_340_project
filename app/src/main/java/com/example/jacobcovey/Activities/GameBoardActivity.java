package com.example.jacobcovey.Activities;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.Views.GameBoardFragment;
import com.example.jacobcovey.Views.TrainCardDrawerView;

/**
 * Created by jacobcovey on 5/19/17.
 */

public class GameBoardActivity extends SingleFragmentActivity implements TrainCardDrawerView.TrainCardDrawerContainer {

    private GameBoardFragment gameBoard;

    @Override
    protected Fragment createFragment() {
        gameBoard = new GameBoardFragment();
        return gameBoard;
    }

    @Override
    public void closeTrainCardDrawer() {
        gameBoard.closeTrainCardDrawer();
    }

}
