package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameOptionsView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;

import shared.classes.Game;
import shared.classes.User;

/**
 * Created by jacobcovey on 5/16/17.
 */

public class GameOptionsPresenter implements IGameOptionsPresenter {

    private IGameOptionsView gameOptionsView;

    private ClientPresenterFacade cpf;

    public GameOptionsPresenter() {
        cpf = new ClientPresenterFacade();
    }

    @Override
    public void setGameOptionsView(IGameOptionsView gameOptionsView) {
        this.gameOptionsView = gameOptionsView;
    }

    @Override
    public void createGame() {
        String gameName = gameOptionsView.getGameName();
        String numPlayers = gameOptionsView.getNumPlayers();
        int numberOfPlayers =  Integer.parseInt(numPlayers);

        User user = cpf.getUser();

        Game game = new Game(gameName,numberOfPlayers,user);

        try {
            cpf.createGame(game, user);
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
//
//        Game createdGame = cpf.getGame();
//
//        cpf.joinGame(createdGame);

        gameOptionsView.navToGameLobbyScreenActivity();
    }
}
