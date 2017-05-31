package com.example.jacobcovey.model;

import com.example.jacobcovey.communication.ServerProxy;

import java.io.IOException;
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
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.User;
import shared.interfaces.iGameInfo;
import shared.classes.Route;

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

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.STARTGAME, game));

    }

    public void leaveGame(GameRequest gameRequest) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.LEAVEGAME, gameRequest));

    }

    public void pickFaceUpCard(TrainCard card) throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.PICKFACEUPCARD, card));

    }

    public void drawFaceDownCard() throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.DRAWFACEDOWNCARD, "Give me a card please!"));

    }

    public void drawDestinationCards() throws IOException {

        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.DRAWDESTINATIONCARDS, "Give me some destinations to choose from!"));

    }

    public void destinationCardsPicked(List<DestinationCard> cardsPicked) throws IOException {
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.DESTINATIONCARDSPICKED, cardsPicked));
    }

    public void sendChatMessage(ChatMessage chat) throws  IOException {
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.SENDCHAT, chat));
    }
    public void claimRoute(Route route) throws IOException {
        ServerProxy._instance.executeCommand(new CommandData(CommandData.Type.CLAIMROUTE, route));
    }

    public Chat getChat() { return ClientModelRoot._instance.getGameInfo().getChat(); }
    public History getHistory() {
        return ClientModelRoot._instance.getGameInfo().getHistory();
    }

    public List<Player> getPlayers() { return  ClientModelRoot._instance.getGameInfo().getPlayers(); }

    public Player getCurrentPlayer() {
        return  ClientModelRoot._instance.getPlayer();
    }

    public Set<DestinationCard> getRouts() {
        return null;
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

    public TrainCard[] getFaceUpDeck() {
        return null;
    }

    public boolean isMyTurn() {
        Player player = ClientModelRoot._instance.getPlayer();
        iGameInfo info = ClientModelRoot._instance.getGameInfo();
        return info.getTurn().getPlayer().equals(player.getUserName());
    }

}
