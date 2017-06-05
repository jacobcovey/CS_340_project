package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iGameListView;

import java.util.List;

import shared.classes.Game;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface iGameListPresenter {

    void setGameListView(iGameListView gameListView);

    void setUpGame();

    void joinGame(Game game);

    List<Game> getGames();



}
