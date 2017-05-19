package com.example.jacobcovey.Presenters;

import android.os.AsyncTask;

import com.example.jacobcovey.Views.IGameLobbyView;
import com.example.jacobcovey.model.ClientModelRoot;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.classes.Game;
import shared.classes.GameRequest;
import shared.classes.User;

/**
 * Created by jacobcovey on 5/17/17.
 */

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    private IGameLobbyView gameLobbyView;

    private ClientPresenterFacade cpf;

    private Boolean viewCreated;

    public GameLobbyPresenter() {
        viewCreated = false;
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }

    @Override
    public void setGameLobbyView(IGameLobbyView gameLobbyView) {
        this.gameLobbyView = gameLobbyView;
    }

    @Override
    public void startGame() {
        Game currentGame = cpf.getGame();

        try {
            cpf.startGame(currentGame);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        cpf.removeObserver(this);

        gameLobbyView.navToGameBoardScreenActivity();
    }

    @Override
    public void leaveGame() {
        gameLobbyView.navToGameListScreenActivity();
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

        if (currentGame.getPlayerLimit() == currentGame.getPlayers().size()){
            return true;
        }
        else {
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
    public void joinCurrentGame() {

        User currentUser = cpf.getUser();
        Game currentGame = cpf.getGame();

        GameRequest gameRequest = new GameRequest(currentUser,currentGame);

        joinGameRequest joinGame = new joinGameRequest();
        joinGame.execute(gameRequest);

    }

    @Override
    public void setViewCreated(Boolean viewCreated) {
        this.viewCreated = viewCreated;
    }

    @Override
    public void setToCurrentState() {
        cpf.setState(ClientModelRoot.State.GAMELOBBY);
    }

    private class joinGameRequest extends AsyncTask<GameRequest, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(GameRequest... params) {
            try {
                cpf.joinGame(params[0]);
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
//                cpf.notifyObservers();
                cpf.setState(ClientModelRoot.State.GAMELOBBY);
            }
        }
    }
}
