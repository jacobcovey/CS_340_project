package server.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.classes.Route;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class ClaimRoute implements iCommand {
    Route data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        ServerFacade serverFacade = ServerFacade._instance;

        List<Player> players = serverFacade.getGameInfo(gameId).getPlayers();
        Player currentPlayer = null;
        for (Player player : players) {
            if (player.getUserName() == userName) {
                currentPlayer = player;
            }
        }
        List<Route> routeList = serverFacade.getGameInfo(gameId).getRoutes();
        for (Route route : routeList) {
            if (route.isRoute(data)) {
                if (route.canClaim(currentPlayer)) {
                    route.claim(currentPlayer);
                }
            }
        }

        if (currentPlayer.getNumberOfTrains() > 3) {
            serverFacade.addCommandToGame(
                    new CommandData(CommandData.Type.NOTIFYLASTTURN, null), gameId);
        }

        serverFacade.addCommandToGame(
                new CommandData(CommandData.Type.ROUTECLAIMED, data), gameId);

        return new ArrayList<>();
    }
}
