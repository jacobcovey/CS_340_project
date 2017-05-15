package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/15/17.
 */

public class GameLeftCommand implements iCommand {

    Object data;

    @Override
    public void execute() {

        ClientFacade._instance.setCurrentGame(null);
        ClientFacade._instance.setColor(null);

    }
}
