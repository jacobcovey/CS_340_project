package shared.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by billrichards on 5/24/17.
 */

public class Route {

    private TrainCardColors color;
    private int length;
    private String id;
    private City city1;
    private City city2;
    private int points;
    private double xCoordinate;
    private double yCoordinate;
    private Player player;
    private boolean isClaimed;
    List<Route> adjacentRoutes = new ArrayList<>();

    public Route(TrainCardColors color, int length, City city1, City city2, int points) {
        this.color = color;
        this.length = length;
        this.city1 = city1;
        this.city2 = city2;
        this.points = points;
        this.player = null;
        this.isClaimed = false;
    }

    public Route(TrainCardColors color, int length, City city1, City city2, double x, double y) {
        this.color = color;
        this.length = length;
        this.city1 = city1;
        this.city2 = city2;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.points = calcPoint();
        this.player = null;
        this.isClaimed = false;
    }

    public TrainCardColors getColor() { return color; }

    public int getLength() { return length; }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getPoints() {
        return points;
    }

    public Player getPlayer() { return player; }

    public boolean getIsClaimed() { return isClaimed; }

    public boolean isRoute(Route route) {
        return this.equals(route);
    }
    public boolean canClaim(Player player) {
        if (isClaimed) {
            return false;
        }
        if (player.getNumberOfTrains() < getLength()) {
            return false;
        }
        Set<TrainCard> allCards = player.getTrainCards();
        int correctCardCount = 0;
        for (TrainCard card : allCards) {
            if (card.getColor() == this.getColor()) {
                correctCardCount++;
            }
        }
        if (correctCardCount < getLength()) {
            return false;
        }
        return true;
    }
    public void claim(Player player) {
        Set<TrainCard> allCards = player.getTrainCards();
        int cardsToRemove = this.getLength();
        for (TrainCard card : allCards) {
            if (card.getColor() == this.getColor() && cardsToRemove != 0) {
                allCards.remove(card);
                cardsToRemove--;
            }
        }
        player.setNumberOfTrains(player.getNumberOfTrains() - getLength());
        this.player = player;
        this.isClaimed = true;
    }

    public int calcPoint() {
        switch (length) {
            case 1: return 1;
            case 2: return 2;
            case 3: return 4;
            case 4: return 7;
            case 5: return 10;
            default: return 15;
        }

    }

    public boolean isEqual(Route route) {
        if (this.getCity1().isEqual(route.getCity1()) && this.getCity2().isEqual(route.getCity2())) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<Route> getAdjacentRoutes() {
        return adjacentRoutes;
    }
    public void addAdjacentRoutes(Route route) {
        adjacentRoutes.add(route);
    }

}
