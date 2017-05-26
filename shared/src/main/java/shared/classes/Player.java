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
    private Set<TrainCard> trainCards;
    private Set<DestinationCard> destinationCards;
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
}
