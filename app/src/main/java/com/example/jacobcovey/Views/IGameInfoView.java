package com.example.jacobcovey.Views;

import java.util.List;

import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.classes.TrainCard;

/**
 * Created by jacobcovey on 5/24/17.
 */

public interface IGameInfoView {

    void setPlayerInfo(List<Player> players);

    void setTrainCardsInfo(List<TrainCard> cards);

    void setRoutesInfo(List<DestinationCard> routes);

}
