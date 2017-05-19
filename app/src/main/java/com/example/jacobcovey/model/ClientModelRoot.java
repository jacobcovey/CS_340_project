package com.example.jacobcovey.model;

/**
 * Created by Dylan on 5/15/2017.
 */
import java.util.List;
import java.util.Observable;

import shared.classes.Game;
import shared.classes.User;

public class ClientModelRoot extends Observable {

    public static ClientModelRoot _instance = new ClientModelRoot();

    public enum State {
        LOGIN,
        GAMELIST,
        GAMECREATION,
        GAMELOBBY,
        GAMESTARTED
    }

    private State currentState;
    private User user;
    private List<Game> gameList;
    private Game currentGame;
    private String color;

    private ClientModelRoot() {
        currentState = State.LOGIN;
    }

    public State getCurrentState() {
        return currentState;
    }
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Game> getGameList() {
        return gameList;
    }
    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
    public Game getCurrentGame() {
        return currentGame;
    }
    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
