package com.example.jacobcovey.Presenters;

import android.graphics.Color;

import com.example.jacobcovey.Views.IGameBoardView;
import com.example.jacobcovey.game_board.Route;

import com.example.jacobcovey.game_board.RouteLoader;
import com.example.jacobcovey.model.ClientModelRoot;
import com.example.jacobcovey.model.ClientPresenterFacade;
import com.example.jacobcovey.model.GameInfo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
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

public class GameBoardPresenter implements iGameBoardPresenter {

    private IGameBoardView boardView;

    private List<Route> mRoutes;
    private ClientPresenterFacade cpf;

    public GameBoardPresenter() {
        cpf = new ClientPresenterFacade();
        cpf.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        if (boardView == null) {
            return;
        }
        mRoutes = cpf.getRoutes();
        //updateBoard();
    }

    @Override
    public void setGameBoardView(IGameBoardView gameBoardView) {
//        this.boardView = gameBoardView;
        this.boardView = gameBoardView;
    }

    @Override
    public void updateBoard() {
        boardView.updateRoutes(mRoutes);
    }

    @Override
    public void updateClientRoot(List<Route> routes) {

    }
    @Override
    public void changeClientRoot() {
        try {
            addRandomRoute();
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

    private void addRandomRoute() {
        List<Route> tempList = RouteLoader.loadRoutes();
        Collections.shuffle(tempList);
        List<Route> tempList2 = new ArrayList<>();
        tempList2.add(tempList.get(0));
        tempList2.add(tempList.get(1));
        tempList2.add(tempList.get(2));
        tempList2.add(tempList.get(3));
        tempList2.add(tempList.get(4));

        tempList2.add(tempList.get(10));
        tempList2.add(tempList.get(11));
        tempList2.add(tempList.get(12));
        tempList2.add(tempList.get(13));
        tempList2.add(tempList.get(14));

        tempList2.get(0).setColor(Color.RED);
        tempList2.get(1).setColor(Color.RED);

        tempList2.get(2).setColor(Color.BLUE);
        tempList2.get(3).setColor(Color.BLUE);

        tempList2.get(4).setColor(Color.BLACK);
        tempList2.get(5).setColor(Color.BLACK);

        tempList2.get(6).setColor(Color.GREEN);
        tempList2.get(7).setColor(Color.GREEN);

        tempList2.get(8).setColor(Color.YELLOW);
        tempList2.get(9).setColor(Color.YELLOW);

        cpf.setRoutes(tempList);
    }
}

