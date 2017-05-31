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
        List<Player> players = ServerFacade._instance.getGameInfo(gameId).getPlayers();
        Player currentPlayer = null;
        for (Player player : players) {
            if (player.getUserName() == userName) {
                currentPlayer = player;
            }
        }
        List<Route> routeList = ServerFacade._instance.getGameInfo(gameId).getRoutes();
        for (Route route : routeList) {
            if (route.isRoute(data)) {
                if (route.canClaim(currentPlayer)) {
                    route.claim(currentPlayer);
                }
            }
        }

        ServerFacade._instance.addCommandToUser(new CommandData(CommandData.Type.ROUTECLAIMED, data), userName);

        ArrayList<CommandData> dList = new ArrayList<>();

        if (routeList != null) {
            CommandData successCmd = new CommandData(CommandData.Type.CLAIMROUTE, routeList);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO CLAIM ROUTE CARDS");
            dList.add(unSuccessCmd);
        }
        return dList;
    }
}
