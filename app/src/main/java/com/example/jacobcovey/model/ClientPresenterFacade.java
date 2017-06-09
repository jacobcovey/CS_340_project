package com.example.jacobcovey.model;

import com.example.jacobcovey.communication.ServerProxy;
import com.example.jacobcovey.gamestates.YourTurn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import shared.classes.Chat;
import shared.classes.ChatMessage;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.Game;
import shared.classes.GameRequest;
import shared.classes.History;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.classes.Route;
import shared.classes.TrainCard;
import shared.classes.TrainCardColors;
import shared.classes.Turn;
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

    public void joinGame(GameRequest gameRequest) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.JOINGAME, gameRequest));

    }

    public void createGame(GameRequest gameRequest) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.CREATEGAME, gameRequest));

    }

    public void startGame(Game game) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.STARTGAME, game, ClientModelRoot._instance.getCurrentGame().getId(), ClientModelRoot._instance.getUser().getUsername()));

    }

    public void leaveGame(GameRequest gameRequest) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.LEAVEGAME, gameRequest));

    }

    public void pickFaceUpCard(TrainCard card) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.PICKFACEUPCARD, card, ClientModelRoot._instance.getCurrentGame().getId(), ClientModelRoot._instance.getUser().getUsername()));

    }

    public void drawFaceDownCard() throws IOException {
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.DRAWFACEDOWNCARD, "Draw Face Down Card", ClientModelRoot._instance.getCurrentGame().getId(), ClientModelRoot._instance.getUser().getUsername()));

    }

    public void drawDestinationCards() throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.DRAWDESTINATIONCARDS, "Draw Destination Card", ClientModelRoot._instance.getCurrentGame().getId(), ClientModelRoot._instance.getUser().getUsername()));

    }

    public void destinationCardsPicked(DestinationCard[] cardsPicked) throws IOException {
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.PICKDESTINATIONCARDS, cardsPicked, ClientModelRoot._instance.getCurrentGame().getId(), ClientModelRoot._instance.getUser().getUsername()));
    }

    public void sendChatMessage(ChatMessage chat) throws  IOException {
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.SENDCHAT, chat, ClientModelRoot._instance.getCurrentGame().getId(), ClientModelRoot._instance.getUser().getUsername()));
    }
    public void sendHistoryAction(HistoryAction action) throws  IOException {
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.SENDHISTORY, action, ClientModelRoot._instance.getCurrentGame().getId(), ClientModelRoot._instance.getUser().getUsername()));
    }
    public void claimRoute(Route route) throws IOException {
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.CLAIMROUTE, route, ClientModelRoot._instance.getCurrentGame().getId(), ClientModelRoot._instance.getUser().getUsername()));
    }

    public Chat getChat() { return ClientFacade._instance.getChat(); }
    public History getHistory() { return ClientFacade._instance.getHistory(); }

    public List<Player> getPlayers() { return  ClientModelRoot._instance.getGameInfo().getPlayers(); }

    public Player getCurrentPlayer() {
        return  ClientModelRoot._instance.getPlayer();
    }

    public List<com.example.jacobcovey.game_board.Route> getRoutes() {
        return ClientModelRoot._instance.getRoutes();

    }

    public Set<DestinationCard> getDestinationCards() {
        return ClientModelRoot._instance.getDestinationCards();
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

    public GameInfo getGameInfo() { return ClientModelRoot._instance.getGameInfo(); }


    public void addObserver(Observer observer) {
        ClientModelRoot._instance.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        ClientModelRoot._instance.deleteObserver(observer);
    }

    public TrainCard[] getFaceUpDeck() {
        return ClientModelRoot._instance.getFaceUpDeck();
    }

    public boolean isMyTurn() {
        Player player = ClientModelRoot._instance.getPlayer();
        Turn turn = ClientModelRoot._instance.getTurn();
        if (player != null  && turn != null) {
            return turn.getPlayer().equals(player.getUserName());
        }
        return false;
    }

    public Turn getTurn() {
        return ClientModelRoot._instance.getTurn();
    }

    public DestinationCard[] getDestCardsToSelectFrom() {
        return ClientModelRoot._instance.getDestCardsToSelectFrom();
    }

    public void clearDestCardsToSelectFrom() {
        ClientModelRoot._instance.setDestCardsToSelectFrom(null);
    }

    public void setRoutes(List<com.example.jacobcovey.game_board.Route> routes) {
        ClientModelRoot._instance.setRoutes(routes);
    }

    public boolean isTrainCardTurn() {
        Turn.TurnState state = getTurn().getState();
        return (state == Turn.TurnState.BEGINNING || state == Turn.TurnState.ONETRAINCARDSELECTED);
    }

    public List<TrainCard> getTrainCardsOfColor(int number, TrainCardColors color) {
        Player player = ClientModelRoot._instance.getPlayer();
        List<TrainCard> returnCards = new ArrayList<>();
        if (number == 0) {
            return returnCards;
        }
        for (TrainCard card : player.getTrainCards()) {
            if (card.getColor() == color) {
                returnCards.add(card);
                if (returnCards.size() == number) {
                    break;
                }
            }
        }
        if (returnCards.size() == number) {
            return returnCards;
        }
        return new ArrayList<TrainCard>();
    }
}
