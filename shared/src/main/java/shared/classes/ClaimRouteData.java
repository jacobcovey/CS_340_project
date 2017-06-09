package shared.classes;

import java.util.List;

/**
 * Created by spencer on 6/8/17.
 */

public class ClaimRouteData {
    private List<TrainCard> trainCards;
    private Route route;

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(List<TrainCard> trainCards) {
        this.trainCards = trainCards;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
