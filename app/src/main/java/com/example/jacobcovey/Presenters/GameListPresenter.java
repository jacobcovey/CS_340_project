package com.example.jacobcovey.Presenters;

import android.os.AsyncTask;

import com.example.jacobcovey.Views.iGameListView;
import com.example.jacobcovey.model.ClientModelRoot;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.classes.Game;
import shared.classes.GameRequest;
import shared.classes.User;

/**
 * Created by jacobcovey on 5/15/17.
 */

public class GameListPresenter implements iGameListPresenter, Observer {

    private iGameListView gameListView;

    private ClientPresenterFacade cpf;



    public GameListPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }

    @Override
    public void setGameListView(iGameListView gameListView) {
        this.gameListView = gameListView;
    }

    @Override
    public void setUpGame() {
        cpf.removeObserver(this);
        gameListView.navToGameOptionsScreenActivity();
    }

    @Override
    public void joinGame(Game game) {
        User user = cpf.getUser();
        joinGameRequest joinGameRequest = new joinGameRequest();
        joinGameRequest.execute(new GameRequest(user, game));
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

    private class joinGameRequest extends AsyncTask<GameRequest, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(GameRequest... params) {
            try {
                cpf.joinGame(params[0]);
            } catch (IOException e) {
                System.out.printf(e.getMessage());
                gameListView.displayToast(e.getMessage());
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                removeObserver();
                cpf.setState(ClientModelRoot.State.GAMELOBBY);
                gameListView.navToGameLobbyScreenActivity();
            }
        }
    }

    private void removeObserver() {
        cpf.removeObserver(this);
    }

}
