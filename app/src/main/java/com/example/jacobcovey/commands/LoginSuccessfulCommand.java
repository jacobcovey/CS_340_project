package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/15/17.
 */

public class LoginSuccessfulCommand implements iCommand {

    String data;

    @Override
    public void execute() {

        ClientFacade._instance.setAuthToken(data);

    }
}
