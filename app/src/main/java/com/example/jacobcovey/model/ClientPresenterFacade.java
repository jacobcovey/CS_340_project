package com.example.jacobcovey.model;

import com.example.jacobcovey.communication.ServerProxy;

import java.util.List;
import java.util.Observer;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.classes.User;

/**
 * Created by billrichards on 5/15/17.
 */

public class ClientPresenterFacade {

    public void login(User user) {
        
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.LOGIN, user));

    }

    public void register(User user) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.REGISTER, user));

    }

    public void joinGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.JOINGAME, game));

    }

    public void createGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.CREATEGAME, game));

    }

    public void startGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.STARTGAME, game));

    }

    public void leaveGame(Game game) {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.LEAVEGAME, game));

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
