package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameInfoView;
import com.example.jacobcovey.model.ClientPresenterFacade;
import com.example.jacobcovey.model.GameInfo;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import shared.classes.DestinationCard;
import shared.classes.Player;

/**
 * Created by jacobcovey on 5/24/17.
 */

public class GameInfoPresenter implements IGameInfoPresenter, Observer {

    private IGameInfoView gameInfoView;
    private ClientPresenterFacade cpf;

    public GameInfoPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }

    @Override
    public void setGameListView(IGameInfoView gameInfoView) {
        this.gameInfoView = gameInfoView;
    }

    @Override
    public Set<DestinationCard> getRoutes() {
        return cpf.getRouts();
    }

    @Override
    public void removeObserver() {
        cpf.removeObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (gameInfoView != null) {
            GameInfo gameInfo = cpf.getGameInfo();

            gameInfoView.setPlayerInfo(gameInfo.getPlayers());

            Player currentPlayer = cpf.getCurrentPlayer();
            gameInfoView.setRoutesInfo(currentPlayer.getDestinationCards());
            gameInfoView.setTrainCardsInfo(currentPlayer.getTrainCards());
        }
    }
}
