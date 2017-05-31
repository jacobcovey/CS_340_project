package shared.interfaces;

import java.util.ArrayList;
import java.util.List;

import shared.classes.Chat;
import shared.classes.ChatMessage;
import shared.classes.History;
import shared.classes.Player;
import shared.classes.Route;
import shared.classes.Turn;

/**
 * Created by billrichards on 5/24/17.
 */

public abstract class iGameInfo {

    private List<Route> routes;
    private List<Player> players = new ArrayList<>();
    private Chat chat = new Chat(new ArrayList<ChatMessage>());
    private History history;
    private Turn turn;

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Chat getChat() { return chat; }

    public History getHistory() {
        return history;
    }

    public Turn getTurn() {
        return turn;
    }
}
