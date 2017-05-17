package com.example.jacobcovey.ticket_to_ride.Presenters;

import com.example.jacobcovey.ticket_to_ride.Views.IGameOptionsView;

/**
 * Created by jacobcovey on 5/16/17.
 */

public interface IGameOptionsPresenter {

    void setGameOptionsView(IGameOptionsView gameOptionsView);

    void createGame();
}
