package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/15/17.
 */

public class GameStartedCommand implements iCommand {

    Game data;

    public GameStartedCommand(Game data) {
        this.data = data;
    }

    @Override
    public List<CommandData> execute() {

        ClientFacade._instance.setCurrentGame(data);
        return null;

    }

}
