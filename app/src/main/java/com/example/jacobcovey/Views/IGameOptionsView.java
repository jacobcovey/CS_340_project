package com.example.jacobcovey.Views;

/**
 * Created by jacobcovey on 5/16/17.
 */

public interface IGameOptionsView {

    String getGameName();

    void setGameName(String gameName);

    String getNumPlayers();

    void setNumPlayers(String numPlayers);

    void navToGameLobbyScreenActivity();

}
