package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iGameOverView;
import com.example.jacobcovey.model.PlayerPoints;

import java.util.List;

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
