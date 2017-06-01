package com.example.jacobcovey.Presenters;

import com.example.jacobcovey.Views.IGameBoardView;
import com.example.jacobcovey.game_board.Route;
import com.example.jacobcovey.game_board.ViewGameBoard;

import com.example.jacobcovey.model.ClientModelRoot;
import com.example.jacobcovey.model.GameInfo;


import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;


import shared.classes.City;
import shared.classes.DestinationCard;
import shared.classes.Game;
import shared.classes.History;
import shared.classes.HistoryAction;
import shared.classes.Player;
import shared.classes.TrainCard;
import shared.classes.TrainCardColors;


/**
 * Created by Riley on 5/31/2017.
 */

public class GameBoardPresenter implements iGameBoardPresenter, Observer {

    private ViewGameBoard boardView;

    private List<Route> mRoutes;

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void setGameBoardView(IGameBoardView gameBoardView) {
        this.boardView = gameBoardView;
    }

    @Override
    public void updateBoard() {

    }

    @Override
    public void updateClientRoot(List<Route> routes) {

    }
    @Override
    public void changeClientRoot() {
        try {
            Game game = ClientModelRoot._instance.getCurrentGame();

            GameInfo gameInfo = ClientModelRoot._instance.getGameInfo();
            List<Player> players = gameInfo.getPlayers();

            for (Player player : players) {
                player.setPoints(player.getPoints() + 5);
                City city1 = new City("City1");
                City city2 = new City("City2");
                DestinationCard route = new DestinationCard(city1, city2, 4);
                Set<DestinationCard> routes = new HashSet<DestinationCard>();
                routes.add(route);
                player.addDestinationCards(routes);
                TrainCard card = new TrainCard(TrainCardColors.BLUE);
                player.addTrainCard(card);
            }

            gameInfo.setPlayers(players);

            History history = gameInfo.getHistory();
            HistoryAction action1 = new HistoryAction("player1", "Claimed a route");
            HistoryAction action2 = new HistoryAction("player2", "Drew a destination card");

            history.addAction(action1);
            history.addAction(action2);

            gameInfo.setHistory(history);

            ClientModelRoot._instance.setGameInfo(gameInfo);

             } catch (NullPointerException e) {
            System.out.println("Tried to update empty client model root");
        }


    }
}

