package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameBoardView;
import com.example.jacobcovey.game_board.Route;
import com.example.jacobcovey.game_board.ViewGameBoard;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Riley on 5/31/2017.
 */

public class GameBoardPresenter implements iGameBoardPresenter, Observer {

    private ViewGameBoard boardView;

    private List<Route> mRoutes;

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void setGameBoardView(IGameBoardView gameBoardView) {
        //this.boardView = gameBoardView;
    }

    @Override
    public void updateBoard() {

    }

    @Override
    public void updateClientRoot(List<Route> routes) {

    }
    @Override
    public void changeClientRoot() {

    }
}

