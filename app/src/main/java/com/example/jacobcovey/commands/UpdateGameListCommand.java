package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.Game;
import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/15/17.
 */

public class UpdateGameListCommand implements iCommand {

    List<Game> data;

    public UpdateGameListCommand(CommandData data) {
        this.data = (List<Game>) data.getData();
    }

    @Override
    public List<CommandData> execute() {

        ClientFacade._instance.setGameList(data);
        return null;

    }
}
