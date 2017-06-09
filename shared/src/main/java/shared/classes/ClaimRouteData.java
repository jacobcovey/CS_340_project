package shared.classes;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by spencer on 6/8/17.
 */

public class ClaimRouteData {
    @Expose
    private List<TrainCard> trainCards;
    @Expose
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
