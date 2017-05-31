package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.TrainCard;
import shared.interfaces.iCommand;
/**
 * Created by Dylan on 5/25/2017.
 */

public class FaceUpTrainCardPicked implements iCommand {
    TrainCard data;

    public FaceUpTrainCardPicked(CommandData data) { this.data = (TrainCard) data.getData(); }

    @Override
    public List<CommandData> execute() {
        ClientFacade._instance.getFaceUpDeck().remove(data);
        ClientFacade._instance.getPlayer().addTrainCard(data);
        return null;
    }
}
