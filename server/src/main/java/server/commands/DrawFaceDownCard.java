package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import server.model.GameInfo;
import shared.classes.CommandData;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.Turn;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class DrawFaceDownCard implements iCommand {
    String data;
    String gameId;
    String userName;

    public List<CommandData> execute() {

        GameInfo gameInfo = ServerFacade._instance.getGameInfo(gameId);
        TrainCard cardDrawn = gameInfo.drawFaceDownCard();
        List<Player> players = gameInfo.getPlayers();
        Player currentPlayer = null;
        for (Player player : players) {
            if (player.getUserName().equals(userName)) {
                player.addTrainCard(cardDrawn);
                currentPlayer = player;
            }
        }
        if (gameInfo.getTurn().getState() == Turn.TurnState.ONETRAINCARDSELECTED) {
            ServerFacade._instance.setNextTurn(gameInfo, currentPlayer);
        } else {
            gameInfo.getTurn().setState(Turn.TurnState.ONETRAINCARDSELECTED);
        }

        gameInfo.setTrainCardDeckSize(gameInfo.getFaceDownTrainCardDeck().size());
        ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEGAMEINFO, gameInfo), gameId);

        HistoryAction historyAction = new HistoryAction(userName, "drew a card from the face down deck");
        ServerFacade._instance.addHistoryItemToGame(historyAction, gameInfo, gameId);
        ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEGAMEINFO, gameInfo), gameId);
        ArrayList<CommandData> dList = new ArrayList<>();
        if (cardDrawn != null) {
            CommandData successCmd = new CommandData(CommandData.Type.FACEDOWNTRAINCARDPICKED, cardDrawn);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO DRAW FACE DOWN CARDS");
            dList.add(unSuccessCmd);
        }
        return dList;

    }
}
