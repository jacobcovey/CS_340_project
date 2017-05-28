package com.example.jacobcovey.Activities;

import android.support.v4.app.Fragment;

import com.example.jacobcovey.Views.GameBoardView;
import com.example.jacobcovey.Views.GameInfoView;
import com.example.jacobcovey.Views.TrainCardDrawerView;

/**
 * Created by jacobcovey on 5/19/17.
 */

public class GameBoardActivity extends SingleFragmentActivity implements TrainCardDrawerView.TrainCardDrawerContainer, GameInfoView.GameInfoDrawerContainer {

    private GameBoardView gameBoard;

    @Override
    protected Fragment createFragment() {
        gameBoard = new GameBoardView();
        return gameBoard;
    }

    @Override
    public void closeTrainCardDrawer() {
        gameBoard.closeTrainCardDrawer();
    }

    @Override
    public void closeGameInfoDrawer() {
        gameBoard.closeGameInfoDrawer();
    }
}
