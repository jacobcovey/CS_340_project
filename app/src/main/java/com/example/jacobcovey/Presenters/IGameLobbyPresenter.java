package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameLobbyView;

/**
 * Created by jacobcovey on 5/17/17.
 */

public interface IGameLobbyPresenter extends IObserver {

    void setGameLobbyView(IGameLobbyView gameLobbyView);

    void startGame();

    void leaveGame();

}
