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

    void login(User user) {
        
        ServerProxy._instance.executeCommand(new CommandData("login", user));

    }

    void register(User user) {

        ServerProxy._instance.executeCommand(new CommandData("register", user));

    }

    void joinGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData("joinGame", game));

    }

    void createGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData("createGame", game));

    }

    void startGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData("startGame", game));

    }

    void leaveGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData("leaveGame", game));

    }

    List<Game> getGameList() {
        return null;
    }

    Game getGame() {
        return null;
    }

    User getUser() {
        return null;
    }

}
