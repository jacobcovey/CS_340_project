package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iGameOverView;
import com.example.jacobcovey.model.ClientPresenterFacade;
import com.example.jacobcovey.model.GameInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.classes.Player;
import shared.classes.PlayerPoints;

/**
 * Created by jacobcovey on 6/5/17.
 */

public class GameOverPresenter implements iGameOverPresenter, Observer {

    private iGameOverView gameOverView;
    private ClientPresenterFacade cpf;
    private List<PlayerPoints> playerPointsList;

    public GameOverPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }

    @Override
    public void setGameOverView(iGameOverView gameOverView) {
        this.gameOverView = gameOverView;
    }

    @Override
    public void removeObserver() {
        cpf.removeObserver(this);
    }

    @Override
    public List<PlayerPoints> getPlayersPoints() {
        return playerPointsList;
    }

    @Override
    public String getWinningPlayerName() {
        //Total Points Winners
        int mostPoints = 0;
        for (PlayerPoints playerPoints : playerPointsList) {
            if (mostPoints < playerPoints.calculateTotalPoints()) {
                mostPoints = playerPoints.calculateTotalPoints();
            }
        }
        List<PlayerPoints> totalPointsWinners = new ArrayList<>();
        for (PlayerPoints playerPoints : playerPointsList) {
            if (mostPoints == playerPoints.calculateTotalPoints()) {
                totalPointsWinners.add(playerPoints);
            }
        }
        if (totalPointsWinners.size() == 1) {
            return totalPointsWinners.get(0).getUserName();
        }
        //Destination Tickets Winners
        int mostDestinations = 0;
        for (PlayerPoints playerPoints : playerPointsList) {
            if (mostDestinations < playerPoints.getDestinationTicketsCompleted()) {
                mostDestinations = playerPoints.getDestinationTicketsCompleted();
            }
        }
        List<PlayerPoints> destinationWinners = new ArrayList<>();
        for (PlayerPoints playerPoints : playerPointsList) {
            if (mostDestinations == playerPoints.getDestinationTicketsCompleted()) {
                destinationWinners.add(playerPoints);
            }
        }
        if (destinationWinners.size() == 1) {
            return destinationWinners.get(0).getUserName();
        }
        for (PlayerPoints playerPoints : playerPointsList) {
            if (playerPoints.getLongestRoutePoints() == 10) {
                return playerPoints.getUserName();
            }
        }
        return  null;
    }

    @Override
    public void setView() {

    }

    @Override
    public void update(Observable o, Object arg) {
        gameOverView.setScores(playerPointsList);
        gameOverView.setWinner(getWinningPlayerName());

    }
}
