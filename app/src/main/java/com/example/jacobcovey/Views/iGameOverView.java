package com.example.jacobcovey.Views;

import com.example.jacobcovey.model.PlayerPoints;

import java.util.List;

/**
 * Created by jacobcovey on 6/5/17.
 */

public interface iGameOverView {

    void setScores(List<PlayerPoints> playerPointsList);

    void setWinner(String playerName);

}
