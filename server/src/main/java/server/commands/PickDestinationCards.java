package server.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.ServerFacade;
import server.model.GameInfo;
import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.Turn;
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
        ServerFacade._instance.getGameInfo(gameId).getDestinationCardDeck().addAll(unPickedCards);
        currentPlayer.addDestinationCards(pickedCards);
        currentPlayer.getDrawnDestinationCards().clear();

        GameInfo gameInfo = ServerFacade._instance.getGameInfo(gameId);
        boolean isNextPlayer = false;
        Player nextPlayer = null;
        for (Player player: players) {
            if (isNextPlayer) {
                nextPlayer = player;
            }
            if (player.getUserName().equals(currentPlayer.getUserName())) {
                isNextPlayer = true;
            }
        }
        if (nextPlayer == null) {
            nextPlayer = players.get(0);
            if (gameInfo.getState() == GameInfo.State.FIRST_TURN) {
                gameInfo.setState(GameInfo.State.NOT_FIRST_TURN);
            }
            gameInfo.setTurn(new Turn(nextPlayer.getUserName(), Turn.TurnState.BEGINNING));
        } else {
            if (gameInfo.getState() == GameInfo.State.FIRST_TURN) {
                gameInfo.setTurn(new Turn(nextPlayer.getUserName(), Turn.TurnState.FIRSTTURN));
            } else {
                gameInfo.setTurn(new Turn(nextPlayer.getUserName(), Turn.TurnState.BEGINNING));
            }
        }

        ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEGAMEINFO, gameInfo), gameId);
        String currentUserName = currentPlayer.getUserName();
        HistoryAction historyAction = new HistoryAction(currentUserName,
                currentUserName + " picked " + pickedCards.size() + " destination cards");
        gameInfo.getHistory().addAction(historyAction);
        ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEHISTORY, historyAction), gameId);

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
