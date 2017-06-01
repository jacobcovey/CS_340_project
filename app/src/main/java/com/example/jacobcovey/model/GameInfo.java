package com.example.jacobcovey.model;

import shared.classes.TrainCard;
import shared.interfaces.iGameInfo;

/**
 * Created by billrichards on 5/24/17.
 */

public class GameInfo extends iGameInfo {
    private TrainCard[] faceUpTrainCardDeck;
    public GameInfo() {}

    public TrainCard[] getFaceUpTrainCardDeck() {
        return faceUpTrainCardDeck;
    }

    public void setFaceUpTrainCardDeck(TrainCard[] faceUpTrainCardDeck) {
        this.faceUpTrainCardDeck = faceUpTrainCardDeck;
    }
}
