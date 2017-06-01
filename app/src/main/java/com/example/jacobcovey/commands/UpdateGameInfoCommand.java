package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;
import com.example.jacobcovey.model.GameInfo;

import java.io.IOException;
import java.util.List;

import shared.classes.CommandData;
import shared.classes.Player;
import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/31/17.
 */

public class UpdateGameInfoCommand implements iCommand {
    GameInfo data;

    @Override
    public List<CommandData> execute() throws IOException {
        ClientFacade._instance.setGameInfo(data);
        for (Player player : data.getPlayers()) {
            if (player.getUserName().equals(ClientFacade._instance.getUser().getUsername())) {
                ClientFacade._instance.setPlayer(player);
                return null;
            }
        }
        return null;
    }
}
