package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameListView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.util.ArrayList;
import java.util.List;

import shared.classes.Game;
import shared.classes.User;

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
    public List<Game> getGames() {
        List<Game> games = new ArrayList<Game>();

        User user1 = new User("player1","pass");
        Game game1 = new Game("Game 1", 3, user1 );

        User user2 = new User("player2","pass");
        Game game2 = new Game("Game 2", 4, user2 );

        User user3 = new User("player3","pass");
        Game game3 = new Game("Game 3", 3, user3 );

        games.add(game1);
        games.add(game2);
        games.add(game3);

        return games;
//        return cpf.getGameList();
    }

    @Override
    public void update() {

    }

}
