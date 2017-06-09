package shared.classes;

import java.util.List;

/**
 * Created by spencer on 6/8/17.
 */

public class ClaimRouteData {
    private List<TrainCard> trainCards;
    private int routeId;

    public ClaimRouteData(List<TrainCard> trainCards, int routeId) {
        this.trainCards = trainCards;
        this.routeId = routeId;
    }

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }

    public int getRouteId() {
        return routeId;
    }
}
