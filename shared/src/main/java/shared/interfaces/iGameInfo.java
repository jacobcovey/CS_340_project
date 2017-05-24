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

}
