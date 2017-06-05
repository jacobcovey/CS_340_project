package com.example.jacobcovey.Views;

import java.util.List;

/**
 * Created by jacobcovey on 5/17/17.
 */

public interface iGameLobbyView {

    void setPlayerList(List<String> names);

    void hideUnusedPlayers(int numGamePlayers);

    void setGameName(String gameName);

    void setGameCreator(String gameCreator);

    void navToGameListScreenActivity();

    void navToGameBoardScreenActivity();
}
