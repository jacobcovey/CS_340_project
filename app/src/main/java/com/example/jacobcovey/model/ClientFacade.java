package com.example.jacobcovey.model;

import java.util.List;

import shared.classes.Game;
import shared.classes.User;
import shared.interfaces.iClient;

/**
 * Created by billrichards on 5/15/17.
 */

public class ClientFacade implements iClient {

    public static ClientFacade _instance = new ClientFacade();

    private ClientFacade() {}

    @Override
    public void setUser(User user) {

        ClientModelRoot._instance.setUser(user);

    }

    @Override
    public void setGameList(List<Game> gameList) {

        ClientModelRoot._instance.setGameList(gameList);

    }

    @Override
    public void setColor(String color) {

        ClientModelRoot._instance.setColor(color);

    }

    @Override
    public void setCurrentGame(Game game) {

        ClientModelRoot._instance.setCurrentGame(game);

    }

}
