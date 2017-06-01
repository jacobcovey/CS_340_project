package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameInfoView;
import com.example.jacobcovey.model.GameInfo;

import java.util.Set;

import shared.classes.DestinationCard;
import shared.classes.Player;

/**
 * Created by jacobcovey on 5/24/17.
 */

public interface IGameInfoPresenter {

    void setGameListView(IGameInfoView gameInfoView);

    Set<DestinationCard> getRoutes();

    public void removeObserver();

    GameInfo getGameInfo();

    Player getCurrentPlayer();

}
