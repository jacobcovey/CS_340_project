package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iGameOverView;

import java.util.List;

import shared.classes.PlayerPoints;

/**
 * Created by jacobcovey on 6/5/17.
 */

public interface iGameOverPresenter {

    public void setGameOverView(iGameOverView gameOverView);

    public void removeObserver();

    public void setView();

    public List<PlayerPoints> getPlayersPoints();

    public String getWinningPlayerName();
}
