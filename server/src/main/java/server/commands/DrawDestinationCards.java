package server.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.Game;
import shared.classes.GameRequest;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.User;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class DrawDestinationCards implements iCommand {
    String data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        Set<DestinationCard> cardsDrawn = new HashSet<>();


        cardsDrawn.add(ServerFacade._instance.getGameInfo(gameId).getDestinationCardDeck().get(0));
        ServerFacade._instance.getGameInfo(gameId).getDestinationCardDeck().remove(0);
        cardsDrawn.add(ServerFacade._instance.getGameInfo(gameId).getDestinationCardDeck().get(0));
        ServerFacade._instance.getGameInfo(gameId).getDestinationCardDeck().remove(0);
        cardsDrawn.add(ServerFacade._instance.getGameInfo(gameId).getDestinationCardDeck().get(0));
        ServerFacade._instance.getGameInfo(gameId).getDestinationCardDeck().remove(0);

        List<Player> players = ServerFacade._instance.getGameInfo(gameId).getPlayers();
        for (Player player : players) {
            if (player.getUserName().equals(userName)) {
                player.setDrawnDestinationCards(cardsDrawn);
            }
        }
        ArrayList<CommandData> dList = new ArrayList<>();

        if (cardsDrawn != null) {
            CommandData successCmd = new CommandData(CommandData.Type.DESTINATIONCARDDRAWN, cardsDrawn);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO DRAW DESTINATION CARDS");
            dList.add(unSuccessCmd);
        }
        return dList;

    }
}
