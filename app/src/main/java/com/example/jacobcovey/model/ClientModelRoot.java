package com.example.jacobcovey.model;

/**
 * Created by Dylan on 5/15/2017.
 */
import com.example.jacobcovey.constants.Constants;
import com.example.jacobcovey.game_board.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import shared.classes.ChatMessage;
import shared.classes.DestinationCard;
import shared.classes.Game;
import shared.classes.HistoryAction;
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
        if (this.gameInfo == null) {
            this.gameInfo = new GameInfo();
            return;
        }
        this.gameInfo.setState(gameInfo.getState());
        this.gameInfo.setTurn(gameInfo.getTurn());
        this.gameInfo.setFaceUpTrainCardDeck(gameInfo.getFaceUpTrainCardDeck());
        this.gameInfo.setChat(gameInfo.getChat());
        this.gameInfo.setPlayers(gameInfo.getPlayers());
        this.gameInfo.setHistory(gameInfo.getHistory());
        this.gameInfo.setDestinationCarDeckSize(gameInfo.getDestinationCarDeckSize());
        this.gameInfo.setTrainCardDeckSize(gameInfo.getTrainCardDeckSize());
        setClientRoutesFromServerRoutes(gameInfo.getRoutes());
        setChanged();
        notifyObservers();
    }

    private void setClientRoutesFromServerRoutes(List<Route> routes) {
        List<Route> clientRoutes = this.gameInfo.getRoutes();
        Route clientRoute = null;
        Route route;
        for (int i = 0; i < routes.size(); i++) {
            route = routes.get(i);
            clientRoute = clientRoutes.get(i);
            if (route.isClaimed() && route.getPlayer() != null) {
                clientRoute.claim(route.getPlayer());
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        setChanged();
        notifyObservers();
    }

    public TrainCard[] getFaceUpDeck() {
        return faceUpDeck;
    }
    public void setFaceUpDeck(TrainCard[] faceUpDeck) {
        this.faceUpDeck = faceUpDeck;
        setChanged();
        notifyObservers();
    }

    public Turn getTurn() {
        if (gameInfo != null) {
            return gameInfo.getTurn();
        }
        return null;
    }

    public DestinationCard[] getDestCardsToSelectFrom() {
        return destCardsToSelectFrom;
    }

    public void setDestCardsToSelectFrom(DestinationCard[] destCardsToSelectFrom) {
        if (destCardsToSelectFrom == null) {
            player.setDrawnDestinationCards(new HashSet<DestinationCard>());
        } else {
            player.setDrawnDestinationCards(new HashSet<DestinationCard>(Arrays.asList(destCardsToSelectFrom)));
        }
        this.destCardsToSelectFrom = destCardsToSelectFrom;
        setChanged();
        notifyObservers();
    }
    public void addChatMessage(ChatMessage message) {
        gameInfo.getChat().addMessage(message);
        setChanged();
        notifyObservers();
    }
    public void addHistoryAction(HistoryAction action) {
        gameInfo.getHistory().addAction(action);
        setChanged();
        notifyObservers();
    }

    public List<Route> getRoutes() {
        return gameInfo.getRoutes();
    }

    public Set<DestinationCard> getDestinationCards() {
        if (player != null) {
            return player.getDestinationCards();
        }
        return null;
    }

    public void addTrainCardToPLayer(TrainCard trainCard) {
        this.player.addTrainCard(trainCard);
        setChanged();
        notifyObservers();
    }

    public void sendNotification() {
        setChanged();
        notifyObservers();
    }

    public void claimRouteById(int id, Player player) {
        Route route = gameInfo.getRouteById(id);
        route.claim(player);
        int compNumb = route.getCompanionRouteNumb();
        ClientPresenterFacade cpf = new ClientPresenterFacade();
        if (compNumb > 0 && cpf.getPlayers().size() < 4) {
            Route companionRoute = gameInfo.getRouteById(compNumb);
            companionRoute.setIsClaimed();
        }
    }

}
