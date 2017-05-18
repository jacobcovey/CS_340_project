package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameListView;

import java.util.List;

import shared.classes.Game;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface IGameListPresenter extends IObserver {

    void setGameListView(IGameListView gameListView);

    void setUpGame();

    void joinGame(Game game);

    List<Game> getGames();



}
