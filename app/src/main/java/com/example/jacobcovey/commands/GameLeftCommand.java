package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/15/17.
 */

public class GameLeftCommand implements iCommand {

    Object data;

    public GameLeftCommand(CommandData data) {
        this.data = data.getData();
    }

    @Override
    public List<CommandData> execute() {

        ClientFacade._instance.setCurrentGame(null);
        return null;

    }
}
