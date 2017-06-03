package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import server.model.GameInfo;
import shared.classes.CommandData;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.TrainCardColors;
import shared.classes.Turn;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class PickFaceUpCard implements iCommand {
    TrainCard data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        GameInfo gameInfo = ServerFacade._instance.getGameInfo(gameId);
        Turn.TurnState turnState = gameInfo.getTurn().getState();
        TrainCard cardDrawn = null;
        if (turnState != Turn.TurnState.ONETRAINCARDSELECTED && data.getColor() != TrainCardColors.WILD) {
            cardDrawn = gameInfo.pickFaceUpCard(data);
            if (cardDrawn != null) {
                List<Player> players = ServerFacade._instance.getGameInfo(gameId).getPlayers();
                Player currentPlayer = null;
                for (Player player : players) {
                    if (player.getUserName().equals(userName)) {
                        player.addTrainCard(cardDrawn);
                        currentPlayer = player;
                    }
                }
                if (cardDrawn.getColor() == TrainCardColors.WILD || turnState == Turn.TurnState.ONETRAINCARDSELECTED) {
                    ServerFacade._instance.setNextTurn(gameInfo, currentPlayer);
                } else {
                    gameInfo.getTurn().setState(Turn.TurnState.ONETRAINCARDSELECTED);
                }
                gameInfo.setTrainCardDeckSize(ServerFacade._instance.getGameInfo(gameId).getFaceDownTrainCardDeck().size());

                ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEFACEUPTRAINCARDDECK, gameInfo.getFaceUpTrainCardDeck()), gameId);
                ServerFacade._instance.addCommandToUser(new CommandData(CommandData.Type.FACEUPTRAINCARDPICKED, cardDrawn), userName);
                HistoryAction historyAction = new HistoryAction(userName, "picked a " + cardDrawn.getColorName() + " card from the face up deck");
                gameInfo.getHistory().addAction(historyAction);
                ServerFacade._instance.addCommandToGame(new CommandData(CommandData.Type.UPDATEHISTORY, historyAction), userName);
            }

        }
        ArrayList<CommandData> dList = new ArrayList<>();
        if (cardDrawn == null) {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO PICK FACE UP CARDS");
            dList.add(unSuccessCmd);
        }
        return dList;
    }
}
