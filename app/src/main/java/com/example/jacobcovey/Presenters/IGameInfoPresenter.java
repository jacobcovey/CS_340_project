package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameInfoView;

import java.util.Set;

import shared.classes.DestinationCard;

/**
 * Created by jacobcovey on 5/24/17.
 */

public interface IGameInfoPresenter {

    void setGameListView(IGameInfoView gameInfoView);

    Set<DestinationCard> getRoutes();

    public void removeObserver();

}
