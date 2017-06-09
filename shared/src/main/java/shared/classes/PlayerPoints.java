package shared.classes;

/**
 * Created by Dylan on 6/8/2017.
 */

import com.google.gson.annotations.Expose;

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

    @Expose
    private Player player;
    @Expose
    private int claimedRoutPoints;
    @Expose
    private int longestRoutePoints;
    @Expose
    private int destinationsReachedPoints;
    @Expose
    private int unreachedDestinationPoints;
    @Expose
    private int totalPoints;
    @Expose
    private int destinationTicketsCompleted;

    public PlayerPoints(Player player, int points, boolean hasLongestPath, Set<DestinationCard> destinationCards) {
        this.player = player;
        this.claimedRoutPoints = points;
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
                destinationsReachedPoints += card.getPoints();
                destinationTicketsCompleted++;
            }
            else {
                unreachedDestinationPoints -= card.getPoints();
            }
        }
    }
    public Player getPlayer() {
        return player;
    }
}
