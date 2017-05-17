package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameLobbyView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.util.ArrayList;
import java.util.List;

import shared.classes.Game;
import shared.classes.User;

/**
 * Created by jacobcovey on 5/17/17.
 */

public class GameLobbyPresenter implements IGameLobbyPresenter {

    private IGameLobbyView gameLobbyView;

    private ClientPresenterFacade cpf;


    @Override
    public void setGameLobbyView(IGameLobbyView gameLobbyView) {
        this.gameLobbyView = gameLobbyView;
    }

    @Override
    public void startGame() {
        Game currentGame = cpf.getGame();

        cpf.startGame(currentGame);

        gameLobbyView.navToGameBoardScreenActivity();
    }

    @Override
    public void leaveGame() {
        gameLobbyView.navToGameListScreenActivity();
    }

    @Override
    public void update() {
        if (checkIfGameFull()) {
            startGame();
        }
        else {
            Game currentGame = cpf.getGame();

            gameLobbyView.setGameName(currentGame.getName());
            gameLobbyView.setGameCreator(currentGame.getOwner().getUsername());

            List<User> users = currentGame.getPlayers();
            List<String> names = new ArrayList<String>();

            for (User user : users) {
                names.add(user.getUsername());
            }

            gameLobbyView.setPlayerList(names);
            gameLobbyView.hideUnusedPlayers(users.size());
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
}
