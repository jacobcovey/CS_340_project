package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.sql.ClientInfoStatus;
import java.util.List;

import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.TrainCard;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class DestinationCardPicked implements iCommand {
    List<DestinationCard> data;

    public DestinationCardPicked(CommandData data) { this.data = (List<DestinationCard>) data.getData(); }

    @Override
    public List<CommandData> execute() {
        for (DestinationCard card : data) {
            ClientFacade._instance.getDrawnDestinationCards().remove(card);
        }
        ClientFacade._instance.getPlayer().addDestinationCards(data);
        return null;
    }
}
