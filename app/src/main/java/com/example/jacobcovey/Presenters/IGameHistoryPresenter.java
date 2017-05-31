package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameHistoryView;

import shared.classes.History;

/**
 * Created by jacobcovey on 5/31/17.
 */

public interface IGameHistoryPresenter {

    void setGameHistoryView(IGameHistoryView gameHistoryView);

    History getGameHistory();

}
