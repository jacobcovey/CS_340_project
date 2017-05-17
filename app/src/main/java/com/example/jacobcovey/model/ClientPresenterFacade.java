package com.example.jacobcovey.model;

import com.example.jacobcovey.communication.ServerProxy;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.classes.User;

/**
 * Created by billrichards on 5/15/17.
 */

public class ClientPresenterFacade {

    public void login(User user) {
        
        ServerProxy._instance.executeCommand(new CommandData("login", user));

    }

    public void register(User user) {

        ServerProxy._instance.executeCommand(new CommandData("register", user));

    }

    public void joinGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData("joinGame", game));

    }

    public void createGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData("createGame", game));

    }

    public void startGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData("startGame", game));

    }

    public void leaveGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData("leaveGame", game));

    }

    public List<Game> getGameList() {
        return null;
    }

    public Game getGame() {
        return null;
    }

    public User getUser() {
        return null;
    }

}
