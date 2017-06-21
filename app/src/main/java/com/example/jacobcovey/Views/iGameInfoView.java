package com.example.jacobcovey.Views;

import com.example.jacobcovey.model.GameInfo;

import java.util.List;
import java.util.Set;

import shared.classes.DestinationCard;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.Turn;

/**
 * Created by jacobcovey on 5/24/17.
 */

public interface iGameInfoView {

    void setPlayerInfo(List<Player> players);

    void setTrainCardsInfo(Set<TrainCard> cards);

    void setRoutesInfo(Set<DestinationCard> routes);

    void setDeckNums(GameInfo gameInfo);

    void setTurnIndicator(Turn turn);
}
