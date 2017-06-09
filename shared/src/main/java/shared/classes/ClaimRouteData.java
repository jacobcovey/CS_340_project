package shared.classes;

import java.util.List;

/**
 * Created by spencer on 6/8/17.
 */

public class ClaimRouteData {
    private List<TrainCard> trainCards;
    private Route route;

    public ClaimRouteData(List<TrainCard> trainCards, Route route) {
        this.trainCards = trainCards;
        this.route = route;
    }

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }

    public Route getRoute() {
        return route;
    }
}
