package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import shared.classes.Game;
import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/15/17.
 */

public class UpdateCurrentGameCommand implements iCommand {

    Game data;

    @Override
    public void execute() {

        ClientFacade._instance.setCurrentGame(data);

    }
}
