package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iGameInfoView;
import com.example.jacobcovey.model.GameInfo;

import java.util.Set;

import shared.classes.DestinationCard;
import shared.classes.Player;

/**
 * Created by jacobcovey on 5/24/17.
 */

public interface iGameInfoPresenter {

    void setGameListView(iGameInfoView gameInfoView);

    Set<DestinationCard> getDestinationCards();

    public void removeObserver();

    GameInfo getGameInfo();

    Player getCurrentPlayer();

}
