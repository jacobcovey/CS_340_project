package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameLobbyView;

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
