package server.commands;

import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.classes.CommandData;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class DrawFaceDownCard implements iCommand {
    String data;
    String gameId;
    String userName;

    public List<CommandData> execute() {

        TrainCard cardDrawn = ServerFacade._instance.getGameInfo(gameId).getFaceDownTrainCardDeck().get(0);
        ServerFacade._instance.getGameInfo(gameId).getFaceDownTrainCardDeck().remove(0);
        List<Player> players = ServerFacade._instance.getGameInfo(gameId).getPlayers();
        for (Player player : players) {
            if (player.getUserName() == userName) {
                player.getTrainCards().add(cardDrawn);
            }
        }
        ServerFacade._instance.getGameInfo(gameId).setTrainCardDeckSize(ServerFacade._instance.getGameInfo(gameId).getFaceDownTrainCardDeck().size());
        ServerFacade._instance.addCommandToUser(new CommandData(CommandData.Type.FACEDOWNTRAINCARDPICKED, cardDrawn), userName);
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
