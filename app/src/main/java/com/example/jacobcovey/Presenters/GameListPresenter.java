package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameListView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.classes.Game;

/**
 * Created by jacobcovey on 5/15/17.
 */

public class GameListPresenter implements IGameListPresenter, Observer {

    private IGameListView gameListView;

    private ClientPresenterFacade cpf;



    public GameListPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }

    @Override
    public void setGameListView(IGameListView gameListView) {
        this.gameListView = gameListView;
    }

    @Override
    public void setUpGame() {
        cpf.removeObserver(this);
        gameListView.navToGameOptionsScreenActivity();
    }

    @Override
    public void joinGame(Game game) {
        cpf.joinGame(game);
        cpf.removeObserver(this);
        gameListView.navToGameLobbyScreenActivity();
    }

    @Override
    public List<Game> getGames() {
        return cpf.getGameList();
    }

    @Override
    public void update(Observable o, Object arg) {
        List<Game> games = cpf.getGameList();
        gameListView.updateGameList(games);
    }
}
