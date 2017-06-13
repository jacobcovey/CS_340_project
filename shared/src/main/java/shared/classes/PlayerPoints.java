package shared.classes;

/**
 * Created by Dylan on 6/8/2017.
 */

import java.util.Comparator;
import java.util.Set;

import java.util.Comparator;
import java.util.Set;

import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.interfaces.iGameInfo;

/**
 * Created by jacobcovey on 6/5/17.
 */

public class PlayerPoints {

    private String userName;
    private int claimedRoutPoints;
    private int longestRoutePoints;
    private int destinationsReachedPoints;
    private int unreachedDestinationPoints;
    private int totalPoints;
    private int destinationTicketsCompleted;

    public PlayerPoints(String userName, int points, boolean hasLongestPath, Set<DestinationCard> destinationCards) {
        this.userName = userName;
        this.claimedRoutPoints = points;
        this.destinationsReachedPoints = 0;
        this.unreachedDestinationPoints = 0;
        if (hasLongestPath) {
            this.longestRoutePoints = 10;
        }
        else {
            this.longestRoutePoints = 0;
        }
        this.destinationTicketsCompleted = 0;
        calculateDestinationPoints(destinationCards);
        this.totalPoints = claimedRoutPoints + longestRoutePoints + destinationsReachedPoints + unreachedDestinationPoints;
    }

    public void setAddLongestRoutePoints(){
        longestRoutePoints = 10;
        totalPoints = claimedRoutPoints + longestRoutePoints + destinationsReachedPoints + unreachedDestinationPoints;
    }

    public int getClaimedRoutPoints() {
        return claimedRoutPoints;
    }

    public int getLongestRoutePoints() {
        return longestRoutePoints;
    }

    public int getDestinationsReachedPoints() {
        return destinationsReachedPoints;
    }

    public int getUnreachedDestinationPoints() {
        return unreachedDestinationPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getDestinationTicketsCompleted() {
        return destinationTicketsCompleted;
    }

    public int calculateTotalPoints() {
        return claimedRoutPoints + longestRoutePoints + destinationsReachedPoints + unreachedDestinationPoints;
    }

    public void calculateDestinationPoints(Set<DestinationCard> destinationCards) {

        Set<DestinationCard> cards = destinationCards;

        for (DestinationCard card : cards ) {
            if (card.isComplete()) {
                this.destinationsReachedPoints += card.getPoints();
                this.destinationTicketsCompleted++;
            }
            else {
                this.unreachedDestinationPoints -= card.getPoints();
            }
        }
    }

    public String getUserName() {
        return userName;
    }
}
