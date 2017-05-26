package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameInfoView;

import java.util.List;

import shared.classes.DestinationCard;

/**
 * Created by jacobcovey on 5/24/17.
 */

public interface IGameInfoPresenter {

    void setGameListView(IGameInfoView gameInfoView);

    List<DestinationCard> getRoutes();

}
