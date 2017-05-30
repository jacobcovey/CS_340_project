package com.example.jacobcovey.commands;
import com.example.jacobcovey.model.ClientFacade;
import com.example.jacobcovey.model.GameInfo;

import java.util.List;

import shared.classes.CommandData;
import shared.interfaces.iCommand;
import shared.classes.Route;
/**
 * Created by Dylan on 5/25/2017.
 */

public class RouteClaimed implements iCommand {
    Route data;

    public RouteClaimed(CommandData data) {
        this.data = (Route) data.getData();
    }

    @Override
    public List<CommandData> execute() {
        for (Route route : ClientFacade._instance.getGameInfo().getRoutes()) {
            if (route.isRoute(data)) {
                if (route.canClaim(ClientFacade._instance.getPlayer())) {
                    route.claim(ClientFacade._instance.getPlayer());
                }
            }
        }
        return null;
    }
}
