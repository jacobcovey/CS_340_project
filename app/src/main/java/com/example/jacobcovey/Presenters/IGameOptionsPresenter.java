package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameOptionsView;

/**
 * Created by jacobcovey on 5/16/17.
 */

public interface IGameOptionsPresenter {

    void setGameOptionsView(IGameOptionsView gameOptionsView);

    void createGame();
}
