package shared.classes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import shared.interfaces.iGameInfo;

import static shared.constants.Constants.NUMBEROFTRAINCARS;

/**
 * Created by billrichards on 5/24/17.
 */

public class Player {

    private PlayerColors color;
    private int numberOfTrains;
    private Set<TrainCard> trainCards = new HashSet<TrainCard>();
    private Set<DestinationCard> destinationCards = new HashSet<DestinationCard>();
    private Set<DestinationCard> drawnDestinationCards;
    private int points;
    private String userName;
    private boolean hasLongestRoad;
    private PlayerPoints playerPoints;

    public Player(PlayerColors color, Set<TrainCard> trainCards, String userName) {
        this.color = color;
        //this.numberOfTrains = NUMBEROFTRAINCARS;
        this.numberOfTrains = 5;
        this.trainCards = trainCards;
        this.destinationCards = new HashSet<DestinationCard>();
        this.points = 0;
        this.userName = userName;
        this.hasLongestRoad = false;
    }

    public Player(PlayerColors color, int numberOfTrains, Set<TrainCard> trainCards, Set<DestinationCard> destinationCards, int points, String userName) {
        this.color = color;
        this.numberOfTrains = numberOfTrains;
        this.trainCards = trainCards;
        this.destinationCards = destinationCards;
        this.points = points;
        this.userName = userName;
        this.hasLongestRoad = false;
    }

    public PlayerColors getColor() {
        return color;
    }

    public void setColor(PlayerColors color) {
        this.color = color;
    }

    public int getNumberOfTrains() {
        return numberOfTrains;
    }

    public void setNumberOfTrains(int numberOfTrains) {
        this.numberOfTrains = numberOfTrains;
    }

    public Set<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(Set<TrainCard> trainCards) {
        this.trainCards = trainCards;
    }

    public Set<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public void setDestinationCards(Set<DestinationCard> destinationCards) {
        this.destinationCards = destinationCards;
    }
    public Set<DestinationCard> getDrawnDestinationCards() {return drawnDestinationCards;}
    public void setDrawnDestinationCards(Set<DestinationCard> drawnDestinationCards) {this.drawnDestinationCards = drawnDestinationCards;}

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addTrainCard(TrainCard card) {
        trainCards.add(card);
    }

    public void addDestinationCards(Set<DestinationCard> cards) {
        for (DestinationCard card : cards) {
            destinationCards.add(card);
        }
    }
    public boolean getHasLongestRoad() {
        return hasLongestRoad;
    }
    public void setHasLongestRoad(boolean hasLongestRoad) {
        this.hasLongestRoad = hasLongestRoad;
    }
    public PlayerPoints getPlayerPoints() {
        return playerPoints;
    }
    public void addPlayerPoints() {
        playerPoints = new PlayerPoints(userName, points, hasLongestRoad, destinationCards);
    }
}
