package com.example.jacobcovey.commands;

import com.example.jacobcovey.model.ClientFacade;

import java.util.List;

import shared.classes.CommandData;
import shared.classes.User;
import shared.interfaces.iCommand;

/**
 * Created by billrichards on 5/15/17.
 */

public class LoginSuccessfulCommand implements iCommand {

    User data;

    public LoginSuccessfulCommand(CommandData data) {
        this.data = (User) data.getData();
    }

    @Override
    public List<CommandData> execute() {

        ClientFacade._instance.setUser(data);
        return null;

    }
}
