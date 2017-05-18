package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameListView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.util.List;

import shared.classes.Game;

/**
 * Created by jacobcovey on 5/15/17.
 */

public class GameListPresenter implements IGameListPresenter {

    private IGameListView gameListView;

    private ClientPresenterFacade cpf;

    public GameListPresenter() {
        cpf = new ClientPresenterFacade();
    }

    @Override
    public void setGameListView(IGameListView gameListView) {
        this.gameListView = gameListView;
    }

    @Override
    public void setUpGame() {
        gameListView.navToGameOptionsScreenActivity();
    }

    @Override
    public void joinGame(Game game) {
        cpf.joinGame(game);
        gameListView.navToGameLobbyScreenActivity();
    }

    @Override
    public List<Game> getGames() {
        return cpf.getGameList();
    }

    @Override
    public void update() {
        List<Game> games = cpf.getGameList();
        gameListView.updateGameList(games);
    }

}
