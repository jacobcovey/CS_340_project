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
import shared.classes.City;
import shared.classes.DestinationCard;
import shared.classes.Game;
import shared.classes.Player;
import shared.classes.PlayerColors;
import shared.classes.Route;
import shared.classes.TrainCard;
import shared.classes.Turn;
import shared.classes.User;
import shared.interfaces.iGameInfo;


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

    public void destinationCardCompleted(DestinationCard destinationCard, String userName) {

        List<Route> playerRoutes = new ArrayList<Route>();

        for(Route route: getRoutes() ) {
            if (route.getPlayer().getUserName() == userName) {
                playerRoutes.add(route);
            }
        }
        boolean connectedToCityOne = false;
        boolean connectedToCityTwo = false;

        for (Route route: playerRoutes) {
            if (destinationCard.getCity1() == route.getCity1() || destinationCard.getCity1() == route.getCity2()) {
                connectedToCityOne = true;
            } else if (destinationCard.getCity2() == route.getCity1() || destinationCard.getCity2() == route.getCity2()){
                connectedToCityTwo = true;
            }
        }

        if (connectedToCityOne && connectedToCityTwo) {
            for (Route route: playerRoutes) {
                if (route.getCity1().isEqual(destinationCard.getCity1())) {
                    dcCompletedHelper(route.getCity2(),playerRoutes, route, destinationCard);
                }
                else if (route.getCity2().isEqual(destinationCard.getCity1())) {
                    dcCompletedHelper(route.getCity1(),playerRoutes, route, destinationCard);
                }
            }
        }
    }

    public void dcCompletedHelper(City city, List<Route> pr, Route route, DestinationCard dc) {
        if (dc.isComplete() == true)
            return;
        if (route.getCity1().isEqual(dc.getCity2()) || route.getCity2().isEqual(dc.getCity2())) {
            dc.setComplete(true);
            return;
        }
        for (Route thisRoute : pr) {
            if (thisRoute.isEqual(route)) {
                break;
            }
            else if (thisRoute.getCity1().isEqual(city)) {
                dcCompletedHelper(thisRoute.getCity2(),pr,thisRoute, dc);
            }
            else if (thisRoute.getCity2().isEqual(city)) {
                dcCompletedHelper(thisRoute.getCity1(),pr,thisRoute, dc);
            }
        }
    }
}

