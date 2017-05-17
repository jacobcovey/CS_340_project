package com.example.jacobcovey.ticket_to_ride.Presenters;

import com.example.jacobcovey.ticket_to_ride.Views.IGameLobbyView;

/**
 * Created by jacobcovey on 5/17/17.
 */

public class GameLobbyPresenter implements IGameLobbyPresenter {

    private IGameLobbyView gameLobbyView;

    @Override
    public void setGameLobbyView(IGameLobbyView gameLobbyView) {
        this.gameLobbyView = gameLobbyView;
    }

    @Override
    public void startGame() {

    }

    @Override
    public void leaveGame() {

    }
}
