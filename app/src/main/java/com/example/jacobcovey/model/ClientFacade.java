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

    public ClientModelRoot.State getState() {
        return ClientModelRoot._instance.getCurrentState();
    }

    public void setState(ClientModelRoot.State state) {

        ClientModelRoot._instance.setCurrentState(state);

    }

    @Override
    public void setUser(User user) {

        ClientModelRoot._instance.setUser(user);
        ClientModelRoot._instance.notifyAll();

    }

    @Override
    public void setGameList(List<Game> gameList) {

        ClientModelRoot._instance.setGameList(gameList);
        ClientModelRoot._instance.notifyAll();

    }

    @Override
    public void setColor(String color) {

        ClientModelRoot._instance.setColor(color);
        ClientModelRoot._instance.notifyAll();

    }

    @Override
    public void setCurrentGame(Game game) {

        ClientModelRoot._instance.setCurrentGame(game);
        ClientModelRoot._instance.notifyAll();

    }

    public User getUser() {
        return ClientModelRoot._instance.getUser();
    }
}
