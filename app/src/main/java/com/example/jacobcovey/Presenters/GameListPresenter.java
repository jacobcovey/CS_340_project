package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameListView;

/**
 * Created by jacobcovey on 5/15/17.
 */

public class GameListPresenter implements IGameListPresenter {

    private IGameListView gameListView;

    @Override
    public void setGameListView(IGameListView gameListView) {
        this.gameListView = gameListView;
    }

    @Override
    public void setUpGame() {
        System.out.println("setUpGame()");
    }

    @Override
    public void joinGame(Object game) {

    }
}
