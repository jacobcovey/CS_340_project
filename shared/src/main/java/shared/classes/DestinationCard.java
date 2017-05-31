package shared.classes;

/**
 * Created by billrichards on 5/24/17.
 */

public class DestinationCard {

    private String id;
    private City city1;
    private City city2;
    private int points;

    public DestinationCard(City city1, City city2, int points) {
        this.city1 = city1;
        this.city2 = city2;
        this.points = points;
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getPoints() {
        return points;
    }

    public String getId() { return id; }

    @Override
    public String toString() {
        return  city1.toString() + " - " + city2.toString() + " " + points;
    }
}
