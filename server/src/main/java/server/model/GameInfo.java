package server.model;

/**
 * Created by Dylan on 5/25/2017.
 */

import com.sun.org.apache.regexp.internal.RE;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import shared.classes.City;
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
        Collections.shuffle(faceDownTrainCardDeck);
        for (int i = 0; i < 5; i++) {
            faceUpTrainCardDeck.add(faceDownTrainCardDeck.get(0));
            faceDownTrainCardDeck.remove(0);
        }

        int destinationId = 0;
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Los Angeles"), ServerModelRoot.getInstance().getCity("New York"), 21));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Duluth"), ServerModelRoot.getInstance().getCity("Houston"), 8));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Sault St. Marie"), ServerModelRoot.getInstance().getCity("Nashville"), 8));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("New York"), ServerModelRoot.getInstance().getCity("Atlanta"), 6));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Portland"), ServerModelRoot.getInstance().getCity("Nashville"), 17));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Vancouver"), ServerModelRoot.getInstance().getCity("Montreal"), 20));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Duluth"), ServerModelRoot.getInstance().getCity("El Paso"), 10));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Toronto"), ServerModelRoot.getInstance().getCity("Miami"), 10));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Portland"), ServerModelRoot.getInstance().getCity("Phoenix"), 11));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Dallas"), ServerModelRoot.getInstance().getCity("New York"), 11));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Calgary"), ServerModelRoot.getInstance().getCity("Salt Lake City"), 7));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Calgary"), ServerModelRoot.getInstance().getCity("Phoenix"), 13));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Los Angeles"), ServerModelRoot.getInstance().getCity("Miami"), 20));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Winnipeg"), ServerModelRoot.getInstance().getCity("Little Rock"), 11));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("San Francisco"), ServerModelRoot.getInstance().getCity("Atlanta"), 17));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Kansas City"), ServerModelRoot.getInstance().getCity("Houston"), 5));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Los Angeles"), ServerModelRoot.getInstance().getCity("Chicago"), 16));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Denver"), ServerModelRoot.getInstance().getCity("Pittsburgh"), 11));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Chicago"), ServerModelRoot.getInstance().getCity("Santa Fe"), 9));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Vancouver"), ServerModelRoot.getInstance().getCity("Santa Fe"), 13));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Boston"), ServerModelRoot.getInstance().getCity("Miami"), 12));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Chicago"), ServerModelRoot.getInstance().getCity("New Orleans"), 7));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Montreal"), ServerModelRoot.getInstance().getCity("Atlanta"), 9));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Seattle"), ServerModelRoot.getInstance().getCity("New York"), 22));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Denver"), ServerModelRoot.getInstance().getCity("El Paso"), 4));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Helena"), ServerModelRoot.getInstance().getCity("Los Angeles"), 8));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Winnipeg"), ServerModelRoot.getInstance().getCity("Houston"), 12));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Montreal"), ServerModelRoot.getInstance().getCity("New Orleans"), 13));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Sault St. Marie"), ServerModelRoot.getInstance().getCity("Oklahoma City"), 9));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity("Seattle"), ServerModelRoot.getInstance().getCity("Los Angeles"), 9));

        Collections.shuffle(destinationCardDeck);
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

