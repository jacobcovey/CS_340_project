package server.model;

/**
 * Created by Dylan on 5/25/2017.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.constants.Constants;
import shared.classes.Game;
import shared.classes.Player;
import shared.classes.PlayerColors;
import shared.classes.TrainCardColors;
import shared.classes.Turn;
import shared.classes.User;
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
    private Set<TrainCard> discardPile = new HashSet<>();
    private List<DestinationCard> destinationCardDeck = new ArrayList<>();

    public enum State {
        FIRST_TURN,
        NOT_FIRST_TURN,
        GAME_OVER
    }
    private State state;


    public GameInfo(Game game) {
        state = State.FIRST_TURN;
        faceDownTrainCardDeck.addAll(Constants.UNSHUFFLED_TRAINCARD_DECK);
        Collections.shuffle(faceDownTrainCardDeck);
        for (int i = 0; i < 5; i++) {
            faceUpTrainCardDeck.add(faceDownTrainCardDeck.get(0));
            faceDownTrainCardDeck.remove(0);
        }

        destinationCardDeck.addAll(Constants.UNSUFFLED_DESTINATION_DECK);
        Collections.shuffle(destinationCardDeck);

        setTrainCardDeckSize(faceDownTrainCardDeck.size());
        setDestinationCarDeckSize(destinationCardDeck.size());

        List<PlayerColors> colors = new ArrayList<>();
        colors.add(PlayerColors.BLUE);
        colors.add(PlayerColors.RED);
        colors.add(PlayerColors.GREEN);
        colors.add(PlayerColors.YELLOW);
        colors.add(PlayerColors.BLACK);
        List<User> users = game.getPlayers();
        for (int i = 0; i < users.size(); i++) {
            addPlayer(users.get(i).getUsername(), colors.get(i));
        }
        setTurn(new Turn(users.get(0).getUsername(), Turn.TurnState.FIRSTTURN));
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
        setTrainCardDeckSize(getTrainCardDeckSize() - 1);
        return drawnCard;
    }
    public TrainCard pickFaceUpCard(TrainCard card) {
        TrainCard cardDrawn = null;
        int index = -1;
        for (int i = 0; i < faceUpTrainCardDeck.size(); i++) {
            cardDrawn = faceUpTrainCardDeck.get(i);
            if (cardDrawn.getId().equals(card.getId())) {
                faceUpTrainCardDeck.set(i, faceDownTrainCardDeck.get(i));
                faceDownTrainCardDeck.remove(i);
                return cardDrawn;
            }
        }
        if (index != -1) {
            cardDrawn = faceUpTrainCardDeck.get(index);
            faceUpTrainCardDeck.remove(index);
            return cardDrawn;
        }
        return null;
    }

    public List<DestinationCard> drawDestinationCards() {
        List<DestinationCard> drawnCards = null;
        drawnCards.add(destinationCardDeck.get(0));
        destinationCardDeck.remove(0);
        drawnCards.add(destinationCardDeck.get(0));
        destinationCardDeck.remove(0);
        drawnCards.add(destinationCardDeck.get(0));
        destinationCardDeck.remove(0);
        setDestinationCarDeckSize(getDestinationCarDeckSize() - 3);
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

    public void addCardsToDiscardPile(Set<TrainCard> cards) {
        discardPile.addAll(cards);
    }


}

