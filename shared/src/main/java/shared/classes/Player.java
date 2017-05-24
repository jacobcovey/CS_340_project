package shared.classes;

import java.util.HashSet;
import java.util.Set;

import static shared.constants.Constants.NUMBEROFTRAINCARS;

/**
 * Created by billrichards on 5/24/17.
 */

public class Player {

    private PlayerColors color;
    private int trainCars;
    private Set<TrainCard> trainCards;
    private Set<DestinationCard> destinationCards;
    private int points;
    private String userName;

    public Player(PlayerColors color, Set<TrainCard> trainCards, String userName) {
        this.color = color;
        this.trainCars = NUMBEROFTRAINCARS;
        this.trainCards = trainCards;
        this.destinationCards = new HashSet<DestinationCard>();
        this.points = 0;
        this.userName = userName;
    }
}
