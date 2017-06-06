package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.iGameOverView;
import com.example.jacobcovey.model.ClientPresenterFacade;
import com.example.jacobcovey.model.GameInfo;
import com.example.jacobcovey.model.PlayerPoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.classes.Player;

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
        updatePlayerPointsList();
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
        Collections.sort(playerPointsList);
        PlayerPoints winnerPlayerPoints = playerPointsList.get(playerPointsList.size() - 1 );
        return  winnerPlayerPoints.getPlayer().getUserName() ;
    }

    @Override
    public void setView() {

    }

    @Override
    public void update(Observable o, Object arg) {
        gameOverView.setScores(playerPointsList);
        gameOverView.setWinner(getWinningPlayerName());

    }

    public void updatePlayerPointsList() {
        GameInfo gameInfo = cpf.getGameInfo();
        List<Player> players = gameInfo.getPlayers();

        playerPointsList = new ArrayList<PlayerPoints>();

        for (Player player : players) {
            PlayerPoints playerPoints = new PlayerPoints(player,gameInfo);
            playerPointsList.add(playerPoints);
        }

        //TODO add points for longest routes to appropriate players

    }
}
