package server.model;

/**
 * Created by Dylan on 5/25/2017.
 */

import java.util.List;

import shared.classes.TrainCardColors;
import shared.interfaces.iGameInfo;
import shared.classes.TrainCard;
import shared.classes.DestinationCard;

public class GameInfo extends iGameInfo {
    private List<TrainCard> faceUpTrainCardDeck;
    private List<TrainCard>  faceDownTrainCardDeck;
    private List<DestinationCard> destinationCardDeck;

    public static GameInfo _instance = new GameInfo();

    private GameInfo() {}

    public List<TrainCard> getFaceUpTrainCardDeck() {
        return faceUpTrainCardDeck;
    }

    public List<TrainCard> getFaceDownTrainCardDeck() {
        return faceDownTrainCardDeck;
    }

    public List<DestinationCard> getDestinationCardDeck() {
        return destinationCardDeck;
    }

    public TrainCard drawFaceDownCard() {
        TrainCard drawnCard = faceDownTrainCardDeck.get(0);
        faceDownTrainCardDeck.remove(0);
        return drawnCard;
    }
    public TrainCard pickFaceUpCard(TrainCardColors color) {
        TrainCard cardDrawn = null;
        int index = -1;
        for (int i = 0; i < faceUpTrainCardDeck.size(); i++) {
            cardDrawn = faceUpTrainCardDeck.get(i);
            if (cardDrawn.getColor() == color) {
                index = i;
                i = faceUpTrainCardDeck.size();
            }
        }
        cardDrawn = faceUpTrainCardDeck.get(index);
        faceUpTrainCardDeck.remove(index);
        return cardDrawn;
    }

    public List<DestinationCard> drawDestinationCards() {
        List<DestinationCard> drawnCards = null;
        drawnCards.add(destinationCardDeck.get(0));
        destinationCardDeck.remove(0);
        drawnCards.add(destinationCardDeck.get(0));
        destinationCardDeck.remove(0);
        drawnCards.add(destinationCardDeck.get(0));
        destinationCardDeck.remove(0);
        return drawnCards;
    }
}

