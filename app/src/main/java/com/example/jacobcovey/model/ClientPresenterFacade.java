package com.example.jacobcovey.model;

import com.example.jacobcovey.communication.ServerProxy;

import java.io.IOException;
import java.util.List;
import java.util.Observer;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.classes.GameRequest;
import shared.classes.User;

/**
 * Created by billrichards on 5/15/17.
 */

public class ClientPresenterFacade {

    public void login(User user) throws IOException {
        
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.LOGIN, user));

    }

    public void register(User user) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.REGISTER, user));

    }

    public void joinGame(Game game, User user) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.JOINGAME, new GameRequest(user, game)));

    }

    public void createGame(Game game, User user) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.CREATEGAME, new GameRequest(user, game)));

    }

    public void startGame(Game game) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.STARTGAME, game));

    }

    public void leaveGame(Game game, User user) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.LEAVEGAME, new GameRequest(user, game)));

    }

    public List<Game> getGameList() {
        return ClientModelRoot._instance.getGameList();
    }

    public Game getGame() {
        return ClientModelRoot._instance.getCurrentGame();
    }

    public void setGame(Game game) {ClientModelRoot._instance.setCurrentGame(game);}

    public User getUser() {
        return ClientModelRoot._instance.getUser();
    }

    public void setState(ClientModelRoot.State state) {
        ClientModelRoot._instance.setCurrentState(state);
    }

    public void addObserver(Observer observer) {

        ClientModelRoot._instance.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        ClientModelRoot._instance.deleteObserver(observer);
    }

}
