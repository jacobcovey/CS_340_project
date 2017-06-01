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

public class PickFaceUpCard implements iCommand {
    TrainCard data;
    String gameId;
    String userName;

    public List<CommandData> execute() {
        List<TrainCard> faceUpCards = ServerFacade._instance.getGameInfo(gameId).getFaceUpTrainCardDeck();
        List<TrainCard> faceDownCards = ServerFacade._instance.getGameInfo(gameId).getFaceDownTrainCardDeck();
        TrainCard cardDrawn;
        int index = -1;
        for (int i = 0; i < faceUpCards.size(); i++) {
            if (faceUpCards.get(i).getId() == data.getId()) {
                index = i;
                i = faceUpCards.size();
            }
        }
        cardDrawn = faceUpCards.get(index);
        TrainCard newCard = faceDownCards.get(0);
        ServerFacade._instance.getGameInfo(gameId).getFaceDownTrainCardDeck().remove(0);
        ServerFacade._instance.getGameInfo(gameId).getFaceUpTrainCardDeck().set(index, newCard);

        List<Player> players = ServerFacade._instance.getGameInfo(gameId).getPlayers();
        for (Player player : players) {
            if (player.getUserName() == userName) {
                player.getTrainCards().add(cardDrawn);
            }
        }
        ServerFacade._instance.getGameInfo(gameId).setTrainCardDeckSize(ServerFacade._instance.getGameInfo(gameId).getFaceDownTrainCardDeck().size());
        ServerFacade._instance.addCommandToUser(new CommandData(CommandData.Type.FACEUPTRAINCARDPICKED, cardDrawn), userName);

        for (Player player : players) {
            ServerFacade._instance.addCommandToUser((new CommandData(CommandData.Type.UPDATEFACEUPTRAINCARDDECK, faceUpCards)), player.getUserName());
        }
        ArrayList<CommandData> dList = new ArrayList<>();

        if (cardDrawn != null) {
            CommandData successCmd = new CommandData(CommandData.Type.FACEUPTRAINCARDPICKED, cardDrawn);
            dList.add(successCmd);
            successCmd = new CommandData(CommandData.Type.UPDATEFACEUPTRAINCARDDECK, faceUpCards);
            dList.add(successCmd);
        } else {
            CommandData unSuccessCmd = new CommandData(CommandData.Type.ERROR, "FAILED TO PICK FACE UP CARDS");
            dList.add(unSuccessCmd);
        }
        return dList;
    }
}
