package com.example.jacobcovey.ticket_to_ride.Presenters;

import com.example.jacobcovey.ticket_to_ride.Views.IGameListView;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface IGameListPresenter {

    void setGameListView(IGameListView gameListView);

    void setUpGame();

    void joinGame(Object game);



}
