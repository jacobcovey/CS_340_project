package com.example.jacobcovey.model;

import com.example.jacobcovey.constants.Constants;
import com.example.jacobcovey.game_board.Route;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import shared.classes.TrainCard;
import shared.interfaces.iGameInfo;

/**
 * Created by billrichards on 5/24/17.
 */

public class GameInfo extends iGameInfo {
    @Expose
    private TrainCard[] faceUpTrainCardDeck;
    @Expose
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
}
