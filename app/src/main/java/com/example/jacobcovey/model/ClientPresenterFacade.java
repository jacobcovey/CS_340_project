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
        
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.LOGIN, user));

    }

    void register(User user) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.REGISTER, user));

    }

    void joinGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.JOINGAME, game));

    }

    void createGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.CREATEGAME, game));

    }

    void startGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.STARTGAME, game));

    }

    void leaveGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.LEAVEGAME, game));

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
