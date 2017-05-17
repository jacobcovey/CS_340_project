package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameListView;
import com.example.jacobcovey.model.ClientPresenterFacade;

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
    public void joinGame() {

        gameListView.navToGameLobbyScreenActivity();
    }

    @Override
    public void update() {

    }

}
