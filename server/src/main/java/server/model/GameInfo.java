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
import server.constants.InitializeRoutes;
import shared.classes.City;
import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.classes.PlayerColors;
import shared.classes.Route;
import shared.classes.TrainCard;
import shared.classes.Turn;
import shared.classes.User;
import shared.interfaces.iGameInfo;

import static shared.classes.TrainCardColors.WILD;


public class GameInfo extends iGameInfo {

    private List<Route> routes = new ArrayList<>();
    private List<TrainCard> faceUpTrainCardDeck = new ArrayList<>();
    private List<TrainCard>  faceDownTrainCardDeck = new ArrayList<>();
    private Set<TrainCard> discardPile = new HashSet<>();
    private List<DestinationCard> destinationCardDeck = new ArrayList<>();
    private boolean isLastTurn;
    private Player playerToTakeLasTurn;

    public boolean isLastTurn() {
        return isLastTurn;
    }

    public Player getPlayerToTakeLasTurn() {
        return playerToTakeLasTurn;
    }

    public GameInfo(Game game) {
        setState(State.FIRST_TURN);
        faceDownTrainCardDeck.addAll(Constants.UNSHUFFLED_TRAINCARD_DECK);
        Collections.shuffle(faceDownTrainCardDeck);
        for (int i = 0; i < 5; i++) {
            faceUpTrainCardDeck.add(drawFaceDownCard());
        }
        checkWildCardsInFaceUpDeck();

        destinationCardDeck.addAll(Constants.UNSUFFLED_DESTINATION_DECK);
        Collections.shuffle(destinationCardDeck);

        setTrainCardDeckSize(faceDownTrainCardDeck.size());
        setDestinationCarDeckSize(destinationCardDeck.size());

        routes = new ArrayList<>(InitializeRoutes.initializeRoutes());

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
        isLastTurn = false;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
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
        if (faceDownTrainCardDeck.size() == 0) {
            faceDownTrainCardDeck.addAll(discardPile);
            discardPile.clear();
            Collections.shuffle(faceDownTrainCardDeck);
            if (faceDownTrainCardDeck.size() == 0) {
                return null;
            }
        }
        TrainCard drawnCard = faceDownTrainCardDeck.get(0);
        faceDownTrainCardDeck.remove(0);
        setTrainCardDeckSize(faceDownTrainCardDeck.size());
        return drawnCard;
    }
    public TrainCard pickFaceUpCard(TrainCard card) {
        TrainCard cardDrawn = null;
        for (int i = 0; i < faceUpTrainCardDeck.size(); i++) {
            cardDrawn = faceUpTrainCardDeck.get(i);
            if (cardDrawn.getId().equals(card.getId())) {
                faceUpTrainCardDeck.set(i, drawFaceDownCard());
                break;
            }
        }
        checkWildCardsInFaceUpDeck();
        return cardDrawn;
    }

    private void checkWildCardsInFaceUpDeck() {
        int wildCount = 0;
        for (TrainCard card : faceUpTrainCardDeck) {
            if (card.getColor() == WILD) {
                wildCount++;
            }
        }
        if (wildCount >= 3) {
            resetFaceUpDeck();
            checkWildCardsInFaceUpDeck();
        }
    }

    private void resetFaceUpDeck() {
        discardPile.addAll(faceUpTrainCardDeck);
        for (int i = 0; i < faceUpTrainCardDeck.size(); i++) {
            faceUpTrainCardDeck.set(i, drawFaceDownCard());
        }
    }

    public List<DestinationCard> drawDestinationCards() {
        List<DestinationCard> drawnCards = new ArrayList<>();
        drawnCards.add(destinationCardDeck.get(0));
        destinationCardDeck.remove(0);
        drawnCards.add(destinationCardDeck.get(0));
        destinationCardDeck.remove(0);
        drawnCards.add(destinationCardDeck.get(0));
        destinationCardDeck.remove(0);
        setDestinationCarDeckSize(destinationCardDeck.size());
        return drawnCards;
    }

    public void initializeRoutes() {

    }

    public void addPlayer(String userName, PlayerColors color) {
        Set<TrainCard> dealtCards = new HashSet<>();
        dealtCards.add(drawFaceDownCard());
        dealtCards.add(drawFaceDownCard());
        dealtCards.add(drawFaceDownCard());
        dealtCards.add(drawFaceDownCard());
        getPlayers().add(new Player(color, dealtCards, userName));
    }

    public void addCardsToDiscardPile(Set<TrainCard> cards) {
        discardPile.addAll(cards);
    }

    public void destinationCardCompleted(DestinationCard destinationCard, String userName) {

        List<Route> playerRoutes = new ArrayList<Route>();

        List<Route> routes = getRoutes();

        for(Route route: routes ) {
            Player player = route.getPlayer();
            if ( player != null) {
                Player compPlayer = route.getPlayer();
                String compName = compPlayer.getUserName();
                if (compName.equalsIgnoreCase(userName)) {
                    playerRoutes.add(route);
                }
            }
        }
        boolean connectedToCityOne = false;
        boolean connectedToCityTwo = false;

        for (Route route: playerRoutes) {
            if (destinationCard.getCity1().isEqual(route.getCity1()) || destinationCard.getCity1().isEqual(route.getCity2())) {
                connectedToCityOne = true;
            } else if (destinationCard.getCity2().isEqual(route.getCity1()) || destinationCard.getCity2().isEqual(route.getCity2())){
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
        for (Player player : getPlayers()) {
            if (player.getUserName().equals(userName)) {
                for (DestinationCard dc : player.getDestinationCards()) {
                    if (dc.getId().equals(destinationCard.getId())) {
                        if (destinationCard.isComplete()) {
                            dc.setComplete(true);
                        }
                    }
                }
            }
        }
    }

    public void dcCompletedHelper(City city, List<Route> pr, Route route, DestinationCard dc) {
        if (dc.isComplete())
            return;
        if (route.getCity1().isEqual(dc.getCity2()) || route.getCity2().isEqual(dc.getCity2())) {
            dc.setComplete(true);
            return;
        }
        for (Route thisRoute : pr) {
            if (thisRoute.isEqual(route)) {
                ;
            }
            else if (thisRoute.getCity1().isEqual(city)) {
                dcCompletedHelper(thisRoute.getCity2(), pr, thisRoute, dc);
            }
            else if (thisRoute.getCity2().isEqual(city)) {
                dcCompletedHelper(thisRoute.getCity1(), pr, thisRoute, dc);
            }
        }
    }


    public void setLastTurn(Player player) {
        isLastTurn = true;
        playerToTakeLasTurn = player;
        setState(State.LAST_TURN);
    }

    public Route getRouteById(int id) {
        if (routes == null) {
            routes = new ArrayList<>(InitializeRoutes.initializeRoutes());
        }
        for (Route route: routes) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }

    public void setRouteById(int id, Route route) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getId() == id) {
                routes.set(i, route);
                return;
            }
        }
    }


}

