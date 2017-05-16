package com.example.jacobcovey.model;

/**
 * Created by Dylan on 5/15/2017.
 */
import java.util.List;

import shared.classes.Game;
import shared.classes.User;

public class ClientModelRoot {
    private String currentState;
    private User user;
    private List<Game> gameList;
    private Game currentGame;
    private String color;

    public String getCurrentState() {
        return currentState;
    }
    public void setCurrentState(String currentState) {
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
