package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;
import com.example.jacobcovey.model.ClientModelRoot;

import java.sql.ClientInfoStatus;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.classes.TrainCard;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class DestinationCardPicked implements iCommand {
    Set<DestinationCard> data;

    public DestinationCardPicked(CommandData data) { this.data = (Set<DestinationCard>) data.getData(); }

    @Override
    public List<CommandData> execute() {
        ClientFacade._instance.getPlayer().getDestinationCards().addAll(data);
        Set<DestinationCard> emptyDrawnCards = new HashSet<>();
        ClientFacade._instance.getPlayer().setDestinationCards(emptyDrawnCards);
        return null;
    }
}
