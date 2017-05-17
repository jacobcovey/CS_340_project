package com.example.jacobcovey.ticket_to_ride.Views;

/**
 * Created by jacobcovey on 5/17/17.
 */

public interface IGameLobbyView {

    void setPlayerList();

    void addPlayerToList();

    void hideUnusedPlayers(int numGamePlayers);
}
