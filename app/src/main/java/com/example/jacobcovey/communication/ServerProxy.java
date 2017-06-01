package com.example.jacobcovey.communication;

import com.google.gson.Gson;

import shared.classes.CommandData;
import shared.interfaces.iCommand;
import shared.interfaces.iServer;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

/**
 * Created by billrichards on 5/15/17.
 */

public class ServerProxy implements iServer {

    public static ServerProxy _instance = new ServerProxy();
    private static Gson gson = new Gson();

    private ServerProxy() {}

    @Override
    public void executeCommand(CommandData commandData) throws IOException {
        CommandManager manager = CommandManager._instance;
        List<CommandData> commands = ClientCommunicator.sendToServer(commandData);
        if (commands != null) {
            iCommand command;
            for (CommandData data : commands) {
                String s = gson.toJson(data);
                command = manager.createCommand(data, s);
                if (command != null) {
                    command.execute();
                }
            }
        }

    }

}
