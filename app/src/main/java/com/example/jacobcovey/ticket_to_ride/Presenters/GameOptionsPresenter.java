package com.example.jacobcovey.ticket_to_ride.Presenters;

import com.example.jacobcovey.ticket_to_ride.Views.IGameOptionsView;

/**
 * Created by jacobcovey on 5/16/17.
 */

public class GameOptionsPresenter implements IGameOptionsPresenter {

    private IGameOptionsView gameOptionsView;

    @Override
    public void setGameOptionsView(IGameOptionsView gameOptionsView) {
        this.gameOptionsView = gameOptionsView;
    }

    @Override
    public void createGame() {
        String gameName = gameOptionsView.getGameName();
        String numberOfPlayers =  gameOptionsView.getNumPlayers();

        System.out.println("Game Name: " + gameName);
        System.out.println("Number of Players: " + numberOfPlayers);
    }
}
