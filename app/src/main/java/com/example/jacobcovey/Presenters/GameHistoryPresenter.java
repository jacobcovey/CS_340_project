package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iGameHistoryView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.util.Observable;
import java.util.Observer;

import shared.classes.History;

/**
 * Created by jacobcovey on 5/31/17.
 */

public class GameHistoryPresenter implements IGameHistoryPresenter, Observer {

    private iGameHistoryView gameHistoryView;
    private ClientPresenterFacade cpf;

    public GameHistoryPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }

    @Override
    public void setGameHistoryView(iGameHistoryView gameHistoryView) {
        this.gameHistoryView = gameHistoryView;
    }

    @Override
    public History getGameHistory() {
        return cpf.getHistory();
    }

    public void removeObserver() {
        cpf.removeObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (gameHistoryView != null) {
            History history = cpf.getHistory();
            gameHistoryView.updateHistory(history);
        }
    }
}
