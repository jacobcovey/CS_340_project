package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.DestinationCard;
import shared.interfaces.iCommand;

/**
 * Created by Dylan on 5/30/2017.
 */

public class DestinationCardDrawn implements iCommand {
    String data;

    public DestinationCardDrawn(CommandData data) { this.data = (String) data.getData(); }

    @Override
    public List<CommandData> execute() {
        ClientFacade._instance.getPlayer().drawCard(data);
        return null;
    }
}
