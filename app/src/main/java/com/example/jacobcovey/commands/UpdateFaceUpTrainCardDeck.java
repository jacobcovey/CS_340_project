package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.TrainCard;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class UpdateFaceUpTrainCardDeck implements iCommand {
    TrainCard[] data;

    public UpdateFaceUpTrainCardDeck(CommandData data) { this.data = (TrainCard[]) data.getData(); }

    @Override
    public List<CommandData> execute() {
        ClientFacade._instance.setFaceUpDeck(data);
        return null;
    }
}
