package com.example.jacobcovey.model;

import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.Set;

import shared.classes.DestinationCard;
import shared.classes.Player;

/**
 * Created by jacobcovey on 6/5/17.
 */

public class PlayerPoints implements Comparator<PlayerPoints>, Comparable<PlayerPoints> {

    private Player player;
    private int claimedRoutPoints;
    private int longestRoutePoints;
    private int destinationsReachedPoints;
    private int unreachedDestinationPoints;
    private int totalPoints;

    public PlayerPoints(Player player, GameInfo gameInfo) {
        this.player = player;
        this.claimedRoutPoints = player.getPoints();
        this.longestRoutePoints = 0;
        calculateDestinationPoints(gameInfo);
        this.totalPoints = claimedRoutPoints + longestRoutePoints + destinationsReachedPoints + unreachedDestinationPoints;
    }

    public void setAddLongestRoutePoints(){
        longestRoutePoints = 10;
        totalPoints = claimedRoutPoints + longestRoutePoints + destinationsReachedPoints + unreachedDestinationPoints;
    }

    public Player getPlayer() {
        return player;
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

    public int calculateTotalPoints() {
        return claimedRoutPoints + longestRoutePoints + destinationsReachedPoints + unreachedDestinationPoints;
    }

    public void calculateDestinationPoints(GameInfo gameInfo) {

        Set<DestinationCard> cards = player.getDestinationCards();

        for (DestinationCard card : cards ) {
            if (card.isComplete()) {
                destinationsReachedPoints += card.getPoints();
            }
            else {
                unreachedDestinationPoints -= card.getPoints();
            }
        }
    }

    @Override
    public int compare(PlayerPoints o1, PlayerPoints o2) {
        return o1.totalPoints - o2.totalPoints;
    }

    @Override
    public int compareTo(@NonNull PlayerPoints o) {
        return 0;
    }
}
