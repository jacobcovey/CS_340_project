package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;

import com.example.jacobcovey.model.ClientModelRoot;
import com.example.jacobcovey.model.GameInfo;

import shared.classes.Player;
import shared.interfaces.iCommand;
/**
 * Created by Dylan on 5/25/2017.
 */

public class GameStarted implements iCommand {
    GameInfo data;
    String gameId;
    String userName;

    public GameStarted(CommandData data) {
        this.data = (GameInfo) data.getData();
        this.gameId = data.getGameId();
        this.userName = data.getUserName();
    }

    @Override
    public List<CommandData> execute() {
        ClientFacade._instance.setGameInfo(data);
        for (Player player : ClientFacade._instance.getGameInfo().getPlayers()) {
            if (player.getUserName().equals(userName)) {
                ClientFacade._instance.setPlayer(player);
            }
        }
        return null;

    }
}
