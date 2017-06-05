package com.example.jacobcovey.Views;

import java.util.List;

import shared.classes.Game;

/**
 * Created by jacobcovey on 5/15/17.
 */

public interface iGameListView {

    void setList(List<Object> list);

    void navToGameOptionsScreenActivity();

    void navToGameLobbyScreenActivity();

    void joinGame(Game game);

    void updateGameList(List<Game> games);

}
