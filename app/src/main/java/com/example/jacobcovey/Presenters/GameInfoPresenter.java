package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameInfoView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.classes.DestinationCard;

/**
 * Created by jacobcovey on 5/24/17.
 */

public class GameInfoPresenter implements IGameInfoPresenter, Observer {

    private IGameInfoView gameInfoView;
    private ClientPresenterFacade cpf;

    @Override
    public void setGameListView(IGameInfoView gameInfoView) {
        this.gameInfoView = gameInfoView;
    }

    @Override
    public List<DestinationCard> getRoutes() {
        return cpf.getRouts();
    }

    @Override
    public void update(Observable o, Object arg) {
        List<DestinationCard> routes = cpf.getRouts();
        gameInfoView.setRoutesInfo(routes);


    }
}
