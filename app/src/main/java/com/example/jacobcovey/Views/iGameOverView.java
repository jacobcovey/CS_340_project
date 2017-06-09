package com.example.jacobcovey.Views;

import java.util.List;

import shared.classes.PlayerPoints;

/**
 * Created by jacobcovey on 6/5/17.
 */

public interface iGameOverView {

    void setScores(List<PlayerPoints> playerPointsList);

    void setWinner(String playerName);

}
