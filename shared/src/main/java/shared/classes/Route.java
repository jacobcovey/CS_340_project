package shared.classes;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by billrichards on 5/24/17.
 */

public class Route {

    @Expose
    List<Route> adjacentRoutes = new ArrayList<>();
    @Expose
    private int id;
    @Expose
    private int length;
    @Expose
    private int points;
    @Expose
    private Player player;
    @Expose
    private boolean isClaimed;
    @Expose
    private City city1;
    @Expose
    private City city2;
    @Expose
    private TrainCardColors routeColor;

    public Route(int size, int id, TrainCardColors routeColor, String city1, String city2) {
        this.id = id;
        length = size;
        setPointsBasedOnLength();

        this.routeColor = routeColor;
        this.city1 = new City(city1);
        this.city2 = new City(city2);
        isClaimed = false;
    }

    public TrainCardColors getRouteColor() { return routeColor; }

    public int getId() {
        return id;
    }

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

    public boolean canClaim(Player player, List<TrainCard> trainCards) {
        if (isClaimed) {
            return false;
        }
        if (player.getNumberOfTrains() < getLength()) {
            return false;
        }
        int correctCardCount = 0;
        if (getRouteColor() == TrainCardColors.WILD && trainCards.size() == getLength()) {
            return true;
        }
        for (TrainCard card : trainCards) {
            if (card.getColor() == getRouteColor() ||
                    card.getColor() == TrainCardColors.WILD) {
                correctCardCount++;
            }
        }
        if (correctCardCount < getLength()) {
            return false;
        }
        return true;
    }

    public void claim(Player player, List<TrainCard> cards) {
        Set<TrainCard> allCards = player.getTrainCards();
        for (TrainCard card : cards) {
            allCards.remove(card);
        }
        player.setNumberOfTrains(player.getNumberOfTrains() - getLength());
        player.setPoints(player.getPoints() + getPoints());
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
        if (this.getId() == route.getId()) {
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

    public void setPointsBasedOnLength() {
        switch (length) {
            case 1:
                points = 1;
                return;
            case 2:
                points = 2;
                return;
            case 3:
                points = 4;
                return;
            case 4:
                points = 7;
                return;
            case 5:
                points = 10;
                return;
            case 6:
                points = 15;
                return;
        }
    }

}
