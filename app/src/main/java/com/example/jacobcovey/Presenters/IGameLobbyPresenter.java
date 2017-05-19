package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameLobbyView;

/**
 * Created by jacobcovey on 5/17/17.
 */

public interface IGameLobbyPresenter {

    void setGameLobbyView(IGameLobbyView gameLobbyView);

    void startGame();

    void leaveGame();

    void setCurrentGame(String gameID);

    public void setViewCreated(Boolean viewCreated);

    public void joinCurrentGame();

    public void setToCurrentState();

}
