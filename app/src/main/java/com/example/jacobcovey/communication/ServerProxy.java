package com.example.jacobcovey.communication;

import shared.classes.CommandData;
import shared.interfaces.iCommand;
import shared.interfaces.iServer;

/**
 * Created by billrichards on 5/15/17.
 */

public class ServerProxy implements iServer {

    public static ServerProxy _instance = new ServerProxy();

    private ServerProxy() {}

    @Override
    public void executeCommand(CommandData commandData) {

        iCommand command = ClientCommunicator.sendToServer(commandData);
        command.execute();

    }

}
