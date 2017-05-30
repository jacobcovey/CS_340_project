package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import com.example.jacobcovey.model.GameInfo;
import shared.interfaces.iCommand;
/**
 * Created by Dylan on 5/25/2017.
 */

public class GameStarted implements iCommand {
    GameInfo data;

    public GameStarted(CommandData data) {
        this.data = (GameInfo) data.getData();
    }

    @Override
    public List<CommandData> execute() {

        ClientFacade._instance.setGameInfo(data);
        return null;

    }
}
