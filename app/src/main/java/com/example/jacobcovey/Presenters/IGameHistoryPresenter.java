package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iGameHistoryView;

import shared.classes.History;

/**
 * Created by jacobcovey on 5/31/17.
 */

public interface IGameHistoryPresenter {

    void setGameHistoryView(iGameHistoryView gameHistoryView);

    History getGameHistory();

    void removeObserver();

}
