package com.example.jacobcovey.Presenters;

import android.os.AsyncTask;

import com.example.jacobcovey.Views.iGameLobbyView;
import com.example.jacobcovey.model.ClientModelRoot;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.lang.String;

import shared.classes.Game;
import shared.classes.GameRequest;
import shared.classes.User;

/**
 * Created by jacobcovey on 5/17/17.
 */

public class GameLobbyPresenter implements iGameLobbyPresenter, Observer {

    private iGameLobbyView gameLobbyView;

    private ClientPresenterFacade cpf;

    private Boolean viewCreated;

    public GameLobbyPresenter() {
        viewCreated = false;
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }

    @Override
    public void setGameLobbyView(iGameLobbyView gameLobbyView) {
        this.gameLobbyView = gameLobbyView;
    }

    @Override
    public void startGame() {
        Game currentGame = cpf.getGame();

        startGameRequest startGame = new startGameRequest();
        startGame.execute(currentGame);
        cpf.removeObserver(this);
    }

    @Override
    public void setCurrentGame(String gameID) {
        List<Game> games = cpf.getGameList();
        for (Game game : games) {
            String compareId = game.getId();
            if (compareId.equals(gameID)) {
                cpf.setGame(game);
            }
        }
    }

    public boolean checkIfGameFull(){
        Game currentGame = cpf.getGame();

        try {
            if (currentGame.getPlayerLimit() == currentGame.getPlayers().size()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e ) {
            System.out.println("Current game is null");
            return false;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (checkIfGameFull()) {
            startGame();
        }
        else if (viewCreated) {
            Game currentGame = cpf.getGame();
            gameLobbyView.setGameName(currentGame.getName());
            gameLobbyView.setGameCreator(currentGame.getOwner().getUsername());

            List<User> users = currentGame.getPlayers();
            List<String> names = new ArrayList<String>();

            for (User user : users) {
                names.add(user.getUsername());
            }

            gameLobbyView.setPlayerList(names);
            gameLobbyView.hideUnusedPlayers(currentGame.getPlayerLimit());
        }
    }

    @Override
    public void setViewCreated(Boolean viewCreated) {
        this.viewCreated = viewCreated;
    }

    @Override
    public void setToCurrentState() {
        cpf.setState(ClientModelRoot.State.GAMELOBBY);
    }

    private class startGameRequest extends AsyncTask<Game, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Game... params) {
            try {
                cpf.startGame(params[0]);
            } catch (IOException e) {
                System.out.printf(e.getMessage());
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                cpf.setState(ClientModelRoot.State.GAMESTARTED);
                prepareStartGame();
                gameLobbyView.navToGameBoardScreenActivity();
            }
        }
    }

    private void prepareStartGame() {
        cpf.removeObserver(this);
    }

}
