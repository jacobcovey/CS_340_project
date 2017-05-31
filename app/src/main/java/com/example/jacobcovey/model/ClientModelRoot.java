package com.example.jacobcovey.model;

/**
 * Created by Dylan on 5/15/2017.
 */
import java.util.List;
import java.util.Observable;

import shared.classes.DestinationCard;
import shared.classes.Game;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.Turn;
import shared.classes.User;

public class ClientModelRoot extends Observable {

    public static ClientModelRoot _instance = new ClientModelRoot();

    public enum State {
        LOGIN,
        GAMELIST,
        GAMECREATION,
        GAMELOBBY,
        GAMESTARTED,
        GAMEINPLAY
    }

    private State currentState;
    private User user;
    private List<Game> gameList;
    private Game currentGame;
    private GameInfo gameInfo;
    private Player player;
    private TrainCard[] faceUpDeck;
    private DestinationCard[] destCardsToSelectFrom;

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
        setChanged();
        notifyObservers();
    }
    public List<Game> getGameList() {
        return gameList;
    }
    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
        setChanged();
        notifyObservers();
    }
    public Game getCurrentGame() {
        return currentGame;
    }
    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
        setChanged();
        notifyObservers();
    }
    public GameInfo getGameInfo() {
        return gameInfo;
    }
    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
        setChanged();
        notifyObservers();
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        setChanged();
        notifyObservers();
    }

    public void setColor(String color) {

    }
    public TrainCard[] getFaceUpDeck() {
        return faceUpDeck;
    }
    public void setFaceUpDeck(TrainCard[] faceUpDeck) {
        this.faceUpDeck = faceUpDeck;
    }

    public Turn getTurn() {
        return gameInfo.getTurn();
    }

    public DestinationCard[] getDestCardsToSelectFrom() {
        return destCardsToSelectFrom;
    }

    public void setDestCardsToSelectFrom(DestinationCard[] destCardsToSelectFrom) {
        this.destCardsToSelectFrom = destCardsToSelectFrom;
    }
}
