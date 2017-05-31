package shared.classes;

/**
 * Created by billrichards on 5/24/17.
 */

public class TrainCard {

    private TrainCardColors color;

    private String id;

    public TrainCard(TrainCardColors color) {
        this.color = color;
    }

    public TrainCardColors getColor() {
        return color;
    }

    public String getId() { return id; }

}
