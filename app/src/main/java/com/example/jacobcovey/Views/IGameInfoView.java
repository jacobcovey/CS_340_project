package com.example.jacobcovey.Views;

import java.util.List;
import java.util.Set;

import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.classes.TrainCard;

/**
 * Created by jacobcovey on 5/24/17.
 */

public interface IGameInfoView {

    void setPlayerInfo(List<Player> players);

    void setTrainCardsInfo(Set<TrainCard> cards);

    void setRoutesInfo(Set<DestinationCard> routes);

}
