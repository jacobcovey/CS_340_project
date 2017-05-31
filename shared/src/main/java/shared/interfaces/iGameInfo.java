package shared.interfaces;

import java.util.List;

import shared.classes.Chat;
import shared.classes.History;
import shared.classes.Player;
import shared.classes.Route;
import shared.classes.Turn;

/**
 * Created by billrichards on 5/24/17.
 */

public abstract class iGameInfo {

    private List<Route> routes;
    private List<Player> players;
    private Chat chat;
    private History history;
    private Turn turn;

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Chat getChat() {
        return chat;
    }



    public History getHistory() {
        return history;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
