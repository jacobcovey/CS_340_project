package com.example.jacobcovey.model;

import com.example.jacobcovey.constants.Constants;
import com.example.jacobcovey.game_board.Route;

import java.util.ArrayList;
import java.util.List;

import shared.classes.TrainCard;

/**
 * Created by billrichards on 5/24/17.
 */

public class GameInfo extends iGameInfo {
    private TrainCard[] faceUpTrainCardDeck;
    private List<Route> routes;

    public GameInfo() {
        routes = new ArrayList<>(Constants.ROUTES);
    }

    public List<Route> getRoutes() {
        if (routes == null) {
            routes = new ArrayList<>(Constants.ROUTES);
        }
        return routes;
    }

    public Route getRouteById(int id) {
        if (routes == null) {
            routes = new ArrayList<>(Constants.ROUTES);
        }
        for (Route route: routes) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }

    public void setRouteById(int id, Route route) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getId() == id) {
                routes.set(i, route);
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

    public void removeById(int compNumb) {
        if (routes == null) {
            routes = new ArrayList<>(Constants.ROUTES);
        }
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getId() == compNumb) {
                routes.remove(i);
                return;
            }
        }
    }
}
