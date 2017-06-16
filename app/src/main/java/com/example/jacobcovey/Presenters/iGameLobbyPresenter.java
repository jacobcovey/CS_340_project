package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iGameLobbyView;

/**
 * Created by jacobcovey on 5/17/17.
 */

public interface iGameLobbyPresenter {

    void setGameLobbyView(iGameLobbyView gameLobbyView);

    void startGame();

    void setCurrentGame(String gameID);

    public void setViewCreated(Boolean viewCreated);

    public void setToCurrentState();

}
