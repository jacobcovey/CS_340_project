package shared.classes;

import java.util.HashSet;
import java.util.Set;

import static shared.constants.Constants.NUMBEROFTRAINCARS;

/**
 * Created by billrichards on 5/24/17.
 */

public class Player {

    private PlayerColors color;
    private int trainCars;
    private Set<TrainCard> trainCards = new HashSet<TrainCard>();
    private Set<DestinationCard> destinationCards = new HashSet<DestinationCard>();
    private Set<DestinationCard> drawnDestinationCards;
    private int points;
    private String userName;

    public Player(PlayerColors color, Set<TrainCard> trainCards, String userName) {
        this.color = color;
        this.trainCars = NUMBEROFTRAINCARS;
        this.trainCards = trainCards;
        this.destinationCards = new HashSet<DestinationCard>();
        this.points = 0;
        this.userName = userName;
    }

    public Player(PlayerColors color, int trainCars, Set<TrainCard> trainCards, Set<DestinationCard> destinationCards, int points, String userName) {
        this.color = color;
        this.trainCars = trainCars;
        this.trainCards = trainCards;
        this.destinationCards = destinationCards;
        this.points = points;
        this.userName = userName;
    }

    public PlayerColors getColor() {
        return color;
    }

    public void setColor(PlayerColors color) {
        this.color = color;
    }

    public int getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(int trainCars) {
        this.trainCars = trainCars;
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
}
