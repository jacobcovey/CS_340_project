package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.TrainCard;
import shared.interfaces.iCommand;
/**
 * Created by Dylan on 5/25/2017.
 */

public class FaceDownTrainCardPicked implements iCommand {
    String data;

    public FaceDownTrainCardPicked(CommandData data) { this.data = (String) data.getData(); }

    @Override
    public List<CommandData> execute() {
        ClientFacade._instance.getPlayer().drawCard(data);
        return null;
    }
}
