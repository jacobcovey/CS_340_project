package server.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.classes.TrainCard;
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
            if (player.getUserName() == userName) {
               currentPlayer = player;
            }
        }
        Set<DestinationCard> drawnCards = currentPlayer.getDrawnDestinationCards();
        Set<DestinationCard> pickedCards = data;
        Set<DestinationCard> unPickedCards = new HashSet<>();
        boolean found;
        for (DestinationCard drawnCard : drawnCards) {
            found = false;
            for (DestinationCard pickedCard : pickedCards) {
                if (drawnCard.getId() == pickedCard.getId()) {
                    found = true;
                }
            }
            if (!found) {
                unPickedCards.add(drawnCard);
            }
        }
        ServerFacade._instance.getGameInfo(gameId).getDestinationCardDeck().addAll(unPickedCards);
        currentPlayer.addDestinationCards(pickedCards);

        ServerFacade._instance.addCommandToUser(new CommandData(CommandData.Type.DESTINATIONCARDSPICKED, data), userName);

        ArrayList<CommandData> dList = new ArrayList<>();

        if (pickedCards != null) {
            CommandData successCmd = new CommandData(CommandData.Type.PICKDESTINATIONCARDS, pickedCards);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO PICK DESTINATION CARDS");
            dList.add(unSuccessCmd);
        }
        return dList;
    }
}