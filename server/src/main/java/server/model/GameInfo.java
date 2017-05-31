package server.model;

/**
 * Created by Dylan on 5/25/2017.
 */

import com.sun.org.apache.regexp.internal.RE;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import shared.classes.TrainCardColors;
import shared.interfaces.iGameInfo;
import shared.classes.TrainCard;
import shared.classes.DestinationCard;

import static shared.classes.TrainCardColors.BLACK;
import static shared.classes.TrainCardColors.BLUE;
import static shared.classes.TrainCardColors.GREEN;
import static shared.classes.TrainCardColors.ORANGE;
import static shared.classes.TrainCardColors.PURPLE;
import static shared.classes.TrainCardColors.RED;
import static shared.classes.TrainCardColors.WHITE;
import static shared.classes.TrainCardColors.WILD;
import static shared.classes.TrainCardColors.YELLOW;


public class GameInfo extends iGameInfo {
    private List<TrainCard> faceUpTrainCardDeck;
    private List<TrainCard>  faceDownTrainCardDeck;
    private List<DestinationCard> destinationCardDeck;

    public static GameInfo _instance = new GameInfo();

    private GameInfo() {
        Set<TrainCardColors> colors = new HashSet<TrainCardColors>();
        colors.add(WHITE);
        colors.add(BLACK);
        colors.add(RED);
        colors.add(ORANGE);
        colors.add(YELLOW);
        colors.add(GREEN);
        colors.add(BLUE);
        colors.add(PURPLE);
        int colorId = 0;
        for (TrainCardColors color : colors) {
            for (int i = 0; i < 12; i++) {
                faceDownTrainCardDeck.add(new TrainCard(String.valueOf(colorId), color));
                colorId++;
            }
        }
        for (int i = 0; i < 14; i++) {
            faceDownTrainCardDeck.add(new TrainCard(String.valueOf(colorId), WILD));
            colorId++;
        }

        
    }

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

