package server.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.ServerFacade;
import shared.classes.GameInfo;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.classes.Turn;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class DrawDestinationCards implements iCommand {
    String data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        GameInfo gameInfo = ServerFacade._instance.getGameInfo(gameId);
        Set<DestinationCard> cardsDrawn = new HashSet<>(gameInfo.drawDestinationCards());
        List<Player> players = gameInfo.getPlayers();
        for (Player player : players) {
            if (player.getUserName().equals(userName)) {
                player.setDrawnDestinationCards(cardsDrawn);
            }
        }
        ArrayList<CommandData> dList = new ArrayList<>();

        if (cardsDrawn != null) {
            if (gameInfo.getTurn().getState() != Turn.TurnState.FIRSTTURN) {
                gameInfo.setTurn(new Turn(userName, Turn.TurnState.DESTINATIONCARDSDRAWN));
            }
            CommandData successCmd = new CommandData(CommandData.Type.DESTINATIONCARDDRAWN, cardsDrawn);
            dList.add(successCmd);
            ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEGAMEINFO, gameInfo), gameId);
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO DRAW DESTINATION CARDS");
            dList.add(unSuccessCmd);
        }
        return dList;

    }
}
