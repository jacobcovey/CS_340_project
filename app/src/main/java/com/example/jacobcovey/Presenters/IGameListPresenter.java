package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameListView;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface IGameListPresenter extends IObserver {

    void setGameListView(IGameListView gameListView);

    void setUpGame();

    void joinGame();



}
