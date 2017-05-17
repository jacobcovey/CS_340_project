package com.example.jacobcovey.ticket_to_ride.Presenters;

import com.example.jacobcovey.ticket_to_ride.Views.IGameLobbyView;

/**
 * Created by jacobcovey on 5/17/17.
 */

public interface IGameLobbyPresenter {

    void setGameLobbyView(IGameLobbyView gameLobbyView);

    void startGame();

    void leaveGame();

}
