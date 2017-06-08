package shared.classes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    private Map<String, Integer> pointsInfo = new HashMap<>();
    private int points;
    private String userName;

    public Player(PlayerColors color, Set<TrainCard> trainCards, String userName) {
        this.color = color;
        this.numberOfTrains = NUMBEROFTRAINCARS;
        this.trainCards = trainCards;
        this.destinationCards = new HashSet<DestinationCard>();
        this.points = 0;
        this.userName = userName;
        pointsInfo.put("Claimed_Route_Points", 0);
        pointsInfo.put("Longest_Route_Points", 0);
        pointsInfo.put("Destinations_Reached_Points", 0);
        pointsInfo.put("Unreached_Destinations_Points", 0);
        pointsInfo.put("Total_Points", 0);
    }

    public Player(PlayerColors color, int numberOfTrains, Set<TrainCard> trainCards, Set<DestinationCard> destinationCards, int points, String userName) {
        this.color = color;
        this.numberOfTrains = numberOfTrains;
        this.trainCards = trainCards;
        this.destinationCards = destinationCards;
        this.points = points;
        this.userName = userName;
        pointsInfo.put("Claimed_Route_Points", 0);
        pointsInfo.put("Longest_Route_Points", 0);
        pointsInfo.put("Destinations_Reached_Points", 0);
        pointsInfo.put("Unreached_Destinations_Points", 0);
        pointsInfo.put("Total_Points", 0);
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
    public Map<String, Integer> getPointsInfo() {
        return pointsInfo;
    }
    public void addPointsInfo(String pointsCategory, Integer points) {
        pointsInfo.put(pointsCategory, points);
    }
}
