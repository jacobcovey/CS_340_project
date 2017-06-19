package server.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.ServerFacade;
import shared.classes.GameInfo;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class PickDestinationCards implements iCommand {
    Set<DestinationCard> data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        List<Player> players = ServerFacade._instance.getGameInfo(gameId).getPlayers();
        Player currentPlayer = null;
        for (Player player : players) {
            if (player.getUserName().equals(userName)) {
               currentPlayer = player;
                break;
            }
        }
        Set<DestinationCard> drawnCards = currentPlayer.getDrawnDestinationCards();
        Set<DestinationCard> pickedCards = data;
        Set<DestinationCard> unPickedCards = new HashSet<>();
        boolean found;
        for (DestinationCard drawnCard : drawnCards) {
            found = false;
            for (DestinationCard pickedCard : pickedCards) {
                if (drawnCard.getId().equals(pickedCard.getId())) {
                    found = true;
                }
            }
            if (!found) {
                unPickedCards.add(drawnCard);
            }
        }
        GameInfo gameInfo = ServerFacade._instance.getGameInfo(gameId);
        gameInfo.getDestinationCardDeck().addAll(unPickedCards);
        gameInfo.setDestinationCarDeckSize(gameInfo.getDestinationCardDeck().size());
        currentPlayer.addDestinationCards(pickedCards);
        currentPlayer.getDrawnDestinationCards().clear();

        ServerFacade._instance.setNextTurn(gameInfo, currentPlayer);

        String currentUserName = currentPlayer.getUserName();
        HistoryAction historyAction = new HistoryAction(currentUserName, "picked " + pickedCards.size() + " destination card(s)");
        ServerFacade._instance.addHistoryItemToGame(historyAction, gameInfo, gameId);
        ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEGAMEINFO, gameInfo), gameId);

        ArrayList<CommandData> dList = new ArrayList<>();

        if (pickedCards != null) {
            CommandData successCmd = new CommandData(CommandData.Type.DESTINATIONCARDSPICKED, pickedCards);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO PICK DESTINATION CARDS");
            dList.add(unSuccessCmd);
        }
        return dList;
    }
}
