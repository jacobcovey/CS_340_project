package com.example.jacobcovey.model;

import com.example.jacobcovey.constants.Constants;
import com.example.jacobcovey.game_board.Route;

import java.util.ArrayList;
import java.util.List;

import shared.classes.TrainCard;
import shared.interfaces.iGameInfo;

/**
 * Created by billrichards on 5/24/17.
 */

public class GameInfo extends iGameInfo {
    private TrainCard[] faceUpTrainCardDeck;
    private List<Route> clientRoutes;

    public GameInfo() {
        clientRoutes = new ArrayList<>(Constants.ROUTES);
    }

    public List<Route> getClientRoutes() {
        if (clientRoutes == null) {
            clientRoutes = new ArrayList<>(Constants.ROUTES);
        }
        return clientRoutes;
    }

    public Route getRouteById(int id) {
        if (clientRoutes == null) {
            clientRoutes = new ArrayList<>(Constants.ROUTES);
        }
        for (Route route: clientRoutes) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }

    public void setRouteById(int id, Route route) {
        for (int i = 0; i < clientRoutes.size(); i++) {
            if (clientRoutes.get(i).getId() == id) {
                clientRoutes.set(i, route);
                return;
            }
        }
    }

    public TrainCard[] getFaceUpTrainCardDeck() {
        return faceUpTrainCardDeck;
    }

    public void setFaceUpTrainCardDeck(TrainCard[] faceUpTrainCardDeck) {
        this.faceUpTrainCardDeck = faceUpTrainCardDeck;
    }
}
