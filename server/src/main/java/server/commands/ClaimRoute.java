package server.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.ServerFacade;
import server.model.GameInfo;
import shared.classes.ClaimRouteData;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.classes.Route;
import shared.classes.TrainCard;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class ClaimRoute implements iCommand {
    ClaimRouteData data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        ServerFacade serverFacade = ServerFacade._instance;
        GameInfo gameInfo = serverFacade.getGameInfo(gameId);
        int claimedRouteId = data.getRouteId();
        Route claimedRoute = gameInfo.getRouteById(claimedRouteId);
        List<TrainCard> trainCards = data.getTrainCards();

        List<Player> players = gameInfo.getPlayers();
        Player currentPlayer = null;
        for (Player player : players) {
            if (player.getUserName().equals(userName)) {
                currentPlayer = player;
            }
        }

        List<Route> routeList = gameInfo.getRoutes();
        Route currentRoute = null;
        for (Route route : routeList) {
            if (route.isRoute(claimedRoute)) {
                if (route.canClaim(currentPlayer, trainCards)) {
                    currentRoute = route;
                    gameInfo.addCardsToDiscardPile(new HashSet<TrainCard>(trainCards));
                    route.claim(currentPlayer, trainCards);
                    serverFacade.setNextTurn(gameInfo, currentPlayer);
                } else {
                    List<CommandData> ret = new ArrayList<>();
                    ret.add(new CommandData(CommandData.Type.ERROR, "Failed to claim route"));
                    return ret;
                }
            }
        }



        // Check if it should be the last round of turns
        if (currentPlayer.getNumberOfTrains() < 3 && !gameInfo.isLastTurn()) {
            serverFacade.setLastTurn(gameInfo, currentPlayer);
        }

        serverFacade.addCommandToGame(
                new CommandData(CommandData.Type.ROUTECLAIMED, currentRoute), gameId);
        HistoryAction action = new HistoryAction(userName, "claimed a route");
        serverFacade.addCommandToGame(
                new CommandData(CommandData.Type.SENDHISTORY, action), gameId);
        serverFacade.addCommandToGame(
                new CommandData(CommandData.Type.UPDATEGAMEINFO, gameInfo), gameId);

        Set<DestinationCard> playerDestinationCards = currentPlayer.getDestinationCards();
        for (DestinationCard card: playerDestinationCards) {
            gameInfo.destinationCardCompleted(card, currentPlayer.getUserName());
        }

        return new ArrayList<>();
    }
}
