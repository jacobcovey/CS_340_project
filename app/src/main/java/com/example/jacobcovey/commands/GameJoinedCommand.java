package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/15/17.
 */

public class GameJoinedCommand implements iCommand {

    Game data;

    public GameJoinedCommand(CommandData data) {
        this.data = (Game) data.getData();
    }

    @Override
    public List<CommandData> execute() {

        ClientFacade._instance.setCurrentGame(data);
        return null;

    }
}
