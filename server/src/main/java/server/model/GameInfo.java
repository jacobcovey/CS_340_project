package server.model;

/**
 * Created by Dylan on 5/25/2017.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shared.classes.Player;
import shared.classes.PlayerColors;
import shared.classes.TrainCardColors;
import shared.interfaces.iGameInfo;
import shared.classes.TrainCard;
import shared.classes.DestinationCard;

import static shared.classes.City.ATLANTA;
import static shared.classes.City.BOSTON;
import static shared.classes.City.CALGARY;
import static shared.classes.City.CHICAGO;
import static shared.classes.City.DALLAS;
import static shared.classes.City.DENVER;
import static shared.classes.City.DULUTH;
import static shared.classes.City.EL_PASO;
import static shared.classes.City.HELENA;
import static shared.classes.City.HOUSTON;
import static shared.classes.City.KANSAS_CITY;
import static shared.classes.City.LITTLE_ROCK;
import static shared.classes.City.LOS_ANGELES;
import static shared.classes.City.MIAMI;
import static shared.classes.City.MONTREAL;
import static shared.classes.City.NASHVILLE;
import static shared.classes.City.NEW_ORLEANS;
import static shared.classes.City.NEW_YORK;
import static shared.classes.City.OKLAHOMA_CITY;
import static shared.classes.City.PHOENIX;
import static shared.classes.City.PITTSBURG;
import static shared.classes.City.PORTLAND;
import static shared.classes.City.SALT_LAKE_CITY;
import static shared.classes.City.SANTA_FE;
import static shared.classes.City.SAN_FRANCISCO;
import static shared.classes.City.SAULT_ST_MARIE;
import static shared.classes.City.SEATTLE;
import static shared.classes.City.TORONTO;
import static shared.classes.City.VANCOUVER;
import static shared.classes.City.WINNIPEG;
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

    private List<TrainCard> faceUpTrainCardDeck = new ArrayList<>();
    private List<TrainCard>  faceDownTrainCardDeck = new ArrayList<>();
    private List<DestinationCard> destinationCardDeck = new ArrayList<>();

    public enum State {
        FIRST_TURN,
        NOT_FIRST_TURN,
        GAME_OVER
    }
    private State state;


    public GameInfo() {
        state = State.FIRST_TURN;
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
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(LOS_ANGELES), ServerModelRoot.getInstance().getCity(NEW_YORK), 21));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(DULUTH), ServerModelRoot.getInstance().getCity(HOUSTON), 8));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(SAULT_ST_MARIE), ServerModelRoot.getInstance().getCity(NASHVILLE), 8));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(NEW_YORK), ServerModelRoot.getInstance().getCity(ATLANTA), 6));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(PORTLAND), ServerModelRoot.getInstance().getCity(NASHVILLE), 17));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(VANCOUVER), ServerModelRoot.getInstance().getCity(MONTREAL), 20));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(DULUTH), ServerModelRoot.getInstance().getCity(EL_PASO), 10));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(TORONTO), ServerModelRoot.getInstance().getCity(MIAMI), 10));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(PORTLAND), ServerModelRoot.getInstance().getCity(PHOENIX), 11));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(DALLAS), ServerModelRoot.getInstance().getCity(NEW_YORK), 11));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(CALGARY), ServerModelRoot.getInstance().getCity(SALT_LAKE_CITY), 7));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(CALGARY), ServerModelRoot.getInstance().getCity(PHOENIX), 13));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(LOS_ANGELES), ServerModelRoot.getInstance().getCity(MIAMI), 20));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(WINNIPEG), ServerModelRoot.getInstance().getCity(LITTLE_ROCK), 11));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(SAN_FRANCISCO), ServerModelRoot.getInstance().getCity(ATLANTA), 17));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(KANSAS_CITY), ServerModelRoot.getInstance().getCity(HOUSTON), 5));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(LOS_ANGELES), ServerModelRoot.getInstance().getCity(CHICAGO), 16));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(DENVER), ServerModelRoot.getInstance().getCity(PITTSBURG), 11));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(CHICAGO), ServerModelRoot.getInstance().getCity(SANTA_FE), 9));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(VANCOUVER), ServerModelRoot.getInstance().getCity(SANTA_FE), 13));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(BOSTON), ServerModelRoot.getInstance().getCity(MIAMI), 12));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(CHICAGO), ServerModelRoot.getInstance().getCity(NEW_ORLEANS), 7));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(MONTREAL), ServerModelRoot.getInstance().getCity(ATLANTA), 9));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(SEATTLE), ServerModelRoot.getInstance().getCity(NEW_YORK), 22));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(DENVER), ServerModelRoot.getInstance().getCity(EL_PASO), 4));

        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(HELENA), ServerModelRoot.getInstance().getCity(LOS_ANGELES), 8));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(WINNIPEG), ServerModelRoot.getInstance().getCity(HOUSTON), 12));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(MONTREAL), ServerModelRoot.getInstance().getCity(NEW_ORLEANS), 13));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(SAULT_ST_MARIE), ServerModelRoot.getInstance().getCity(OKLAHOMA_CITY), 9));
        destinationCardDeck.add(new DestinationCard(String.valueOf(destinationId++),ServerModelRoot.getInstance().getCity(SEATTLE), ServerModelRoot.getInstance().getCity(LOS_ANGELES), 9));

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

    public void initializeRoutes() {

    }

    public void addPlayer(String userName, PlayerColors color) {
        Set<TrainCard> dealtCards = new HashSet<>();
        dealtCards.add(faceDownTrainCardDeck.get(0));
        faceDownTrainCardDeck.remove(0);
        dealtCards.add(faceDownTrainCardDeck.get(0));
        faceDownTrainCardDeck.remove(0);
        dealtCards.add(faceDownTrainCardDeck.get(0));
        faceDownTrainCardDeck.remove(0);
        dealtCards.add(faceDownTrainCardDeck.get(0));
        faceDownTrainCardDeck.remove(0);
        getPlayers().add(new Player(color, dealtCards, userName));
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }


}

