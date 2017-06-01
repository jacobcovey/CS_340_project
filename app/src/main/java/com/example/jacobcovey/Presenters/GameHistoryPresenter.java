package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.GameHistoryView;
import com.example.jacobcovey.Views.IGameHistoryView;
import com.example.jacobcovey.model.ClientPresenterFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.classes.History;
import shared.classes.HistoryAction;

/**
 * Created by jacobcovey on 5/31/17.
 */

public class GameHistoryPresenter implements IGameHistoryPresenter, Observer {

    private IGameHistoryView gameHistoryView;
    private ClientPresenterFacade cpf;

    public GameHistoryPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }

    @Override
    public void setGameHistoryView(IGameHistoryView gameHistoryView) {
        this.gameHistoryView = gameHistoryView;
    }

    @Override
    public History getGameHistory() {
        HistoryAction action1 = new HistoryAction("player1","Drew a card");
        HistoryAction action2 = new HistoryAction("player2","claimed a route");
        List<HistoryAction> historyActions = new ArrayList<HistoryAction>();
        historyActions.add(action1);
        historyActions.add(action2);
        History history = new History(historyActions);
        return history;
//     return cpf.getHistory();
    }

    @Override
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
