package com.example.jacobcovey.model;

import java.util.List;
import java.util.Set;

import shared.classes.Chat;
import shared.classes.ChatMessage;
import shared.classes.DestinationCard;
import shared.classes.Game;
import shared.classes.History;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.User;
import shared.interfaces.iClient;
import shared.interfaces.iGameInfo;

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

    }

    @Override
    public void setGameList(List<Game> gameList) {

        ClientModelRoot._instance.setGameList(gameList);

    }

    @Override
    public void setCurrentGame(Game game) {

        ClientModelRoot._instance.setCurrentGame(game);

    }

    public Game getCurrentGame() {
        return ClientModelRoot._instance.getCurrentGame();
    }

    public User getUser() {
        return ClientModelRoot._instance.getUser();
    }


    public GameInfo getGameInfo() {
        return ClientModelRoot._instance.getGameInfo();
    }
    public Chat getChat() {
        return ClientModelRoot._instance.getGameInfo().getChat();
    }
    public History getHistory() {
        return ClientModelRoot._instance.getGameInfo().getHistory();
    }
    public Player getPlayer() {
        return ClientModelRoot._instance.getPlayer();
    }
    public void setPlayer(Player player) {
        ClientModelRoot._instance.setPlayer(player);
    }
    public void setGameInfo(GameInfo gameInfo) {
        ClientModelRoot._instance.setGameInfo(gameInfo);
    }
    public void addChatMessage(ChatMessage message) {
        ClientModelRoot._instance.addChatMessage(message);
    }
    public void addHistoryAction(HistoryAction action) {
        ClientModelRoot._instance.addHistoryAction(action);
    }
    public TrainCard[] getFaceUpDeck() {
        return ClientModelRoot._instance.getFaceUpDeck();
    }
    public void setFaceUpDeck(TrainCard[] faceUpDeck) {
        ClientModelRoot._instance.setFaceUpDeck(faceUpDeck);
    }
    public void setDrawnDestinationCards(DestinationCard[] drawnDestinationCards) {
        ClientModelRoot._instance.setDestCardsToSelectFrom(drawnDestinationCards);
    }

    public void addTrainCardToPlayer(TrainCard trainCard) {
        ClientModelRoot._instance.addTrainCardToPLayer(trainCard);
    }
}
