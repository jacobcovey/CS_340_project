package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;
import com.example.jacobcovey.model.ClientModelRoot;
import com.example.jacobcovey.model.GameInfo;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.classes.Player;
import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/15/17.
 */

public class GameStartedCommand implements iCommand {
    GameInfo data;

    public GameStartedCommand(CommandData data) {
        this.data = (GameInfo) data.getData();
    }

    @Override
    public List<CommandData> execute() {
        ClientFacade._instance.setGameInfo(data);
        for (Player player : ClientFacade._instance.getGameInfo().getPlayers()) {
            if (player.getUserName().equals(ClientFacade._instance.getUser().getUsername())) {
                ClientFacade._instance.setPlayer(player);
                return null;
            }
        }
        return null;

    }

}
